package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

	private int id;

	private int idAsset;

	private String type;

	private Long price;

	private Date dateTime;

	private Long fee;

	private String notes;

	private Long amount;

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
