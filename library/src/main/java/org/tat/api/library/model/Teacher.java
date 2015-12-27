package org.tat.api.library.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;

@DiscriminatorValue("TEACHER")
@MappedSuperclass
public class Teacher extends Employee {

}