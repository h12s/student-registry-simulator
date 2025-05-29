package exceptions;

import java.io.IOException;

public class BadFileContentException extends IOException
{
	public BadFileContentException() {}
	
	public BadFileContentException(String message) 
	{
		super(message);
	}
	
}