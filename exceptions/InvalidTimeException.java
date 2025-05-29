package exceptions;

import java.lang.RuntimeException;

public class InvalidTimeException extends RuntimeException
{
	public InvalidTimeException() {}
	
	public InvalidTimeException(String message)
	{
		super(message);
	}

}