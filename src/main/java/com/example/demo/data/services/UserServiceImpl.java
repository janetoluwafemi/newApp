package com.example.demo.data.services;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import com.example.demo.data.exceptions.*;
import com.example.demo.data.models.Payment;
import com.example.demo.data.models.Product;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.PaymentRepo;
import com.example.demo.data.repositories.ProductRepo;
import com.example.demo.data.repositories.UserRepo;
import com.example.demo.data.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Component
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public RegisterUserResponse registerUserResponse(RegisterUserRequest registerUserRequest) {
        User user = new User();
        Optional<User> user1 = userRepo.findUserByEmail(registerUserRequest.getEmail());
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        if (user1.isPresent()) {
            throw new UserAlreadyExist("User Already Exist");
        }
        if (registerUserRequest.getEmail().isEmpty() || registerUserRequest.getPassword().isEmpty()) {
            throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
        }
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        Random randomFourNumbers = new Random();
        int otpStr = randomFourNumbers.nextInt(10000);
        int otp = Integer.parseInt(String.format("%04d", otpStr));
        System.out.println("OPT generated is" + otpStr);
//        try {
//            mailService.sendEmailToUser(
//                    registerUserRequest.getEmail(),
//                    String.valueOf(otp),
//                    "Welcome To Our App"
//            );
//            user.setOtp(otp);
            userRepo.save(user);
            registerUserResponse.setMessage("User Registered Successfully");
//        } catch (Exception error) {
//            System.out.println(error.getMessage() + "Failed to send email");
//        }
        return registerUserResponse;
    }

    @Override
    public VerifyEmailResponse verifyEmailResponse(VerifyEmailRequest verifyEmailRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(verifyEmailRequest.getEmail());
        if(existingUser.isPresent()) {
            User user = existingUser.get();
            if (String.valueOf(verifyEmailRequest.getOtp()).equals(String.valueOf(existingUser.get().getOtp()))) {
                user.setOtp(verifyEmailRequest.getOtp());
                userRepo.save(user);
                VerifyEmailResponse verifyEmailResponse = new VerifyEmailResponse();
                verifyEmailResponse.setId(user.getId());
                verifyEmailResponse.setMessage("User verified successfully");
                user.setOtp(0);
                userRepo.save(user);
                return verifyEmailResponse;
            }
            throw new InvalidOTPException("Invalid OTP");
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public LoginUserResponse loginUserResponse(LoginUserRequest loginUserRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(loginUserRequest.getEmail());
        System.out.println(existingUser);
        if (loginUserRequest.getEmail().isEmpty() || loginUserRequest.getPassword().isEmpty()) {
            throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
        }
        if (existingUser.isPresent()) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword())
                );
                org.springframework.security.core.userdetails.UserDetails userDetails = (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
                LoginUserResponse loginUserResponse = new LoginUserResponse();
            assert userDetails != null;
            String token = jwtUtility.generateToken(userDetails.getUsername());
            User user = existingUser.get();
            user.setToken(token);
            userRepo.save(user);
            loginUserResponse.setMessage("User logged in successfully");
            loginUserResponse.setToken(token);
            return loginUserResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public LogOutUserResponse logOutUserResponse(LogOutUserRequest logOutUserRequest) {
        return null;
    }

    @Override
    public ChangePasswordResponse changePasswordResponse(ChangePasswordRequest changePasswordRequest) {
        Optional<User> findUser = userRepo.findUserByEmail(changePasswordRequest.getEmail());
        if (findUser.isPresent()) {
            User user = findUser.get();
                if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()) );
                    userRepo.save(user);
                    ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
                    changePasswordResponse.setMessage("Password Changed Successfully");
                    return changePasswordResponse;
                }
                throw new InCorrectPasswordException("Incorrect Password");
        }
        throw new UserNotFoundException("User Not Found");
    }
    @Override
    public SendOTPResponse sendOTPResponse(String email){
        Optional<User> existingUser = userRepo.findUserByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            Random randomFourNumbers = new Random();
            int otpStr = randomFourNumbers.nextInt(10000);
            int otp = Integer.parseInt(String.format("%04d", otpStr));
            mailService.sendEmailToUser(
                    email,
                    String.valueOf(otp),
                    "Otp sent, reset your password"
            );
            user.setOtp(otp);
            userRepo.save(user);
            SendOTPResponse sendOTPResponse = new SendOTPResponse();
            sendOTPResponse.setOtp(String.valueOf(otp));
            return sendOTPResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }
    @Override
    public ResetPasswordResponse resetPasswordResponse(String email, ResetPasswordRequest resetPasswordRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (resetPasswordRequest.getOtp() == 0 || resetPasswordRequest.getNewPassword().isEmpty()) {
                throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
            }
            if(user.getOtp() != resetPasswordRequest.getOtp()) {
                throw new InvalidOTPException("Invalid OTP");
            }
            user.setOtp(resetPasswordRequest.getOtp());
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            userRepo.save(user);
            ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
            resetPasswordResponse.setMessage("Password Reset Successfully");
            return resetPasswordResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public FindUserEmailResponse findUserEmailResponse(String email) {
        Optional<User> user = userRepo.findUserByEmail(email);
        if (user.isPresent()) {
            FindUserEmailResponse findUserEmailResponse = new FindUserEmailResponse();
            findUserEmailResponse.setEmail(user.get().getEmail());
            return findUserEmailResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }
    @Override
    public AddProductResponse addProductResponse(String email, AddProductRequest addProductRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(email);
        if (email.equals("oluwafemijanet85@gmail.com")) {
            if (existingUser.isPresent()) {
                Product product = new Product();
                Optional<Product> productOptional = productRepo.findProductByProductName(addProductRequest.getProductName());
                if (productOptional.isPresent()) {
                    throw new ProductAlreadyExistException("Product Already Exist");
                }
                product.setProductName(addProductRequest.getProductName());
                product.setCategory(addProductRequest.getCategory());
                product.setPrice(addProductRequest.getPrice());
                product.setDescription(addProductRequest.getDescription());
                product.setQuantity(addProductRequest.getQuantity());
                product.setImageUrl(addProductRequest.getImageUrl());
                productRepo.save(product);
                AddProductResponse addProductResponse = new AddProductResponse();
                addProductResponse.setProductName(product.getProductName());
                addProductResponse.setCategory(product.getCategory());
                addProductResponse.setPrice(product.getPrice());
                addProductResponse.setDescription(product.getDescription());
                addProductResponse.setQuantity(product.getQuantity());
                addProductResponse.setImageUrl(product.getImageUrl());
                addProductResponse.setMessage("Product Added Successfully");
                return addProductResponse;
            }
            throw new UserNotFoundException("User not found");
        }
        throw new IllegalArgumentException("Not Allowed To Add Product");
    }

    @Override
    public RemoveProductResponse removeProductResponse(String email, RemoveProductRequest removeProductRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(email);
        if (email.equals("oluwafemijanet85@gmail.com")) {
            if (existingUser.isPresent()) {
                Optional<Product> product = productRepo.findProductById(removeProductRequest.getProductId());
                if (product.isPresent()) {
                    productRepo.delete(product.get());
                    RemoveProductResponse removeProductResponse = new RemoveProductResponse();
                    removeProductResponse.setMessage("Product Removed Successfully");
                    return removeProductResponse;
                }
                throw new ProductDoesNotExistException("Product Does Not Exist");
            }
            throw new UserNotFoundException("User not found");
        }
        throw new IllegalArgumentException("Not Allowed To Remove Product");
    }

    @Override
//    public GetProductResponse getProductResponse(String token, Long productId) {
    public GetProductResponse getProductResponse(Long productId) {
//        Optional<User> user = userRepo.findUserByToken(token);
//        if (user.isPresent()) {
            Optional<Product> product = productRepo.getProductById(productId);
            if (product.isPresent()) {
                Product getProduct = product.get();
                GetProductResponse getProductResponse = new GetProductResponse();
                getProductResponse.setProduct(getProduct);
                getProductResponse.setMessage("Product Retrieved Successfully");
                return getProductResponse;
            }
            throw new ProductDoesNotExistException("Product Does Not Exist");
//        }
//        throw new UserNotFoundException("User not found");
    }

    @Override
//    public GetAllProductsResponse getAllProductsResponse(String token) {
    public GetAllProductsResponse getAllProductsResponse() {
//        Optional<User> user = userRepo.findUserByToken(token);
//        if (user.isPresent()) {
            List<Product> products = productRepo.findAll();
            GetAllProductsResponse getAllProductsResponse = new GetAllProductsResponse();
            getAllProductsResponse.setProducts(products);
            getAllProductsResponse.setMessage("All Products Successfully Retrieved");
            return getAllProductsResponse;

//        }
//        throw new UserNotFoundException("User not found");
    }

    @Override
    public FindUserPaymentResponse findUserPaymentResponse(String email, FindUserPaymentRequest findUserPaymentRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(email);
        if (email.equals("oluwafemijanet85@gmail.com")) {
            if (existingUser.isPresent()) {
                Payment payment = paymentRepo.findPaymentByEmail(findUserPaymentRequest.getUserEmail())
                        .orElseThrow(() -> new NoPaymentWasMadeWithThisEmailException("No Payment Was Made With This Email"));
                FindUserPaymentResponse findUserPaymentResponse = new FindUserPaymentResponse();
                findUserPaymentResponse.setPayment(payment);
                findUserPaymentResponse.setMessage("Payment Made With This Email " + findUserPaymentRequest.getUserEmail() + " Is Found Successfully");
                return findUserPaymentResponse;
            }
            throw new UserNotFoundException("User not found");
        }
        throw new IllegalArgumentException("Not Allowed To Add Product");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        }
        throw new UserNotFoundException("User Not Found");
    }
}
