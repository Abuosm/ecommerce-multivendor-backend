package com.abubakar.service;

import com.abubakar.request.LoginRequest;
import com.abubakar.response.AuthResponse;
import com.abubakar.response.SignupRequest;

import jakarta.mail.MessagingException;

public interface AuthService {

  void sendLoginOtp(String email) throws MessagingException;
  String createUser(SignupRequest req) throws Exception;
  AuthResponse signing(LoginRequest req) throws Exception; 
} 