package org.tat.api.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "STUDENT_USR_ID")
public class Student extends User {

	@Column(name = "ROLL_NO", nullable = false)
	private Long rollNo;

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}


}
