package com.poc.microservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.microservice.entity.Employee;
import com.poc.microservice.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class MicroServiceApplicationTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	EmployeeRepository employeeRepository;

	/*
	 * fetching employees  from repository
	 */
	@Test
	public void testGetAllEmployees() throws Exception {

		MvcResult andReturn = mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String actualString = andReturn.getResponse().getContentAsString();
		assertTrue(andReturn.getResponse() != null);
		assertEquals(
				"[{\"id\":10,\"firstname\":\"venu\",\"lastname\":\"kota\"},{\"id\":20,\"firstname\":\"srikanth\",\"lastname\":\"polimera\"},{\"id\":30,\"firstname\":\"sai\",\"lastname\":\"kuruba\"},{\"id\":40,\"firstname\":\"kavitha\",\"lastname\":\"murali\"}]",
				actualString);

	}

	/*
	 * fetching a particular employee from repository using Id value
	 */
	@Test
	public void testGetEmployeeById() throws Exception {

		MvcResult andReturn = mockMvc.perform(get("/employees/40").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String actualString = andReturn.getResponse().getContentAsString();
		System.out.println(actualString);
		assertTrue(andReturn.getResponse() != null);
		assertEquals("{\"id\":40,\"firstname\":\"kavitha\",\"lastname\":\"Nataraj\"}", actualString);

	}

	/*
	 * Saving an employee
	 */
	@Test
	public void testSaveEmployee() throws Exception {
		Employee emp1 = new Employee();
		emp1.setFirstname("dhivya");
		emp1.setLastname("murali");
		emp1.setId(50);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee").content(objectMapper.writeValueAsString(emp1))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertTrue(emp1 != null);
	}

	/*
	 * Deleting an employee by passing its id
	 */

	
	  @Test public void testDeleteEmployee() throws Exception {
	  
	  MvcResult andReturn = mockMvc
	  .perform(MockMvcRequestBuilders.delete("/employees/50").contentType(MediaType
	  .APPLICATION_JSON)) .andExpect(status().isOk()).andReturn();
	  assertTrue(andReturn != null); }
	 
}
