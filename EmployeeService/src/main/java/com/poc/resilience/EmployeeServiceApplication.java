package com.poc.resilience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.poc.resilience.model.Address;
import com.poc.resilience.model.Department;
import com.poc.resilience.model.Employee;
import com.poc.resilience.service.EmployeeServiceImpl;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Employee employee1 = new Employee("100", "Gopal", new Department("10", "IT"),
				new Address("addressLine1", "addressLine2", "9876543210"));
		Employee employee2 = new Employee("101", "Ram", new Department("20", "Finance"),
				new Address("addressLine1", "addressLine2", "9876543210"));

		logger.info("Employees created :");
		logger.info("-------------------------------");
		
		employeeServiceImpl.addEmployee(employee1);
		employeeServiceImpl.addEmployee(employee2);


		employeeServiceImpl.employeeList.stream().forEach(employee -> logger.info(employee.toString()));
		
		logger.info("-------------------------------");
	}

}
