package com.example.demo;

import com.example.demo.data.dto.requests.CancelOrderRequest;
import com.example.demo.data.dto.requests.MakeAnOrderRequest;
import com.example.demo.data.dto.responses.CancelOrderResponse;
import com.example.demo.data.dto.responses.MakeAnOrderResponse;
import com.example.demo.data.services.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testMakeAnOrder() {
        MakeAnOrderRequest makeAnOrderRequest = new MakeAnOrderRequest();
        makeAnOrderRequest.setUserId(1L);
        makeAnOrderRequest.setProductId(2L);
        makeAnOrderRequest.setEmail("oluwafemijanet85@gmail.com");
        MakeAnOrderResponse makeAnOrderResponse = orderService.makeAnOrderResponse(makeAnOrderRequest);
        assertThat(makeAnOrderResponse.getMessage().equals("Order made successfully"));
    }
    @Test
    public void testCancelOrder() {
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setUserId(1L);
        cancelOrderRequest.setOrderId(1L);
        cancelOrderRequest.setEmail("oluwafemijanet85@gmail.com");
        CancelOrderResponse cancelOrderResponse = orderService.cancelOrderResponse(cancelOrderRequest);
        assertThat(cancelOrderResponse.getMessage().equals("Order cancelled successfully"));
    }
}
