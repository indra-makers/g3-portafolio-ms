package com.co.indra.coinmarketcap.portafolio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;

@Service
public class PortfolioService {

	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	public void createPortfolio(Portfolio portfolio) {
		
		portfolioRepository.create(portfolio);
	}
	
	public List<Portfolio> getPorfolioByUser(int idUser){
		return portfolioRepository.getPorfolioByUser(idUser);
	}
}
