package com.bms.authservice.model;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class CustomerDTO {

	@NotEmpty(message = "Role cannot be left empty.")
	private String role;
	@NotEmpty(message = "First Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.")
	private String firstName; 
	@NotEmpty(message = "Last Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.")
	private String lastName;
	@Pattern(regexp = "MALE|FEMALE|OTHERS", message = "Gender can only accept 'MALE','FEMALE' or 'OTHERS'.")
	@NotEmpty(message = "Gender cannot be left empty.")
	private String gender;
	@NotEmpty(message = "DOB cannot be left empty.")
	private String dob;
	@NotEmpty(message = "Father Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Father Name must be between 3 and 50.")
	private String fatherName;

	@Valid
	private Contact contact;
	@NotEmpty(message = "Username cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of User Name must be between 3 and 50.")
	private String username;
	@NotEmpty(message = "Password cannot be left empty.")
	private String password;

	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(String role, String firstName, String lastName, String gender, String dob, String fatherName,
			Contact contact, String username, String password) {
		super();
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.fatherName = fatherName;
		this.contact = contact;
		this.username = username;
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) { 
		this.dob = dob;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
