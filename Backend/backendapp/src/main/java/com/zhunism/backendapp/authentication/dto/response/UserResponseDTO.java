package com.zhunism.backendapp.authentication.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
}
