package com.wipro.shopforhome.orderservice.service;

import com.wipro.shopforhome.orderservice.exception.AuthenticationFailException;
import com.wipro.shopforhome.orderservice.model.AuthenticationToken;
import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * Authentication Service class to define the business logic and interact with the 
 * Token Repository.
 */
@Service
public class AuthenticationService {

	@Autowired
	private TokenRepository tokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		this.tokenRepository.save(authenticationToken);
	}

	public AuthenticationToken getToken(User user) {
		return this.tokenRepository.findByUser(user);
	}

	public User getUser(String token) {
		AuthenticationToken authenticationToken = this.tokenRepository.findByToken(token);
		if (authenticationToken == null) {
			return null;
		}
		return authenticationToken.getUser();
	}

	public void authenticate(String token) {
		if (token == null) {
			throw new AuthenticationFailException("Token not found");
		}

		if (getUser(token) == null) {
			throw new AuthenticationFailException("Invalid Token");
		}
	}
}
