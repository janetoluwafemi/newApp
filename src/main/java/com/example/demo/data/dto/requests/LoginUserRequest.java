package com.example.demo.data.dto.requests;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
