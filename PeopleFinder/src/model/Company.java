package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COMPANY database table.
 * 
 */
@Entity
@Table(name="Company", schema="testDB")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long companyid;

	private String company;

	//bi-directional many-to-one association to People
	@OneToMany(mappedBy="company")
	private List<People> peoples;

	public Company() {
	}

	public long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<People> getPeoples() {
		return this.peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
	}

	public People addPeople(People people) {
		getPeoples().add(people);
		people.setCompany(this);

		return people;
	}

	public People removePeople(People people) {
		getPeoples().remove(people);
		people.setCompany(null);

		return people;
	}

}