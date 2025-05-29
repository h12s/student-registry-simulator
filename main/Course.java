package main;

public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	   
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	   
	/**
	   Returns course code
	*/
	public String getCode()
	{
	   return code;
	}
	   
	/**
	   Returns course name 
	*/
	public String getName()
	{
	  return name;
	}
	   
	/**
	   Returns course format 
	*/
	public String getFormat()
	{
	  return format;
	}
	
	/**
	   Returns course description (String contained in the instance variable)
	*/
	public String getDescr()	
	{
	  return description;
	}
	   
	/**
	   Returns course description containing name, description, and format 
	*/
	public String getDescription()
	{
	  return code + " - " + name + "\n" + description + "\n" + format;
	}
	
	/**
	   Returns general course information (course code and name)
	*/
	public String getInfo()
	{
	  return code +" - " + name;
	}
	 
	 /**
	    Static method of this class to convert a numeric grade of type double,
		into a letter grade 
	 */
	 public static String convertNumericGrade(double score)
	 {
		 if (score >= 90 && score <= 100)
			 return "A+";
		 else if (score >= 85)
			 return "A";
		 else if (score >= 80)
			 return "A-";
		 else if (score >= 77)
			 return "B+";
		 else if (score >= 73)
			 return "B";
		 else if (score >= 70)
			 return "B-";
		 else if (score >= 67)
			 return "C+";
		 else if (score >= 63)
			 return "C";
		 else if (score >= 60)
			 return "C-";
		 else if (score >= 57)
			 return "D+";
		 else if (score >= 53)
			 return "D";
		 else if (score >= 50)
			 return "D-";
		 else
			return "F";
	 }
	 
}
