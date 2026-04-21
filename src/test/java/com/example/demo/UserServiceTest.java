package com.example.demo;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import com.example.demo.data.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testRegisterUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("oluwafemijanet85@gmail.com");
        request.setPassword("janet@2007");
        request.setFirstName("Oluwafemi");
        request.setLastName("Janet");
        RegisterUserResponse registerUserResponse = userService.registerUserResponse(request);
        assertThat(registerUserResponse.getMessage().equals("User Registered successfully"));
    }
    @Test
    public void testUserVerification() {
        VerifyEmailRequest request = new VerifyEmailRequest();
        request.setEmail("oluwafemijanet85@gmail.com");
        request.setOtp(6771);
        VerifyEmailResponse verifyEmailResponse = userService.verifyEmailResponse(request);
        assertThat(verifyEmailResponse.getMessage().equals("User verified successfully"));
    }
    @Test
    public void testLoginUser() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("oluwafemijanet85@gmail.com");
        request.setPassword("janet@2007");
        LoginUserResponse loginUserResponse = userService.loginUserResponse(request);
        assertThat(loginUserResponse.getMessage()).isEqualTo("User logged in successfully");
        assertThat(loginUserResponse.getToken()).isNotNull();
        assertThat(loginUserResponse.getToken()).isNotEmpty();
    }
    @Test
    public void testResetPassword() {
        String email = "oluwafemijanet85@gmail.com";
        SendOTPResponse sendOTPResponse = userService.sendOTPResponse(email);
        assertThat(sendOTPResponse.getOtp()!= null);
        assertThat(sendOTPResponse).isNotNull();
        assertThat(sendOTPResponse.getOtp()).isNotNull();
        assert sendOTPResponse.getOtp() != null;
        int otp = Integer.parseInt(sendOTPResponse.getOtp());
        System.out.println("OtPP" + otp);
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setOtp(otp);
        request.setNewPassword("Janet@2007");
        ResetPasswordResponse resetPasswordResponse = userService.resetPasswordResponse(email, request);
        assertThat(resetPasswordResponse.getMessage()).isEqualTo("Password Reset Successfully");
    }
    @Test
    public void testChangePassword() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setEmail("oluwafemijanet85@gmail.com");
        changePasswordRequest.setOldPassword("Janet@2007");
        changePasswordRequest.setNewPassword("janet@2007");
        ChangePasswordResponse changePasswordResponse = userService.changePasswordResponse(changePasswordRequest);
        assertThat(changePasswordResponse.getMessage()).isEqualTo("Password Changed Successfully");
    }
    @Test
    public void testAdminCanAddProduct() {
      AddProductRequest addProductRequest = new AddProductRequest();
      String email = "oluwafemijanet85@gmail.com";
      addProductRequest.setProductName("Organic Hair Growth Cream");
      addProductRequest.setCategory("Hair Care");
      addProductRequest.setDescription("A nourishing blend of natural oils that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest.setPrice(19.99);
      addProductRequest.setQuantity(50);
      addProductRequest.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaedTwR0NEG6b5xta2deg7f6Q069gV6U4aBA&s");
      AddProductResponse addProductResponse = userService.addProductResponse(email, addProductRequest);
      assertThat(addProductResponse.getMessage().equals("Product Added Successfully"));
}
      @Test
      public void testAdminCanDeleteProduct() {
        RemoveProductRequest removeProductRequest = new RemoveProductRequest();
        String email = "oluwafemijanet85@gmail.com";
        removeProductRequest.setProductId(1L);
        RemoveProductResponse removeProductResponse = userService.removeProductResponse(email, removeProductRequest);
        assertThat(removeProductResponse.getMessage().equals("Product Removed Successfully"));
    }
    @Test
    public void textUserCanGetAllProducts() {
        GetAllProductsRequest getAllProductsRequest = new GetAllProductsRequest();
        getAllProductsRequest.setUserId(1L);
        String token = "";
        GetAllProductsResponse getAllProductsResponse = userService.getAllProductsResponse(token);
        assertThat(getAllProductsResponse.getMessage()).isEqualTo("All Products Successfully Retrieved");
    }
}
