package com.co.indra.coinmarketcap.portafolio.services;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import com.co.indra.coinmarketcap.portafolio.exceptions.BusinessException;
import com.co.indra.coinmarketcap.portafolio.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.repository.AssetRepository;
import com.co.indra.coinmarketcap.portafolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

    public void createAsset(Asset asset, int idPortfolio){
        if(portfolioRepository.findByPortfolioId(idPortfolio).isEmpty()){
            throw new NotFoundException(ErrorCodes.PORTFOLIO_DOES_NOT_EXIST.getMessage());
        }
        if(assetRepository.findById(asset.getId()).get(0).getIdPortfolio()==idPortfolio){
            throw new BusinessException(ErrorCodes.PORTFOLIO_WITH_ASSET_ALREADY_EXISTS);
        }

        assetRepository.createAsset(asset);

    }

}
