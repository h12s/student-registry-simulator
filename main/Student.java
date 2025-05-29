package main;

import java.util.ArrayList;

public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  /**
     Given a name and id, constructs a new student object 
  */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  /**
	 Returns the id of the student 
  */
  public String getId()
  {
	  return id;
  }
  
  /**
	 Returns the name of the student 
  */
  public String getName()
  {
	  return name;
  }
  
  /** 
     Given a course name, course code, course description, course format, course semester, and grade, 
	 adds a credit course to list of courses for this student
  */
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  courses.add(new CreditCourse(courseName, courseCode, descr, format, sem, grade));
	  courses.get(courses.size() - 1).setActive();
  }
  
  /** 
     Given a course code, finds and removes the course from student's list
     of credit courses, and removes it only if it is an active course 
  */
  public void removeActiveCourse(String courseCode)
  {
	 for (int i = 0; i < courses.size(); i++)		//find the course in the student's credit course list 
	 {
		 if (courses.get(i).getCode().equalsIgnoreCase(courseCode))
		 {
			 if (courses.get(i).getActive() == true)	//if found, remove it only if it was not taken in the past (i.e. is currently active)
			 {
				 courses.remove(i);
				 break;
			 }
		 }
	 }
  }
  
  /** 
     Prints all completed (i.e. non active) courses for this student (prints course code, course name, 
     semester, letter grade) 
  */
  public void printTranscript()
  {
	  for (CreditCourse credCourse : courses)
	  {
		  if (credCourse.getActive() == false)
			  System.out.println(credCourse.displayGrade());
	  }
  }
  
  /** 
     Prints all active courses this student is enrolled in
  */
  public void printActiveCourses()
  {
	 for (CreditCourse credCourse : courses)
	 {
		 if (credCourse.getActive() == true)
			 System.out.println(credCourse.getDescription());
	 }
  }
 
  /**
     Overrides toString method inherited from superclass Object 
  */
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  /** 
     Override equals method inherited from superclass Object.
     Two students are the same if they have the same name and id
  */
  public boolean equals(Object other)
  {
	  Student otherStudent = (Student) other;
	  if (this.getName().equalsIgnoreCase(otherStudent.getName()) && this.getId().equals(otherStudent.getId()))
		  return true;
	  return false;
  }
  
  /**
     Implements comparable interface. Students are compared by their name
  */
  public int compareTo(Student otherStudent)
  {
	  return this.getName().compareTo(otherStudent.getName());
  }
  
}
