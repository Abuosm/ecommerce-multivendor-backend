package com.abubakar.service;

import com.abubakar.model.Product;
import com.abubakar.model.User;
import com.abubakar.model.Wishlist;

public interface WishListService {
	Wishlist  createWishList(User user);
	Wishlist getWishListByUserId(User user);
	Wishlist addProductToWishList(User user,Product product);
}
