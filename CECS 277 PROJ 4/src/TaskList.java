import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the main program of a task list
 * @author Amy Yang
 *
 */
public class TaskList 
{
	public static void main(String[] args) 
	{
		int action;
		Scanner in = new Scanner (System.in);
		Heap <Job> taskList = new Heap <Job>();
		taskList = readFile(taskList);
		
		menu();
		action = UserInput.getInt();
		
		while (action <1 || action > 6)
		{
			System.out.println("Please try again.");
			action = UserInput.getInt();
		}
		
		while (action > 0 && action <6)
		{
			if (action == 1) // display tasklist
			{
				if(taskList.isEmpty())
				{
					System.out.println("There are no tasks.");
				}
				else
				{
					taskList.printHeap();
				}
			}
			
			if (action == 2) // display current task
			{
				if(taskList.isEmpty())
				{
					System.out.println("There are no tasks.");
				}
				else
				{
					System.out.println(taskList.getObjectAt(0));
				}
			}
			
			if(action == 3) // add new task
			{
				String task;
				
				System.out.println("Enter a task: ");
				task = in.nextLine();

				Job job = enterDate_Time(task);
				taskList.addMinObject(job);
				System.out.println("\nSuccessfully added: " + job);
			}
			
			if (action == 4) // mark complete
			{
				if(taskList.isEmpty())
				{
					System.out.println("There are no tasks to remove.");
				}
				else
				{
					System.out.println("Completed: " + taskList.removeMin());
				}
			}
			
			if (action == 5) // postpone current task
			{
				if(taskList.isEmpty())
				{
					System.out.println("There are no tasks to postpone.");
				}
				else
				{
					Job removed = taskList.removeMin();
					String task = removed.getTask();
					System.out.println("Postponing: " + removed);
					Job job = enterDate_Time(task);
					taskList.addMinObject(job);
					System.out.println("\nSuccessfully postponed: " + job);
				}
			}
			
			System.out.println();
			menu();
			action = UserInput.getInt();
			
			while (action <1 || action > 6)
			{
				System.out.println("Please try again.");
				action = UserInput.getInt();
			}
		}
		
	}
	
	/**
	 * Reads in the list of tasks from file and adds to the heap
	 * @param tasksList empty heap
	 * @return heap with  list of tasks
	 */
	public static Heap <Job> readFile(Heap <Job> tasksList)
	{
		try
		{
			Scanner read=new Scanner(new File("tasklist.txt"));
			
			do
			{
				String line = read.nextLine();
				String [] tokens = line.split(",");
				
				Job task = new Job (tokens[0], tokens[1], tokens[2]); // input data in constructor
				
				tasksList.addMinObject(task);
				
			}while(read.hasNextLine());
				
			read.close();
		} 
		catch(FileNotFoundException fnf)
		{
			System.out.println("File was not found");
		}
		
		return tasksList;
	}
	
	/**
	 * Prints the menu
	 */
	public static void menu()
	{
		System.out.println("Please choose a function");
		System.out.println("1. Display tasklist");
		System.out.println("2. Display current task");
		System.out.println("3. Add a new task");
		System.out.println("4. Mark complete");
		System.out.println("5. Postpone current task\n6. Quit");
	}
	
	/**
	 * This method allows the user to input date and time. It validates each input info of date and time
	 * and concatenates the info and returns a job object
	 * @param task the task to included in the job object
	 * @return a job object
	 */
	public static Job enterDate_Time(String task)
	{
		int month,day,year,hour,min;
		System.out.println("Enter a month: ");
		month = UserInput.getInt();
		
		while(month <1 || month >12)
		{
			System.out.println("Invalid entry. Enter a month: ");
			month = UserInput.getInt();
		}
		
		System.out.println("Enter a day: ");
		day = UserInput.getInt();
		
		if (month == 2)
		{
			while(day <1 || day >28)
			{
				System.out.println("Invalid. There are only 28 days in February. Enter a day: ");
				day = UserInput.getInt();
			}
		}
		else
		{
			while(day <1 || day >31)
			{
				System.out.println("Invalid entry. Enter a day: ");
				day = UserInput.getInt();
			}
		}
		
		System.out.println("Enter a year: ");
		year = UserInput.getInt();
		
		final int CURRENT_YEAR = 2016;
		while(year < CURRENT_YEAR || year >= 10000)
		{
			System.out.println("Invalid entry. Enter a year: ");
			year = UserInput.getInt();
		}
		
		System.out.println("Enter an hour: ");
		hour = UserInput.getInt();
		
		while(hour <0 || hour >23)
		{
			System.out.println("Invalid entry. Enter a hour: ");
			hour = UserInput.getInt();
		}
		
		System.out.println("Enter a minute: ");
		min = UserInput.getInt();
		
		while(min <0 || min >59)
		{
			System.out.println("Invalid entry. Enter a minute: ");
			min = UserInput.getInt();
		}
		
		String date="", time="";
		
		if (month <10)
		{
			date = date + "0" + Integer.toString(month) + "/";
		}
		else
		{
			date = date + Integer.toString(month) + "/";
		}
		
		if (day < 10)
		{
			date = date + "0" + Integer.toString(day) + "/"+ Integer.toString(year);
		}
		else
		{
			date = date + Integer.toString(day) + "/"+ Integer.toString(year);
		}

		if (hour <10)
		{
			time = time + "0" + Integer.toString(hour) + ":";
		}
		else
		{
			time = time + Integer.toString(hour) + ":";
		}
		
		if(min < 10)
		{
			time =  time + "0" + Integer.toString(min);
		}
		else
		{
			time =  time + Integer.toString(min);
		}
		
		Job job = new Job(task, date, time);
		
		return job;
	}
}
