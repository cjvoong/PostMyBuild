package postmybuild.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Builder {

	public Builder(){
	}
	
	public Builder(String name, String forename, String surname, Address address) {
		super();
		this.name = name;
		this.forename = forename;
		this.surname = surname;
		this.address = address;
	}

	@javax.persistence.Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String forename;
	private String surname;
	
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="addressId")
	private Address address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
