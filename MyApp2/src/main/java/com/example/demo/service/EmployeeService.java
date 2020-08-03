package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService {

	List<Employee> employeeList();

	Employee findById(int empId);

	String addEmployee(Employee newEmployee);

	String updateEmployee(int empId, Employee theEmployee);

	String deleteById(int empId);

}
