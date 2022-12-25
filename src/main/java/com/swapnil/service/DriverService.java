package com.swapnil.service;

import com.swapnil.exception.DriverException;
import com.swapnil.model.Driver;

public interface DriverService {

	public Driver registerDriver(Driver driver)throws DriverException;
	
}
