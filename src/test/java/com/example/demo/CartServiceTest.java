package com.example.demo;

import com.example.demo.data.dto.requests.AddToCartRequest;
import com.example.demo.data.dto.requests.RemoveFromCartRequest;
import com.example.demo.data.dto.responses.AddToCartResponse;
import com.example.demo.data.dto.responses.RemoveFromCartResponse;
import com.example.demo.data.services.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartServiceImpl cartService;

    @Test
    public void testAddCart() {
        AddToCartRequest request = new AddToCartRequest();
        request.setUserId(1L);
        request.setProductId(2L);
        request.setQuantity(6);
        AddToCartResponse response = cartService.addToCartResponse(request);
        assertThat(response.getMessage().equals("Successfully added to the cart"));
    }
    @Test
    public void testRemoveCart() {
        RemoveFromCartRequest removeFromCartRequest = new RemoveFromCartRequest();
        removeFromCartRequest.setUserId(1L);
        removeFromCartRequest.setProductId(2L);
        removeFromCartRequest.setCartItemId(1L);
        RemoveFromCartResponse response = cartService.removeFromCartResponse(removeFromCartRequest);
        assertThat(response.getMessage().equals("Successfully removed from the cart"));
    }
}
