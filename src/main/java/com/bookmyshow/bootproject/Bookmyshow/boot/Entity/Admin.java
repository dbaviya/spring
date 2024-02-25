package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Entity
public class Admin {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
	private String  adminName;
	@Positive(message = "contact can't be negative value")
	@Min(value = 6000000000l,message = "number must start above 6*10 ")
	@Max(value = 9999999999l,message = "number must end before 10*10 ")
	private Long admincontact;
	@NotNull(message = "email can't be not null")
	@NotBlank(message = "email can't be not blank")
	@Email(message = "enter valid email (ex: abc@gmail.com)")
	private String adminemail;
	@NotNull(message = "password can't  be not null")
	@NotBlank(message = "password can't be not blank")
	@Size(min = 8 , message = "password must be in minimum 8 charecter ")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
		message = "Your password must have at least minimum 8 charecter, 1 digit, 1 uppercase, 1 lowercase & 1 special charecter")
	private String adminpassword;
 
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> aUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> aTheatre;
	
}
