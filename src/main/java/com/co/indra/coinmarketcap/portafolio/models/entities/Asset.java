package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

public class Asset implements Serializable {

	private int id;

	private int idPortfolio;

	private int accouting;

	private String nameAsset;

	private int quantity;

	private Double price;

	private Double dailyVariation;

	private Double holding;

	private Double avgBuyPrice;

	private Double profit;

	private Double loss;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDailyVariation() {
		return dailyVariation;
	}

	public void setDailyVariation(Double dailyVariation) {
		this.dailyVariation = dailyVariation;
	}

	public Double getHolding() {
		return holding;
	}

	public void setHolding(Double holding) {
		this.holding = holding;
	}

	public Double getAvgBuyPrice() {
		return avgBuyPrice;
	}

	public void setAvgBuyPrice(Double avgBuyPrice) {
		this.avgBuyPrice = avgBuyPrice;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

}
