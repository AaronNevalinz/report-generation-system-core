package com.school.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRegistrationDto {
    @NotEmpty(message = "School Name is required")
    private String schoolName;

    @NotEmpty(message = "Admin first name is required")
    private String adminFirstName;

    @NotEmpty(message = "Admin last name is required")
    private String adminLastName;

    @NotEmpty(message = "Username is required")
    @Size(min=5, message = "Username must be at least 5 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min=5, message = "Password must be at least 5 characters")
    private String password;
}
