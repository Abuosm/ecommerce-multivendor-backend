package com.abubakar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abubakar.domain.HomeCategorySection;

import com.abubakar.model.Deal;
import com.abubakar.model.Home;
import com.abubakar.model.HomeCategory;
import com.abubakar.repository.DealRepository;
import com.abubakar.service.HomeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
	
	private final DealRepository dealRepository;
	
	@Override
	public Home createHomePageData(List<HomeCategory> allCategories) {
		
		List<HomeCategory> gridCategory = allCategories.stream()
				.filter(category  ->
				category.getSection() == HomeCategorySection.GRID)
				.collect(Collectors.toList());
		
		List<HomeCategory> shopByCategory = allCategories.stream()
				.filter(category ->
				category.getSection() == HomeCategorySection.SHOP_BY_CATEGORY)
				.collect(Collectors.toList());
		List<HomeCategory> electricCategory = allCategories.stream()
				.filter(category ->
				category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
				.collect(Collectors.toList());
		List<HomeCategory> dealCategory = allCategories.stream()
				.filter(category ->
				category.getSection() == HomeCategorySection.DEALS)
				.collect(Collectors.toList());
		
		List<Deal> createdDeals = dealRepository.findAll();

if (createdDeals.isEmpty()) {

    createdDeals = dealRepository.saveAll(
            allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(null, 10, category))
                    .collect(Collectors.toList())
    );
}
		Home home = new Home();
		home.setGrid(gridCategory);
		home.setShopByCategory(shopByCategory);
		home.setElectricCategories(electricCategory);
		home.setDeals(createdDeals);
		home.setDealCategories(dealCategory);
		
		return home;
	}

}
