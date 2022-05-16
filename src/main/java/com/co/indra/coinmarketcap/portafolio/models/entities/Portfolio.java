package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

public class Portfolio implements Serializable {

	private int id;

	private String name;

	private int idUser;

	private Long balance;

	public Portfolio() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
