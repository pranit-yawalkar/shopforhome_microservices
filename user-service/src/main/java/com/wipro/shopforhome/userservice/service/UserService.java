package com.wipro.shopforhome.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.shopforhome.userservice.dto.ResponseDTO;
import com.wipro.shopforhome.userservice.dto.SigninDTO;
import com.wipro.shopforhome.userservice.dto.SignupDTO;
import com.wipro.shopforhome.userservice.exception.AuthenticationFailException;
import com.wipro.shopforhome.userservice.exception.CustomException;
import com.wipro.shopforhome.userservice.exception.ResourceNotFoundException;
import com.wipro.shopforhome.userservice.model.AuthenticationToken;
import com.wipro.shopforhome.userservice.model.User;
import com.wipro.shopforhome.userservice.repository.TokenRepository;
import com.wipro.shopforhome.userservice.repository.UserRepository;

/*
 * User Service class to define the business logic and interact with the 
 * User Repository.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private AuthenticationService authenticationService;

	@Transactional
	public ResponseDTO signup(SignupDTO signupDTO) {
		// check if user is already present
		if (userRepository.findByEmail(signupDTO.getEmail()) != null) {
			throw new CustomException("User already exists!");
		}

		// hash the password
		String encryptedPassword = this.passwordService.passwordEncoder().encode(signupDTO.getPassword());

		// save the user
		User user = new User();
		user.setFirstName(signupDTO.getFirstName());
		user.setLastName(signupDTO.getLastName());
		user.setEmail(signupDTO.getEmail());
		user.setPassword(encryptedPassword);
		user.setAddress(signupDTO.getAddress());
		user.setCity(signupDTO.getCity());
		user.setState(signupDTO.getState());
		user.setZipCode(signupDTO.getZipCode());
		user.setContactNumber(signupDTO.getContactNumber());
		user.setRole("user");
		this.userRepository.save(user);

		// create the token
		AuthenticationToken authenticationToken = new AuthenticationToken(user);

		this.authenticationService.saveConfirmationToken(authenticationToken);

		return new ResponseDTO("success", "User signed up successfully!", user.getRole());
	}

	public ResponseDTO signin(SigninDTO signinDTO) {
		// find user by email
		User user = this.userRepository.findByEmail(signinDTO.getEmail());

		if (user == null) {
			throw new AuthenticationFailException("User is not valid");
		}
		
		if(!this.passwordService.passwordEncoder().matches(signinDTO.getPassword(), user.getPassword())) {
			throw new AuthenticationFailException("Invalid Credentials");
		} 

		// if password matches then retrieve the token
		AuthenticationToken token = authenticationService.getToken(user);

		if (token == null) {
			throw new CustomException("Token is not present");
		}
		return new ResponseDTO("success", token.getToken(), user.getRole());
	}

	public List<User> getAllUsers(String role) {
		if(role.equals("admin")) {
			return this.userRepository.findAll();
		} else {
			throw new CustomException("Access Denied");
		}
	}

	public User getUserById(Long userId, String role) {
		if(role.equals("admin")) {
			return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
		} else {
			throw new CustomException("Access Denied");
		}
	}
	
	public User getUserByToken(String token) {
		return this.authenticationService.getUser(token);
	}
	
	public User updateUser(Long userId, String role, User user) {
		if(role.equals("admin")) {
			User updateUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setEmail(user.getEmail());
			updateUser.setContactNumber(user.getContactNumber());
			updateUser.setAddress(user.getAddress());
			updateUser.setCity(user.getCity());
			updateUser.setState(user.getState());
			updateUser.setZipCode(user.getZipCode());
			updateUser.setRole(user.getRole());
			return this.userRepository.save(updateUser);
		} else {
			throw new CustomException("Access Denied");
		}
	}
	
	@Transactional
	public void deleteUser(Long id, String role) {
		if(role.equals("admin")) {
			User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
			this.tokenRepository.deleteByUser(user);
			this.userRepository.delete(user);
		} else {
			throw new CustomException("Access Denied");
		}
	}

}
