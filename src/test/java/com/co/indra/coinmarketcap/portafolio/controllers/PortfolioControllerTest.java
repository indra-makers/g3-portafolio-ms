package com.co.indra.coinmarketcap.portafolio.controllers;

import java.util.List;

import javax.transaction.Transactional;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
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
	
	@Test
    public void addPortafolioHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "    \"name\": \"my_coins2\",\n" +
                        "    \"idUser\": \"1\",\n" +
                        "    \"balance\": \"45\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Portfolio> portafolio = portfolioRepository.findByNameAndUsername(1,"my_coins2" );
        Assertions.assertEquals(1, portafolio.size());

        Portfolio portafolioToAssert = portafolio.get(0);

        Assertions.assertEquals("my_coins2", portafolioToAssert.getName());
        Assertions.assertEquals(1, portafolioToAssert.getIdUser());
        Assertions.assertEquals(45, portafolioToAssert.getBalance());
    }
	
	@Test
    public void addPortafolioPortafolioAlreadyExist() throws Exception {
        //----la preparacion de los datos de prueba-------
        portfolioRepository.create(new Portfolio("my_coins",1,45.45d));

        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "    \"name\": \"my_coins\",\n" +
                        "    \"idUser\": \"1\",\n" +
                        "    \"balance\": \"45.45\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //------------ las verificaciones--------------------
        Assertions.assertEquals(412, response.getStatus());

        String textREsponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textREsponse, ErrorResponse.class);

        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("Portafolio with that name already exists", error.getMessage());

    }
	
	@Test
    @Sql("/testdata/get_portafolios_user.sql")
    public void getPortafoliosByUser() throws Exception {
        //----la ejecucion de la prueba misma--------------
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.PORTFOLIO_PATH + Routes.PORTFOLIO_USER, 1)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //------------ las verificaciones--------------------
        Assertions.assertEquals(200, response.getStatus());

        Portfolio[] portfolio = objectMapper.readValue(response.getContentAsString(), Portfolio[].class);
        Assertions.assertEquals(4, portfolio.length);
    }

}
