package postmybuild.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	@Column(name = "builder_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "forename")
	private String forename;

	@Column(name = "surname")
	private String surname;

	@OneToMany
	@JoinColumn(name = "builder_id")
	private List<Address> addresses;

	public Long getBuilderId() {
		return id;
	}

	public void setBuilderId(Long builderId) {
		this.id = builderId;
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

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
