package org.tat.api.library.model;

import java.util.Set;

public class Shelf {

	private int id;

	private String location;

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

}