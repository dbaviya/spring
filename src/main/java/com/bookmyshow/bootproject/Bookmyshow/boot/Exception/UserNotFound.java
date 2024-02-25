package com.bookmyshow.bootproject.Bookmyshow.boot.Exception;

public class UserNotFound extends RuntimeException{
  
	 String message;
	 public String getMessage() {
		 return message;
	 }
      public UserNotFound(String message) {
    	  this.message = message;
      }
	
}
