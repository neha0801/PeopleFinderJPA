package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the LOCATION database table.
 * 
 */
@Entity
@Table(name="Location", schema="testDB")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOCATION_ID")
	private long locationId;

	private String city;

	private String states;

	//bi-directional many-to-one association to People
	@OneToMany(mappedBy="location")
	private List<People> peoples;

	public Location() {
	}

	public long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStates() {
		return this.states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public List<People> getPeoples() {
		return this.peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
	}

	public People addPeople(People people) {
		getPeoples().add(people);
		people.setLocation(this);

		return people;
	}

	public People removePeople(People people) {
		getPeoples().remove(people);
		people.setLocation(null);

		return people;
	}

}