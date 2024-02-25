												package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;
import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Component
public class Theatre {
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private int TheatreId;
	@NotNull(message = "name can't be not null")
	@NotBlank(message = "name can't be not blank")
  private String TheatreName;
	@NotNull(message = "location can't be not null")
	@NotBlank(message = "location can't be not blank")
  private String location;
	@Positive(message = "no of seats can't be negative value")
	@Min(value = 1,message = "capacity allowed minimum 1 ")
	@Max(value = 50,message = "capacity allowed maximum 50 only")
  private int capacity;
  
  @OneToMany(cascade = CascadeType.ALL)
    private List<Shows> tShows;
   
	
}
