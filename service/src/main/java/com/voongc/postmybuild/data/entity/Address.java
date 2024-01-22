package com.voongc.postmybuild.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address {

	public Address(String houseNo, String street, String county, String country, String postcode) {
		super();
		this.houseNo = houseNo;
		this.street = street;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String houseNo;
	private String street;
	private String county;
	private String country;
	private String postcode;

	@ManyToOne
	@JsonBackReference
	private Builder builder;

//	@ManyToOne
//	@JsonBackReference
//	private User user;

}
