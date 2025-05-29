package main;

import exceptions.UnknownCourseException;
import exceptions.InvalidDayException;
import exceptions.InvalidTimeException;
import exceptions.InvalidDurationException;
import exceptions.LectureTimeCollisionException;
import exceptions.BadFileFormatException;
import exceptions.BadFileContentException;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import java.io.FileNotFoundException;

public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  try 
	  {
	  Registry registry = new Registry();	
	  Scheduler scheduler = new Scheduler(registry.getCourses());
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  if (commandLine.hasNext())			//get the name
			  {
				  String name = commandLine.next();
				  if (commandLine.hasNext())		//get the id
				  {
					  String id = commandLine.next();
					  if (isStringOnlyAlphabet(name) && isNumeric(id))
					  {
						  boolean added = registry.addNewStudent(name, id);		//check if student added or not 
						  if (added == false)
							  System.out.println("Student " + name + " already registered");
					  }
				  }
			  }  
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  if (commandLine.hasNext())		//get the id 
			  {
				  String id = commandLine.next();
				  if (isNumeric(id))
				  {
					  boolean removed = registry.removeStudent(id);		//check if student removed or not 
					  if (removed == false)
						  System.out.println("Student " + id + " not found");
				  }
			  }
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 if (commandLine.hasNext())		//get the id
			 {
				 String id = commandLine.next();
				 if (commandLine.hasNext())		//get the course code
				 {
					 String courseCode = commandLine.next();
					 if (isNumeric(id)) 
						 registry.addCourse(id, courseCode);
				 }
			 }
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  if (commandLine.hasNext())		//get the id
			  {
				  String id = commandLine.next();
				  if (commandLine.hasNext())		//get the course code 
				  {
					  String courseCode = commandLine.next();
					  if (isNumeric(id))
						  registry.dropCourse(id, courseCode);
				  }
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  if (commandLine.hasNext())	//get the course code 
			  {
				  String courseCode = commandLine.next();
			      registry.printClassList(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  { 
			  if (commandLine.hasNext())		//get the course code 
			  {
				  String courseCode = commandLine.next();
				  registry.printGrades(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  if (commandLine.hasNext())	//get the course code 
			  {
				  String id = commandLine.next();
				  if (isNumeric(id))
					  registry.printStudentCourses(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  if (commandLine.hasNext())		//get the id
			  {
				  String id = commandLine.next();
				  if (isNumeric(id))
					  registry.printStudentTranscript(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  if (commandLine.hasNext())		//get the course code 
			  {
				  String courseCode = commandLine.next();
				  if (commandLine.hasNext())		//get the id
				  {
					  String id = commandLine.next();
					  if (commandLine.hasNextDouble())		//get the grade 
					  {
						  double grade = commandLine.nextDouble();
						  if (isNumeric(id))
							  registry.setFinalGrade(courseCode, id, grade);
					  }
				  }
			  }
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  if (commandLine.hasNext())	//get the course code and sort by name
			  {
				  String courseCode = commandLine.next();
				  registry.sortCourseByName(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			if (commandLine.hasNext())		//get the course code and sort by grade 
			{
				String courseCode = commandLine.next();
				registry.sortCourseById(courseCode);
			}
		  }
		  else if (command.equalsIgnoreCase("SCH"))
		  {
			  if (commandLine.hasNext())	//get the course code
			  {
				  String courseCode = commandLine.next();	
	
				  if (commandLine.hasNext())	//get the day
				  {
					  String day = commandLine.next();
					  
					  if (commandLine.hasNextInt())		//get the start time
					  {
						  int start = commandLine.nextInt();
						  
						  if (commandLine.hasNextInt())		//get the duration 
						  {
							  int duration = commandLine.nextInt();
							  
							  try
							  {
								  scheduler.setDayAndTime(courseCode, day, start, duration);
							  }
							  catch (UnknownCourseException exception)
							  {
								  System.out.println(exception.getMessage());
							  }
							  catch (InvalidDayException exception)
							  {
								  System.out.println(exception.getMessage());
							  }
							  catch (InvalidTimeException exception)
							  {
								  System.out.println(exception.getMessage());
							  }
							  catch (InvalidDurationException exception)
							  {
								  System.out.println(exception.getMessage());
							  }
							  catch (LectureTimeCollisionException exception)
							  {
								  System.out.println(exception.getMessage());
							  }
							  
						  }
					  }
				  }
					
			  }
		  }
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  if (commandLine.hasNext())	//get the course code
			  {
				  String courseCode = commandLine.next();
				  scheduler.clearSchedule(courseCode);
			  }
		  }
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  scheduler.printSchedule();
		  }
		  
		  System.out.print("\n>");
	  }
	  }
	  catch (FileNotFoundException exception)
	  {
		  System.out.println("students.txt Not Found");
		  return;
	  }
	  catch (BadFileFormatException exception)
	  {
		  System.out.println(exception.getMessage());
		  return;
	  }
	  catch (BadFileContentException exception)
	  {
		  System.out.println(exception.getMessage());
		  return;
	  }
	  catch (IOException exception)
	  {
		  System.out.println("File processing error: " + exception);
		  return;
	  }
  }
  
  /**
	Checks if string contains only alphabet characters 				
  */
  public static boolean isStringOnlyAlphabet(String str) 
  { 
	  char[] characters = str.toLowerCase().toCharArray();
	  for (char i : characters)
	  {
		  if (i < 'a' || i > 'z')
		  {
			  System.out.println("Invalid Characters in Name " + str);
			  return false;
		  }
	  }
	  return true;
  } 
  
  /** 
	Checks if string contains only numeric characters
	Also checks if length is 5 as this function is exclusivley used to check IDs only
  */
  public static boolean isNumeric(String str)
  {
	  if (str.length() != 5)	//check if id is proper length 
	  {
		  System.out.println("ID must be 5 digits");
		  return false;
	  }
	  
	  char[] characters = str.toCharArray();
	  for (char i : characters)
	  {
		  if (i < '0' || i > '9')
		  {
			  System.out.println("Invalid Characters in ID " + str);
			  return false;
		  }
	  }
	  return true;
  }
  
  /**
	Checks if string contains only alphabet characters 
	However, this function does not print any message			
  */
  public static boolean isStringOnlyAlphabetWithoutMessage(String str) 
  { 
	  char[] characters = str.toLowerCase().toCharArray();
	  for (char i : characters)
	  {
		  if (i < 'a' || i > 'z')
		  {
			  return false;
		  }
	  }
	  return true;
  } 
  
  /** 
	Checks if string contains only numeric characters
	Also checks if length is 5 as this function is exclusivley used to check IDs only
	However, this function does not print any message
  */
  public static boolean isNumericWithoutMessage(String str)
  {
	  if (str.length() != 5)	//check if id is proper length 
	  {
		  return false;
	  }
	  
	  char[] characters = str.toCharArray();
	  for (char i : characters)
	  {
		  if (i < '0' || i > '9')
		  {
			  return false;
		  }
	  }
	  return true;
  }
  
}