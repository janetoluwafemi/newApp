package com.example.demo.data.services;

import com.example.demo.data.dto.requests.AddToCartRequest;
import com.example.demo.data.dto.requests.DeleteCartRequest;
import com.example.demo.data.dto.requests.RemoveFromCartRequest;
import com.example.demo.data.dto.responses.AddToCartResponse;
import com.example.demo.data.dto.responses.DeleteCartResponse;
import com.example.demo.data.dto.responses.RemoveFromCartResponse;
import com.example.demo.data.exceptions.CartDoesNotExistException;
import com.example.demo.data.exceptions.ProductDoesNotExistException;
import com.example.demo.data.models.Cart;
import com.example.demo.data.models.CartItem;
import com.example.demo.data.models.Product;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.CartItemRepo;
import com.example.demo.data.repositories.CartRepo;
import com.example.demo.data.repositories.ProductRepo;
import com.example.demo.data.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Component
public class CartServiceImpl implements CartServiceInterface{

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    @Transactional
    public AddToCartResponse addToCartResponse(AddToCartRequest addToCartRequest) {
        Optional<User> user = userRepo.findUserById(addToCartRequest.getUserId());
        Optional<Product> product = productRepo.findProductById(addToCartRequest.getProductId());
        if (user.isPresent()) {
            if (product.isPresent()) {
                Cart cart = cartRepo.findCartById(addToCartRequest.getUserId())
                        .orElseGet(() -> {
                            Cart newCart = new Cart();
                            newCart.setUser(user.get());
                            newCart.setItems(new ArrayList<>());
                            return newCart;
                        });
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product.get());
                cartItem.setQuantity(addToCartRequest.getQuantity());
                cartItemRepo.save(cartItem);
                cartItem.setCart(cart);
                cart.getItems().add(cartItem);
                cartRepo.save(cart);
                AddToCartResponse addToCartResponse = new AddToCartResponse();
                addToCartResponse.setCartItem(cartItem);
                addToCartResponse.setMessage("Successfully added to the cart");
                return addToCartResponse;
            }
            throw new ProductDoesNotExistException("Product does not exist");
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public DeleteCartResponse deleteCartResponse(DeleteCartRequest deleteCartRequest) {
        Optional<User> user = userRepo.findUserById(deleteCartRequest.getUserId());
        if (user.isPresent()) {
            Cart existingCart = cartRepo.findCartById(deleteCartRequest.getUserId())
                    .orElseThrow(() -> new CartDoesNotExistException("Cart does not exist"));
            cartRepo.delete(existingCart);
            DeleteCartResponse deleteCartResponse = new DeleteCartResponse();
            deleteCartResponse.setMessage("Successfully deleted the cart");
            return deleteCartResponse;
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public RemoveFromCartResponse removeFromCartResponse(RemoveFromCartRequest removeFromCartRequest) {
        Optional<User> user = userRepo.findUserById(removeFromCartRequest.getUserId());
        Optional<Product> product = productRepo.findProductById(removeFromCartRequest.getProductId());
        if (user.isPresent()) {
            if (product.isPresent()) {
                Cart cart = cartRepo.findCartById(removeFromCartRequest.getUserId())
                        .orElseThrow(() -> new CartDoesNotExistException("Cart does not exist"));
                Optional<CartItem> cartItem = cartItemRepo.findCartItemById(removeFromCartRequest.getCartItemId());
                if (cartItem.isPresent()) {
                    CartItem cartItemItem = cartItem.get();
                    cartItemRepo.delete(cartItemItem);
                    cartRepo.save(cart);
                    RemoveFromCartResponse removeFromCartResponse = new RemoveFromCartResponse();
                    removeFromCartResponse.setMessage("Successfully removed from the cart");
                    return removeFromCartResponse;
                }
                throw new RuntimeException("Cart Item Not Found");
            }
            throw new ProductDoesNotExistException("Product does not exist");
        }
        throw new UsernameNotFoundException("User not found");
    }
}
