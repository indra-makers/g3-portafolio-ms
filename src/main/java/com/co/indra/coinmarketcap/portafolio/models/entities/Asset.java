package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

public class Asset implements Serializable {

   private int id;

   private int idPortfolio;

   private String symbol;

   private int accouting;

   private String type;

   private String nameAsset;

   private int quantity;

   private Double price;

   private Double dailyVariation;

   private Double holding;

   private Double avgBuyPrice;

   private Double profit;

   private Double loss;

   public Asset() {

   }

   public Asset(int id, int idPortfolio, String symbol, int accouting, String type, String nameAsset, int quantity, Double price, Double dailyVariation, Double holding, Double avgBuyPrice, Double profit, Double loss) {
      this.id = id;
      this.idPortfolio = idPortfolio;
      this.symbol = symbol;
      this.accouting = accouting;
      this.type = type;
      this.nameAsset = nameAsset;
      this.quantity = quantity;
      this.price = price;
      this.dailyVariation = dailyVariation;
      this.holding = holding;
      this.avgBuyPrice = avgBuyPrice;
      this.profit = profit;
      this.loss = loss;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getIdPortfolio() {
      return idPortfolio;
   }

   public void setIdPortfolio(int idPortfolio) {
      this.idPortfolio = idPortfolio;
   }

   public String getSymbol() {
      return symbol;
   }

   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }

   public int getAccouting() {
      return accouting;
   }

   public void setAccouting(int accouting) {
      this.accouting = accouting;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getNameAsset() {
      return nameAsset;
   }

   public void setNameAsset(String nameAsset) {
      this.nameAsset = nameAsset;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
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

   public Double getHolding() {
      return holding;
   }

   public void setHolding(Double holding) {
      this.holding = holding;
   }

   public Double getAvgBuyPrice() {
      return avgBuyPrice;
   }

   public void setAvgBuyPrice(Double avgBuyPrice) {
      this.avgBuyPrice = avgBuyPrice;
   }

   public Double getProfit() {
      return profit;
   }

   public void setProfit(Double profit) {
      this.profit = profit;
   }

   public Double getLoss() {
      return loss;
   }

   public void setLoss(Double loss) {
      this.loss = loss;
   }
}
