package com.abubakar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abubakar.model.HomeCategory;
import com.abubakar.repository.HomeCategoryRepository;
import com.abubakar.service.HomeCategorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategorService {

	private final HomeCategoryRepository homeCategoryRepository;

	public HomeCategory createHomeCategory(HomeCategory homeCategory) {

		return homeCategoryRepository.save(homeCategory);
	}

	@Override
	public List<HomeCategory> createHomeCategories(List<HomeCategory> homeCategories) {
		if (homeCategoryRepository.findAll().isEmpty()) {
			return homeCategoryRepository.saveAll(homeCategories);
		}
		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory updateHomeCategory(HomeCategory category, Long id) throws Exception {
		HomeCategory existingCategory = homeCategoryRepository.findById(id)
				.orElseThrow(() -> new Exception("Category not found"));
		if (category.getImage() != null) {
			existingCategory.setImage(category.getImage());
		}
		if (category.getCategoryId() != null) {
			existingCategory.setCategoryId(category.getCategoryId());
		}
		return homeCategoryRepository.save(existingCategory);
	}

	@Override
	public List<HomeCategory> getAllHomeCategories() {

		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory createHomeCatagory(HomeCategory homeCatehory) {
	
		throw new UnsupportedOperationException("Unimplemented method 'createHomeCatagory'");
	}

}
