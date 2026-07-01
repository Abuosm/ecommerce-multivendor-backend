package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.HomeCategory;
@Repository
public interface HomeCategoryRepository extends JpaRepository<HomeCategory, Long> {

}
