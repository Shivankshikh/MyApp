package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public String hello() {
		return "Hello";
	}

	@GetMapping("/employees")
	public List<Employee> listEmployees() {
		return empService.employeeList();
	}

	@GetMapping("/employees/{empId}")
	public Employee getEmployee(@PathVariable int empId) {
		return empService.findById(empId);
	}

	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee thEmployee) {
		return empService.addEmployee(thEmployee);
	}

	@PutMapping("/employees/{empId}")
	public String updateEmployee(@PathVariable int empId, @RequestBody Employee thEmployee) {
		return empService.updateEmployee(empId, thEmployee);
	}

	@DeleteMapping("/employees/{empId}")
	public String deleteEmployee(@PathVariable int empId) {
		return empService.deleteById(empId);
	}

}
