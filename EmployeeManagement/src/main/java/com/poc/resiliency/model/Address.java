package com.poc.resiliency.model;

public class Address {
	
	private String addressLine1;
	private String addressLine2;
	private String mobileNumber;
	
	public Address(String addressLine1, String addressLine2, String mobileNumber) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.mobileNumber = mobileNumber;
	}
	public Address() {
	}
	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", mobileNumber="
				+ mobileNumber + "]";
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
