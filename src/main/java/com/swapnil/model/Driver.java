package com.swapnil.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	@Pattern(regexp = "^[a-zA-Z]{6,12}*$", message = "Special Charcters not allowed. in firstname")
	private String firstname;
	@Pattern(regexp = "^[a-zA-Z]{6,12}*$", message = "Special Charcters not allowed. in lastname")
	private String lastName;
	@Email(message = "Enter proper email")
	private String email;
	@Size(min = 10,max = 10, message = "mobile number should be 10 digit")
	private String mobile;
	private Integer[] position=new Integer[2];
	@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	@Embedded
	private Cab cab;
	
}
