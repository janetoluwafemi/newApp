package com.example.demo.data.services;

import com.example.demo.data.dto.requests.*;
import com.example.demo.data.dto.responses.*;
import com.example.demo.data.exceptions.*;
import com.example.demo.data.models.User;
import com.example.demo.data.repositories.UserRepo;
import com.example.demo.data.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private MailService mailService;
    private int otp;
    private String token;
    @Override
    public RegisterUserResponse registerUserResponse(RegisterUserRequest registerUserRequest) {
        User user = new User();
        Optional<User> user1 = userRepo.findUserByEmail(registerUserRequest.getEmail());
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        if (user1.isPresent()) {
            throw new UserAlreadyExist("User Already Exist");
        }
        if (registerUserRequest.getEmail().isEmpty() || registerUserRequest.getPassword().isEmpty() ||
                registerUserRequest.getFirstName().isEmpty() || registerUserRequest.getLastName().isEmpty()) {
            throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
        }
        user.setEmail(registerUserRequest.getEmail());
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setPassword(registerUserRequest.getPassword());
        Random randomFourNumbers = new Random();
        this.otp = randomFourNumbers.nextInt(1, 4);
        mailService.sendEmailToUser(
                registerUserRequest.getEmail(),
                String.valueOf(otp),
                "Welcome To Our App"
        );
        userRepo.save(user);
        registerUserResponse.setMessage("User Registered Successfully");
        return registerUserResponse;
    }

    @Override
    public VerifyEmailResponse verifyEmailResponse(VerifyEmailRequest verifyEmailRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(verifyEmailRequest.getEmail());
        if(existingUser.isPresent()) {
            User user = existingUser.get();
            if (verifyEmailRequest.getOtp() == otp) {
                user.setOtp(verifyEmailRequest.getOtp());
                userRepo.save(user);
                VerifyEmailResponse verifyEmailResponse = new VerifyEmailResponse();
                verifyEmailResponse.setId(user.getId());
                return verifyEmailResponse;
            }
            throw new InvalidOTPException("Invalid OTP");
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public LoginUserResponse loginUserResponse(LoginUserRequest loginUserRequest, AuthenticationManager authenticationManager) {
        Optional<User> user = userRepo.findUserByEmail(loginUserRequest.getEmail());
        if (loginUserRequest.getEmail().isEmpty() || loginUserRequest.getPassword().isEmpty()) {
            throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
        }
        if (user.isPresent()) {
            if (user.get().getPassword().equals(loginUserRequest.getPassword())){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword())
                );
                LoginUserResponse loginUserResponse = new LoginUserResponse();
                loginUserResponse.setMessage("User logged in successfully");
                this.token = jwtUtility.generateToken(String.valueOf(authentication));
                loginUserResponse.setToken(token);
                return loginUserResponse;
            }
        }
        throw new UserNotFoundException("User Not Found");
    }

    @Override
    public LogOutUserResponse logOutUserResponse(LogOutUserRequest logOutUserRequest) {
        return null;
    }

    @Override
    public ChangePasswordResponse changePasswordResponse(ChangePasswordRequest changePasswordRequest) {
        Optional<User> userThatHasLoggedIn = userRepo.findUserByToken(token);
        if (!token.isEmpty()) {
            User user = userThatHasLoggedIn.get();
            if(user.getPassword().equals(changePasswordRequest.getOldPassword())) {
                user.setPassword(changePasswordRequest.getNewPassword());
                userRepo.save(user);
                ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
                changePasswordResponse.setMessage("Password Changed Successfully");
                return changePasswordResponse;
            }
        }
        throw new UserNotLoggedInException("User Not Logged In");
    }

    @Override
    public ResetPasswordResponse resetPasswordResponse(ResetPasswordRequest resetPasswordRequest) {
        Optional<User> existingUser = userRepo.findUserByEmail(resetPasswordRequest.getEmail());
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            Random randomFourNumbers = new Random();
            this.otp = randomFourNumbers.nextInt(1, 4);
            mailService.sendEmailToUser(
                    resetPasswordRequest.getEmail(),
                    String.valueOf(otp),
                    "Reset Your Password"

            );
            ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
            if (resetPasswordRequest.getOtp().isEmpty() || resetPasswordRequest.getPassword().isEmpty()) {
                throw new AllFieldsMustBeInputted("All Fields Must Be Inputted");
            }
            user.setOtp(Integer.parseInt(resetPasswordRequest.getOtp()));
            user.setPassword(resetPasswordRequest.getPassword());
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), "{noop}" + user.get().getPassword(), new ArrayList<>());
        }
        throw new UserNotFoundException("User Not Found");
    }
}
