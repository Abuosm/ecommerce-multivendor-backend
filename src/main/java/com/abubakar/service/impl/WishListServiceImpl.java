package com.abubakar.service.impl;

import org.springframework.stereotype.Service;

import com.abubakar.model.Product;
import com.abubakar.model.User;
import com.abubakar.model.Wishlist;
import com.abubakar.repository.WishListRepository;
import com.abubakar.service.WishListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

	private final WishListRepository wishListRepository;
	@Override
	public Wishlist createWishList(User user) {
		Wishlist wishList = new Wishlist();
		wishList.setUser(user);
		return wishListRepository.save(wishList);
	}

	@Override
	public Wishlist getWishListByUserId(User user) {
		Wishlist wishList = wishListRepository.findByUserId(user.getId());
		if(wishList == null) {
			wishList = createWishList(user);
		}
		return wishList;
	}

	@Override
	public Wishlist addProductToWishList(User user, Product product) {
		Wishlist wishList = getWishListByUserId(user);
		
		if(wishList.getProducts().contains(product)) {
			wishList.getProducts().remove(product);
		}
		else wishList.getProducts().add(product);
		return wishListRepository.save(wishList);
	}

}
