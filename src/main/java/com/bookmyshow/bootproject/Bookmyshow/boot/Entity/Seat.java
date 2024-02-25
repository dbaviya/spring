package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Component
@Setter
@Getter
@Entity
public class Seat{
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int seatid;
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
	private String seattype;
	@Positive(message = "no of seats can't be negative value")
	@Min(value = 1,message = "seats allowed minimum 1 ")
	@Max(value = 50,message = "seats allowed maximum 50 only")
	private int sNoOfSeats;
	
	@ManyToOne
	private Shows sShow;
  
 }
