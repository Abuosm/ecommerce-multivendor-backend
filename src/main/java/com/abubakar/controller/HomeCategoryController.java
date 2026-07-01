package com.abubakar.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abubakar.model.Home;
import com.abubakar.model.HomeCategory;
import com.abubakar.service.HomeCategorService;
import com.abubakar.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/categories")
public class HomeCategoryController {
	
	
	private final HomeCategorService homeCategoryService;
	private final HomeService homeService;
	
	// getting home page with seprated category
	@PostMapping
	public ResponseEntity<Home> creatHomeCategory(
			@RequestBody List<HomeCategory> homeCategory){
		// giving all the categories to home service
		List<HomeCategory> categoreies = homeCategoryService.createHomeCategories(homeCategory);
		// here home service will filter and arange in seperate section and return home page
		Home home = homeService.createHomePageData(categoreies);
		return new ResponseEntity<>(home,HttpStatus.ACCEPTED);
	}
	
	// for admin only
	// geting all home categoris
	@GetMapping("/admin/home-category")
	public ResponseEntity<List<HomeCategory>> getHomeCategory() throws Exception{
		List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
		return ResponseEntity.ok(categories);
	}
	
	// to update home category
	@PatchMapping("/admin/home-category/{id}")
	public ResponseEntity<HomeCategory> updateCategoryHandler(
			@PathVariable Long id,
			@RequestBody HomeCategory homeCategory) throws Exception{
		HomeCategory updateCategory = homeCategoryService.updateHomeCategory(homeCategory, id);
		return ResponseEntity.ok(updateCategory);
	}
	
	
	
}
