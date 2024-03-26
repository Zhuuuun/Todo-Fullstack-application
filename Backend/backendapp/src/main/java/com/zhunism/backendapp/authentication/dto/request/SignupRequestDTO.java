package com.zhunism.backendapp.authentication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDTO {

    @NotBlank(message = "Firstname is mandatory")
    @Size(max = 50, message = "Firstname must under 50 characters")
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(max = 50, message = "Lastname must under 50 characters")
    private String lastName;

    @NotBlank(message =  "Username is mandatory")
    @Pattern(regexp = "^[A-Za-z]\\w{5,20}$", message = "username doesn't match policies")
    private String userName;

    @NotBlank(message = "phone number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "incorrect phone number")
    private String phone;

    @NotBlank(message = "password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "password doesn't match policies")
    private String password;

    /* Regex pattern for password

            ^                 # start-of-string
        (?=.*[0-9])           # a digit must occur at least once
        (?=.*[a-z])           # a lower case letter must occur at least once
        (?=.*[A-Z])           # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])      # a special character must occur at least once
        (?=\S+$)              # no whitespace allowed in the entire string
        .{8,}                 # anything, at least eight places though
        $                     # end-of-string

    */

}
