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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "RESOURCE")
@JsonInclude(Include.NON_NULL)
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESOURCE_ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "PUBLICATION")
	private String publication;

	@Column(name = "YEAR")
	private int year;

	@Column(name = "TYPE")
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "RESOURCE_USER", joinColumns = { @JoinColumn(name = "RU_RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "RU_USER_ID") })
	@JsonBackReference
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "RESOURCE_RACK", joinColumns = { @JoinColumn(name = "RR_RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "RR_RACK_ID") })
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

	public Rack getRack() {
		return rack;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

}
