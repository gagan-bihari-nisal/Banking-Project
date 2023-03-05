package com.bms.loanservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loans")
public class LoanDao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long loan_id;
	private String username;
	private String type;
	private Double principal;
	private Double rate;
	private int tenure;
	private LocalDate createdOn;
	
	
}
