package com.co.indra.coinmarketcap.portafolio.config;

public enum ErrorCodes {
	NAME_ALREADY_IN_USE("the portfolio name is already in use", "001"), 
	USER_NOT_EXIST("USER NOT EXIST", "002"),
	PORTFOLIO_DOES_NOT_EXIST("PORTFOLIO WITH THIS ID DOES NOT EXISTS", "003"),
	PORTFOLIO_WITH_ASSET_ALREADY_EXISTS("A ASSET WITH THE GIVEN PORTFOLIO ALREADY EXISTS", "004"),
	ASSET_NOT_EXIST("ASSET NOT EXIST", "005");

	String message;
	String code;

	ErrorCodes(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

}
