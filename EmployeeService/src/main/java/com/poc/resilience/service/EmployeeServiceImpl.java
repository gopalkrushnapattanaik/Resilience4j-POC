package com.poc.resilience.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.resilience.model.Employee;

@RestController
@Service
@RequestMapping("/employee")
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	public List<Employee> employeeList = new ArrayList<>();

	@Override
	@PostMapping(value = "/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

		logger.info("addEmployee Invoked for  :: " + employee.toString());

		employeeList.add(employee);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return response;
	}

	@Override
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable("name") String name) {

		logger.info("getEmployeeByName :::::::");

		List<Employee> employees;
		employees = employeeList.stream().filter(emp -> emp.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());

		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		return response;
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		logger.info("getEmployees :::::::");

		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);

		return response;
	}

	@Override
	@PostMapping(value = "/remove/{id}")
	public ResponseEntity<String> removeEmployee(@PathVariable("id") String id) {

		logger.info("removeEmployee :::::::");

		employeeList.removeIf(emp -> emp.getId().equalsIgnoreCase(id));

		ResponseEntity<String> response = new ResponseEntity<String>("Removed Employee", HttpStatus.OK);

		return response;
	}

	@Override
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {

		logger.info("getEmployeeById :::::::");

		Employee employee;
		ResponseEntity<Employee> response = null;
		employee = employeeList.stream().filter(emp -> emp.getId().equalsIgnoreCase(id)).findFirst().get();

		response = new ResponseEntity<Employee>((employee), HttpStatus.OK);

		return response;
	}

	@Override
	@GetMapping(value = "/department/{department}")
	public ResponseEntity<List<Employee>> findByDepartment(@PathVariable("department") String departmentName) {

		logger.info("findByDepartment :::::::");

		List<Employee> employees;

		employees = employeeList.stream().filter(emp -> emp.getDepartment().getName().equalsIgnoreCase(departmentName))
				.collect(Collectors.toList());

		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		return response;
	}

}