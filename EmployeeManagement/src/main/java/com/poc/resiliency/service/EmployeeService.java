package com.poc.resiliency.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.resiliency.model.Employee;

@RestController
@RequestMapping("/employee/manage")
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeServiceManager employeeService;

	@GetMapping(value = "/get")
	public List<Employee> getEmployees() {
		logger.info("getEmployees :::::::");

		return employeeService.getEmployees();
	}

	@GetMapping(value = "/get/name")
	public List<Employee> getEmployeesByName(String name) {
		logger.info("getEmployeesByName :::::::");

		return employeeService.getEmployeesByName(name);
	}
	
	public Employee addEmployee(@RequestBody Employee emp ) {
		
		return employeeService.addEmployee(emp);
	}

}
