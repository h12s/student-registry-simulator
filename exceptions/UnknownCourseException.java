package exceptions;

import java.lang.RuntimeException;

public class UnknownCourseException extends RuntimeException
{
	public UnknownCourseException() {}
	
	public UnknownCourseException(String message)
	{	
		super(message);
	}
	
}