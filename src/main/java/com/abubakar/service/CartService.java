package com.abubakar.service;

import com.abubakar.model.Cart;
import com.abubakar.model.CartItem;
import com.abubakar.model.Product;
import com.abubakar.model.User;

public interface CartService {
	
	public CartItem addCartItem(
			User user,
			Product product,
			String size,
			int quantity);
	
	public Cart findUserCart(User user);
	
}
