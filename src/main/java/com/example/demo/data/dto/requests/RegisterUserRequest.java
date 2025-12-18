package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
