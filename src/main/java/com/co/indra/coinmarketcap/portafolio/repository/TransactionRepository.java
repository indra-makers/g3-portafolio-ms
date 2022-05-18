package com.co.indra.coinmarketcap.portafolio.repository;


import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


class TransactionRowMapper implements RowMapper<Transaction> {
	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setIdAsset(rs.getInt("id_asset"));
		transaction.setType(rs.getString("type"));
		transaction.setPrice(rs.getDouble("price_transaction"));
		transaction.setDateTime(rs.getString("date_time"));
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
		try {
			template.update("INSERT INTO tbl_assets_transaction(id_asset,type,price_transaction,date_time,fee,notes,quantity,amount) values (?,?,?,?,?,?,?,?)",
					idAsset, transaction.getType(), transaction.getPrice(), new SimpleDateFormat("yyyy-MM-dd").parse(transaction.getDateTime()), transaction.getFee(), transaction.getNotes(), transaction.getQuantity(), transaction.getPrice()*transaction.getQuantity());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
