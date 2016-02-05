package org.tat.api.core.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.tat.api.core.exceptions.InvalidSearchInputException;
/**
 * Represents a search criteria
 *
 */
public class Search extends Criteria {

	private Operator operator;
	private String fieldName;
	private String input;
	private String pattern;
	private Object value;
	private Map<String, Object> namedParams;
	
	// regex for all the datatypes
	private static final String STRING_VALIDATION_PATTERN = "^(?!.*([*,!])\\1)[!//*]?[A-Za-z0-9//*//,@//+//-//$#]*[A-Za-z0-9//*@//+//-//$#]$";
	private static final String LONG_VALIDATION_PATTERN = "^(?!.*([*,!><~])\\1)[!//*//>//<]?[0-9//*//,//~]*[0-9//*]$";
	private static final String DOUBLE_VALIDATION_PATTERN = "^(?!.*([*!><.,])\\1)[!//*//>//<]?[0-9//*]*[//.]?[0-9//*]*[0-9//*]$";
	private static final String TIMESTAMP_VALIDATION_PATTERN = "^[<>]?(?:(?:31(-)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(-)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)\\d{2})$|^[<>]?(?:29(-)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^[<>]?(?:0?[1-9]|1\\d|2[0-8])(-)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)\\d{2})$";
	private static final String UNDERSCORE_ZERO = "_0";
	private static final String UNDERSCORE_ONE = "_1";

	private static final String LIKE_PATTERN = "{column} like :{column}";
	private static final String EQUAL_PATTERN = "{column} = :{column}";
	private static final String NOT_PATTERN = "{column} <> :{column}";
	private static final String IN_PATTERN = "{column} in (:{column})";
	private static final String DATE_BETWEEN_PATTERN = "TRUNC({column}) BETWEEN TO_DATE(:{column}_0, 'DD-MM-YYYY') AND TO_DATE(:{column}_1, 'DD-MM-YYYY')";
	private static final String DATE_GREATER_PATTERN = "TRUNC({column}) >  TO_DATE(:{column}, 'DD-MM-YYYY')";
	private static final String DATE_LESS_PATTERN = "TRUNC({column}) <  TO_DATE(:{column}, 'DD-MM-YYYY')";
	private static final String DATE_EQUAL_PATTERN = "TRUNC({column}) =  TO_DATE(:{column}, 'DD-MM-YYYY')";
	private static final String NUM_BETWEEN_PATTERN = "{column} BETWEEN :{column}_0 AND :{column}_1";
	private static final String NUM_GREATER_PATTERN = "{column} > :{column}";
	private static final String NUM_LESS_PATTERN = "{column} < :{column}";

	static Map<String, String> patternMap;

