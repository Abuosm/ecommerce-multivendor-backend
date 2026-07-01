package com.abubakar.service;

import com.abubakar.model.User;
public interface UserService {

	User findUserByJwtToken(String jwt) throws Exception;

	User findUserByEmail(String email) throws Exception;

	User addAddress(User user, com.abubakar.model.Address address);

}
