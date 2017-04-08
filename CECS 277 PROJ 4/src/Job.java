import java.util.*;
import java.text.*;

/**
 * This class represents a job
 * @author Amy Yang
 *
 */
public class Job implements Comparable<Job> 
{
	/**Represents a task*/
	private String task;
	
	/**Represents the due date*/
	private Date dueDate;

	/**
	 * Constructor initializes a date object by splitting the date and time
	 * @param chore Represents the task
	 * @param day Represents due date
	 * @param time Represents due date time
	 */
	public Job (String chore, String day, String time)
	{
		task = chore;
		String d = day+" " +time;
		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			dueDate = formatter.parse(d);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the task
	 * @return task as a string
	 */
	public String getTask ()
	{
		return task;
	}
	
	/**
	 * Gets the task's due date
	 * @return due date as a date object
	 */
	public Date getDate ()
	{
		return dueDate;
	}

	
	/**
	 * Compares 2 due dates
	 * @param date2 another due date
	 * @return -1 if the date is before date2, 0 if the dates are the same, 1 if after date2
	 */
	@Override
	public int compareTo (Job job2)
	{
		return dueDate.compareTo(job2.getDate());
	}
	
	/**
	 * Converts task to a string
	 * @return a String
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		return task + ", " + formatter.format(dueDate);
	}
}