	static {
		String underscore = "_";
		patternMap = new HashMap<String, String>();
		patternMap.put(
				ColumnType.STRING.name().concat(underscore)
				.concat(Operator.ASTERISK.name()), LIKE_PATTERN);
		patternMap.put(
				ColumnType.STRING.name().concat(underscore)
				.concat(Operator.EQUAL.name()), EQUAL_PATTERN);
		patternMap.put(
				ColumnType.STRING.name().concat(underscore)
				.concat(Operator.NOT.name()), NOT_PATTERN);
		patternMap.put(
				ColumnType.STRING.name().concat(underscore)
				.concat(Operator.COMMA.name()), IN_PATTERN);
		patternMap.put(
				ColumnType.TIMESTAMP.name().concat(underscore)
				.concat(Operator.EQUAL.name()), DATE_EQUAL_PATTERN);
		patternMap.put(
				ColumnType.TIMESTAMP.name().concat(underscore)
				.concat(Operator.LESS.name()), DATE_LESS_PATTERN);
		patternMap.put(
				ColumnType.TIMESTAMP.name().concat(underscore)
				.concat(Operator.GREATER.name()), DATE_GREATER_PATTERN);
		patternMap.put(
				ColumnType.TIMESTAMP.name().concat(underscore)
				.concat(Operator.TILD.name()), DATE_BETWEEN_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.ASTERISK.name()), LIKE_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.EQUAL.name()), EQUAL_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.NOT.name()), NOT_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.COMMA.name()), IN_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.GREATER.name()), NUM_GREATER_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.LESS.name()), NUM_LESS_PATTERN);
		patternMap.put(
				ColumnType.LONG.name().concat(underscore)
				.concat(Operator.TILD.name()), NUM_BETWEEN_PATTERN);

		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.ASTERISK.name()), LIKE_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.EQUAL.name()), EQUAL_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.NOT.name()), NOT_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.COMMA.name()), IN_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.GREATER.name()), NUM_GREATER_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.LESS.name()), NUM_LESS_PATTERN);
		patternMap.put(
				ColumnType.DOUBLE.name().concat(underscore)
				.concat(Operator.TILD.name()), NUM_BETWEEN_PATTERN);
	}

	public Search(String fieldName, String input, Field field) throws InvalidSearchInputException {
		super(field);
		this.fieldName = fieldName;
		this.input = input;

		if (!this.validateInput()) {
			throw new InvalidSearchInputException(input);
		} else {
			identifyAndExtractValues();
			castParams();
			createPattern();
			formNamedParams();
		}
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * This method checks if the input string is valid 
	 * @return true if the input is valid.
	 */
	private boolean validateInput() {
		Pattern excludePattern;
		boolean result = Boolean.FALSE;

		ColumnType columnType = this.getField().getDataType();

		result = validateStartingOperator(input,operator);
		if(result){
			if (columnType.equals(ColumnType.STRING)) {
				excludePattern = Pattern.compile(STRING_VALIDATION_PATTERN);
				result = excludePattern.matcher(input).find();
			} else if (columnType.equals(ColumnType.LONG)) {
				excludePattern = Pattern.compile(LONG_VALIDATION_PATTERN);
				result = excludePattern.matcher(input).find();
			} else if (columnType.equals(ColumnType.DOUBLE)) {
				excludePattern = Pattern.compile(DOUBLE_VALIDATION_PATTERN);
				if (input.indexOf(Operator.TILD.getValue()) >= 0) {
					result = validateBetweenValues(excludePattern);
				} else if(input.indexOf(Operator.COMMA.getValue()) >= 0){
					List<String> values = Arrays.asList(input.split(Operator.COMMA.getValue()));
					if(input.endsWith(Operator.COMMA.getValue())){
						return false;
					}
					for(String value:values){
						result = excludePattern.matcher(value).find();
						if (!result)
							return result;
					}
				}else {
					result = excludePattern.matcher(input).find();
				}
			} else if (columnType.equals(ColumnType.TIMESTAMP)) {
				excludePattern = Pattern.compile(TIMESTAMP_VALIDATION_PATTERN);
				if (input.indexOf(Operator.TILD.getValue()) >= 0) {
					result = validateBetweenValues(excludePattern);
				} else {
					result = excludePattern.matcher(input).find();
				}
			}
		}
		if (result)
			result = validateMultipleOperators();


		return result;
	}

	private boolean validateBetweenValues(Pattern excludePattern) {
		String values[] = input.split(Operator.TILD.getValue());
		boolean result = excludePattern.matcher(values[0]).find();
		if (!result)
			return result;
		result = excludePattern.matcher(values[1]).find();
		return false;
	}

	private boolean validateStartingOperator(String input2, Operator operator) {
		if(input.startsWith(Operator.COMMA.getValue()) || input.startsWith(Operator.TILD.getValue())){
			return false;
		}
		return true;
	}

	/**
	 * @return true if the input contains only a single type of operator
	 */
	private boolean validateMultipleOperators() {

		int counter = 0;
		boolean result = true;
		for (Operator o : Operator.values()) {
			if (input.indexOf(o.getValue()) >= 0) {
				counter++;
			}
			if (counter > 1) {
				result = false;
				return result;
			}
		}

		return result;
	}

	/**
	 * Identifies the operator in the input and extracts the value to be filtered.
	 */
	private void identifyAndExtractValues() {

		for (Operator o : Operator.values()) {
			if (input.indexOf(o.getValue()) >= 0) {
				this.operator = o;
				break;
			}
		}
		if (operator == null)
			this.operator = Operator.EQUAL;

		switch (operator) {
		case ASTERISK: {
			this.value = input.replace("*", "%");
			break;
		}
		case GREATER:
		case LESS:
		case NOT: {
			this.value = input.substring(1);
			break;
		}
		case TILD: {
			String[] values = input.split(operator.getValue());
			this.value = Arrays.asList(values[0], values[1]);
			break;
		}
		case COMMA: {
			String[] values = input.split(operator.getValue());
			this.value = Arrays.asList(values);
			break;
		}
		case EQUAL: {
			this.value = input;
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Casts the extracted values according to the respective database column data type.
	 */
	private void castParams() {
		if (this.getField().getDataType().equals(ColumnType.LONG)
				|| this.getField().getDataType().equals(ColumnType.DOUBLE)) {
			if (this.operator.equals(Operator.LESS)
					|| this.operator.equals(Operator.GREATER)
					|| this.operator.equals(Operator.NOT)
					|| this.operator.equals(Operator.EQUAL)) {
				if (this.getField().getDataType().equals(ColumnType.LONG))
					this.value = Long.valueOf((String) value);
				else
					this.value = Double.valueOf((String) value);
			} else if (operator.equals(Operator.TILD)
					|| operator.equals(Operator.COMMA)) {
				List<Number> list = new ArrayList<Number>();
				for (Object object : (List<?>) this.value) {
					if (this.getField().getDataType().equals(ColumnType.LONG)) {
						list.add(Long.valueOf((String) object));
					} else {
						list.add(Double.valueOf((String) object));
					}
				}
				this.value = list;
			}
		}
	}

	/**
	 * Determines the placeholder to be used for the search criteria.
	 */
	private void createPattern() {
		StringBuffer key = new StringBuffer();
		key.append(this.getField().getDataType().name()).append(UNDERSCORE)
		.append(operator.name());
		StringBuffer replacement = new StringBuffer(this.getField()
				.getQualifiedName());
		this.pattern = patternMap.get(key.toString()).replace("{column}",
				replacement.toString());
	}

	/**
	 * Forms the map of named parameters for the criteria
	 */
	private void formNamedParams() {
		namedParams = new HashMap<String, Object>();
		StringBuffer key = new StringBuffer(this.getField().getQualifiedName());
		if (operator.equals(Operator.TILD)) {

			if (this.getField().getDataType().name()
					.equals(ColumnType.TIMESTAMP.name())) {
				namedParams.put(key.append(UNDERSCORE_ZERO).toString(),
						(String) ((List<?>) value).get(0));
				namedParams.put(key.toString().replace(UNDERSCORE_ZERO, UNDERSCORE_ONE),
						(String) ((List<?>) value).get(1));
			} else if (this.getField().getDataType().name()
					.equals(ColumnType.LONG.name())) {
				namedParams.put(key.append(UNDERSCORE_ZERO).toString(),
						(Long) ((List<?>) value).get(0));
				namedParams.put(key.toString().replace(UNDERSCORE_ZERO, UNDERSCORE_ONE),
						(Long) ((List<?>) value).get(1));
			} else if (this.getField().getDataType().name()
					.equals(ColumnType.DOUBLE.name())) {
				namedParams.put(key.append(UNDERSCORE_ZERO).toString(),
						(Double) ((List<?>) value).get(0));
				namedParams.put(key.toString().replace(UNDERSCORE_ZERO, UNDERSCORE_ONE),
						(Double) ((List<?>) value).get(1));
			}
		} else {
			namedParams.put(key.toString(), value);
		}
	}

	public Map<String, Object> getNamedParams() {
		return namedParams;
	}

}
