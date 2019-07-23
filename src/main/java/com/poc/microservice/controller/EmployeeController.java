package com.poc.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.microservice.entity.Employee;
import com.poc.microservice.service.EmployeeService;

@RestController
public class EmployeeController {

	String str;
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public int saveEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return employee.getId();
	}

	@GetMapping(path = "/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable("id") int id) {
		employeeService.delete(id);
	}
	
	public void findbugTest(){
	System.out.println("FindBugs Testing Null"+str);
	}
}
