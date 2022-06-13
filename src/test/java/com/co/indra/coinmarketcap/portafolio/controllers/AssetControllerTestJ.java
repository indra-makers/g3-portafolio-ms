package com.co.indra.coinmarketcap.portafolio.controllers;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AssetControllerTestJ {
   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private AssetRepository assetRepository;

   @Autowired
   private TransactionRepository transactionRepository;

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToAssetHappy() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 3,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<Transaction> transactionList = transactionRepository.getTransactionByIdAsset(666);
      Assertions.assertEquals(1, transactionList.size());

      Transaction transaction = transactionList.get(0);

      Assertions.assertEquals(5000, transaction.getPrice());
      Assertions.assertEquals(3, transaction.getQuantity());
   }

   @Test
   public void addTransactionToNoAsset() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 3,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(404, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("NOT FOUND", error.getCode());
   }

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToBadType() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"ASDE\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 3,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("048", error.getCode());
      Assertions.assertEquals("UNKNOWN TYPE TRANSACTION", error.getMessage());
   }

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToBadSell() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"SELL\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 6,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());

      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("009", error.getCode());
      Assertions.assertEquals("THE VALUE TO SELL EXCEEDS THE CURRENT", error.getMessage());
   }

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToAssetBadRequest() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 0,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(412, response.getStatus());
      String textResponse = response.getContentAsString();
      ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

      Assertions.assertEquals("007", error.getCode());
      Assertions.assertEquals("THE PRICE|QUANTITY SHOULD NOT BE LESS OR EQUAL TO 0", error.getMessage());
   }

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToAssetBadDate() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"id\": 666,\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 5000,\n"
                  + "    \"dateTime\": \"2045-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 3,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(400, response.getStatus());
   }

   @Test
   @Sql("/testdata/add_transaction_to_asset_happy.sql")
   public void addTransactionToAssetValidateAVG() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 3287,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 4,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 2580.44,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 6,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response1 = mockMvc.perform(request1).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders
            .post(Routes.ASSETS_PATH + Routes.ADD_TRANSACTION_TO_ASSET, 666)
            .content("{\n" + "    \"type\": \"BUY\",\n" + "    \"price\": 4876.44,\n"
                  + "    \"dateTime\": \"2022-05-18T00:00:00.000-05:00\",\n" + "    \"fee\": 30.30,\n"
                  + "    \"notes\": \"ADSERTEET WEQ crte\",\n" + "    \"quantity\": 2,\n" + "    \"amount\": 15\n"
                  + "}")
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response2 = mockMvc.perform(request2).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<Asset> assetList = assetRepository.findById(666);
      Asset asset = assetList.get(0);

      Assertions.assertEquals(17, asset.getQuantity());
      Assertions.assertEquals(3198.6266666666666, asset.getAvgBuyPrice());
   }
}