package com.abubakar.dto;

public enum UserRole {
	
	// defining user roles
	// it helps to avoid typos in the code and makes it easier to manage user roles
	// we can use this enum in the User model class
	// it corrects the role field in the User class to be of type UserRole instead of String
	ROLE_ADMIN, 
	ROLE_CUSTOMER,
	ROLE_SELLER

}
