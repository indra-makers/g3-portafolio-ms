package com.co.indra.coinmarketcap.portafolio.controllers;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.services.PortfolioService;

@RestController
@RequestMapping(Routes.PORTFOLIO_PATH)
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private AssetService assetService;

    @PostMapping
    public void create(@RequestBody Portfolio portfolio) {
        portfolioService.createPortfolio(portfolio);
    }

    @GetMapping(Routes.PORTFOLIO_USER)
    public List<Portfolio> getDeviceLocation(@PathVariable(name = "id") int id) {
        return portfolioService.getPorfolioByUser(id);

    }

    @PostMapping(Routes.PORTFOLIO_ID)
    public void createAssetToPortfolio(@RequestBody Asset asset, @PathVariable("idPortfolio") int idPortfolio) {
        assetService.createAsset(asset, idPortfolio);
    }


    @GetMapping(Routes.PORTFOLIO_ID)
    public List<Asset> getAssetsInPortfolio(@PathVariable(name = "idPortfolio") int id){
        return assetService.getAssetsInPortfolio(id);
    }

}
