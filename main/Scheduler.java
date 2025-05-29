package main;

import exceptions.UnknownCourseException;
import exceptions.InvalidDayException;
import exceptions.InvalidTimeException;
import exceptions.InvalidDurationException;
import exceptions.LectureTimeCollisionException;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler 
{
	
	private TreeMap <String, ActiveCourse> schedule;
		
	/**
	   Given a treemap of courses, constructs a new Scheduler object 
	*/
	public Scheduler(TreeMap <String, ActiveCourse> courses)
	{
	  schedule = courses;										
	}
	
	/**
	   Given a course code, day, start time, and duration, this method will schedule the course
	   for the given day and time and duration, if it is valid 
	*/
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws UnknownCourseException, InvalidDayException, InvalidTimeException, InvalidDurationException, LectureTimeCollisionException
	{
		//check if course exists
		boolean courseExists = false;
		for (String key : schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode))
				courseExists = true;
		}
		if (courseExists == false)
			throw new UnknownCourseException("Unknown Course: " + courseCode);
			
		//check if day is valid
		if (day.equalsIgnoreCase("Mon") == false && day.equalsIgnoreCase("Tue") == false && day.equalsIgnoreCase("Wed") == false && day.equalsIgnoreCase("Thur") == false && day.equalsIgnoreCase("Fri") == false)
			throw new InvalidDayException("Invalid Course Day");
		
		//check if duration is valid 
		if (duration != 1 && duration != 2 && duration != 3)
			throw new InvalidDurationException("Invalid Lecture Duration");
		
		//check if start time is valid, the start time can only occur at the beginning of an hour 
		if (startTime % 100 != 0 || startTime < 800 || startTime + (duration * 100) > 1700)
			throw new InvalidTimeException("Invalid Lecture Start Time");
		
		//check if lecture time does not collide with another lecture time
		for (String key : schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode) == false)		//lecture can not collide with itself 
			{
				ActiveCourse course = schedule.get(key);
				
				if (course.getLectureDay().equalsIgnoreCase(day))    //collision can only happen on the same day
				{
					int courseStartTime = course.getLectureStart();
					int courseEndTime = course.getLectureStart() + (course.getLectureDuration() * 100);
					int endTime = startTime + (duration * 100);
					
					if (courseEndTime != startTime && courseStartTime != endTime)	//check if times are adjacent		
					{
						ArrayList<Integer> hoursInInput = new ArrayList<Integer>();
						ArrayList<Integer> hoursInCourse = new ArrayList<Integer>();
						
						for (int i = startTime; i <= endTime; i += 100)
							hoursInInput.add(i);
						
						for (int i = courseStartTime; i <= courseEndTime; i += 100)
							hoursInCourse.add(i);
						
						for (int hour : hoursInInput)		//check if any time in the input is in the course's times if they are not adjacent 
						{
							if (hoursInCourse.contains(hour))
								throw new LectureTimeCollisionException("Lecture Time Collision");
						}
						
						for (int hour : hoursInCourse)
						{
							if (hoursInInput.contains(hour))
								throw new LectureTimeCollisionException("Lecture Time Collision");
						}
						
					}
					
					
				}
			}
		}
		
		//set the lecture starting time, duration, and day
		for (String key : schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode))
			{
				schedule.get(key).setLectureStart(startTime);
				schedule.get(key).setLectureDuration(duration);
				schedule.get(key).setLectureDay(day);
			}
		}
		
	}
	
	/**
	   Given a course code, this method clears the schedule for that course 
	*/
	public void clearSchedule(String courseCode)
	{
		for (String key : schedule.keySet())
		{
			if (key.equalsIgnoreCase(courseCode))
			{
				schedule.get(key).setLectureStart(0);
				schedule.get(key).setLectureDuration(0);
				schedule.get(key).setLectureDay("");
			}
		}
	}
		
	/**
	   Print the schedule
	*/
	public void printSchedule()
	{
		String line = "      Mon    Tue    Wed    Thu    Fri";	
		System.out.println(line);
		
		ArrayList<String> days = new ArrayList<String>();
		days.add("Mon"); days.add("Tue"); days.add("Wed"); days.add("Thur"); days.add("Fri");
		
		for (int time = 800; time <= 1600; time += 100)    //go through each hour of the day 
		{
			if (time == 800 || time == 900)		//ensuring all times for the day have 4 digits
				line = "0" + time + " ";
			else
				line = time + " ";
			
			boolean courseIsHere = false;
			
			for (String day : days)		//go through each day
			{
				for (String key : schedule.keySet())    
				{
					ActiveCourse course = schedule.get(key);
					
					if (course.getLectureDay().equalsIgnoreCase(day))		//find if a course has a lecture on that day 
					{
						if (time >= course.getLectureStart() && time < course.getLectureStart() + (course.getLectureDuration() * 100))    //find if the course has a lecture during that time
						{
							line += course.getCode() + " ";
							courseIsHere = true;
						}
					}
				}
				
				if (courseIsHere == false)
					line += "       ";
				
				courseIsHere = false;
				
			}
			
			System.out.println(line);
			
		}
		
	}
	
}

