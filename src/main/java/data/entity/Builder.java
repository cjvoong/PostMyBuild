package data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Builder {

	@javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String builderName;
	private String builderForename;
	private String builderSurname;
	private Address Address;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getBuilderName() {
		return builderName;
	}
	public void setBuilderName(String builderName) {
		this.builderName = builderName;
	}
	public String getBuilderForename() {
		return builderForename;
	}
	public void setBuilderForename(String builderForename) {
		this.builderForename = builderForename;
	}
	public String getBuilderSurname() {
		return builderSurname;
	}
	public void setBuilderSurname(String builderSurname) {
		this.builderSurname = builderSurname;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	
}
