package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRequest;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl {
	@Autowired
	UserRepository repository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public List<User> listUser() {
		return repository.findAll();
	}

	public User findById(Long id) {
		return repository.findById(id).get();
	}

	public User getUserByUsername(String username) {
		return repository.findByUsername(username);
	}

	public String deleteUser(long id) {
		repository.deleteById(id);
		return "Successfully deleted";
	}

	public User addUser(UserRequest user) {
		User user2 = new User();
		user2.setUsername(user.getUsername());
		user2.setAge(user.getAge());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setSalary(user.getSalary());

		return repository.save(user2);

	}

}
