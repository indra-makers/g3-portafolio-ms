package com.co.indra.coinmarketcap.portafolio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AssetRepository {
	
	@Autowired
	private JdbcTemplate template;

}
