package com.example.demo.data.dto.responses;

import com.example.demo.data.models.Payment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FindUserEmailResponse {
    private String email;
}
