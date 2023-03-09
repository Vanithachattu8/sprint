package com.sprint.exceptions;

public class TransactionRecordNotFoundException extends Exception{

	public TransactionRecordNotFoundException() {
		super();
	}
	
	public TransactionRecordNotFoundException(String s) {
		super(s);
	}
}
