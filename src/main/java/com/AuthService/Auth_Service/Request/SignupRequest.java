package com.AuthService.Auth_Service.Request;

import com.AuthService.Auth_Service.utils.NameNotSame;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@NameNotSame
public class SignupRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must be 8+ characters with uppercase, lowercase, digit, and special character")
    private String password;
}
