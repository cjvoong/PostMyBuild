package com.voongc.postmybuild.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//a builder has one address
@Data
@NoArgsConstructor
@Entity
public class Builder {

	public Builder(String name, String forename, String surname, List<Address> addresses) {
		super();
		this.name = name;
		this.forename = forename;
		this.surname = surname;
		this.addresses = addresses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String forename;
	private String surname;

	@OneToMany(mappedBy = "builder", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Address> addresses;

	public void addAddresses(Address address){
		this.addresses.add(address);
		address.setBuilder(this);
	}
}
