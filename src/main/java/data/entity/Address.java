package data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	public Address(){
		
	}
	
	
	public Address(String houseNo, String street, String county,
			String country, String postcode) {
		super();
		this.houseNo = houseNo;
		this.street = street;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
	}



	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String houseNo;
	private String street;
	private String county;
	private String country;
	private String postcode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
