package com.example.demo.data.webhook;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class paystackWebhookController {

    @PostMapping
    public void handleWebhook(@RequestBody String payload, @RequestHeader("x-paystack-signature") String signature) {}
}
