package org.tat.api.library.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity  
@DiscriminatorValue("Teacher") 
public class Teacher extends Employee{

}
