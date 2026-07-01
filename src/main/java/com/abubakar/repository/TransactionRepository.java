package com.abubakar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findBySellerId(Long sellerId);
	
}
