package com.bms.beneficiaryservice.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BfDto {
	@NotEmpty(message = "First Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.")
	private String firstName;
	@NotEmpty(message = "Last Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.")
	private String lastName;
	@Pattern(regexp = "PARENT|CHILDREN|SIBLINGS|OTHERS", message = "Relation can only accept 'PARENT', 'CHILDREN', 'SIBLINGS' or 'OTHERS'.")
	@NotEmpty(message = "Relation cannot be left empty.")
	private String relation;
	@Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.")
	private String phone;
	@DecimalMin(value = "1.0", message = "Percent must be equal or greater than 1")
	@DecimalMax(value ="100.0", message = "Percent must be equal or less than 100")
	private Double percent;
	public BfDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BfDto(
			@NotEmpty(message = "First Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.") String firstName,
			@NotEmpty(message = "Last Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.") String lastName,
			@Pattern(regexp = "PARENT|CHILDREN|SIBLINGS|OTHERS", message = "Relation can only accept 'PARENT', 'CHILDREN', 'SIBLINGS' or 'OTHERS'.") @NotEmpty(message = "Relation cannot be left empty.") String relation,
			@Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.") String phone,
			@DecimalMin(value = "1.0", message = "must be equal or greater than 1") @DecimalMax(value = "100.0", message = "must be equal or less than 100") Double percent) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.relation = relation;
		this.phone = phone;
		this.percent = percent;
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	

}
