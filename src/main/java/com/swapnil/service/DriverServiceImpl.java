package com.swapnil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swapnil.exception.DriverException;
import com.swapnil.model.Cab;
import com.swapnil.model.Driver;

import com.swapnil.repository.DriverDAO;
import com.swapnil.repository.MyUserDAO;

@Service
public class DriverServiceImpl implements DriverService{

	
	@Autowired
	private DriverDAO dDao;
	
	@Autowired
	private PasswordEncoder pEnd;
	
	@Override
	public Driver registerDriver(Driver driver) throws DriverException {
		Driver d=dDao.findByEmail(driver.getEmail());
		
		if(d!=null) {
			throw new DriverException("Driver already present");
		}else {
			driver.setPassword(pEnd.encode(driver.getPassword()));
			Driver driv=dDao.save(driver);
			return driv;
		}
	
	}

}
