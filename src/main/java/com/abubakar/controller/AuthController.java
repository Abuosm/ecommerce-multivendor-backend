package com.abubakar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abubakar.domain.USER_ROLE;
import com.abubakar.model.VerificationCode;
import com.abubakar.request.LoginRequest;
import com.abubakar.response.ApiResponse;
import com.abubakar.response.AuthResponse;
import com.abubakar.response.SignupRequest;
import com.abubakar.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("User created successfully");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(
            @RequestBody VerificationCode req) throws Exception {

        authService.sendLoginOtp(req.getEmail());

        ApiResponse res = new ApiResponse();
        res.setMessage("Otp sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest req) throws Exception {

        return ResponseEntity.ok(authService.signing(req));
    }
}