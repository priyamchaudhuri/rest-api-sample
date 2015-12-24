package org.tat.api.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ADDRESS")
@JsonInclude(Include.NON_NULL)
public class Address {

	@Id
	@JsonIgnore
    private String addressId;
    




	@Column(name = "ADDRESSLINE1", nullable = false)
    private String addressLine1;
    
    @Column(name = "ADDRESSLINE2", nullable = false)
    private String addressLine2;
    
    @Column(name = "ADDRESSLINE3", nullable = false)
    private String addressLine3;
    
    @Column(name = "CITY", nullable = false)
    private String city;
    
    @Column(name = "STATE", nullable = false)
    private String state;
    
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    
  

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
}

