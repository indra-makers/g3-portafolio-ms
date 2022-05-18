package com.co.indra.coinmarketcap.portafolio.controllers;
import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
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

    @Test
    @Sql("/testdata/add_transaction_to_asset_happy.sql")
    public void addTransactionToAssetHappy() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.ASSETS_PATH+Routes.ADD_TRANSACTION_TO_ASSET, 666)
                .content("{\n" +
                        "    \"type\": \"Buy\",\n" +
                        "    \"price\": 1,\n" +
                        "    \"dateTime\": \"2022-14-05\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERTEET WEQ crte\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"amount\": 15\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void addTransactionToNoAsset() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.ASSETS_PATH+Routes.ADD_TRANSACTION_TO_ASSET, 666)
                .content("{\n" +
                        "    \"type\": \"Buy\",\n" +
                        "    \"price\": 1,\n" +
                        "    \"dateTime\": \"2022-14-05\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERTEET WEQ crte\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"amount\": 15\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
    }

    @Test
    @Sql("/testdata/add_transaction_to_asset_happy.sql")
    public void addTransactionToAssetBadRequest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.ASSETS_PATH+Routes.ADD_TRANSACTION_TO_ASSET, 666)
                .content("{\n" +
                        "    \"type\": \"Buy\",\n" +
                        "    \"price\": 1,\n" +
                        "    \"dateTime\": \"2022-14-05\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERTEET WEQ crte\",\n" +
                        "    \"quantity\": 0,\n" +
                        "    \"amount\": 15\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @Sql("/testdata/add_transaction_to_asset_happy.sql")
    public void addTransactionToAssetBadDate() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.ASSETS_PATH+Routes.ADD_TRANSACTION_TO_ASSET, 666)
                .content("{\n" +
                        "    \"type\": \"Buy\",\n" +
                        "    \"price\": 1,\n" +
                        "    \"dateTime\": \"2025-20-05\",\n" +
                        "    \"fee\": 30.30,\n" +
                        "    \"notes\": \"ADSERTEET WEQ crte\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"amount\": 15\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(412, response.getStatus());
    }
}
