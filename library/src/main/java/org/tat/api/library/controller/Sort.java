package org.tat.api.library.controller;


public class Sort {
	private String field;
	private  SortType sortType;
	
	public Sort() {
		// TODO Auto-generated constructor stub
	}
	
	public Sort(String field, SortType sortType) {
		this.field = field;
		this.sortType = sortType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
	
	
}