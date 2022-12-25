package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Driver;

@Repository
public interface DriverDAO extends JpaRepository<Driver, Integer>{

	public Driver findByEmail(String email);
	
	
}
