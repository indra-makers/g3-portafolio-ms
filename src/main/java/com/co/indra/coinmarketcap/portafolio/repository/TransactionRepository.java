package com.co.indra.coinmarketcap.portafolio.repository;

import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TransactionRepository {
	
	@Autowired
	private JdbcTemplate template;

	public void addTransactionToAsset(Transaction transaction, int idAsset){
		template.update("INSERT INTO tbl_assets_transaction(id_asset,type,price_transaction,date_time,fee,notes,amount) values (?,?,?,?,?,?,?)",
				idAsset, transaction.getType(), transaction.getPrice(), transaction.getDateTime(), transaction.getFee(), transaction.getNotes(), transaction.getAmount());
	}

}
