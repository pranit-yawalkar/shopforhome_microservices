package com.wipro.shopforhome.userservice.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * AuthenticationToken Model Class in which attributes 
 * and methods related to AuthenticationToken model are defined.
 * 
 */

@Entity
@Table(name = "tokens")
public class AuthenticationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private Long id;

	@Column(name = "token")
	private String token;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public AuthenticationToken() {
		super();
	}

	/*
	 * Using uuid to generate random string as a user token
	 */
	public AuthenticationToken(User user) {
		this.user = user;
		this.createdDate = new Date();
		this.token = UUID.randomUUID().toString();
	}

	public AuthenticationToken(Long id, String token, Date createdDate, User user) {
		super();
		this.id = id;
		this.token = token;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
