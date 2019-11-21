package com.mobiquityinc.exception;

/**
 * This is class to wrap all project exceptions can be enhanced to add LOGGER and present error to user
 * 
 * @author Mohd.Rahil Shaikh
 *
 */
public class APIException extends Exception
{
	public APIException(String message)
	{
		super(message);
	}

	public APIException(Throwable cause)
	{
		super(cause);
	}

	public APIException(String message, String lineArg)
	{
		super(message);
	}

	public APIException(IllegalArgumentException e, String line)
	{
		super(e);
	}
}
