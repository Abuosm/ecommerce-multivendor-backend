package com.abubakar.service;

import java.util.List;

import com.abubakar.model.Product;
import com.abubakar.model.Review;
import com.abubakar.model.User;
import com.abubakar.request.CreateReviewRequest;

public interface ReviewService {
	
	Review createReview(
			CreateReviewRequest req,
			User user,
			Product product);
	List<Review> getReviewByProductId(Long productId);
	
	Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
	
	void deleteReview(Long reviewId,Long userId) throws Exception;
	
	Review getReviewById(Long reviewId) throws Exception;
}
