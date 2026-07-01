package com.abubakar.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.abubakar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
