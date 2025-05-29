package exceptions;

import java.lang.RuntimeException;

public class InvalidDayException extends RuntimeException
{
	public InvalidDayException() {}
	
	public InvalidDayException(String message)
	{
		super(message);
	}
	
}