package com.abubakar.service;

import java.util.List;

import com.abubakar.model.HomeCategory;

public interface HomeCategorService {
	
	HomeCategory createHomeCatagory(HomeCategory homeCatehory);
	
	List<HomeCategory> createHomeCategories(List<HomeCategory> homeCategories);
	HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception;
	List<HomeCategory> getAllHomeCategories();
}
