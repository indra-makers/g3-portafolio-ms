package com.co.indra.coinmarketcap.portafolio.exceptions;

import com.co.indra.coinmarketcap.portafolio.config.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.co.indra.coinmarketcap.portafolio.models.responses.ErrorResponse;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

   @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
   @ResponseBody // que la respuesta va a ser personalizada.
   @ExceptionHandler(BusinessException.class)
   public ErrorResponse handleBusinessException(BusinessException exception) {
      return new ErrorResponse(exception.getCode(), exception.getMessage());
   }

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody // que la respuesta va a ser personalizada.
   @ExceptionHandler(NotFoundException.class)
   public ErrorResponse handleBusinessException(NotFoundException exception) {
      return new ErrorResponse("NOT FOUND", exception.getMessage());
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handleNotFoundException(MethodArgumentNotValidException exception) {
      return new ErrorResponse("ARGUMENT INVALID", exception.getMessage());
   }
   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ResponseBody
   @ExceptionHandler(HttpClientErrorException.NotFound.class)
   public ErrorResponse handleNotFoundExceptionExternalApi(HttpClientErrorException.NotFound exception) {
      return new ErrorResponse("ERROR", ErrorCodes.USER_DOES_NOT_EXIST.getMessage());
   }

}
