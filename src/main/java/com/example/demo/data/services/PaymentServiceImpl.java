package com.example.demo.data.services;

import com.example.demo.data.dto.requests.InitializePaymentRequest;
import com.example.demo.data.dto.requests.VerifyPaymentRequest;
import com.example.demo.data.dto.responses.InitializePaymentResponse;
import com.example.demo.data.dto.responses.VerifyPaymentResponse;
import com.example.demo.data.models.Payment;
import com.example.demo.data.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
@Component
public class PaymentServiceImpl implements PaymentServiceInterface{

    @Value("${paystack.secret.key}")
    private String secretKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private MailService mailService;
    @Override
    public InitializePaymentResponse initializePaymentResponse(InitializePaymentRequest initializePaymentRequest) {
        String url = "https://api.paystack.co/transaction/initialize";
        String email = "oluwafemijanet85@gmail.com";
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer" + secretKey);
        headers.setBearerAuth(secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> body = new HashMap<>();
        body.put("email", initializePaymentRequest.getEmail());
        body.put("amount", initializePaymentRequest.getAmount() * 100);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<InitializePaymentResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, request, InitializePaymentResponse.class
        );
        String message = "A Payment of " + initializePaymentRequest.getAmount() + " was successfully made by this email " + initializePaymentRequest.getEmail();
        if (response.getStatusCode().is2xxSuccessful()) {
            mailService.sendEmailToUser(
                    email,
                    initializePaymentRequest.getEmail(),
                    message
            );
            Payment payment = new Payment();
            payment.setEmail(initializePaymentRequest.getEmail());
            payment.setAmount(initializePaymentRequest.getAmount());
            paymentRepo.save(payment);
        }
        return response.getBody();
    }

    @Override
    public VerifyPaymentResponse verifyPaymentResponse(VerifyPaymentRequest verifyPaymentRequest) {
        String url = "https://api.paystack.co/transaction/verify/" + verifyPaymentRequest.getReference();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + secretKey);
        HttpEntity<String> body = new HttpEntity<>(headers);
        ResponseEntity<VerifyPaymentResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, body, VerifyPaymentResponse.class
        );
        return response.getBody();
    }
}
