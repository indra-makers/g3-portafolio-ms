package com.co.indra.coinmarketcap.portafolio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Portfolio;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

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
    
    
    public void removePortafolio(String name) {
       
       if(portfolioRepository.findPortafolioByName(name).isEmpty()) {
          throw new RuntimeException("PortFolio not exist for delete");
       }
          portfolioRepository.deletePortafolio(name);
       
       
       }
    
}
