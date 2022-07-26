package com.wipro.shopforhome.userservice.dto;

/*
 *	SigninDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class SigninDTO {
	private String email;
	private String password;

	public SigninDTO() {
		super();
	}

	public SigninDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
