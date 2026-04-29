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

//    @Test
//    public void testRegisterUser() {
//        RegisterUserRequest request = new RegisterUserRequest();
//        request.setEmail("oluwafemijanet85@gmail.com");
//        request.setPassword("janet@2007");
//        RegisterUserResponse registerUserResponse = userService.registerUserResponse(request);
//        assertThat(registerUserResponse.getMessage().equals("User Registered successfully"));
//    }
//    @Test
//    public void testUserVerification() {
//        VerifyEmailRequest request = new VerifyEmailRequest();
//        request.setEmail("oluwafemijanet85@gmail.com");
//        request.setOtp(6771);
//        VerifyEmailResponse verifyEmailResponse = userService.verifyEmailResponse(request);
//        assertThat(verifyEmailResponse.getMessage().equals("User verified successfully"));
//    }
//    @Test
//    public void testLoginUser() {
//        LoginUserRequest request = new LoginUserRequest();
//        request.setEmail("oluwafemijanet85@gmail.com");
//        request.setPassword("janet@2007");
//        LoginUserResponse loginUserResponse = userService.loginUserResponse(request);
//        assertThat(loginUserResponse.getMessage()).isEqualTo("User logged in successfully");
//        assertThat(loginUserResponse.getToken()).isNotNull();
//        assertThat(loginUserResponse.getToken()).isNotEmpty();
//    }
//    @Test
//    public void testResetPassword() {
//        String email = "oluwafemijanet85@gmail.com";
//        SendOTPResponse sendOTPResponse = userService.sendOTPResponse(email);
//        assertThat(sendOTPResponse.getOtp()!= null);
//        assertThat(sendOTPResponse).isNotNull();
//        assertThat(sendOTPResponse.getOtp()).isNotNull();
//        assert sendOTPResponse.getOtp() != null;
//        int otp = Integer.parseInt(sendOTPResponse.getOtp());
//        System.out.println("OtPP" + otp);
//        ResetPasswordRequest request = new ResetPasswordRequest();
//        request.setOtp(otp);
//        request.setNewPassword("Janet@2007");
//        ResetPasswordResponse resetPasswordResponse = userService.resetPasswordResponse(email, request);
//        assertThat(resetPasswordResponse.getMessage()).isEqualTo("Password Reset Successfully");
//    }
//    @Test
//    public void testChangePassword() {
//        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
//        changePasswordRequest.setEmail("oluwafemijanet85@gmail.com");
//        changePasswordRequest.setOldPassword("Janet@2007");
//        changePasswordRequest.setNewPassword("janet@2007");
//        ChangePasswordResponse changePasswordResponse = userService.changePasswordResponse(changePasswordRequest);
//        assertThat(changePasswordResponse.getMessage()).isEqualTo("Password Changed Successfully");
//    }

    @Test
    public void testAdminCanAddProduct() {
      AddProductRequest addProductRequest = new AddProductRequest();
      String email = "oluwafemijanet85@gmail.com";
      addProductRequest.setProductName("Moringa Hair Growth Oil");
      addProductRequest.setCategory("Hair Care");
      addProductRequest.setDescription("A nourishing blend of natural oils and herb that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest.setPrice(34.99);
      addProductRequest.setQuantity(50);
      addProductRequest.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPMZpZsJ1xAv8jRmmriGlzfhXAgikFBUdVag&s");
      AddProductResponse addProductResponse = userService.addProductResponse(email, addProductRequest);
      assertThat(addProductResponse.getMessage().equals("Product Added Successfully"));
        AddProductRequest addProductRequest2 = new AddProductRequest();
        String email2 = "oluwafemijanet85@gmail.com";
        addProductRequest2.setProductName("Alma Hair Growth Oil");
        addProductRequest2.setCategory("Hair Care");
        addProductRequest2.setDescription("A nourishing blend of natural oils and herbs that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
        addProductRequest2.setPrice(16.99);
        addProductRequest2.setQuantity(50);
        addProductRequest2.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS6pNZJtq2tqbTZJN4a90heaYkMaWGQ6IxHQ&s");
        AddProductResponse addProductResponse2 = userService.addProductResponse(email2, addProductRequest2);
        assertThat(addProductResponse2.getMessage().equals("Product Added Successfully"));
        AddProductRequest addProductRequest3 = new AddProductRequest();
        String email3 = "oluwafemijanet85@gmail.com";
        addProductRequest3.setProductName("Hair Growth Butter");
        addProductRequest3.setCategory("Hair Care");
        addProductRequest3.setDescription("A nourishing blend of natural oils, and herbs that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
        addProductRequest3.setPrice(49.99);
        addProductRequest3.setQuantity(50);
        addProductRequest3.setImageUrl("https://i.etsystatic.com/25768363/r/il/7eed7d/5769562350/il_fullxfull.5769562350_o8f9.jpg");
        AddProductResponse addProductResponse3 = userService.addProductResponse(email3, addProductRequest3);
        assertThat(addProductResponse3.getMessage().equals("Product Added Successfully"));
        AddProductRequest addProductRequest4 = new AddProductRequest();
        String email4 = "oluwafemijanet85@gmail.com";
        addProductRequest4.setProductName("Fenugreek  Hair Growth Oil");
        addProductRequest4.setCategory("Hair Care");
        addProductRequest4.setDescription("A nourishing blend of natural oils that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
        addProductRequest4.setPrice(32.99);
        addProductRequest4.setQuantity(50);
        addProductRequest4.setImageUrl("https://ng.jumia.is/JCRfQojNLeQbVoAxWM_uLI_42rs=/fit-in/500x500/filters:fill(white)/product/92/8212914/1.jpg?5060");
        AddProductResponse addProductResponse4 = userService.addProductResponse(email4, addProductRequest4);
        assertThat(addProductResponse4.getMessage().equals("Product Added Successfully"));
      AddProductRequest addProductRequest5 = new AddProductRequest();
      String email5 = "oluwafemijanet85@gmail.com";
      addProductRequest5.setProductName("Hair Growth Cream");
      addProductRequest5.setCategory("Hair Care");
      addProductRequest5.setDescription("A nourishing blend of natural oils and herb that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest5.setPrice(34.99);
      addProductRequest5.setQuantity(50);
      addProductRequest5.setImageUrl("https://www.supermart.ng/cdn/shop/files/spxvl2913_909eff95-c3d7-495f-be13-dc272c14d4a2.jpg?v=1756131220");
      AddProductResponse addProductResponse5 = userService.addProductResponse(email5, addProductRequest5);
      assertThat(addProductResponse5.getMessage().equals("Product Added Successfully"));
      AddProductRequest addProductRequest6 = new AddProductRequest();
      String email6 = "oluwafemijanet85@gmail.com";
      addProductRequest6.setProductName("Hair Growth Hair Conditioner");
      addProductRequest6.setCategory("Hair Care");
      addProductRequest6.setDescription("A nourishing blend of natural oils and herbs that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest6.setPrice(16.99);
      addProductRequest6.setQuantity(50);
      addProductRequest6.setImageUrl("https://www.supermart.ng/cdn/shop/files/medp2154_atone_nature-s_miracle_creme_conditioner_154_g.jpg?v=1688992322");
      AddProductResponse addProductResponse6 = userService.addProductResponse(email6, addProductRequest6);
      assertThat(addProductResponse6.getMessage().equals("Product Added Successfully"));
      AddProductRequest addProductRequest7 = new AddProductRequest();
      String email7 = "oluwafemijanet85@gmail.com";
      addProductRequest7.setProductName("Hair Growth Hair LeavenIn Conditioner");
      addProductRequest7.setCategory("Hair Care");
      addProductRequest7.setDescription("A nourishing blend of natural oils, and herbs that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest7.setPrice(49.99);
      addProductRequest7.setQuantity(50);
      addProductRequest7.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYjnkpmy5klIx5WR_19bbUuY-m1JN4OCUXGQ&s");
      AddProductResponse addProductResponse7 = userService.addProductResponse(email7, addProductRequest7);
      assertThat(addProductResponse7.getMessage().equals("Product Added Successfully"));
      AddProductRequest addProductRequest8 = new AddProductRequest();
      String email8 = "oluwafemijanet85@gmail.com";
      addProductRequest8.setProductName("Alma Hair Growth Cream");
      addProductRequest8.setCategory("Hair Care");
      addProductRequest8.setDescription("A nourishing blend of natural oils that promotes hair growth, strengthens roots, reduces breakage, and adds shine. مناسب for all hair types.");
      addProductRequest8.setPrice(32.99);
      addProductRequest8.setQuantity(50);
      addProductRequest8.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYUCbKZR-nWVExyvo6bRXMfRNFb1j_0FX9Rw&s");
      AddProductResponse addProductResponse8 = userService.addProductResponse(email8, addProductRequest8);
      assertThat(addProductResponse8.getMessage().equals("Product Added Successfully"));
}
//      @Test
//      public void testAdminCanDeleteProduct() {
//        RemoveProductRequest removeProductRequest = new RemoveProductRequest();
//        String email = "oluwafemijanet85@gmail.com";
//        removeProductRequest.setProductId(1L);
//        RemoveProductResponse removeProductResponse = userService.removeProductResponse(email, removeProductRequest);
//        assertThat(removeProductResponse.getMessage().equals("Product Removed Successfully"));
//    }
//    @Test
//    public void testUserCanGetProduct() {
////        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvbHV3YWZlbWlqYW5ldDg1QGdtYWlsLmNvbSIsImlhdCI6MTc3NjczMDg1OSwiZXhwIjoxNzc2ODE3MjU5fQ.RcYRBFVo0VZx5cjX5oibFyJJTNB9K2GmlaH7X89bArk";
//        Long productId = 2L;
//        GetProductResponse getProductResponse = userService.getProductResponse(productId);
//        assertThat(getProductResponse.getMessage()).isEqualTo("Product Retrieved Successfully");
//    }
//    @Test
//    public void textUserCanGetAllProducts() {
////        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvbHV3YWZlbWlqYW5ldDg1QGdtYWlsLmNvbSIsImlhdCI6MTc3NjczMDg1OSwiZXhwIjoxNzc2ODE3MjU5fQ.RcYRBFVo0VZx5cjX5oibFyJJTNB9K2GmlaH7X89bArk";
//        GetAllProductsResponse getAllProductsResponse = userService.getAllProductsResponse();
//        assertThat(getAllProductsResponse.getMessage()).isEqualTo("All Products Successfully Retrieved");
//    }
}
