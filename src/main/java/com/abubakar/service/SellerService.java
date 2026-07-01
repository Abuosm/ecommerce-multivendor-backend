package com.abubakar.service;

import java.util.List;

import com.abubakar.dto.AccountStatus;
import com.abubakar.exceptions.SellerException;
import com.abubakar.model.Seller;

public interface SellerService {
	
	
	Seller getSellerProfile(String jwt) throws SellerException;
	Seller createSeller(Seller seller) throws SellerException;
	Seller getSellerById(Long id) throws SellerException;
	Seller getSellerByEmail(String email) throws SellerException;
	
	List<Seller> getAllSellers(AccountStatus status);
	Seller updateSeller(Long id,Seller seller) throws SellerException;
	public void deleteSeller(Long id) throws SellerException;
	Seller VerifyEmail(String email, String otp) throws SellerException;
	// using this admin can modify seller account
	Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws SellerException;
  Seller updateSellerAccountStatus(Long sellerId, com.abubakar.domain.AccountStatus status) throws SellerException;
	

}
