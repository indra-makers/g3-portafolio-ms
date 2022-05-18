package com.co.indra.coinmarketcap.portafolio.controllers;


import com.co.indra.coinmarketcap.portafolio.config.Routes;
import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;
import com.co.indra.coinmarketcap.portafolio.models.entities.Transaction;
import com.co.indra.coinmarketcap.portafolio.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.ASSETS_PATH)
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping(Routes.ADD_TRANSACTION_TO_ASSET)
    public void addTransactionToAsset(@Valid @RequestBody Transaction transaction, @PathVariable("idAsset") int idAsset){
        assetService.addTransactionToAsset(transaction, idAsset);
    }
    
    @DeleteMapping(Routes.DELETE_ASSETS)
    public void delete(@PathVariable("idAsset") int idAsset) {
    	assetService.delete(idAsset);
    }
    

}
