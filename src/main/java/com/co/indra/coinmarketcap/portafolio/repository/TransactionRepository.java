package com.co.indra.coinmarketcap.portafolio.repository;

import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class TransactionRowMapper implements RowMapper<Transaction> {
   @Override
   public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
      Transaction transaction = new Transaction();
      transaction.setIdAsset(rs.getInt("id_asset"));
      transaction.setType(rs.getString("type"));
      transaction.setPrice(rs.getDouble("price_transaction"));
      transaction.setDateTime(rs.getDate("date_time"));
      transaction.setFee(rs.getDouble("fee"));
      transaction.setNotes(rs.getString("notes"));
      transaction.setQuantity(rs.getInt("quantity"));
      transaction.setAmount(rs.getDouble("amount"));
      return transaction;
   }
}

@Repository
public class TransactionRepository {

   @Autowired
   private JdbcTemplate template;

   public void addTransactionToAsset(Transaction transaction, int idAsset) {
      template.update(
            "INSERT INTO tbl_assets_transaction(id_asset,type,price_transaction,date_time,fee,notes,quantity,amount) values (?,?,?,?,?,?,?,?)",
            idAsset, transaction.getType(), transaction.getPrice(), transaction.getDateTime(), transaction.getFee(),
            transaction.getNotes(), transaction.getQuantity(), transaction.getPrice() * transaction.getQuantity());
   }

   public List<Transaction> getTransactionByIdAsset(int idAsset) {
      return template.query(
            "select id_asset, type, price_transaction, date_time, fee, notes, quantity, amount from tbl_assets_transaction where id_asset=?",
            new TransactionRowMapper(), idAsset);
   }

}
