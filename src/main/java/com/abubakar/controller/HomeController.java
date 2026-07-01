package com.abubakar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abubakar.response.ApiResponse;

@RestController
public class HomeController {
  
  @GetMapping
  public ApiResponse homeControllerHandler() {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setMessage("Welcome to E-commerce Multivendor Application of abubakar!");
    return apiResponse;
}
}
