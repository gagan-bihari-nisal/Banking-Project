package com.bms.fdservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FdDto {

	@Min(value = 10000, message = "Principal must be equal or greater than 10000")
	@Max(value = 500000, message = "Principal must be equal or less than 500000")
	private Double principal;

	@Min(value = 1, message = "Tenure must be equal or greater than 1")
	@Max(value = 5, message = "Tenure must be equal or less than 5")
	private int tenure;

	public FdDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FdDto(Double principal, int tenure) {
		super();
		this.principal = principal;
		this.tenure = tenure;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

}
