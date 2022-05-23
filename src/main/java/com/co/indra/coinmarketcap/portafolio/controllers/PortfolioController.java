package com.co.indra.coinmarketcap.portafolio.controllers;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;

import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @DeleteMapping("/{name}")
    public void removeportafolio(@PathVariable("name") String name ) {
       portfolioService.removePortafolio(name);
       
    }

}
