package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	
	private int lectureStart;
	private int lectureDuration;
	private String lectureDay;
	
   /**
     Given a course name, course code, course description, course format, course semester, and a list of students,
	 constructs a new ActiveCourse object 
   */
   public ActiveCourse(String name, String code, String descr, String fmt, String semester, ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   
	   this.lectureStart = 0;
	   this.lectureDuration = 0;
	   this.lectureDay = "";
   }
   
   /**
      Set the lecture start time
   */
   public void setLectureStart(int start)
   {
	   this.lectureStart = start;
   }
   
   /**
      Return the lecture start time 
   */
   public int getLectureStart()
   {
	   return lectureStart;
   }
   
   /**
      Set the lecture duration 
   */
   public void setLectureDuration(int duration)
   {
	   this.lectureDuration = duration;
   }
   
   /**
      Get the lecture duration 
   */
   public int getLectureDuration()
   {
	   return lectureDuration;
   }
   
   /**
      Set the lecture day 
   */
   public void setLectureDay(String day)
   {
	   this.lectureDay = day;
   }
   
   /**
      Get the lecture day 
   */
   public String getLectureDay()
   {
	   return lectureDay;
   }
   
   /**
	  Returns the list of students in this active university course 
   */
   public ArrayList<Student> getStudents()		
   {
	   return students;
   }
   
   /**
      Given a student object, adds the student to the list of students in this active university course 
   */
   public void addStudentToActiveCourse(Student student)	
   {
	   students.add(student);
   }
   
   /**
      Given a student object, removes the student from the list of students in this active university course 
   */
   public void removeStudentFromActiveCourse(Student student)	
   {
	   students.remove(student);
   }
   
   /**
      Returns the semester of this active course 
   */
   public String getSemester()
   {
	   return semester;
   }
   
   /** 
      Prints the students in this course  (prints name and student id)
   */
   public void printClassList()
   {
	   for (Student stu : students)
	   {
		   System.out.println(stu);
	   }
   }
   
   /**
      Prints the grade of each student in this course (along with name and student id)
   */
   public void printGrades()
   {
	   for (Student stu : students) 
	   {
		   System.out.println(stu.getId() + " " + stu.getName() + " " + getGrade(stu.getId()));
	   }
   }
   
   /** 
      Given a studentId, returns the numeric grade of the student in this course.
      If student not found in course, returns 0
   */ 
   public double getGrade(String studentId)
   {
	  for (Student stu : students)		//find the student in the list of students given the id 
	  {
		  if (stu.getId().equals(studentId))
		  {
			  for (CreditCourse credCourse : stu.courses)		//if student found, search the student's list of credit courses to find the course code that matches this active course
			  {
				  if (credCourse.getCode().equalsIgnoreCase(this.getCode()))
				  {
				      return credCourse.grade;		//student found, so returns their grade stored in their credit course object 			
				  }
			  }
		  }
	  }
	  return 0;		//student not found, so return 0
   }
   
   /**
	  Returns a String containing the course information as well as the semester and the number of students 
      enrolled in the course
   */
   public String getDescription()
   {
	   return super.getDescription() + " " + this.semester + " Enrollment: " + this.students.size() + "\n";
   }
    
	
   /**
      Sorts the students in this course by name 
   */
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   /**
	  Implements the Comparator interface. This implementation compares two students
	  by their name
   */
   private class NameComparator implements Comparator<Student>
   {
		public int compare(Student a, Student b)
		{
			return a.getName().compareTo(b.getName());			
		}
   }
   
   /**
      Sorts the students in this course by id 
   */
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   /**
      Implements the Comparator interface. This implementation compares two students
	  by their id
   */
   private class IdComparator implements Comparator<Student>
   {
		public int compare(Student a, Student b)
		{
			return a.getId().compareTo(b.getId());
		}
   }
}
