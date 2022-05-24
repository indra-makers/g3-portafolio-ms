package com.co.indra.coinmarketcap.portafolio.models.responses;

public class PortfolioSumary {
	
	private String name;
	private Double balance;

	public PortfolioSumary(String name, Double balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
