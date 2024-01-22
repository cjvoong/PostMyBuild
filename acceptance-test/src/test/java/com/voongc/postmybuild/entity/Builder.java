package com.voongc.postmybuild.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//a builder has one address
@Data
@NoArgsConstructor
public class Builder {

	public Builder(String name, String forename, String surname, List<Address> addresses) {
		super();
		this.name = name;
		this.forename = forename;
		this.surname = surname;
		this.addresses = addresses;
	}

	private Long id;
	private String name;
	private String forename;
	private String surname;
	private List<Address> addresses;

	public void addAddresses(Address address){
		this.addresses.add(address);
		address.setBuilder(this);
	}
}
