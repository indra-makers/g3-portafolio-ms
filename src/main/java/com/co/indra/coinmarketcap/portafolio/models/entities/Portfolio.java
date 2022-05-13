package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tbl_portfolio")
public class Portfolio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_portfolio")
	private int id;

	@Column(name = "name_portfolio")
	private String name;

	@Column(name = "id_user")
	private int id_user;

	@Column(name = "balance_portfolio")
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

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
