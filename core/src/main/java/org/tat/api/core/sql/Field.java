package org.tat.api.core.sql;

/**
 * Represents a database column
 *
 */
public class Field {

	private String dbColumn;
	private String identifier;
	private ColumnType dataType;
	private String tableName;

	public Field(String dbColumn, String identifier, ColumnType dataType,
			String tableName) {
		super();
		this.dbColumn = dbColumn;
		this.identifier = identifier;
		this.dataType = dataType;
		this.tableName = tableName;
	}

	public String getDbColumn() {
		return dbColumn.toUpperCase();
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getTableName() {
		return tableName.toUpperCase();
	}

	public ColumnType getDataType() {
		return dataType;
	}

	/**
	 * @return column name with the format {TABLE_NAME}.{COLUMN_NAME}
	 */
	public String getQualifiedName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getTableName()).append(Criteria.DOT).append(getDbColumn());
		return sb.toString();
	}
	
	/**
	 * @return column alias with the format {TABLE_NAME}_{COLUMN_NAME}
	 */
	public String getAliasName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getTableName()).append(Criteria.UNDERSCORE).append(getDbColumn());
		return sb.toString();
	}

}
