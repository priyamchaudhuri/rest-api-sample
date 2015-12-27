package org.tat.api.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "SHELF")
@JsonInclude(Include.NON_NULL)
public class Shelf {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHELF_ID")
	private int id;

	@Column(name = "LOCATION")
	private String location;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "RACK_ID")
	private Set<Rack> racks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Rack> getRacks() {
		return racks;
	}

	public void setRacks(Set<Rack> racks) {
		this.racks = racks;
	}

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", location=" + location + ", racks="
				+ racks + "]";
	}

}