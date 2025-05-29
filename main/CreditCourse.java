package main;

public class CreditCourse extends Course 
{
	private boolean active;
	public String semester;
	public double grade;

	/**
	   Given a course name, course code, course description, course format, course semester, and grade,
	   constructs a new CreditCourse object
	*/
	public CreditCourse(String name, String code, String descr, String fmt, String semester, double grade)
	{
		super(name, code, descr, fmt);
		this.active = true;
		this.semester = semester;
		this.grade = 0;
	}
	
	/**
	  Returns the active status of this course
	*/
	public boolean getActive()
	{
		return active;
	}
	
	/**
	  Sets the course as active 
	*/
	public void setActive()			   
	{
		active = true;
	}
	
	/**
	  Sets the course as inactive 
	*/
	public void setInactive()		
	{
		active = false;
	}
	
	/**
	   Returns a string containing info about this course, which semester it is in, and the grade achieved
	*/
	public String displayGrade()
	{
		return super.getCode() + " " + super.getName() + " " + this.semester + " " + Course.convertNumericGrade(this.grade);
	}
	
}