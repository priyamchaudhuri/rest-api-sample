package org.tat.api.library.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tat.api.core.exceptions.Error;
import org.tat.api.core.exceptions.InvalidFieldNameException;
import org.tat.api.core.exceptions.InvalidSearchInputException;

@ControllerAdvice
public class CentralControllerHandler {

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<List<Error>> handleValidationException(
			HttpMessageNotReadableException e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		Error error = new Error(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		errors.add(error);

		return new ResponseEntity<List<Error>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<List<Error>> handleValidationException(
			MethodArgumentNotValidException e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			Error error = new Error(fieldError.getDefaultMessage());
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			errors.add(error);
		}

		return new ResponseEntity<List<Error>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ InvalidFieldNameException.class })
	public ResponseEntity<List<Error>> handleValidationException(
			InvalidFieldNameException e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		Error error = new Error(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		errors.add(error);

		return new ResponseEntity<List<Error>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ InvalidSearchInputException.class })
	public ResponseEntity<List<Error>> handleValidationException(
			InvalidSearchInputException e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		Error error = new Error(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		errors.add(error);

		return new ResponseEntity<List<Error>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<List<Error>> handleValidationException(HttpMediaTypeNotSupportedException e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		Error error = new Error("415-001:bang bang. I dont understand your language.");
		error.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		errors.add(error);

		return new ResponseEntity<List<Error>>(errors,
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<List<Error>> handleValidationException(Exception e) {
		e.printStackTrace();

		List<Error> errors = new ArrayList<Error>();

		Error error = new Error("500-001: I am bit under the weather.");
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errors.add(error);

		return new ResponseEntity<List<Error>>(errors,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}