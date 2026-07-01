package com.abubakar.service;

import java.util.List;

import com.abubakar.model.Home;
import com.abubakar.model.HomeCategory;

public interface HomeService {
	public Home createHomePageData( List<HomeCategory> allCategories);
}
