package com.poc.resilience.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.poc.resilience.model.Employee;

public interface EmployeeService {

	ResponseEntity<Employee> addEmployee(Employee employee);

	ResponseEntity<Employee> getEmployeeById(String id);

	ResponseEntity<List<Employee>>  getEmployees();

	ResponseEntity<String> removeEmployee(String id);

	ResponseEntity<List<Employee>> getEmployeeByName(String name);

	ResponseEntity<List<Employee>> findByDepartment(String departmentName);
}
