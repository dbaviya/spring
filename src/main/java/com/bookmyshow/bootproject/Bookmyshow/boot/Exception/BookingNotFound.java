package com.bookmyshow.bootproject.Bookmyshow.boot.Exception;

public class BookingNotFound extends RuntimeException{
	 String message;
	 public String getMessage() {
		 return message;
	 }
      public BookingNotFound(String message) {
    	  this.message = message;
      }
}
