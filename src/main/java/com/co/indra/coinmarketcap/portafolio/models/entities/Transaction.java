package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="tbl_assets_transaction")
public class Transaction implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private int id;
	
	@Column(name="id_asset")
	private int id_asset;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price_transaction")
	private Long price;
	
	@Column(name="date_time")
	private Date date_time;
	
	@Column(name="fee")
	private Long fee;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="amount")
	private Long amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_asset() {
		return id_asset;
	}

	public void setId_asset(int id_asset) {
		this.id_asset = id_asset;
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

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
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
