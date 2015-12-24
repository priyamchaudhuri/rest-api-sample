package org.tat.api.library.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity  
@Table(name="EMPLOYEE") 
@DiscriminatorValue("Teacher") 
public class Teacher extends Employee{

}