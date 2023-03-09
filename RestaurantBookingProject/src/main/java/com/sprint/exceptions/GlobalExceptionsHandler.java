package com.sprint.exceptions;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
	@Value(value="${message1}")
	private String message1;
	
	@Value(value="${message2}")
	private String message2;
	
	@Value(value="${message3}")
	private String message3;
	
	@Value(value="${message4}")
	private String message4;
	
	@Value(value="${message5}")
	private String message5;
	
	@Value(value="${message6}")
	private String message6;
	
	@Value(value="${message7}")
	private String message7;
	
	@Value(value="${message8}")
	private String message8;
	
	
	@ExceptionHandler(value=BookingNotFoundException.class)
	public ResponseEntity<String> BookingNotFoundException(BookingNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public ResponseEntity<String> CustomerNotFoundException(CustomerNotFoundException c) {
		return new ResponseEntity<String>(message2,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=BookingAlreadyExistsException.class)
	public ResponseEntity NoBookingFoundException(BookingAlreadyExistsException ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=TransactionRecordNotFoundException.class)
	public ResponseEntity TransactionRecordNotFoundException(TransactionRecordNotFoundException b) {
		return new ResponseEntity(message4,HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(value=CustomerAlreadyExistsException.class)
	public ResponseEntity CustomerAlreadyExistsException(CustomerAlreadyExistsException b) {
		return new ResponseEntity(message5,HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(value=InvalidCredentialsException.class)
	public ResponseEntity InvalidCredentialsException(InvalidCredentialsException b) {
		return new ResponseEntity(message6,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=InvalidEmailPasswordException.class)
	public ResponseEntity InvalidEmailPasswordException(InvalidEmailPasswordException b) {
		return new ResponseEntity(message7,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value=TableNotFoundException.class)
    public ResponseEntity<String> TableNotFoundException(TableNotFoundException c) {
        return new ResponseEntity<String>(c.getMessage(),HttpStatus.NOT_FOUND);
    }
	public GlobalExceptionsHandler() {
		
		
		
	}

}
