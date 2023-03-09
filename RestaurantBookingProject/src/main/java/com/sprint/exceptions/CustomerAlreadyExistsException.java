package com.sprint.exceptions;

public class CustomerAlreadyExistsException extends Exception{

	public CustomerAlreadyExistsException() {
		super();
	}
	public CustomerAlreadyExistsException(String s) {
		super(s);
	}
}
