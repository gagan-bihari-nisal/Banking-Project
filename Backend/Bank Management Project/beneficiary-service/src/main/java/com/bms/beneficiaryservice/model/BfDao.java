package com.bms.beneficiaryservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="beneficiaries")
public class BfDao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bf_id;
	private String username;
	private String firstName;
	private String lastName;
	private String relation;
	private String phone;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate addedOn;
	private Double percent;
	
	@ManyToOne(targetEntity=DAOCustomer.class)
	@JoinColumn(name="customerId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private DAOCustomer customer;
	public BfDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BfDao(Long bf_id, String username, String firstName, String lastName, String relation, String phone,
			LocalDate addedOn, Double percent, DAOCustomer customer) {
		super();
		this.bf_id = bf_id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.relation = relation;
		this.phone = phone;
		this.addedOn = addedOn;
		this.percent = percent;
		this.customer = customer;
	}

	public DAOCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(DAOCustomer customer) {
		this.customer = customer;
	}

	public Long getBf_id() {
		return bf_id;
	}
	public void setBf_id(Long bf_id) {
		this.bf_id = bf_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public LocalDate getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
}
