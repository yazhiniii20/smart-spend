package com.yazh.smartspend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid Email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Size(min = 4,message = "Password must be atleast 4 characters")
    private String password;

    @NotBlank(message = "Role cannot be empty")
    private String role;
}
