package exceptions;

import java.lang.RuntimeException;

public class LectureTimeCollisionException extends RuntimeException
{
	public LectureTimeCollisionException() {}
	
	public LectureTimeCollisionException(String message)
	{
		super(message);
	}
	
}