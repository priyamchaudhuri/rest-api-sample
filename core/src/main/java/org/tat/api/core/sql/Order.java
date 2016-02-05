package org.tat.api.core.sql;

/**
 * Enum listing the sort orders
 *
 */
public enum Order {

	ASC(""),DESC("-");
	
	private String value;

	private Order(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
