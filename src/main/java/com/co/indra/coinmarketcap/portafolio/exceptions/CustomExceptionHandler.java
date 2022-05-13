package com.co.indra.coinmarketcap.portafolio.exceptions;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;


@ControllerAdvice
public class CustomExceptionHandler {
	
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ResponseBody
	    @ExceptionHandler(SQLException.class)
	    public ErrorResponse handleException(SQLException exception) {
	        return new ErrorResponse("500", "the portfolio name is already in use");
	    }

}
