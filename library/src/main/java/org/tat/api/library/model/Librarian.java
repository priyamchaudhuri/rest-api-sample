package org.tat.api.library.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;

@DiscriminatorValue("LIBRARIAN") 
@MappedSuperclass
public class Librarian extends Employee{

}
