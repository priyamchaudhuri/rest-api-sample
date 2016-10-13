package org.tat.api.core.sql;

/**
 * Enumeration of the types of operators allowed for a where clause
 *
 */
public enum Operator {
	ASTERISK("*"), COMMA(","), TILD("~"), GREATER(">"), LESS("<"), NOT("!"),EQUAL("=");
	private String value;

	private Operator(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
