package com.co.indra.coinmarketcap.portafolio.messagingQueue.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Coin implements Serializable {
   @Pattern(regexp = "[A-Z]{3}")
   private String symbol;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String nameCoin;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String icon;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double price;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double dailyVariation;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double weeklyVariation;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double marketCap;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double dailyVol;
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Double circulatingSupply;

   public Coin() {
   }

   public Coin(String symbol, String nameCoin, String icon) {
      this.symbol = symbol;
      this.nameCoin = nameCoin;
      this.icon = icon;
   }

   public Coin(String symbol, String nameCoin, String icon, Double price, Double dailyVariation,
               Double weeklyVariation, Double marketCap, Double dailyVol, Double circulatingSupply) {
      this.symbol = symbol;
      this.nameCoin = nameCoin;
      this.icon = icon;
      this.price = price;
      this.dailyVariation = dailyVariation;
      this.weeklyVariation = weeklyVariation;
      this.marketCap = marketCap;
      this.dailyVol = dailyVol;
      this.circulatingSupply = circulatingSupply;
   }

   public String getSymbol() {
      return symbol;
   }

   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }

   public String getNameCoin() {
      return nameCoin;
   }

   public void setNameCoin(String nameCoin) {
      this.nameCoin = nameCoin;
   }

   public String getIcon() {
      return icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Double getDailyVariation() {
      return dailyVariation;
   }

   public void setDailyVariation(Double dailyVariation) {
      this.dailyVariation = dailyVariation;
   }

   public Double getWeeklyVariation() {
      return weeklyVariation;
   }

   public void setWeeklyVariation(Double weeklyVariation) {
      this.weeklyVariation = weeklyVariation;
   }

   public Double getMarketCap() {
      return marketCap;
   }

   public void setMarketCap(Double marketCap) {
      this.marketCap = marketCap;
   }

   public Double getDailyVol() {
      return dailyVol;
   }

   public void setDaily_vol(Double dailyVol) {
      this.dailyVol = dailyVol;
   }

   public Double getCirculatingSupply() {
      return circulatingSupply;
   }

   public void setCirculatingSupply(Double circulatingSupply) {
      this.circulatingSupply = circulatingSupply;
   }
}
