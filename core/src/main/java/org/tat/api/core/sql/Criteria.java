package org.tat.api.core.sql;

/**
 * Parent class for the different kinds of criteria in a query.
 *
 */
public class Criteria {

	private Field field;
	public static final String DOT = ".";
	public static final String SPACE = " ";
	public static final String UNDERSCORE = "_";

	public Criteria(Field field) {
		super();
		this.field = field;
	}

	public Field getField() {
		return field;
	}

}
