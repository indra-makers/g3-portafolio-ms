package com.co.indra.coinmarketcap.portafolio.restPortfolio;

import java.util.List;

public class RespuestaModelResp {
    private List<ModelResp> Data;


    public RespuestaModelResp() {
    }

    public RespuestaModelResp(List<ModelResp> data) {
        Data = data;
    }

    public List<ModelResp> getData() {
        return Data;
    }

    public void setData(List<ModelResp> data) {
        Data = data;
    }

}