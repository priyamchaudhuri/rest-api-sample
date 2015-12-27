package org.tat.api.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(Include.NON_NULL)
public abstract class User implements Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ADDRESS_ID")
	private Address address;

	@Column(name = "FIRST_NAME", nullable = false)
	private String fname;

	@Column(name = "LAST_NAME", nullable = false)
	private String lname;

	@Column(name = "JOINING_DATE", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonIgnore
	private LocalDate joiningDate;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "RESOURCE_USER", joinColumns = { @JoinColumn(name = "RU_USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "RU_RESOURCE_ID") })
	@JsonManagedReference
	private Set<Resource> resources;

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + id; result = prime * result + ((fname == null)
	 * ? 0 : fname.hashCode()); return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (!(obj instanceof User)) return
	 * false; User other = (User) obj; if (id != other.id) return false; if
	 * (fname == null) { if (other.fname != null) return false; } else if
	 * (!fname.equals(other.fname)) return false; return true; }
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}