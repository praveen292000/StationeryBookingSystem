package com.cg.sbs.dto.customer;

import javax.validation.constraints.NotBlank;


/*
 *
 * Author : G V V Nagendra Prasad
 * Description : This is Removecustomer DTO
 *
*/


public class RemoveCustomerRequest {

	@NotBlank
	private String id;

	public RemoveCustomerRequest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}