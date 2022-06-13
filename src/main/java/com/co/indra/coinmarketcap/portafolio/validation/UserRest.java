package com.co.indra.coinmarketcap.portafolio.validation;

import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users.url}")
    private String apiUrl;

    @Cacheable(value = "getUserByIdExternal", cacheManager = "expire30Mins", key = "#id", unless = "#result == null")
    public UserModel getUserById(int id) {
        String url = apiUrl+"/"+id;
        ResponseEntity<UserModel> response = restTemplate.getForEntity(url, UserModel.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new NotFoundException(ErrorCodes.USER_DOES_NOT_EXIST.getMessage());
        }
        return response.getBody();
    }

}
