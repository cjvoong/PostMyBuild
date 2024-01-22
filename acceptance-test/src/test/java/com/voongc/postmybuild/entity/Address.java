package com.voongc.postmybuild.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

	public Address(String houseNo, String street, String county, String country, String postcode) {
		super();
		this.houseNo = houseNo;
		this.street = street;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}

	private Long id;
	private String houseNo;
	private String street;
	private String county;
	private String country;
	private String postcode;
	private Builder builder;
}
