package org.tat.api.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "RESOURCE")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "PUBLICATION")
	private String publication;

	@Column(name = "year")
	private int year;

	@Column(name = "type")
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "Resource_Mapping", joinColumns = { @JoinColumn(name = "resourceId") }, inverseJoinColumns = { @JoinColumn(name = "userId") })
	@JsonBackReference
	private User user;

	
	  @ManyToOne(optional=true)
	  
	  @JoinTable(name = "Resource_Mapping", joinColumns = {
	  @JoinColumn(name="resourceId") }, inverseJoinColumns = {
	  @JoinColumn(name="rackId") } )
	  @JsonBackReference
	  private Rack rack;
	 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * public Rack getRack() { return rack; }
	 * 
	 * public void setRack(Rack rack) { this.rack = rack; }
	 */

}
