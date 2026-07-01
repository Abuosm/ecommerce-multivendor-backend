package com.abubakar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abubakar.model.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

}
