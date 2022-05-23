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
import java.util.List;

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
        if (asset.getType().equals("BUY")) {
            assetRepository.createAsset(asset, idPortfolio);
            portfolioRepository.modifyBalancePortfolio(idPortfolio, (asset.getPrice() * asset.getQuantity()));
        }else{
            throw new BusinessException(ErrorCodes.FIRST_TRANSACTION_MUST_BE_BUY);
        }

    }

    public void addTransactionToAsset(Transaction transaction, int idAsset) {
        int quantityTotal = 0;
        Double amountTotal = 0d;

        if (assetRepository.findById(idAsset).isEmpty()) {
            throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
        }
        if (transaction.getPrice() <= 0 || transaction.getQuantity() <= 0) {
            throw new BusinessException(ErrorCodes.PRICE_QUANTITY_LESS_ZERO);
        }
        List<Asset> assetList = assetRepository.findById(idAsset);
        if (transaction.getType().equals("BUY")) {
            transactionRepository.addTransactionToAsset(transaction, idAsset);
            List<Transaction> transactions = transactionRepository.getTransactionByIdAsset(idAsset);
            for (int i = 0; i < transactions.size(); i++) {
                quantityTotal = quantityTotal + transactions.get(i).getQuantity();
                amountTotal = amountTotal + transactions.get(i).getAmount();
            }
            assetRepository.updateAsset(transaction, idAsset, amountTotal, quantityTotal, assetList.get(0).getQuantity(), assetList.get(0).getPrice());
        } else if (transaction.getType().equals("SELL")) {
            if (transaction.getQuantity() > assetList.get(0).getQuantity()) {
                throw new BusinessException(ErrorCodes.VALUE_TO_SELL_EXCEEDS_CURRENT_VALUE);
            } else {
                transaction.setQuantity(-transaction.getQuantity());
                transactionRepository.addTransactionToAsset(transaction, idAsset);
                assetRepository.updateAsset(transaction, idAsset, amountTotal, quantityTotal, assetList.get(0).getQuantity(), assetList.get(0).getPrice());
            }
        } else {
            throw new BusinessException(ErrorCodes.UNKNOWN_TYPE);
        }


    }

    public int delete(int id) {
        if (assetRepository.delete(id) == 0) {
            throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
        } else {

            return assetRepository.delete(id);
        }

    }

    public List<Asset> getAssetsInPortfolio(int idPortfolio){
        if (portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()) {
            throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
        }
        return assetRepository.findByPortfolioId(idPortfolio);
    }

}
