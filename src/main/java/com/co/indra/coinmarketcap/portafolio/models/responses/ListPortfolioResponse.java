package com.co.indra.coinmarketcap.portafolio.models.responses;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;

public class ListPortfolioResponse {

	private List<Portfolio> portfolios;
	private Double total;

	public ListPortfolioResponse(List<Portfolio> portfolios, Double total) {
		super();
		this.portfolios = portfolios;
		this.total = total;
	}

	public List<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
