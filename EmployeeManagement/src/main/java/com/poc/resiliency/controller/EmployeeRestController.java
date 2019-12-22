package com.poc.resiliency.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.resiliency.model.Employee;
import com.poc.resiliency.service.EmployeeService;


@RestController
@RequestMapping("/manageEmployee")
public class EmployeeRestController {

	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/getAll")
	public CompletableFuture<List<Employee>> getEmployees() {
		logger.info("getEmployees :::::::");

		return employeeService.getEmployees();
	}

	@GetMapping(value = "/get/name")
	public CompletableFuture<List<Employee>> getEmployeesByName(String name) {
		logger.info("getEmployeesByName :::::::");

		return employeeService.getEmployeesByName(name);
	}
	
	@PostMapping(value = "/add")
	public CompletableFuture<Employee> addEmployee(@RequestBody Employee emp ) {
		
		return employeeService.addEmployee(emp);
	}

	
	
}
