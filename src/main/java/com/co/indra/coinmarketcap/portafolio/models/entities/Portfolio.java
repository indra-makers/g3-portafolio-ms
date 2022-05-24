package com.co.indra.coinmarketcap.portafolio.models.entities;

import java.io.Serializable;

public class Portfolio implements Serializable {

   private int id;

   private String name;

   private int idUser;

   private double balance;

   public Portfolio() {
      super();
   }

   public Portfolio(String name, double balance, int idUser) {
      super();
      this.name = name;
      this.idUser = idUser;
      this.balance = balance;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getIdUser() {
      return idUser;
   }

   public void setIdUser(int idUser) {
      this.idUser = idUser;
   }

   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

}
