package com.poc.resiliency.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.resiliency.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeServiceManager employeeServiceManager;

	public CompletableFuture<List<Employee>> getEmployees() {
		
		return employeeServiceManager.getEmployees();
	}

	public CompletableFuture<List<Employee>> getEmployeesByName(String name) {
		return employeeServiceManager.getEmployeesByName(name);
	}

	public CompletableFuture<Employee> addEmployee(Employee emp) {
		return employeeServiceManager.addEmployee(emp);
	}
}
