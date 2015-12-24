package org.tat.api.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SHELF")
public class Shelf {

	  
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="ID")
	  private int id;
	  

	   @Column(name = "LOCATION")
	   private String location;

	   
	  @OneToMany
	  @JoinColumn(name="rackId")     
	  private Set<Rack> rack;
	  
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


		public Set<Rack> getRack() {
			return rack;
		}


		public void setRack(Set<Rack> rack) {
			this.rack = rack;
		}


	  
}
