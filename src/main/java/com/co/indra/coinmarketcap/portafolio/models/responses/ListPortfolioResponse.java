package com.co.indra.coinmarketcap.portafolio.models.responses;

import java.util.List;

public class ListPortfolioResponse {

   private List<PortfolioSumary> portfolios;
   private Double total;

   public ListPortfolioResponse(List<PortfolioSumary> portfolios, Double total) {
      super();
      this.portfolios = portfolios;
      this.total = total;
   }

   public List<PortfolioSumary> getPortfolios() {
      return portfolios;
   }

   public void setPortfolios(List<PortfolioSumary> portfolios) {
      this.portfolios = portfolios;
   }

   public Double getTotal() {
      return total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

}
