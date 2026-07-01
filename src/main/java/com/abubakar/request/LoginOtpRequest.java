package com.abubakar.request;

import com.abubakar.dto.UserRole;

import lombok.Data;

@Data
public class LoginOtpRequest {
	private String email;
	private String otp;
	private UserRole role;
}
