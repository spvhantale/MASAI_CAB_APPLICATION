package com.swapnil.service;

import java.util.List;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.DriverException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Driver;
import com.swapnil.model.User;

public interface UserService {

	
	public User registerUser(User user)throws UserException;
	
	public CurrentUserSession login(LoginDTO login) throws CurrentUserException;
	
	public String logout(String key) throws CurrentUserException;
	
	public List<Driver> getAllDriver(String key)throws DriverException,CurrentUserException;
	
	public String updateDriverAndUser(Integer driverId,Integer x,Integer y,String key) throws DriverException,CurrentUserException;
}
