package com.co.indra.coinmarketcap.portafolio.controllers;

import java.util.List;

import javax.transaction.Transactional;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import com.co.indra.coinmarketcap.portafolio.models.responses.ListPortfolioResponse;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioDistribution;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PortfolioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RedisConnection redisConnection;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    public void beforeEach() {
        redisConnection.flushAll();
    }
	
	@Test
    public void addPortafolioHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "    \"name\": \"my_coins2\",\n" +
                        "    \"idUser\": \"1\",\n" +
                        "    \"balance\": \"0\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Portfolio> portafolio = portfolioRepository.findByNameAndUsername(1,"my_coins2" );
        Assertions.assertEquals(1, portafolio.size());

        Portfolio portafolioToAssert = portafolio.get(0);

        Assertions.assertEquals("my_coins2", portafolioToAssert.getName());
        Assertions.assertEquals(1, portafolioToAssert.getIdUser());
        Assertions.assertEquals(0, portafolioToAssert.getBalance());
    }
	
	@Test
    public void addPortafolioPortafolioAlreadyExist() throws Exception {
        //----la preparacion de los datos de prueba-------
        portfolioRepository.create(new Portfolio("my_coins", 45.45D , 1));

        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "    \"name\": \"my_coins\",\n" +
                        "    \"idUser\": \"1\",\n" +
                        "    \"balance\": \"0.0\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //------------ las verificaciones--------------------
        Assertions.assertEquals(412, response.getStatus());

        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("THE PORTFOLIO NAME IS ALREADY IN USE", error.getMessage());

    }
	
	@Test
   @Sql("/testdata/get_portafolios_user.sql")
   public void getPortafoliosByUser() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(Routes.PORTFOLIO_PATH + Routes.PORTFOLIO_SUMARY, 1).contentType(MediaType.APPLICATION_JSON);
      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      ListPortfolioResponse[] portfolio = objectMapper.readValue(response.getContentAsString(),
            ListPortfolioResponse[].class);
      Assertions.assertEquals(10.5052, portfolio[0].getTotal());
      Assertions.assertEquals(2, portfolio[0].getPortfolios().size());
   }

    @Test
   @Sql("/testdata/get_assets_avg_distribution.sql")
   public void getDistributionPortfolio() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(Routes.PORTFOLIO_PATH + Routes.DISTRIBUTION_PATH, 999).contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      PortfolioDistribution[] assetAvgDists = objectMapper.readValue(response.getContentAsString(),
            PortfolioDistribution[].class);
      Assertions.assertEquals(4, assetAvgDists[0].getAssets().size());
      Assertions.assertEquals(40, Math.round(assetAvgDists[0].getAssets().get(0).getAvg()));
      Assertions.assertEquals(24, Math.round(assetAvgDists[0].getAssets().get(1).getAvg()));
      Assertions.assertEquals(8, Math.round(assetAvgDists[0].getAssets().get(2).getAvg()));
      Assertions.assertEquals(28, Math.round(assetAvgDists[0].getAssets().get(3).getAvg()));
      Double sumPercent = 0d;
      for (int i = 0; i < assetAvgDists[0].getAssets().size(); i++) {
         sumPercent = sumPercent + assetAvgDists[0].getAssets().get(i).getAvg();
      }
      Assertions.assertEquals(100, Math.round(sumPercent));

   }


    @Test
    @Sql("/testdata/getTransacctionInPortfolio.sql")
    public void getTransactionInPortfolioHappyPath() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_TRANSACTIONS,1)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        Transaction[] transactions = objectMapper.readValue(response.getContentAsString(), Transaction[].class);
        Assertions.assertEquals(1, transactions.length);
    }

    @Test
    public void getTransactionInPortfolioWhenPortfolioDoesNotExist() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_TRANSACTIONS, 1)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());

        ErrorResponse error = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        Assertions.assertEquals("PORTFOLIO WITH THIS ID DOES NOT EXISTS", error.getMessage());
    }

     @Test
   @Sql("/testdata/get_assets_avg_distribution.sql")
   public void getDistributionNoPortfolio() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(Routes.PORTFOLIO_PATH + Routes.DISTRIBUTION_PATH, 996).contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(404, response.getStatus());
      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
      Assertions.assertEquals("NOT FOUND", error.getCode());
      Assertions.assertEquals("PORTFOLIO WITH THIS ID DOES NOT EXISTS", error.getMessage());
   }

   // Test para eliminar un portafolio sin ningun error
   @Test
   @Sql("/testdata/Create_portafolio_On_tbl_porfolio.sql")
   public void removePortafolioByNameHappyPath() throws Exception {

      MockHttpServletRequestBuilder requestRemovePortafolioByName = MockMvcRequestBuilders
            .delete(Routes.PORTFOLIO_PATH + Routes.DELETE_PORTFOLIO_BY_NAME, "PortafolioBTC")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemovePortafolioByName = mockMvc.perform(requestRemovePortafolioByName)
            .andReturn().getResponse();
      Assertions.assertEquals(200, responseRemovePortafolioByName.getStatus());

      List<Portfolio> PortfolioList = portfolioRepository.findPortafolioByName("PortafolioBTC");
      Assertions.assertEquals(0, PortfolioList.size());

   }

   // Test para eliminar un portafolio donde arroja un error cuando no se
   // encuentra el portafolio por medio de su nombre
   @Test
   @Sql("/testdata/Create_portafolio_On_tbl_porfolio.sql")
   public void removePortafolioByNameNotFoundPortfolio() throws Exception {

      MockHttpServletRequestBuilder requestRemovePortafolioByName = MockMvcRequestBuilders
            .delete(Routes.PORTFOLIO_PATH + Routes.DELETE_PORTFOLIO_BY_NAME, "PortfolioBC")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse responseRemovePortafolioByName = mockMvc.perform(requestRemovePortafolioByName)
            .andReturn().getResponse();
      Assertions.assertEquals(404, responseRemovePortafolioByName.getStatus());

      List<Portfolio> PortfolioList = portfolioRepository.findPortafolioByName("PortafolioBTC");
      Assertions.assertEquals(1, PortfolioList.size());

      String textResponse = responseRemovePortafolioByName.getContentAsString();

      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("NOT FOUND", error.getCode());
      Assertions.assertEquals("PORTFOLIO WITH THIS NAME DOES NOT EXISTS", error.getMessage());

   }

   @Test
   @Sql("/testdata/put_portfolio.sql")
   public void editPortfolio() throws Exception {
      List<Portfolio> portafolio1 = portfolioRepository.findByPortfolioId(3);
      Portfolio portafolioToAssert1 = portafolio1.get(0);
      Assertions.assertEquals("AAB", portafolioToAssert1.getName());
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .put(Routes.PORTFOLIO_PATH + Routes.EDIT_PORTFOLIO, 3)
            .content("{\n" + "    \"name\": \"portfolio1\"\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<Portfolio> portafolio = portfolioRepository.findByPortfolioId(3);
      Assertions.assertEquals(1, portafolio.size());

      Portfolio portafolioToAssert = portafolio.get(0);

      Assertions.assertEquals("portfolio1", portafolioToAssert.getName());
   }

   @Test
   public void editBadPortfolio() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .put(Routes.PORTFOLIO_PATH + Routes.EDIT_PORTFOLIO, 5)
            .content("{\n" + "    \"name\": \"portfolio1\"\n" + "}").contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(404, response.getStatus());
      String textREsponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

      Assertions.assertEquals("NOT FOUND", error.getCode());
      Assertions.assertEquals("PORTFOLIO WITH THIS ID DOES NOT EXISTS", error.getMessage());
   }

    @Test
    @Sql("/testdata/put_transaction.sql")
    public void editTransactionHappyPath() throws Exception {
        List<Transaction> transactions = transactionRepository.getTransactionById(3);
        Transaction transactionToAssert = transactions.get(0);
        Assertions.assertEquals(transactionToAssert.getIdAsset(), 1);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(Routes.PORTFOLIO_PATH+Routes.TRANSACTIONS_PATH + Routes.EDIT_TRANSACTION, 3)
                .content("{\n" +
                        "    \"price\": 5000,\n" +
                        "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERT WEQ crte\",\n" +
                        "    \"quantity\": 3,\n" +
                        "    \"amount\": 15\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Transaction> transactions1 = transactionRepository.getTransactionById(3);
        Assertions.assertEquals(1, transactions1.size());

        Transaction transactionToAssert1 = transactions1.get(0);

        Assertions.assertEquals(5000, transactionToAssert1.getPrice());
        Assertions.assertEquals("2022-05-18", transactionToAssert1.getDateTime().toString());
        Assertions.assertEquals(30.30, transactionToAssert1.getFee());
        Assertions.assertEquals("ADSERT WEQ crte", transactionToAssert1.getNotes());
        Assertions.assertEquals(3, transactionToAssert1.getQuantity());
        Assertions.assertEquals(15, transactionToAssert1.getAmount());

    }

    @Test
    public void editBadTransaction() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(Routes.PORTFOLIO_PATH+Routes.TRANSACTIONS_PATH + Routes.EDIT_TRANSACTION, 3)
                .content("{\n" +
                        "    \"price\": 5000,\n" +
                        "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERT WEQ crte\",\n" +
                        "    \"quantity\": 3,\n" +
                        "    \"amount\": 15\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("NOT FOUND", error.getCode());
        Assertions.assertEquals("012", error.getMessage());
    }
}