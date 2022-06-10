package com.co.indra.coinmarketcap.portafolio.controllers;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import com.co.indra.coinmarketcap.portafolio.validation.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void userExistsHappyPath() throws Exception{
        UserModel mockUser = new UserModel(1, "juliancho", "julian.giraldo2", 1);
        ResponseEntity<UserModel> entity = new ResponseEntity<>(mockUser, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<UserModel>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "  \"balance\": 23,\n" +
                        "  \"id\": 1,\n" +
                        "  \"idUser\": 1,\n" +
                        "  \"name\": \"port\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();

        Assertions.assertEquals(200,response.getStatus());

    }

    @Test
    public void UserDoesNotExists() throws Exception{
        ResponseEntity<UserModel> entity = new ResponseEntity(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplate.getForEntity(
                Mockito.anyString(),
                Mockito.<Class<UserModel>>any()
        )).thenReturn(entity);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(Routes.PORTFOLIO_PATH)
                .content("{\n" +
                        "  \"balance\": 23,\n" +
                        "  \"id\": 1,\n" +
                        "  \"idUser\": 2,\n" +
                        "  \"name\": \"port\"\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        Assertions.assertEquals(404, response.getStatus());
        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("NOT FOUND", error.getCode());
        Assertions.assertEquals(ErrorCodes.USER_NOT_EXIST.getMessage(), error.getMessage());
    }
}
