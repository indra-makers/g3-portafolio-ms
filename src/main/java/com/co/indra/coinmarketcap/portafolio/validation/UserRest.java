package com.co.indra.coinmarketcap.portafolio.validation;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRest {

    private RestTemplate template;

    public UserRest(RestTemplateBuilder restTemplateBuilder) {
        this.template = restTemplateBuilder.build();
    }

    public UserModel getUserById(int id){
        String url ="https://g3-users-ms.herokuapp.com/api/user-ms/users/"+id;
        return this.template.getForObject(url, UserModel.class);
    }

}
