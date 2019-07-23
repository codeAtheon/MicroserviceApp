package com.poc.microservice.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.poc.microservice.entity.Employee;
import com.poc.microservice.repository.EmployeeRepository;
import com.poc.microservice.service.impl.EmployeeServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	public void init() {
		MockitoAnnotations.initMocks(this);
		
	}
	/*
	 Checks for the Null pointer Exception
	 */
	@Test(expected=NullPointerException.class)
	public void testGetAllEmployeesForNullPointerException() {
		Mockito.when(employeeRepository.findAll()).thenReturn(null);
        employeeServiceImpl.getAllEmployees();
        verify(employeeRepository,times(1)).findAll();
    }
	
	/*
	 Checks for the Empty Result Set
	 */
	@Test
	public void testGetAllEmployeesEmptyResultSet() {
		Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>());
	    List<Employee> empList =employeeServiceImpl.getAllEmployees();
		assertTrue(empList!=null);
		assertTrue(empList.isEmpty());
		verify(employeeRepository,times(1)).findAll();
		
	}
	
	/*
	 Checks for the size of the Result Set
	 */
	@Test
	public void testGetAllEmployeesForSuccess() {
		List<Employee> employees=new ArrayList<Employee>();
		Employee emp1=new Employee();
		emp1.setFirstname("emp1firstname");
		emp1.setLastname("emp1lastname");
		Employee emp2=new Employee();
		emp2.setFirstname("emp2firstname");
		emp2.setLastname("emp2lastname");
		employees.add(emp1);
		employees.add(emp2);
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> employeesList=employeeServiceImpl.getAllEmployees();
		assertTrue(employeesList!=null);
		assertTrue(!employeesList.isEmpty());
		assertEquals("Employee List Size",2, employeesList.size());
		verify(employeeRepository,times(1)).findAll();
}
	
	/*
	 Checks for the particular Employee by passing ID
	 */
	@Test
	public void testGetEmployeeByID() {
		
		Employee emp = new Employee();
		emp.setFirstname("Kavitha");
		emp.setLastname("Murali");
		emp.setId(1);
		Mockito.when(employeeRepository.findById(1)).thenReturn(emp);
		Employee empOne = employeeServiceImpl.getEmployeeById(1);
		assertTrue(empOne!=null);
		assertEquals("FirstName Validation","Kavitha", empOne.getFirstname());
		assertEquals("LastName validation","Murali", empOne.getLastname());
		verify(employeeRepository,times(1)).findById(1);
	}
	/*
	 Save operation 
	 */
	@Test 
    public void testSaveOrUpdate() {
	Employee employee=new Employee();
	employee.setId(2);
	employee.setFirstname("Dhivya");
	employee.setLastname("Murali");
	employeeServiceImpl.saveOrUpdate(employee);
	assertTrue(employee!=null);
	assertTrue(employee.getId()==2);
	verify(employeeRepository,times(1)).save(employee);
	
}
	
	@Test
	public void testDelete() {
		Employee employee=new Employee();
		employee.setId(2);
		employee.setFirstname("Dhivya");
		employee.setLastname("Murali");
		employeeServiceImpl.delete(2);
		assertTrue(employee!=null);
		verify(employeeRepository,times(1)).deleteById(2);
		
	}
}

