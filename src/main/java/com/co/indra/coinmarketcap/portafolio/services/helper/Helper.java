package com.co.indra.coinmarketcap.portafolio.services.helper;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;

public class Helper {

	public static void helperAssetValidate(Transaction transaction, TransactionRepository transactionRepository,
			AssetRepository assetRepository, int idAsset, List<Asset> assetList) {
		
		int quantityTotal = 0;
		Double amountTotal = 0d;
		if (transaction.getType().equals("BUY")) {
			transactionRepository.addTransactionToAsset(transaction, idAsset);
			List<Transaction> transactions = transactionRepository.getTransactionByIdAsset(idAsset);
			for (int i = 0; i < transactions.size(); i++) {
				quantityTotal = quantityTotal + transactions.get(i).getQuantity();
				amountTotal = amountTotal + transactions.get(i).getAmount();
			}
			assetRepository.updateAsset(transaction, idAsset, amountTotal, quantityTotal,
					assetList.get(0).getQuantity(), assetList.get(0).getPrice());
		} else if (transaction.getType().equals("SELL")) {
            if (transaction.getQuantity() > assetList.get(0).getQuantity()) {
                throw new BusinessException(ErrorCodes.VALUE_TO_SELL_EXCEEDS_CURRENT_VALUE);
            } else {
                transaction.setQuantity(-transaction.getQuantity());
                transactionRepository.addTransactionToAsset(transaction, idAsset);
                List<Transaction> transactions = transactionRepository.getTransactionByIdAsset(idAsset);
                for (int i = 0; i < transactions.size(); i++) {
                    quantityTotal = quantityTotal + transactions.get(i).getQuantity();
                    amountTotal = amountTotal + transactions.get(i).getAmount();
                }
                assetRepository.updateAsset(transaction, idAsset, amountTotal, quantityTotal, assetList.get(0).getQuantity(), assetList.get(0).getPrice());
            }
        } else {
            throw new BusinessException(ErrorCodes.UNKNOWN_TYPE);
        }

	}

}
