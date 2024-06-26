package com.zhunism.backendapp.authentication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @NotBlank(message =  "Username is mandatory")
    private String userName;

    @NotBlank(message = "password is mandatory")
    private String password;

}
