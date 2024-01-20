package postmybuild.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Address {

	public Address() {
	}

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

	public Builder getBuilder(){
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public Long getAddressId() {
		return id;
	}

	public void setAddressId(Long addressId) {
		this.id = addressId;
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
