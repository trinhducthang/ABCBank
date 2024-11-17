package com.ducthang._footbank.dto;

import com.ducthang._footbank.entity.enum_.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @NotNull(message = "Username is not null")
    @Size(max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
    @NumberFormat
    private String phone;

    @Past(message = "Date of birth must be in the past")
    private Date dob;

    private Gender gender;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

}
