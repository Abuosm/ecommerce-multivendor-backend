package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abubakar.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

  User findByEmail(String email);

 
}
