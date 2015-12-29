package org.tat.api.library.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "LIBRARIAN")
@PrimaryKeyJoinColumn(name = "LIBRARIAN_USER_ID")
public class Librarian extends Employee{
	@Column(name = "FAV_BOOK", nullable = false)
	private String favBook;
public String getFavBook() {
		return favBook;
	}

	public void setFavBook(String favBook) {
		this.favBook = favBook;
	}


}
