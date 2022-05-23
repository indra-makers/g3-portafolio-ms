package com.co.indra.coinmarketcap.portafolio.models.responses;

import com.co.indra.coinmarketcap.portafolio.models.entities.Asset;

import java.util.List;

public class PortfolioDistribution {
    private List<AssetAvgDist> assets;

    public PortfolioDistribution() {
    }
    public PortfolioDistribution(List<AssetAvgDist> assets) {
        this.assets = assets;
    }

    public List<AssetAvgDist> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetAvgDist> assets) {
        this.assets = assets;
    }

}
