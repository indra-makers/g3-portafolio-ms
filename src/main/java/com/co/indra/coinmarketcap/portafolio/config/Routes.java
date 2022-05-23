package com.co.indra.coinmarketcap.portafolio.config;

public class Routes {
   public static final String PORTFOLIO_PATH = "/portfolios";
   public static final String PORTFOLIO_SUMARY = "/{idUser}/sumary";
   public static final String PORTFOLIO_USERS = "/{idUser}/portfolio";
   public static final String PORTFOLIO_ASSETS = "/{idPortfolio}/assets";
   public static final String ASSETS_PATH = "/assets";
   public static final String PORTFOLIO_ID = "/{idPortfolio}/assets";
   public static final String ADD_TRANSACTION_TO_ASSET = "/{idAsset}/transactions";
   public static final String DELETE_ASSETS = "/{idAsset}";
   public static final String DISTRIBUTION_PATH = "/{idPortfolio}/distributions";
   public static final String TRANSACTIONS_PATH = "/transactions";
   public static final String PORTFOLIO_USER = "/users/{id}/portfolios";
   public static final String EDIT_PORTFOLIO = "/{idPortfolio}";
   public static final String DELETE_PORTFOLIO_BY_NAME = "/{name}";

    public static final String PORTFOLIO_TRANSACTIONS = "/{IdPortfolio}/transactions";

}
