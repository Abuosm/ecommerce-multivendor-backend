package com.abubakar.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// this acts as a response object of all error messages
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private String error;
	private String details;
	private LocalDateTime timestamp;
}
