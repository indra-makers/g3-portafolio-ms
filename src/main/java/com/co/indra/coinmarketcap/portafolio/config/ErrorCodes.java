package com.co.indra.coinmarketcap.portafolio.config;

public enum ErrorCodes {
	error("", "");

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
