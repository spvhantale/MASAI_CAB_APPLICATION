package com.swapnil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Special Charcters not allowed. in Firstname")
	private String firstName;
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Special Charcters not allowed. in Lastname")
	private String lastName;
	@Email(message = "Enter proper email")
	private String email;
	@Size(min = 10,max = 10, message = "mobile number should be 10 digit")
	private String mobileNumber;
	
	private Integer[] currentPosition=new Integer[2];
	@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])).{6,100}$", message = "password not allowed")
	private String password;
	
	
	
}
