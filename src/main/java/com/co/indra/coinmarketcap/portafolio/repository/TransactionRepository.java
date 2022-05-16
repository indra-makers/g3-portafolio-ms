package com.co.indra.coinmarketcap.portafolio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionRepository {
	
	@Autowired
	private JdbcTemplate template;

}
