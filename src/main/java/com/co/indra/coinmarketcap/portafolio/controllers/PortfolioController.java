package com.co.indra.coinmarketcap.portafolio.controllers;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioDistribution;
import com.co.indra.coinmarketcap.portafolio.models.responses.ListPortfolioResponse;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping(Routes.PORTFOLIO_ASSETS)
	public void createAssetToPortfolio(@RequestBody Asset asset, @PathVariable("idPortfolio") int idPortfolio) {
		assetService.createAsset(asset, idPortfolio);
	}

	@GetMapping(Routes.PORTFOLIO_USERS)
	public List<Portfolio> getPortfolio(@PathVariable int idUsuario) {
		return portfolioService.getPorfolioByUser(idUsuario);
	}

	@GetMapping(Routes.PORTFOLIO_ID)
	public List<Asset> getAssetsInPortfolio(@PathVariable(name = "idPortfolio") int id) {
		return assetService.getAssetsInPortfolio(id);
	}

	@GetMapping(Routes.DISTRIBUTION_PATH)
	public PortfolioDistribution getDistributionPortfolio(@PathVariable(name = "idPortfolio") int idPortfolio) {
		return portfolioService.getDistributionPortfolio(idPortfolio);
	}

	public ListPortfolioResponse getPortfolioSumary(@PathVariable int idUser) {
		return portfolioService.getSumary(idUser);
	}

}
