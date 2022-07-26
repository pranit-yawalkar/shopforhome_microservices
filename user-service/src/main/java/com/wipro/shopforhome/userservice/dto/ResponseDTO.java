package com.wipro.shopforhome.userservice.dto;

/*
 *	ResponseDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class ResponseDTO {
	private String status;
	private String message;
	private String role;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String status, String message, String role) {
		super();
		this.status = status;
		this.message = message;
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
