package com.example.demo.data.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FindUserEmailRequest {
    private String email;
}
