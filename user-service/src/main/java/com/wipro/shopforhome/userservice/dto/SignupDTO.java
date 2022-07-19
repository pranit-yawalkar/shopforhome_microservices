package com.wipro.shopforhome.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	private String city;
	private String state;
	private Integer zipCode;
	private Long contactNumber;
}