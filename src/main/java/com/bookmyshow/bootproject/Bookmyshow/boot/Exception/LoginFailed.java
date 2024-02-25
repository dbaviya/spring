package com.bookmyshow.bootproject.Bookmyshow.boot.Exception;

	import lombok.Getter;

	@Getter
	public class LoginFailed extends RuntimeException {

		String message;

		public LoginFailed(String message) {
			
			this.message = message;
			
		}
		
		
	}

