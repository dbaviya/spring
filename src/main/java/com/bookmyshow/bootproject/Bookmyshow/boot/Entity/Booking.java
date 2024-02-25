package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Component
@Entity
@Setter
@Getter
public class Booking {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int Bookingid;
	@Positive(message = "no of tickets can't be negative value")
	@Min(value = 1,message = "booking allowed minimum 1 ticket")
	@Max(value = 5,message = "booking allowed maximum 5 ticket only")
	private int nooftickets;
	@NotNull(message = "enter movie name")
	@NotBlank(message = "enter movie name")
	private String bMovieName;
	private double bprice;
	private LocalDate BookingDate;
	private int bseats;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shows bShows;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User bUser;
	
	
}
