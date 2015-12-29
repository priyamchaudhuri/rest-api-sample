package org.tat.api.library.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "EMPLOYEE")
@PrimaryKeyJoinColumn(name = "EMP_USER_ID")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(Include.NON_NULL)
public class Employee extends User {

	@Column(name = "EMP_CODE")
	private Long empCode;

	public Long getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}



}
