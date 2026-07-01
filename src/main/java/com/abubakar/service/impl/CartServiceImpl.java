package com.abubakar.service.impl;

import org.springframework.stereotype.Service;

import com.abubakar.model.Cart;
import com.abubakar.model.CartItem;
import com.abubakar.model.Coupon;
import com.abubakar.model.Product;
import com.abubakar.model.User;
import com.abubakar.repository.CartItemRepository;
import com.abubakar.repository.CartRespository;
import com.abubakar.repository.CouponRepository;
import com.abubakar.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRespository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final CouponRepository couponRepository;

	@Override
	public CartItem addCartItem(User user, Product product, String size, int quantity) {

		Cart cart = findUserCart(user);

		CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

		if (isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setSize(size);
			cartItem.setUserId(user.getId());

			int totalPrice = quantity * product.getSellingPrice();
			cartItem.setSellingPrice(totalPrice);
			cartItem.setMrpPrice(quantity * product.getMrpPrice());
			cart.getItems().add(cartItem);
			cartItem.setCart(cart);

			return cartItemRepository.save(cartItem);

		}
		return isPresent;
	}

	@Override
	public Cart findUserCart(User user) {

		Cart cart = cartRepository.findByUserId(user.getId());

		if (cart == null) {
			Cart newCart = new Cart();
			newCart.setUser(user);
			cart = cartRepository.save(newCart);
		}

		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;

		for (CartItem cartItem : cart.getItems()) {
			totalPrice += cartItem.getMrpPrice();
			totalDiscountedPrice += cartItem.getSellingPrice();
			totalItem += cartItem.getQuantity();
		}

		cart.setTotalMrpPrice(totalPrice);
		cart.setTotalItems(totalItem);
		cart.setTotalSellingPrice(totalDiscountedPrice);

		if (cart.getCouponCode() != null) {
			Coupon coupon = couponRepository.findByCode(cart.getCouponCode());
			if (coupon != null && coupon.isActive()) {
				double discountAmount = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
				cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountAmount);
			} else {
				// Coupon invalid or expired, remove it
				cart.setCouponCode(null);
			}
		}

		cart.setDiscount(calculateDiscountPercentage(totalPrice, cart.getTotalSellingPrice()));
		cart.setTotalItems(totalItem);

		return cartRepository.save(cart);
	}

	private int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentge = (discount / mrpPrice) * 100;

		return (int) discountPercentge;
	}

}
