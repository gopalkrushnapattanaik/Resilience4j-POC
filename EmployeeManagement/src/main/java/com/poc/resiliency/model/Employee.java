package com.poc.resiliency.model;

public class Employee {

	private String id;
	private String name;
	private Department department;
	private Address address;
	
	
	public Employee(String id, String name, Department department, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.address = address;
	}
	
	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department.toString() + ", address=" + address.toString() + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
