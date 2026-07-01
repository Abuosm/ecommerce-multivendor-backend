package com.abubakar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abubakar.model.Product;
import com.abubakar.model.Review;
import com.abubakar.model.User;
import com.abubakar.request.CreateReviewRequest;
import com.abubakar.response.ApiResponse;
import com.abubakar.service.ProductService;
import com.abubakar.service.ReviewService;
import com.abubakar.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
	
	private final ReviewService reviewService;
	private final UserService userService;
	private final ProductService productService;
	
	
	@GetMapping("/products/{productId}/review")
	public ResponseEntity<List<Review>> getREviewByProductIdHandler(
			@PathVariable Long productId) {
		
		List<Review> reviews = reviewService.getReviewByProductId(productId);
		
		return ResponseEntity.ok(reviews);
	}
	
	
	@PostMapping("/products/{productId}/review")
	public ResponseEntity<Review> writeReview(
			@RequestBody CreateReviewRequest req,
			@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt) throws Exception {
		
		Product product = productService.findProductById(productId);
		User user = userService.findUserByJwtToken(jwt);		
		Review review = reviewService.createReview(req, user, product);
		
		return ResponseEntity.ok(review);
	}
	
	@PatchMapping("/reviews/{reviewId}")
	public ResponseEntity<Review> updateReview(
			@RequestBody CreateReviewRequest req,
			@PathVariable Long reviewId,
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		Review review = reviewService.updateReview(reviewId, req.getReviewText(), req.getReviewRating(), user.getId());
		
		return ResponseEntity.ok(review);
	}
	
	
	@DeleteMapping("/revviews/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReviewHandler(
			@PathVariable Long reviewId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		reviewService.deleteReview(reviewId, user.getId());
		ApiResponse resp = new ApiResponse();
		resp.setMessage("delete review successfull");
		
		return ResponseEntity.ok(resp);
		
	}
	
}
