package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRequest;
import com.example.demo.exception.InvalidUserCredentialsException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.UserServiceImpl;

@RestController
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody LoginUser loginUser) {
		final Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		} catch (BadCredentialsException e) {

			throw new InvalidUserCredentialsException("Inalid credentials");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtTokenProvider.generateToken(authentication);

	}

	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody UserRequest userRequest) {
		User user = userServiceImpl.getUserByUsername(userRequest.getUsername());
		if (user == null) {
			userServiceImpl.addUser(userRequest);
			return new ResponseEntity<String>("Successfully added", HttpStatus.OK);
		}

		else {
			return new ResponseEntity<String>("Already exists", HttpStatus.CONFLICT);
		}

	}

}
