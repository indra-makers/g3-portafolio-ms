package com.co.indra.coinmarketcap.portafolio.services;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;
import com.co.indra.coinmarketcap.portafolio.services.helper.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
      } else {
         throw new BusinessException(ErrorCodes.FIRST_TRANSACTION_MUST_BE_BUY);
      }
   }

   public void addTransactionToAsset(Transaction transaction, int idAsset) {
      if (assetRepository.findById(idAsset).isEmpty()) {
         throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
      }
      if (transaction.getPrice() <= 0 || transaction.getQuantity() <= 0) {
         throw new BusinessException(ErrorCodes.PRICE_QUANTITY_LESS_ZERO);
      }
      List<Asset> assetList = assetRepository.findById(idAsset);

      Helper.helperAssetValidate(transaction, transactionRepository, assetRepository, idAsset, assetList);
   }

   public int delete(int id) {
      if (assetRepository.delete(id) == 0) {
         throw new NotFoundException(ErrorCodes.ASSET_NOT_EXIST.getMessage());
      } else {
         return assetRepository.delete(id);
      }
   }

   public List<Asset> getAssetsInPortfolio(int idPortfolio) {
      if (portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()) {
         throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
      }
      return assetRepository.findByPortfolioId(idPortfolio);
   }

}
