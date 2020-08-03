package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeDaoRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDaoRepository repo;

	@Override
	public List<Employee> employeeList() {

		return repo.findAll();
	}

	@Override
	public Employee findById(int empId) {
		Optional<Employee> result = repo.findById(empId);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new EmployeeNotFoundException("Employee does not exists");
		}

		return employee;
	}

	@Override
	public String addEmployee(Employee newEmployee) {
		newEmployee.setId(0);
		repo.saveAndFlush(newEmployee);
		return "Successfully added";
	}

	@Override
	public String updateEmployee(int empId, Employee theEmployee) {
		Employee employee = repo.getOne(empId);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee does not exists");
		} else {
			employee.setDesignation(theEmployee.getDesignation());
		}
		return "Successfully updated";
	}

	@Override
	public String deleteById(int empId) {
		Employee employee = repo.getOne(empId);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee does not exists");
		}

		repo.deleteById(empId);
		return "Successfully deleted";
	}

}
