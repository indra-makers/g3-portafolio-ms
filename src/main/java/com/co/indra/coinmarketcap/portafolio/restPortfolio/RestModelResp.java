package com.co.indra.coinmarketcap.portafolio.restPortfolio;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestModelResp {
    private final RestTemplate template;

    public RestModelResp(RestTemplateBuilder restTemplateBuilder) {
        this.template =  restTemplateBuilder.build();
    }

    public ModelResp getUserById(int id) {
        String url = "https://g3-users-ms.herokuapp.com/api/user-ms/users/1"+id;
        return this.template.getForObject(url, ModelResp.class);
    }
}