package com.abubakar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


// when excpetion pass through controller befor reaching to spring exception handler
// this controller advice will catch that excepiton and check if there any method which can handle that perticuler exception
// if there is any method it will pass that to that perticuler method which will handle and reaturn error details response
// also u can use @RestControllerAdvice better for rest apis
@ControllerAdvice
public class GlobalException {
	
	// this is like key for what type of excetion this method is handling
	@ExceptionHandler(SellerException.class)
	// WebRequest ot get request object to send error details response
	public ResponseEntity<ErrorDetails> sellerExceptionHandler(SellerException se, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setError(se.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		errorDetails.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException pe, WebRequest req) {
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setError(pe.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		errorDetails.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}

}
