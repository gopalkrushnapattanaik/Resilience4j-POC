package com.poc.resiliency.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.resiliency.model.Employee;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeServiceManager {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceManager.class);

	private RestTemplate restTemplate = new RestTemplate();

	private final String URL = "http://localhost:8080/employee/";

	@CircuitBreaker(name = "addEmployee", fallbackMethod = "addEmployeefallBack")
	public Employee addEmployee(Employee employee) {

		logger.info("addEmployee ::");

		ResponseEntity<Employee> response = restTemplate.postForEntity(URL + "/add", employee, Employee.class);

		return response.getBody();

	}

	@Retry(name = "getEmployees", fallbackMethod = "getEmployeesFallBack")
	public List<Employee> getEmployees() {

		ResponseEntity<List> response = restTemplate.getForEntity(URL, List.class);
		List<Employee> employees = response.getBody();

		logger.info("getEmployees ::" + employees);
		return employees;
	}

	@Bulkhead(name = "getEmployeesByName", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "getEmployeesByNameFallBack")
	public List<Employee> getEmployeesByName(String name) {

		logger.info("getEmployeesByName ::");

		ResponseEntity<List> response = restTemplate.getForEntity(URL + "/name/" + name, List.class);
		List<Employee> employees = response.getBody();

		logger.info("getEmployees ::" + employees);
		return employees;

	}

	public Employee addEmployeefallBack(Employee employee, Throwable t) {
		
		logger.error("Inside circuit breaker addEmployeefallBack, cause - {}", t.toString());

		return null;
	}

	public List<Employee> getEmployeesFallBack(Throwable t) {
		
		logger.error("Inside getEmployeesFallBack, cause - {}", t.toString());


		return null;

	}

	public List<Employee> getEmployeesByNameFallBack() {

		logger.info("getEmployeesByNameFallBack ::");

		return null;

	}

}
