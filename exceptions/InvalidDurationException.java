package exceptions;

import java.lang.RuntimeException;

public class InvalidDurationException extends RuntimeException
{
	public InvalidDurationException() {}
	
	public InvalidDurationException(String message)
	{
		super(message);
	}
	
}