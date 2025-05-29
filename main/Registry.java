package main;

import exceptions.BadFileFormatException;
import exceptions.BadFileContentException;

import java.util.ArrayList;
import java.util.Collections;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class Registry
{
   private TreeMap <String, Student> students = new TreeMap <String, Student>();
   private TreeMap <String, ActiveCourse> courses = new TreeMap <String, ActiveCourse>();
   
   public Registry() throws IOException, BadFileFormatException, BadFileContentException
   {
	   Scanner fileReader = new Scanner(new File("students.txt"));		//open the file for reading
	   
	   try {
			while (fileReader.hasNextLine())		//read each line 
			{
				String line = fileReader.nextLine();
				String[] parameters = readLine(line);     //get the name and id 
		   
				String name = parameters[0]; 
				String id = parameters[1];
				
				students.put(id, new Student(name ,id));    //add the student
			}
	   }
	   finally 
	   {
		   fileReader.close();
	   }
	   
	   //student references used below to create classes with students in them 
	   Student s1 = students.get("34562");
	   Student s2 = students.get("38467");
	   Student s3 = students.get("98345");
	   Student s4 = students.get("57643");
	   Student s5 = students.get("25347");
	   Student s6 = students.get("46532");
	   
	   ArrayList<Student> list = new ArrayList<Student>();
	   
	   // Add some active courses with students
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   if (s2 != null) list.add(s2); if (s3 != null) list.add(s3); if (s4 != null) list.add(s4);
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   // Add course to student list of courses
	   if (s2 != null) s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s3 != null) s3.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s4 != null) s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	  
	   // CPS511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   if (s1 != null) list.add(s1); if (s5 != null) list.add(s5); if (s6 != null) list.add(s6);
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	   if (s1 != null) s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s5 != null) s5.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s6 != null) s6.addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
	   // CPS643
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   if (s1 != null) list.add(s1); if (s2 != null) list.add(s2); if (s4 != null) list.add(s4); if (s6 != null) list.add(s6);
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   if (s1 != null) s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s2 != null) s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s4 != null) s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s6 != null) s6.addCourse(courseName,courseCode,descr,format,"W2020", 0);  
	   
	   // CPS706
	   list.clear();
       courseName = "Computer Networks";
       courseCode = "CPS706";
       descr = "Learn about Computer Networking";
       format = "3Lec 1Lab";
	   if (s1 != null) list.add(s1); if (s2 != null) list.add(s2);
       courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   if (s1 != null) s1.addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   if (s2 != null) s2.addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
       // CPS616
	   list.clear();
       courseName = "Algorithms";
       courseCode = "CPS616";
       descr = "Learn about Algorithms";
       format = "3Lec 1Lab";
	   if (s3 != null) list.add(s3); if (s4 != null) list.add(s4);
       courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   if (s3 != null) s3.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   if (s4 != null) s4.addCourse(courseName,courseCode,descr,format,"W2020", 0);
	   
   }
   
   /**
      Given a string, this function will return the name and id of a student.
   */
   private static String[] readLine(String line) throws BadFileFormatException, BadFileContentException
   {
	   Scanner lineReader = new Scanner(line);
	   String name = null;
	   String id = null;
	   
	   //try getting the name
	   if (lineReader.hasNext())
		   name = lineReader.next();
	   else 
		   throw new BadFileFormatException("Bad File Format students.txt");
	   
	   //try getting the id
	   if (lineReader.hasNext())
		   id = lineReader.next();
	   else
		   throw new BadFileFormatException("Bad File Format students.txt");
	   
	   //make sure there are no more parameters
	   if (lineReader.hasNext())
		   throw new BadFileFormatException("Bad File Format students.txt");
	   
	   //make sure name and id are alphabetical, and numerical, respectivley											
	   if (StudentRegistrySimulator.isStringOnlyAlphabetWithoutMessage(name) == false || StudentRegistrySimulator.isNumericWithoutMessage(id) == false)
		   throw new BadFileContentException("Bad File Content students.txt");
	   
	   //return name and id extracted from line
	   String[] parameters = {name , id};
	   return parameters;
   }
   
   /**
      Returns the courses 
   */
   public TreeMap <String, ActiveCourse> getCourses()
   {
	   return courses;
   }
   
   /**
     Given a name, and id, add a new student to the registry and return 
	 true or false indicating whether the student could be added or not.
	 This function will add a student even if they have the same name as
	 another student so long as they have a different id, as the id is the 
	 unique identifier for each student. 
   */
   public boolean addNewStudent(String name, String id)
   {
	   Student temp = students.get(id);    //two students can have the same name as long as their ids are different 
	   
	   if (temp == null)	//no student exists with given id, so add this student 
	   {
		   students.put(id, new Student(name, id));
		   return true;
	   }
	   
	   return false;	//a student exists already with this given id
   }
   
   /** 
     Given a studentId, find and remove the student from the registry and return 
	 true or false indicating whether the student could be removed or not.
	 This function assumes that before removing a student, all their active courses are already dropped
   */
   public boolean removeStudent(String studentId)			
   {	
	   Student temp = students.get(studentId);
	   
	   if (temp != null)	//student found, so remove this student
	   {
		   students.remove(studentId);
		   return true;
	   }
	   
	   return false;	//student not found
   }
   
   /**
     Print all registered students 
   */
   public void printAllStudents()
   {
	   Set<String> keySet = students.keySet();
	   
	   for (String key : keySet)
	   {
		   System.out.println(students.get(key));
	   }
   }
   
   /** 
     Given a studentId and a course code, add student to the active course
   */
   public void addCourse(String studentId, String courseCode)
   {
	   Student element = students.get(studentId);		//find the student in the registry given the id
	   
	   if (element != null)		
	   {
		   boolean courseAlreadyTaken = false;
		   for (CreditCourse course : element.courses)		//check if course already taken in the past
		   {
			   if (course.getCode().equalsIgnoreCase(courseCode))
				   courseAlreadyTaken = true;
		   }
		   
		   
		   if (courseAlreadyTaken == false)		//student has not already taken this course
		   {
			   Set<String> keySet = courses.keySet();
			   
			   for (String key : keySet)	//find course
			   {
				   if (key.equalsIgnoreCase(courseCode))	
				   {
					   boolean alreadyEnrolled = false;		//check if student already enrolled in this course
					   ArrayList<Student> classList = new ArrayList<Student>(courses.get(key).getStudents());
					   
					   for (Student stu : classList)
					   {
						   if (stu.equals(element))
							   alreadyEnrolled = true;
					   }
					   
					   if (alreadyEnrolled == false)		//student is not already enrolled in course
					   {
						   courses.get(key).addStudentToActiveCourse(element);		//add student to the active course 
						   element.addCourse(courses.get(key).getName(), courses.get(key).getCode(), courses.get(key).getDescr(), courses.get(key).getFormat(), courses.get(key).getSemester(), 0);		//add course to student list of credit courses with initial grade of 0
					   }
					   
				   }
			   }
		   }
	   }
	   
   }
   
   /**
     Given a studentId and a course code, drop student from the active course
   */
   public void dropCourse(String studentId, String courseCode)			
   {
	   Student element = students.get(studentId);		//find the student in students
	   
	   if (element != null)
	   {
		   for (String key : courses.keySet())		//find the active course
		   {
			   if (key.equalsIgnoreCase(courseCode))
			   {
				   courses.get(key).removeStudentFromActiveCourse(element);   //remove student from the active course 
				   element.removeActiveCourse(courseCode);					  //remove course from student's list of credit courses 
			   }
		   }
	   }
   }
   
   /**
     Print all active courses
   */
   public void printActiveCourses()
   {
	   for (String key : courses.keySet())
	   {
		   System.out.println(courses.get(key).getDescription());
	   }
   }
   
   /**
     Given a course code, print the list of students in the active course
   */
   public void printClassList(String courseCode)
   {
	  for (String key : courses.keySet())
	  {
		  if (key.equalsIgnoreCase(courseCode))
			  courses.get(key).printClassList();
	  }
   }
   
   /** 
     Given a course code, find course and sort class list by student name
   */
   public void sortCourseByName(String courseCode)
   {
	   for (String key : courses.keySet())
	   {
		   if (key.equalsIgnoreCase(courseCode))
			   courses.get(key).sortByName();
	   }
   }
   
   /**
     Given a course code, find course and sort class list by student id
   */
   public void sortCourseById(String courseCode)
   {
	   for (String key : courses.keySet())
	   {
		   if (key.equalsIgnoreCase(courseCode))
			   courses.get(key).sortById();
	   }
   }
   
   /**
     Given a course code, find course and print student names and grades
   */
   public void printGrades(String courseCode)
   {
	   for (String key : courses.keySet())
	   {
		   if (key.equalsIgnoreCase(courseCode))
			   courses.get(key).printGrades();
	   }
   }
   
   /**
     Given a studentId, print all active courses of student
   */
   public void printStudentCourses(String studentId)
   {
	   Student stu = students.get(studentId);
	   
	   if (stu != null)
		   stu.printActiveCourses();
   }
   
   /**  
     Given a studentId, print all completed courses and grades of student
   */
   public void printStudentTranscript(String studentId)
   {
	   Student stu = students.get(studentId);
	   
	   if (stu != null)
		   stu.printTranscript();
   }
   
   /** 
     Given a course code, student id and numeric grade set the final grade of the student 
   */
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   for (String key : courses.keySet())
	   {
		   if (key.equalsIgnoreCase(courseCode))	//find the active course
		   {
			   ArrayList<Student> classList = new ArrayList<Student>(courses.get(key).getStudents());
			   
			   for (Student stu : classList)
			   {
				   if (stu.getId().equals(studentId))		//find the student in the active course 
				   {
					   for (CreditCourse credCourse : stu.courses)		//find the credit course in the the student's credit course list
					   {
						   if (credCourse.getCode().equalsIgnoreCase(courseCode))
						   {
							   if (credCourse.getActive() == true)		//can only set grade once  
							   {
								   credCourse.grade = grade;
								   credCourse.setInactive();
							   }
						   }
					   }
				   }
			   }
		   }
	   }
   }
  
}
