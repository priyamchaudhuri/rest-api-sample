package org.tat.api.library.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="RACK")
public class Rack implements Owner{


	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="RACKID")
      private int rackId;
	  
	  @Column(name = "NUMBER")
      private int number;
	 
	  @ManyToOne
	  @JoinColumn(name="shelfId")     // inverse = false
	  private Shelf shelf;
	  
	   @OneToMany
	    @JoinTable(name = "Resource_Mapping",
	      joinColumns = {
	        @JoinColumn(name="RackId")           
	      },
	      inverseJoinColumns = {
	        @JoinColumn(name="resourceId")
	      }
	    )
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

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
}
