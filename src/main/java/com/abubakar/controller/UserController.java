package com.abubakar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abubakar.model.User;
import com.abubakar.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    // fetching details from database
    @GetMapping("/users/profile")
    public ResponseEntity<User> getUserHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(user);
    }


    @PostMapping("/users/address")
    public ResponseEntity<User> addAddressHandler(
            @RequestBody com.abubakar.model.Address address,
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserByJwtToken(jwt);

        User updatedUser =
                userService.addAddress(user, address);

        return ResponseEntity.ok(updatedUser);
    }
}