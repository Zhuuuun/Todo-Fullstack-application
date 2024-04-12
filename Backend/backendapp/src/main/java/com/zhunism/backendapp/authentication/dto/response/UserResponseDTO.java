package com.zhunism.backendapp.authentication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
}
