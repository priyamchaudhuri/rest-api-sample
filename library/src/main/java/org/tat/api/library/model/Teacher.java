package org.tat.api.library.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER")
@PrimaryKeyJoinColumn(name = "TEACHER_USR_ID")
public class Teacher extends Employee {
	@Column(name = "DEPARTMENT", nullable = false)
    private String Department;

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
}