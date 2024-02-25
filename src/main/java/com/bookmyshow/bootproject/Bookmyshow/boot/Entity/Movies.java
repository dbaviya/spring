package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Entity
public class Movies {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int movieid;
	@NotNull(message = "title can't be not null")
	@NotBlank(message = "title can't be not blank")
	private String movietitle;
	@NotNull(message = "genre can't be not null")
	@NotBlank(message = "genre can't be not blank")
	private String moviejourner;
	@Positive(message = "contact can't be negative value")
	@Min(value = 3,message = "ratting must start above 3 ")
	@Max(value = 10,message = "ratting must end before 10 ")
	private double movieRatting;
	private double movieprice;
	private LocalTime movieduration;
	private LocalDate movieDate;
	
	@OneToOne
	private Shows mShow;
}
