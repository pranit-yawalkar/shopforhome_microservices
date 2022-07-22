package com.wipro.shopforhome.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.shopforhome.userservice.dto.ResponseDTO;
import com.wipro.shopforhome.userservice.dto.SigninDTO;
import com.wipro.shopforhome.userservice.dto.SignupDTO;
import com.wipro.shopforhome.userservice.model.User;
import com.wipro.shopforhome.userservice.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseDTO signup(@RequestBody SignupDTO signupDTO) {
		return this.userService.signup(signupDTO);
	}

	@PostMapping("/signin")
	public ResponseDTO signin(@RequestBody SigninDTO signinDTO) {
		return this.userService.signin(signinDTO);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = this.userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/getuser")
	public ResponseEntity<User> getUserByToken(@RequestParam("token") String token) {
		User user = this.userService.getUserByToken(token);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
