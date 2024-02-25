package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Setter
@Getter
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private double ticketPrice;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Shows tShows;
	
    @OneToOne(cascade = CascadeType.ALL)
    private Movies idMovies;
    
    
 }
