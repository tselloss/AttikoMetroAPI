package com.example.attikometroapi.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @NotNull(message = "User Id can't be null...")
    @NotBlank(message = "User Id cannot be blank.")
    @NotEmpty(message = "User Id cannot be empty.")
    private String userId;

    private String password;
}
