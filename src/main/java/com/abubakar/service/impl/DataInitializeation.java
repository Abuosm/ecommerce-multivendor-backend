package com.abubakar.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abubakar.domain.USER_ROLE;

import com.abubakar.model.User;
import com.abubakar.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializeation implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) {
		initializeAdminUser();
	}
	
	public void initializeAdminUser() {
		String adminUsername = "lordking9990@gmail.com";
		if (userRepository.findByEmail(adminUsername)==null) {
			User adminUser = new User();
			
			adminUser.setEmail(adminUsername);
			adminUser.setPassword(passwordEncoder.encode("Zeeshan9"));
			adminUser.setFullName("Saif Ali Khan");
			adminUser.setRole(USER_ROLE.ROLE_ADMIN);
			
			userRepository.save(adminUser);
		}
	}
	
}
