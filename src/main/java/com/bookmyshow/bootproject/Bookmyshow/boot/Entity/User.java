package com.bookmyshow.bootproject.Bookmyshow.boot.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter  
@Component
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be blank")
    private String username;

    @Email(message = "enter valid email (ex: abc@gmail.com)")
    @NotBlank(message = "email can't be blank")
    private String email;

    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    @Size(min = 8, max = 20, message = "Your password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Your password must have at least 1 digit, 1 uppercase letter, 1 lowercase letter, 1 special character, and no whitespace")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> uBooking;
}
