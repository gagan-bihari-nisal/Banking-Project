package com.bms.authservice.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Embeddable
public class Contact { 

	@NotEmpty(message = "Mobile Number cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.")
	private String phone;
	@NotEmpty(message = "State name cannot be left empty.")
	private String state;
	@NotEmpty(message = "City name cannot be left empty.")
	private String city;
	@NotEmpty(message = "District name cannot be left empty.")
	private String district;
	@NotEmpty(message = "Pincode cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid pincode.")
	private String pincode;
	
	
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(
			@NotEmpty(message = "Mobile Number cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.") String phone,
			@NotEmpty(message = "State name cannot be left empty.") String state,
			@NotEmpty(message = "City name cannot be left empty.") String city,
			@NotEmpty(message = "District name cannot be left empty.") String district,
			@NotEmpty(message = "Pincode cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid pincode.") String pincode) {
		super();
		this.phone = phone;
		this.state = state;
		this.city = city;
		this.district = district;
		this.pincode = pincode;
		
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
}
