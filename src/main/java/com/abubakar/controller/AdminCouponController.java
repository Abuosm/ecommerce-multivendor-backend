package com.abubakar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.annotation.RequestScope;

import com.abubakar.model.Cart;
import com.abubakar.model.Coupon;
import com.abubakar.model.User;
import com.abubakar.service.CartService;
import com.abubakar.service.CouponService;
import com.abubakar.service.UserService;
import com.abubakar.service.impl.CouponServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class AdminCouponController {

    private final CouponServiceImpl couponServiceImpl;

	private final CouponService couponService;
	private final UserService userService;
	private final CartService cartService;

	// User operation
	
	@PostMapping("/apply")
	public ResponseEntity<Cart> applyCouponHandler(
			@RequestParam String apply,
			@RequestParam String code,
			@RequestParam double orderValue,
			@RequestHeader("Authorization")String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		Cart cart;
		
		if(apply.equals("true")) {
			cart = couponService.applyCoupon(code, orderValue, user);
		}
		else {
			cart = couponService.removeCoupon(code, user);
		}
		
		return ResponseEntity.ok(cart);
	}
	
	
	// Admin operations only for admin user
	
	@PostMapping("/admin/create")
	public ResponseEntity<Coupon> createCouponHandler(@RequestBody Coupon coupon){
		Coupon createdCoupon = couponService.createCoupon(coupon);
		return ResponseEntity.ok(createdCoupon);
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteCouponHandler(@PathVariable Long id) throws Exception {
		couponService.deleteCoupon(id);
		return ResponseEntity.ok("coupon deleted successfully");
	}
	
	@GetMapping("/admin/all")
	public ResponseEntity<List<Coupon>> getAllCouponHandler(){
		List<Coupon> coupons = couponService.findAllCoupons();
		return ResponseEntity.ok(coupons);
	}
}
