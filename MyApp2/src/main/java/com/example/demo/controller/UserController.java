package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRequest;
import com.example.demo.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "jwtToken") })

	public List<User> listUsers() {
		return userServiceImpl.listUser();
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/users/{id}")
	public User getOne(@PathVariable Long id) {
		return userServiceImpl.findById(id);

	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/users/name/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userServiceImpl.getUserByUsername(username);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return userServiceImpl.deleteUser(userId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/users")
	public User saveUser(@RequestBody UserRequest userRequest) {
		return userServiceImpl.addUser(userRequest);
	}
}
