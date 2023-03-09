package com.sprint.exceptions;

public class InvalidEmailPasswordException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidEmailPasswordException()
	{
		super();
	}
	
	public InvalidEmailPasswordException(String s)
	{
		super(s);
	}
}
