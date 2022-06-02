package com.co.indra.coinmarketcap.portafolio.restPortfolio;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestModelResp {
    private final RestTemplate restTemplate;

    public RestModelResp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RespuestaModelResp getPostsPlainJSON() {
        String url = "https://g3-users-ms.herokuapp.com/api/user-ms/users/1";
        return this.restTemplate.getForObject(url, RespuestaModelResp.class);
    }
}