package com.co.indra.coinmarketcap.portafolio.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.responses.AssetAvgDist;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioSumary;

class PortfolioRowMapper implements RowMapper<Portfolio> {
   @Override
   public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
      Portfolio portfolio = new Portfolio();
      portfolio.setName(rs.getString("name_portfolio"));
      portfolio.setIdUser(rs.getInt("id_user"));
      portfolio.setBalance(rs.getDouble("balance_portfolio"));
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

   public List<Portfolio> findByPortfolioId(int id) {
      return template.query("select * from tbl_portfolio where id_portfolio = ?", new PortfolioRowMapper(), id);
   }

   public List<Portfolio> findByNameAndUsername(int idUser, String name) {
      return template.query(
            "select id_user, name_portfolio, balance_portfolio from tbl_portfolio where id_user = ? and name_portfolio = ?",
            new PortfolioRowMapper(), idUser, name);
   }

   public List<Portfolio> getPorfolio(int idUser) {
      return template.query("select id_user, name_portfolio, balance_portfolio from tbl_portfolio where id_user=?",
            new PortfolioRowMapper(), idUser);
   }

   public List<PortfolioSumary> getSumary(int idUser) {
      return template.query("select name_portfolio,balance_portfolio from tbl_portfolio where id_user = ?",
            (rs, rn) -> new PortfolioSumary(rs.getString("name_portfolio"), rs.getDouble("balance_portfolio")), idUser);

   }

   public void modifyBalancePortfolio(int idPortfolio, Double value) {
      List<Portfolio> portfolios = template.query(
            "select id_user, name_portfolio, balance_portfolio from tbl_portfolio where id_portfolio=?",
            new PortfolioRowMapper(), idPortfolio);

      template.update("UPDATE tbl_portfolio SET balance_portfolio  = ? WHERE id_portfolio  = ?",
            portfolios.get(0).getBalance() + value, idPortfolio);
   }

   public void editPortfolio(Portfolio portfolio, int idProtfolio) {
      template.update("UPDATE tbl_portfolio SET name_portfolio = ? WHERE id_portfolio = ?", portfolio.getName(),
            idProtfolio);
   }

   // Eliminar portafolio
   public void deletePortafolio(String name) {
      template.update("DELETE FROM tbl_portfolio WHERE name_portfolio = ? ", name);
   }

   // Buscar portafolio por nombre
   public List<Portfolio> findPortafolioByName(String name) {
      return template.query(
            "SELECT id_user, name_portfolio, balance_portfolio from tbl_portfolio WHERE name_portfolio = ?",
            new PortfolioRowMapper(), name);
   }

}
