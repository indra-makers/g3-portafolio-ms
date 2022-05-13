package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

public class Asset implements Serializable {

	private int id;

	private int idPortfolio;

	private int accouting;

	private String nameAsset;

	private int quantity;

	private Long price;

	private Long dailyVariation;

	private Long holding;

	private Long avgBuyPrice;

	private Long profit;

	private Long loss;

	public Asset() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPortfolio() {
		return idPortfolio;
	}

	public void setIdPortfolio(int idPortfolio) {
		this.idPortfolio = idPortfolio;
	}

	public int getAccouting() {
		return accouting;
	}

	public void setAccouting(int accouting) {
		this.accouting = accouting;
	}

	public String getNameAsset() {
		return nameAsset;
	}

	public void setNameAsset(String nameAsset) {
		this.nameAsset = nameAsset;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getDailyVariation() {
		return dailyVariation;
	}

	public void setDailyVariation(Long dailyVariation) {
		this.dailyVariation = dailyVariation;
	}

	public Long getHolding() {
		return holding;
	}

	public void setHolding(Long holding) {
		this.holding = holding;
	}

	public Long getAvgBuyPrice() {
		return avgBuyPrice;
	}

	public void setAvgBuyPrice(Long avgBuyPrice) {
		this.avgBuyPrice = avgBuyPrice;
	}

	public Long getProfit() {
		return profit;
	}

	public void setProfit(Long profit) {
		this.profit = profit;
	}

	public Long getLoss() {
		return loss;
	}

	public void setLoss(Long loss) {
		this.loss = loss;
	}

}
