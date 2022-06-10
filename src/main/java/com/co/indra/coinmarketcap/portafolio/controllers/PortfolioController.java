package com.co.indra.coinmarketcap.portafolio.controllers;

import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.models.responses.ListPortfolioResponse;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioDistribution;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import com.co.indra.coinmarketcap.portafolio.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

   @PostMapping(Routes.PORTFOLIO_ASSETS)
   public void createAssetToPortfolio(@RequestBody Asset asset, @PathVariable("idPortfolio") int idPortfolio) {
      assetService.createAsset(asset, idPortfolio);
   }

   @GetMapping(Routes.PORTFOLIO_USERS)
   public List<Portfolio> getPortfolio(@PathVariable int idUser) {
      return portfolioService.getPorfolioByUser(idUser);
   }

   @GetMapping(Routes.PORTFOLIO_ID)
   public List<Asset> getAssetsInPortfolio(@PathVariable(name = "idPortfolio") int id) {
      return assetService.getAssetsInPortfolio(id);
   }

   @GetMapping(Routes.DISTRIBUTION_PATH)
   public PortfolioDistribution getDistributionPortfolio(@PathVariable(name = "idPortfolio") int idPortfolio) {
      return portfolioService.getDistributionPortfolio(idPortfolio);
   }

   @GetMapping(Routes.PORTFOLIO_SUMARY)
   public ListPortfolioResponse getPortfolioSumary(@PathVariable int idUser) {
      return portfolioService.getSumary(idUser);
   }

   @PutMapping(Routes.EDIT_PORTFOLIO)
   public void editPortfolio(@PathVariable("idPortfolio") int id, @RequestBody Portfolio portfolio) {
      portfolioService.editPortfolio(portfolio, id);
   }

   // Eliminar portafolio por medio del nombre
   @DeleteMapping(Routes.DELETE_PORTFOLIO_BY_NAME)
   public void removePortafolio(@PathVariable("name") String name) {
      portfolioService.removePortafolio(name);

   }

    @GetMapping(Routes.PORTFOLIO_TRANSACTIONS)
    public List<Transaction> getPortfolioTransactions(@PathVariable(name = "IdPortfolio") int id){
        return portfolioService.getTransactionByPortfolioId(id);
    }

   @PutMapping(Routes.TRANSACTIONS_PATH+Routes.EDIT_TRANSACTION)
   public void editTransaction(@PathVariable("IdTransaction") int id, @RequestBody Transaction transaction) {
      portfolioService.editTransaction(transaction, id);
   }
}
