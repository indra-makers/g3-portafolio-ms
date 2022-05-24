package com.co.indra.coinmarketcap.portafolio.models.responses;

public class AssetAvgDist {
    private String name;
    private Double avg;

    public AssetAvgDist() {
    }

    public AssetAvgDist(String name, Double avg) {
        this.name = name;
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
