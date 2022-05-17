package com.co.indra.coinmarketcap.portafolio.controllers;


import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.PORTFOLIO_PATH)
public class AssetController {

    @Autowired
    AssetController assetController;

    @Autowired
    AssetService assetService;

    @PostMapping(Routes.PORTFOLIO_ID)
    public void createAsset(@RequestBody Asset asset, @PathVariable("idPortfolio") int idPortfolio){
        assetService.createAsset(asset,idPortfolio);
    }

}
