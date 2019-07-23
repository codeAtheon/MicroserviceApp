package com.poc.microservice.service;

import java.util.List;

import com.poc.microservice.entity.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(int id);

	public void saveOrUpdate(Employee employee);

	public void delete(int id);

}
