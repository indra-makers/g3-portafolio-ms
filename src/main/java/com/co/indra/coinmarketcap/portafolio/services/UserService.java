package com.co.indra.coinmarketcap.portafolio.services;

import com.co.indra.coinmarketcap.portafolio.restPortfolio.ModelResp;
import com.co.indra.coinmarketcap.portafolio.restPortfolio.RespuestaModelResp;
import com.co.indra.coinmarketcap.portafolio.restPortfolio.RestModelResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private RestModelResp restModelResp;

    public ModelResp user(String userId){
        RespuestaModelResp respuestaModelResp = restModelResp.getPostsPlainJSON();
        for(int i=0; i<=respuestaModelResp.getData().size(); i++){
            if(respuestaModelResp.getData().get(i).getUserId().equals(userId)){
                return new ModelResp(respuestaModelResp.getData().get(i).getUserId(),respuestaModelResp.getData().get(i).getName(),respuestaModelResp.getData().get(i).getMail(),
                        respuestaModelResp.getData().get(i).getIdMembership());
            }
        }
        return null;
    }
}
