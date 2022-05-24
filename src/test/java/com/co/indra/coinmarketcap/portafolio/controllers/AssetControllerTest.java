package com.co.indra.coinmarketcap.portafolio.controllers;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
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
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AssetRepository assetRepository;

    @Test
    @Sql("/testdata/addPortfolio.sql")
    public void createAssetHappyPath() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_ID, 666)
                .content("{\n" +
                        "    \"accouting\": 5,\n" +
                        "    \"nameAsset\": \"ZZZ\",\n" +
                        "    \"type\": \"BUY\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"price\": 1087.23,\n" +
                        "    \"dailyVariation\": 50.50,\n" +
                        "    \"holding\": 3,\n" +
                        "    \"avgBuyPrice\": 20.20,\n" +
                        "    \"profit\": 10.10,\n" +
                        "    \"loss\": 30.30\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<Asset> assets = assetRepository.findByPortfolioIdNameAsset(666,"ZZZ");
        Asset AssetToAssert = assets.get(0);

        Assertions.assertEquals("ZZZ", AssetToAssert.getNameAsset());
        Assertions.assertEquals(1, assets.size());
    }

    @Test
    @Sql("/testdata/insertAsset.sql")
    public void createAssetThatAlreadyExists() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_ID, 1)
                .content("{\n" +
                        "    \"accouting\": 5,\n" +
                        "    \"nameAsset\": \"TVQ\",\n" +
                        "    \"type\": \"BUY\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"price\": 1087.23,\n" +
                        "    \"dailyVariation\": 50.50,\n" +
                        "    \"holding\": 3,\n" +
                        "    \"avgBuyPrice\": 20.20,\n" +
                        "    \"profit\": 10.10,\n" +
                        "    \"loss\": 30.30\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();

        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("004", error.getCode());
        Assertions.assertEquals("A ASSET WITH THE GIVEN PORTFOLIO ALREADY EXISTS", error.getMessage());
    }

    @Test
    @Sql("/testdata/addPortfolio.sql")
    public void createAssetSell() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_ID, 666)
                .content("{\n" +
                        "    \"accouting\": 5,\n" +
                        "    \"nameAsset\": \"TVQ\",\n" +
                        "    \"type\": \"SELL\",\n" +
                        "    \"quantity\": 1,\n" +
                        "    \"price\": 1087.23,\n" +
                        "    \"dailyVariation\": 50.50,\n" +
                        "    \"holding\": 3,\n" +
                        "    \"avgBuyPrice\": 20.20,\n" +
                        "    \"profit\": 10.10,\n" +
                        "    \"loss\": 30.30\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);
        Assertions.assertEquals("010", error.getCode());
        Assertions.assertEquals("THE FIRST TRANSACTION MUST BE BUY", error.getMessage());
    }

    @Test
    @Sql("/testdata/getAssetsInPortfolio.sql")
    public void getAssetsInPortfolioHappyPath() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_ID, 1)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        Asset[] assets = objectMapper.readValue(response.getContentAsString(), Asset[].class);
        Assertions.assertEquals(3, assets.length);
    }

    @Test
    public void getAssetInPortfolioThatDoesNotExist() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.PORTFOLIO_PATH+Routes.PORTFOLIO_ID, 1)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());

        ErrorResponse error = objectMapper.readValue(response.getContentAsString(), ErrorResponse.class);
        Assertions.assertEquals("PORTFOLIO WITH THIS ID DOES NOT EXISTS", error.getMessage());
    }

}