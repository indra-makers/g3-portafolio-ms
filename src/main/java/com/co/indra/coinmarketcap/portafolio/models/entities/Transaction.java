package com.co.indra.coinmarketcap.portafolio.models.entities;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

	private int id;

	private int idAsset;

	private String type;
	@Min(1)
	private Double price;

	private String dateTime;

	private Double fee;

	private String notes;
	@Min(1)
	private int quantity;

	private Double amount;

	public Transaction() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAsset() {
		return idAsset;
	}

	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
