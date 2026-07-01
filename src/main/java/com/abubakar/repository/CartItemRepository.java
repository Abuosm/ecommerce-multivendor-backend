 package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Cart;
import com.abubakar.model.CartItem;
import com.abubakar.model.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
		CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
