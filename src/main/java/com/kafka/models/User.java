package com.kafka.models;

public class User {
	private String firstName;
	private String lastName;
	private int age;
	private String phoneNumber;
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "First Name: " + this.firstName + ", Last Name: " + this.lastName + ", Age: " + this.age
				+ ", Phone Number: " + this.phoneNumber + ", Email: " + this.email;
	}
}
