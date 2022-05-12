package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="tbl_assets")
public class Asset implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_assets")
	private int id;
	
	@Column(name="id_portfolio")
	private int id_portfolio;
	
	@Column(name="accouting")
	private int accouting;
	
	@Column(name="name_asset")
	private String name_asset;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="daily_variation")
	private Long daily_variation;
	
	@Column(name="holding")
	private Long holding;
	
	@Column(name="avg_buy_price")
	private Long avg_buy_price;
	
	@Column(name="profit")
	private Long profit;
	
	@Column(name="loss")
	private Long loss;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_portfolio() {
		return id_portfolio;
	}

	public void setId_portfolio(int id_portfolio) {
		this.id_portfolio = id_portfolio;
	}

	public int getAccouting() {
		return accouting;
	}

	public void setAccouting(int accouting) {
		this.accouting = accouting;
	}

	public String getName_asset() {
		return name_asset;
	}

	public void setName_asset(String name_asset) {
		this.name_asset = name_asset;
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

	public Long getDaily_variation() {
		return daily_variation;
	}

	public void setDaily_variation(Long daily_variation) {
		this.daily_variation = daily_variation;
	}

	public Long getHolding() {
		return holding;
	}

	public void setHolding(Long holding) {
		this.holding = holding;
	}

	public Long getAvg_buy_price() {
		return avg_buy_price;
	}

	public void setAvg_buy_price(Long avg_buy_price) {
		this.avg_buy_price = avg_buy_price;
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
