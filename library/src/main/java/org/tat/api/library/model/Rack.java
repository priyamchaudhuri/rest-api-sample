package org.tat.api.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "RACK")
@JsonInclude(Include.NON_NULL)
public class Rack implements Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RACK_ID")
	private int rackId;

	@Column(name = "NUMBER")
	private int number;

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RACK_SHELF_ID")
	private Shelf shelf;*/

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "RESOURCE_RACK", joinColumns = { @JoinColumn(name = "RR_RACK_ID") }, inverseJoinColumns = { @JoinColumn(name = "RR_RESOURCE_ID") })
	@JsonManagedReference
	private Set<Resource> resource;

	public Set<Resource> getResource() {
		return resource;
	}

	public void setResource(Set<Resource> resource) {
		this.resource = resource;
	}

	public int getRackId() {
		return rackId;
	}

	public void setRackId(int rackId) {
		this.rackId = rackId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Rack [rackId=" + rackId + ", number=" + number + ", resource="
				+ resource + "]";
	}

	/*public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}*/

	/*@Override
	public String toString() {
		return "Rack [rackId=" + rackId + ", number=" + number + ", shelf="
				+ shelf + ", resource=" + resource + "]";
	}*/
	
	
	
}
