package org.tat.api.core.exceptions;

public class InvalidFieldNameException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message ;
	
	public InvalidFieldNameException(String input){
		this. message = "400-003:Field name is invalid - " + input;
	}
	@Override
	public String getMessage() {
		
		return this.message;
	}


}
