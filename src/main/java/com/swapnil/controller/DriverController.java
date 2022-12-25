package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.DriverException;
import com.swapnil.model.Driver;
import com.swapnil.service.DriverService;

@RestController
@RequestMapping("/masaicab/driver")
public class DriverController {

	
	@Autowired
	private DriverService dService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) throws DriverException{
		
		Driver d=dService.registerDriver(driver);
		
		return new ResponseEntity<Driver>(d, HttpStatus.OK);
	}
}
