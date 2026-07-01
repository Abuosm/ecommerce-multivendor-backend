package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Wishlist;
@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Long> {

	Wishlist findByUserId(Long userId);
}
