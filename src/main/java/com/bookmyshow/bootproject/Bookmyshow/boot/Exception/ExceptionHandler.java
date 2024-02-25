package com.bookmyshow.bootproject.Bookmyshow.boot.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

   public class ExceptionHandler extends ResponseEntityExceptionHandler {
		@org.springframework.web.bind.annotation.ExceptionHandler
			public ResponseEntity<ResponseStructure<String>> AdminNotFoundException(AdminNotFound ex){
			   ResponseStructure<String> structure = new ResponseStructure<String>();
				   structure.setData(ex.getMessage());
				   structure.setMessage("Admin does not exits");
				   structure.setStatus(HttpStatus.NOT_FOUND.value());
				   return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
				   
			   }
		public ResponseEntity<ResponseStructure<String>> BookingNotFoundException(BookingNotFound ex){
	     	ResponseStructure<String> structure = new ResponseStructure<String>();
					   structure.setData(ex.getMessage());
					   structure.setMessage("Booking does not exits");
					   structure.setStatus(HttpStatus.NOT_FOUND.value());
					   return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
                        
				   }
		public ResponseEntity<ResponseStructure<String>> MoviesNotFoundException(MovieNotFound ex){
		 	ResponseStructure<String> structure = new ResponseStructure<String>();
						   structure.setData(ex.getMessage());
						   structure.setMessage("Movie does not exits");
						   structure.setStatus(HttpStatus.NOT_FOUND.value());
						   return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);

					   }
		public ResponseEntity<ResponseStructure<String>> SeatNotFoundException(SeatNotFound ex){
		 	ResponseStructure<String> structure = new ResponseStructure<String>();
						   structure.setData(ex.getMessage());
						   structure.setMessage("seat does not exits");
						   structure.setStatus(HttpStatus.NOT_FOUND.value());
						   return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);

					   }
		public ResponseEntity<ResponseStructure<String>> ShowNotFoundException(ShowsNotFound ex){
		   ResponseStructure<String> structure=new ResponseStructure<String>();
		     structure.setData(ex.getMessage());
		     structure.setMessage("show does not exits");
		     structure.setStatus(HttpStatus.NOT_FOUND.value());
		     return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		     
		}
		public ResponseEntity<ResponseStructure<String>> TheatreNotFoundException(ShowsNotFound ex){
			   ResponseStructure<String> structure=new ResponseStructure<String>();
			     structure.setData(ex.getMessage());
			     structure.setMessage("theatre does not exits");
			     structure.setStatus(HttpStatus.NOT_FOUND.value());
			     return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			}
		
		public ResponseEntity<ResponseStructure<String>> UserNotFoundException(UserNotFound ex){
			   ResponseStructure<String> structure=new ResponseStructure<String>();
			     structure.setData(ex.getMessage());
			     structure.setMessage("User does not exits");
			     structure.setStatus(HttpStatus.NOT_FOUND.value());
			     return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);		    
			     
		}
    
   }