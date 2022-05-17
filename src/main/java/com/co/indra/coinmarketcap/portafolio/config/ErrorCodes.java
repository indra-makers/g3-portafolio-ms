package com.co.indra.coinmarketcap.portafolio.config;

public enum ErrorCodes {
	NAME_ALREADY_IN_USE("the portfolio name is already in use", "001"), 
	USER_NOT_EXIST("USER NOT EXIST", "002");

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
