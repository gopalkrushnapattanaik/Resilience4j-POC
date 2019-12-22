package com.poc.resiliency.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.resiliency.model.Employee;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeServiceManager {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceManager.class);

	private RestTemplate restTemplate = new RestTemplate();

	private final String URL = "http://localhost:8080/employee/";

	@CircuitBreaker(name = "EmployeeService_AddEmployee", fallbackMethod = "addEmployeefallBack")
	@Bulkhead(name = "EmployeeService_AddEmployee", type = Type.THREADPOOL)
	@RateLimiter(name = "EmployeeService_AddEmployee")
	@Retry(name = "EmployeeService_AddEmployee", fallbackMethod = "addEmployeefallBack")
	public CompletableFuture<Employee> addEmployee(Employee employee) {

		logger.info("addEmployee ::");

		CompletableFuture<Employee> future = CompletableFuture
				.supplyAsync(() -> restTemplate.postForEntity(URL + "/add", employee, Employee.class).getBody());

		return future;

	}

	@CircuitBreaker(name = "EmployeeService_GetEmployees", fallbackMethod = "getEmployeesFallBack")
	@Bulkhead(name = "EmployeeService_GetEmployees", type = Type.THREADPOOL)
	@RateLimiter(name = "EmployeeService_GetEmployees")
	@Retry(name = "EmployeeService_GetEmployees", fallbackMethod = "getEmployeesFallBack")
	public CompletableFuture<List<Employee>> getEmployees() {

		logger.info("getEmployees ::");

		CompletableFuture<List<Employee>> future = CompletableFuture
				.supplyAsync(() -> restTemplate.getForEntity(URL, List.class).getBody());

		return future;
	}

	@CircuitBreaker(name = "EmployeeService_getEmpByName", fallbackMethod = "getEmployeesByNameFallBack")
	@Bulkhead(name = "EmployeeService_getEmpByName", type = Type.THREADPOOL)
	@RateLimiter(name = "EmployeeService_getEmpByName")
	@Retry(name = "EmployeeService_getEmpByName", fallbackMethod = "getEmployeesByNameFallBack")
	public CompletableFuture<List<Employee>> getEmployeesByName(String name) {

		logger.info("getEmployeesByName ::");

		CompletableFuture<List<Employee>> future = CompletableFuture
				.supplyAsync(() -> restTemplate.getForEntity(URL + "/name/" + name, List.class).getBody());

		return future;

	}

	public CompletableFuture<Employee> addEmployeefallBack(Employee employee, Throwable t) {

		logger.error("Inside circuit breaker addEmployeefallBack, cause - {}", t.toString());

		return CompletableFuture.completedFuture(null);
	}

	public CompletableFuture<List<Employee>> getEmployeesFallBack(Throwable t) {

		logger.error("Inside getEmployeesFallBack, cause - {}", t.toString());

		return CompletableFuture.completedFuture(null);

	}

	public CompletableFuture<List<Employee>> getEmployeesByNameFallBack() {

		logger.info("getEmployeesByNameFallBack ::");

		return CompletableFuture.completedFuture(null);

	}

}
