package com.voongc.postmybuild.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//a builder has one address
@Entity
public class Builder {

	public Builder() {
	}

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

	public Long getBuilderId() {
		return id;
	}

	public void setBuilderId(Long builderId) {
		this.id = builderId;
	}

	public void addAddresses(Address address){
		this.addresses.add(address);
		address.setBuilder(this);
	}

	public String getBuilderName() {
		return name;
	}

	public void setBuilderName(String builderName) {
		this.name = builderName;
	}

	public String getBuilderForename() {
		return forename;
	}

	public void setBuilderForename(String builderForename) {
		this.forename = builderForename;
	}

	public String getBuilderSurname() {
		return surname;
	}

	public void setBuilderSurname(String builderSurname) {
		this.surname = builderSurname;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses){
		this.addresses = addresses;
	}

}
