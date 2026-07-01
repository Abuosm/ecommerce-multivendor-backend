package com.abubakar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abubakar.dto.AccountStatus;
import com.abubakar.model.Seller;

public interface SellerRepository  extends JpaRepository<Seller, Long> {
  Seller findByEmail(String email);

  List<Seller> findByAccountStatus(AccountStatus status);

  
} 