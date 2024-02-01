package com.zhunism.backendapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    @NotBlank(message =  "Username is mandatory")
    private String userName;

    @NotBlank(message = "password is mandatory")
    private String password;

}
