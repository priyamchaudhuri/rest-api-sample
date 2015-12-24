package org.tat.api.library.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity  
@Table(name="STUDENT") 
@PrimaryKeyJoinColumn(name="ID")  
public class Student extends User{

	private Long rollNo;

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + "]";
	}


}
