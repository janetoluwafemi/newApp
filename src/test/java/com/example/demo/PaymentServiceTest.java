package com.example.demo;

import com.example.demo.data.dto.requests.InitializePaymentRequest;
import com.example.demo.data.dto.responses.InitializePaymentResponse;
import com.example.demo.data.services.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentServiceImpl paymentService;

    @Test
    public void testThatUserCanMakePayment() {
        InitializePaymentRequest request = new InitializePaymentRequest();
        request.setEmail("eniolaonafujabi@gmail.com");
        request.setAmount(3500);
        InitializePaymentResponse response = paymentService.initializePaymentResponse(request);
        assertNotNull(response);
        assertThat(response);
    }
    @Test
    public void testUserCanVerifyPayment() {}
}
