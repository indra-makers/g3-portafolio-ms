package com.co.indra.coinmarketcap.portafolio.messagingQueue;

import com.co.indra.coinmarketcap.portafolio.messagingQueue.model.Coin;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AssetService assetService;


    @RabbitListener(queues = "portafolio_coin_queue")
    public void getCoinQueue(String coin){
        try {
            System.out.print(coin);
            Coin coin1 = objectMapper.readValue(coin, Coin.class);
            assetService.updateAsset(coin1.getPrice(), coin1.getSymbol(), coin1.getDailyVariation());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}