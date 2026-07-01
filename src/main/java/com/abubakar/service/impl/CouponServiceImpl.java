package com.abubakar.service.impl;

// import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abubakar.model.Cart;
import com.abubakar.model.Coupon;
import com.abubakar.model.User;
import com.abubakar.repository.CartRespository;
import com.abubakar.repository.CouponRepository;
import com.abubakar.repository.UserRepository;
import com.abubakar.service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;
	private final CartRespository cartRepository;
	private final UserRepository userRepository;

	

	@Override
	public Cart removeCoupon(String code, User user) throws Exception {
		Coupon coupon = couponRepository.findByCode(code);
		if (coupon == null) {
			throw new Exception("coupon not found...");
		}

		Cart cart = cartRepository.findByUserId(user.getId());

		double originalPrice = cart.getTotalSellingPrice() / (1 - (coupon.getDiscountPercentage() / 100.0));

		cart.setTotalSellingPrice((int) originalPrice);
		cart.setCouponCode(null);

		return cartRepository.save(cart);
	}

	@Override
	public Coupon findCouponById(Long id) throws Exception {

		return couponRepository.findById(id).orElseThrow(() -> new Exception("coupon not found with given id"));
	}

	@Override
	// whenever user try to invoke this method spring boot will pre authoriz user
	// role
	// it allows only admin user to access this method
	@PreAuthorize("hasRole ('ADMIN')")
	public Coupon createCoupon(Coupon coupon) {

		return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupons() {

		return couponRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole ('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {

		findCouponById(id);
		couponRepository.deleteById(id);

	}

	@Override
	public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
		 
		throw new UnsupportedOperationException("Unimplemented method 'applyCoupon'");
	}

}
