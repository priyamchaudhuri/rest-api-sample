package org.tat.api.core.exceptions;

public class InvalidSearchInputException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message ;
	
	public InvalidSearchInputException(String input){
		this. message = "400-002:Search input is invalid - " + input;
	}
	@Override
	public String getMessage() {
		
		return this.message;
	}

}
