package com.poc.microservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.microservice.entity.Employee;
import com.poc.microservice.repository.EmployeeRepository;
import com.poc.microservice.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee -> employees.add(employee));
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void saveOrUpdate(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}

}
