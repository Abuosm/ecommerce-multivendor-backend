package com.abubakar.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abubakar.config.JwtProvider;
import com.abubakar.domain.USER_ROLE;
import com.abubakar.model.Cart;
import com.abubakar.model.Seller;
import com.abubakar.model.User;
import com.abubakar.model.VerificationCode;

import com.abubakar.repository.CartRespository;
import com.abubakar.repository.SellerRepository;
import com.abubakar.repository.UserRepository;
import com.abubakar.repository.VerificationCodeRepository;
import com.abubakar.request.LoginRequest;
import com.abubakar.response.AuthResponse;
import com.abubakar.response.SignupRequest;
import com.abubakar.service.AuthService;
import com.abubakar.service.EmailService;
import com.abubakar.utils.OtpUtil;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final CartRespository cartRepository;
	private final VerificationCodeRepository verificationCodeRepository;
	private final EmailService emailService;
	private final CustomUserServiceImpl costomeUserService;
	private final SellerRepository sellerRepository;

	
	
	
	
	
	@Override
	public String createUser(SignupRequest req) throws Exception {
		
		VerificationCode verificationCode  = verificationCodeRepository.findByEmail(req.getEmail());
		
		if(verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())) {
			throw new Exception("wrong otp...");
		}
		
		User user = userRepository.findByEmail(req.getEmail());
		
		if(user!=null) {
			throw new Exception("Email is already registered. Please sign in.");
		}
		User createdUser = new User();
		createdUser.setFullName(req.getFullName());
		createdUser.setEmail(req.getEmail());
		createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
		createdUser.setMobile("98987439799");
		createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
		
		user = userRepository.save(createdUser);
		
		Cart cart = new Cart();
		cart.setUser(user);
		cartRepository.save(cart);
		
		 verificationCodeRepository.delete(verificationCode);
		
		List<GrantedAuthority> authorites = new ArrayList<>();
		authorites.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null,authorites);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		return jwtProvider.generateToken(authentication);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void sentLoginAndSignupOtp(String email, USER_ROLE role) throws Exception {
		
		String SIGNING_PREFIX = "signing_";

		
		if(email.startsWith(SIGNING_PREFIX)) {
			email= email.substring(SIGNING_PREFIX.length());
			
			if(role.equals(USER_ROLE.ROLE_SELLER)) {
				Seller seller = sellerRepository.findByEmail(email);
				if(seller == null) {
					throw new Exception("Seller Not found");
				}
				
				
			}
			else {
				User user = userRepository.findByEmail(email);
				if(user==null) {
					throw new Exception("user not exist with provided email");
				}
			}
			
		}
		
		VerificationCode isExist = verificationCodeRepository.findByEmail(email);
		
		if(isExist!=null) {
			verificationCodeRepository.delete(isExist);
		}
		
		String otp = OtpUtil.generateOtp();
		
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setOtp(otp);
		verificationCode.setEmail(email);
		
		verificationCodeRepository.save(verificationCode);
		
		
		String subject = "ComZee Login/Signup OTP";
		String text = "your Login/Signup otp is - " +otp;
		
		emailService.sendVerificationOtpEmail(email, otp, subject, text);
		
		
	}













	@Override
	public AuthResponse signing(LoginRequest req) {
		String username = req.getEmail();
		String otp= req.getOtp();
		
		
		Authentication authentication = authenticate(username, otp);
		// it setes authentication object to global context which make this id card available to all other parts
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authresponse = new AuthResponse();
		authresponse.setJwt(token);
		authresponse.setMessage("Login Success");
		
		Collection<? extends GrantedAuthority> authorityies = authentication.getAuthorities();
		String roleName = authorityies.isEmpty()?null:authorityies.iterator().next().getAuthority();
		authresponse.setRole(USER_ROLE.valueOf(roleName));
		
		return authresponse;
	}

	
	// In authServiceImpl.java

	private Authentication authenticate(String username, String otp) {
		String SELLER_PREFIX = "seller_";
	    UserDetails userDetails = costomeUserService.loadUserByUsername(username);
	    
	    if(username.startsWith(SELLER_PREFIX)) {
	    	username = username.substring(SELLER_PREFIX.length());
		}
	    
	    
	    if (userDetails == null) {
	        throw new BadCredentialsException("Invalid Username or Email");
	    }

	    // 1. FETCH the verification code from the database for this user
	    VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);
	    
	    // 2. CHECK if the code exists and if the OTP from the database matches the one provided
	    if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
	        throw new BadCredentialsException("Wrong OTP provided.");
	    }
	    
	    // 3. (Important!) DELETE the OTP after it has been used successfully
	    verificationCodeRepository.delete(verificationCode);

	    // 4. If everything is correct, create the Authentication object
	    // this authentication object is like ID card contians (principle , credential, authorites)
	    // principle who the user is
	    // credentials: the proof of identity
	    // Authorites; what the user is allowed to do
	    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}













	@Override
	public void sendLoginOtp(String email) throws MessagingException {
		// 
		throw new UnsupportedOperationException("Unimplemented method 'sendLoginOtp'");
	}
	
}
