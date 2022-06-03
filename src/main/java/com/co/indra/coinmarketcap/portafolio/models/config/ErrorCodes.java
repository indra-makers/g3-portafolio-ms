package com.co.indra.coinmarketcap.portafolio.models.config;

public enum ErrorCodes {
   NAME_ALREADY_IN_USE("THE PORTFOLIO NAME IS ALREADY IN USE", "001"),

   USER_NOT_EXIST("USER NOT EXIST", "002"),
   PORTFOLIO_DOES_NOT_EXIST("PORTFOLIO WITH THIS ID DOES NOT EXISTS", "003"),
   PORTFOLIO_WITH_ASSET_ALREADY_EXISTS("A ASSET WITH THE GIVEN PORTFOLIO ALREADY EXISTS", "004"),
   ASSET_NOT_EXIST("ASSET DOES NOT EXIST", "005"),
   PRICE_QUANTITY_LESS_ZERO("THE PRICE|QUANTITY SHOULD NOT BE LESS OR EQUAL TO 0", "007"),
   UNKNOWN_TYPE("UNKNOWN TYPE TRANSACTION", "048"),
   VALUE_TO_SELL_EXCEEDS_CURRENT_VALUE("THE VALUE TO SELL EXCEEDS THE CURRENT", "009"),
   FIRST_TRANSACTION_MUST_BE_BUY("THE FIRST TRANSACTION MUST BE BUY", "010"),
   PORTFOLIO_NOT_EXIST_WHIT_THIS_NAME("PORTFOLIO WITH THIS NAME DOES NOT EXISTS", "011"),

   USER_DOES_NOT_EXIST("THE USER DOES NOT EXIST ON THE EXTERNAL API", "005"),

   TRANSACTION_NOT_EXIST_WHIT_THIS_ID("TRANSACTION WITH THIS ID DOES NOT EXISTS", "012");
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
