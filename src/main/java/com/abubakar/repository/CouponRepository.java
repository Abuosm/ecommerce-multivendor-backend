package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
