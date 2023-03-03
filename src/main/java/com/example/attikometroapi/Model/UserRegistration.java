package com.example.attikometroapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Size(min = 5, max = 15, message = "Username should be min 5 and max 15 Characters!")
    private String username;

    @Size(min = 5, max = 15, message = "Password should be min 8 and max 15 Characters!")
    private String password;

    @Email(message = "Please enter a valid email...")
    private String email;

    public UserRegistration(
            @Size(min = 5, max = 15, message = "Username should be min 5 and max 15 Characters!") String username,
            @Pattern(regexp = "^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[&!@#%$^]){8,16}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character from[&!@#%^$] and 1 digit") @Size(min = 8, max = 16, message = "Password must contain min 8 and max 16 digits!") String password,
            @Email(message = "Please enter a valid email...") String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserRegistration(Integer customer_id, String username, LocalDateTime rightNow) {

    }
}
