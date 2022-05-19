package com.co.indra.coinmarketcap.portafolio.services;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AssetService {

	@Autowired
	AssetRepository assetRepository;

	@Autowired
	PortfolioRepository portfolioRepository;

	@Autowired
	TransactionRepository transactionRepository;

	public void createAsset(Asset asset, int idPortfolio) {
		if (portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()) {
			throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
		}
		if (!assetRepository.findByPortfolioIdNameAsset(idPortfolio, asset.getNameAsset()).isEmpty()) {
			throw new BusinessException(ErrorCodes.PORTFOLIO_WITH_ASSET_ALREADY_EXISTS);
		}
		assetRepository.createAsset(asset, idPortfolio);
		portfolioRepository.modifyBalancePortfolio(idPortfolio, (asset.getPrice() * asset.getQuantity()));

	}

	public void addTransactionToAsset(Transaction transaction, int idAsset) {
		if (assetRepository.findById(idAsset).isEmpty()) {
			throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
		}

		transactionRepository.addTransactionToAsset(transaction, idAsset);
		assetRepository.modifyAvgBuyPrice(idAsset);
	}

	public int delete(int id) {

		if (assetRepository.delete(id) == 0) {
			throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
		} else {

			return assetRepository.delete(id);
		}

	}

}
