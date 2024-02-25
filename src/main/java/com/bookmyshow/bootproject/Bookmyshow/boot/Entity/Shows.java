package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
@Component
public class Shows {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int showid;
	@NotNull(message = "show name can't be null")
	@NotBlank(message = "show name can't be blank")
	private String sname;
	private int starttime;
	private int endtime;
	@OneToOne(cascade = CascadeType.ALL)
	private Movies sMovie;
     
	@OneToMany(cascade =CascadeType.ALL)
	private List<Seat>  sSeats;
	
	@ManyToOne
	private Theatre sTheatre;

}
