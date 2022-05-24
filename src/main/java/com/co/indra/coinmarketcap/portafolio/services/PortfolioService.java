package com.co.indra.coinmarketcap.portafolio.services;

import java.util.List;

import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.models.responses.AssetAvgDist;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioDistribution;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.models.responses.ListPortfolioResponse;
import com.co.indra.coinmarketcap.portafolio.models.responses.PortfolioSumary;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;

@Service
public class PortfolioService {
   @Autowired
   private PortfolioRepository portfolioRepository;
   @Autowired
   private AssetRepository assetRepository;

   @Autowired
   private TransactionRepository transactionRepository;

   public void createPortfolio(Portfolio portfolio) {
      List<Portfolio> portfolioByname = portfolioRepository.findByNameAndUsername(portfolio.getIdUser(),
            portfolio.getName());
      if (!portfolioByname.isEmpty()) {
         throw new BusinessException(ErrorCodes.NAME_ALREADY_IN_USE);
      } else {
         portfolioRepository.create(portfolio);
      }
   }

   public List<Portfolio> getPorfolioByUser(int idUser) {

      if (portfolioRepository.getPorfolio(idUser).isEmpty()) {
         throw new NotFoundException(ErrorCodes.USER_NOT_EXIST.getMessage());
      } else {
         return portfolioRepository.getPorfolio(idUser);
      }
   }

   public PortfolioDistribution getDistributionPortfolio(int idPortfolio) {
      if (portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()) {
         throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
      }
      List<AssetAvgDist> assetAvgDists = assetRepository.findAssetsAvgNameByIdPortfolio(idPortfolio);
      return new PortfolioDistribution(assetAvgDists);
   }

   public ListPortfolioResponse getSumary(int idUser) {
      List<PortfolioSumary> portfolios = portfolioRepository.getSumary(idUser);
      double sum = 0;
      for (PortfolioSumary portfolio : portfolios) {
         sum += portfolio.getBalance();
      }
      return new ListPortfolioResponse(portfolios, sum);
   }

   public void editPortfolio(Portfolio portfolio, int id) {
      if (portfolioRepository.findByPortfolioId(id).isEmpty()) {
         throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
      }
      portfolioRepository.editPortfolio(portfolio, id);
   }

   public void removePortafolio(String name) {

      if (portfolioRepository.findPortafolioByName(name).isEmpty()) {
         throw new NotFoundException(ErrorCodes.PORTFOLIO_NOT_EXIST_WHIT_THIS_NAME.getMessage());
      }
      portfolioRepository.deletePortafolio(name);

   }

   public List<Transaction> getTransactionByPortfolioId(int idPortfolio){
      if(portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()){
         throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
      }
      return transactionRepository.getTransactionInPortfolio(idPortfolio);
   }

   public void editTransaction(Transaction transaction, int id) {
      if (transactionRepository.getTransactionById(id).isEmpty()) {
         throw new NotFoundException(ErrorCodes.TRANSACTION_NOT_EXIST_WHIT_THIS_ID.getCode());
      }
      transactionRepository.editTransaction(transaction,id);
   }


}
