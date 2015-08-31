package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PEOPLE database table.
 * 
 */
@Entity
@Table(name="People", schema="testDB")
@NamedQuery(name="People.findAll", query="SELECT p FROM People p")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String emailaddress;

	private String firstname;

	private String fullname;

	private String lastname;

	private String positions;

	private String streetaddress;

	private String title;

	private String zipcode;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private Company company;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;

	public People() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPositions() {
		return this.positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getStreetaddress() {
		return this.streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}