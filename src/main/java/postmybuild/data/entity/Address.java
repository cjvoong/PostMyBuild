package postmybuild.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column(name = "address_id")
	private Long id;

	@Column(name = "house_no")
	private String houseNo;

	@Column(name = "street")
	private String street;

	@Column(name = "county")
	private String county;

	@Column(name = "country")
	private String country;

	@Column(name = "postcode")
	private String postcode;

	@ManyToOne
	@JoinColumn(name = "builder_id")
	private Builder builder;

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
