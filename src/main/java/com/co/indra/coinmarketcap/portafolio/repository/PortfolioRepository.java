package com.co.indra.coinmarketcap.portafolio.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;

class PortfolioRowMapper implements RowMapper<Portfolio> {
	@Override
	public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
		Portfolio portfolio = new Portfolio();
		portfolio.setName(rs.getString("name_portfolio"));
		portfolio.setIdUser(rs.getInt("id_user"));
		portfolio.setBalance(rs.getLong("balance_portfolio"));
		return portfolio;
	}
}

@Repository
public class PortfolioRepository {
	@Autowired
	private JdbcTemplate template;

	public void create(Portfolio portfolio) {
		template.update("INSERT INTO tbl_portfolio(name_portfolio,id_user,balance_portfolio) values (?,?,?)",
				portfolio.getName(), portfolio.getIdUser(), portfolio.getBalance());
	}

	public List<Portfolio> getPorfolioByUser(int idUser) {
		return template.query("select name_portfolio, balance_portfolio where id_user=?", new PortfolioRowMapper(),
				idUser);
	}

}
