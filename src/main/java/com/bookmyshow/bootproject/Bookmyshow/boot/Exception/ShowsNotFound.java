package com.bookmyshow.bootproject.Bookmyshow.boot.Exception;

public class ShowsNotFound extends RuntimeException{
	 String message;
	 public String getMessage() {
		 return message;
	 }
      public ShowsNotFound(String message) {
    	  this.message = message;
      }
}
