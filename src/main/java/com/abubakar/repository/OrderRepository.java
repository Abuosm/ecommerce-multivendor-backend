package com.abubakar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByUserId(Long userId);
	List<Order> findBySellerId(Long sellerId);
	

}
