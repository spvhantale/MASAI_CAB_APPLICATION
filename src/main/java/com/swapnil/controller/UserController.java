package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.DriverException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Driver;
import com.swapnil.model.User;
import com.swapnil.service.UserService;

@RestController
@RequestMapping("/masaicab/user")
public class UserController {

	@Autowired
	private UserService uService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException{
		
		User u=uService.registerUser(user);
		
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginUser(@RequestBody LoginDTO login) throws CurrentUserException{
		
		CurrentUserSession u=uService.login(login);
		
		return new ResponseEntity<CurrentUserSession>(u,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam String key) throws CurrentUserException{
		
		String s=uService.logout(key);
		return new  ResponseEntity<String>(s, HttpStatus.OK);
	}
	
	@GetMapping("/findride")
	public ResponseEntity<List<Driver>> getAllDriver(@RequestParam String key) throws DriverException, CurrentUserException{
		
		List<Driver> dlist=uService.getAllDriver(key);
		
		return new ResponseEntity<List<Driver>>(dlist,HttpStatus.OK);
	}
	
	@PutMapping("/book/{driverId}/{x}/{y}")
	public ResponseEntity<String> book(@PathVariable("driverId") Integer driverId,@PathVariable("x") Integer x,@PathVariable("y") Integer y,@RequestParam String key) throws DriverException, CurrentUserException{
		
		String u=uService.updateDriverAndUser(driverId, x, y, key);
		
		return new ResponseEntity<String>(u, HttpStatus.OK);
	}
	
	
	
}
