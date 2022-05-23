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


}
