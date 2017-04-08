import java.util.Scanner;

/**
 * This is the main class that allows users to play the game
 * @author Amy Yang
 *
 */
public class Main 
{
	public static void main (String[] args)
	{
		Scanner in = new Scanner (System.in);
		String jediName;
		int choice;
		
		System.out.println("Welcome to the Star Wars game!");
		System.out.println("Choose a name for your Jedi:");
		jediName = in.nextLine();
		
		System.out.println("Choose a mission:");
		System.out.println("1. Hunt Darth Vader \n2. Save Princess Leila \n3. Quit");
		choice = UserInput.getInt();
		
		while (choice <1 || choice > 3)
		{
			System.out.println("Invalid entry. Try again.");
			choice = UserInput.getInt();
		}
		
		while (choice == 1 || choice == 2)
		{
			Entity [] rebels = new Entity [6];
			rebels [0] = new Jedi (jediName, 100, "Saber", "Feel the power of my force!\n", "blue");
			rebels [1] = new Rebel ("Rebel 1", 60, "gun", "You're going down.\n");
			rebels [2] = new Rebel ("Rebel 2", 60, "blaster", "You just got blasted.\n");
			rebels [3] = new Rebel ("Rebel 3", 60, "gun", "I will kill you all.\n");
			rebels [4] = new MedicalDroid ("MedBot", 30);
			rebels [5] = new AstromechDroid ("AstroBot", 30);

			Entity [] imperials = new Entity [6];
			imperials [0] = new SithLord ("Darth Vader", 100, "Saber", "I will defeat you!\n", "black");
			imperials [1] = new Stormtrooper ("Stormtrooper 1", 60, "gun", "Rebel Scum!\n");
			imperials [2] = new Stormtrooper ("Stormtrooper 2", 60, "blaster", "You're no match for me.\n");
			imperials [3] = new Stormtrooper ("Stormtrooper 3", 60, "gun", "I will blast you into pieces.");
			imperials [4] = new Computer ("Computer", 30);
			imperials [5] = new Door ("Door", 30);
			
			if (choice == 1)
			{
				mission1(rebels, imperials);
			}
			else
			{
				mission2(rebels, imperials);
			}
			
			System.out.println("Choose a mission:");
			System.out.println("1. Hunt Darth Vader \n2. Save Princess Leila \n3. Quit");
			choice = UserInput.getInt();
			
			while (choice <1 || choice > 3)
			{
				System.out.println("Invalid entry. Try again.");
				choice = UserInput.getInt();
			}
		}

		
	}
	
	/**Prints list of options*/
	public static void subMenu()
	{
		System.out.println("Please choose an action.");
		System.out.println("1. Attack with lightsaber");
		System.out.println("2. Attack Darth Vader with force");
		System.out.println("3. Heal a rebel ");
		System.out.println("4. Use Astromech Droid to attack a computer or door");
	}
	
	/**
	 * Prints list of rebels to heal, user can input
	 * @param e array of entities
	 */
	public static void healRebelList(Entity [] e)
	{
		int input;
		System.out.println("Please choose a rebel to heal.");
		System.out.println("1. " + e[0].getName());
		System.out.println("2. " + e[1].getName());
		System.out.println("3. " + e[2].getName() + "\n4. " + e[3].getName());
		input = UserInput.getInt();
		
		while (input < 1 || input > 4)
		{
			System.out.println("Invalid entry. Try again.");
			input = UserInput.getInt();
		}
		MedicalDroid md = (MedicalDroid) e[4]; // downcasting
		
		if (input == 1)
		{
			Jedi j =  (Jedi) e[0];
			j.heal(md);
		}
		else
		{
			Rebel r = (Rebel) e[input-1];
		}
		
	}
	
	/**
	 * Prints the scores of each entity in the army
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void printScores(Entity[] rebels, Entity[] imperials)
	{
		System.out.println("\nGood guys: ");
		for (int i=0; i<rebels.length; i++)
		{
			System.out.println(rebels[i].getName() + "\t" + rebels[i].getHp());
		}
		
		System.out.println("\nBad guys: ");
		for (int i=0; i<imperials.length; i++)
		{
			System.out.println(imperials[i].getName() + "\t" + imperials[i].getHp());
		}
	}
	
	/**
	 * Jedi attacks Sith Lord with Force
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void attackWithForce (Entity[] rebels, Entity[] imperials)
	{
		Jedi jedi = (Jedi) rebels[0];
		jedi.useForce(imperials[0]);
	}
	
	/**
	 * Both armies fight
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void fightArmy(Entity[] rebels, Entity[] imperials)
	{
		for(int i =0; i<4; i++)
		{
			if(imperials[i].getActive()==true)
			{
				rebels[i].doTask(imperials[i]);
			}
		}
		for(int i =0; i<4; i++)
		{
			if(rebels[i].getActive()==true)
			{
				imperials[i].doTask(rebels[i]);
			}
		}
	}
	
	/**
	 * Attack with Astromech droid
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void attackWithDroid (Entity[] rebels, Entity[] imperials)
	{
		int input;
		System.out.println("Choose which to attack");
		System.out.println("1. Computer \n2. Door");
		input = UserInput.getInt();
		
		while (input < 1 || input > 2)
		{
			System.out.println("Invalid entry. Try again.");
			input = UserInput.getInt();
		}
		
		if(input == 1)
		{
			rebels[5].doTask(imperials[4]);
		}
		else
		{
			rebels[5].doTask(imperials[5]);
		}
	}
	
	/**
	 * Mission 1: hunt darth vader, executes the whole scene until jedi or darth vader dies
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void mission1 (Entity[] rebels, Entity[] imperials)
	{
		int action;
		System.out.println("You have chosen the mission to hunt Darth Vader.");
		
		while (rebels[0].getActive() && imperials[0].getActive())
		{
			subMenu();
			action = UserInput.getInt();
			
			while (action < 1 || action > 4)
			{
				System.out.println("Invalid entry. Try again.");
				action = UserInput.getInt();
			}
			
			if (action == 1)
			{
				fightArmy(rebels, imperials);
			}
			if(action == 2)
			{
				attackWithForce(rebels, imperials);
			}
			if(action == 3)
			{
				healRebelList(rebels);
			}
			if(action == 4)
			{
				attackWithDroid(rebels, imperials);
			}
			
			printScores(rebels,imperials);
		}
		
		if(rebels[0].getActive() == false)
		{
			System.out.println(rebels[0].getName() + " has died. Game over!");
		}
		else
		{
			System.out.println(imperials[0].getName() + " has died. You have successfully hunted Darth Vader.");
		}
	}
	
	/**
	 * Mission 1: save princess Leila, executes the whole scene until jedi or darth vader dies
	 * @param rebels array of rebel army
	 * @param imperials array of imperial army
	 */
	public static void mission2 (Entity[] rebels, Entity[] imperials)
	{
		int action;
		System.out.println("You have chosen the mission to save Princess Leila.");
		
		while (rebels[0].getActive() && imperials[0].getActive())
		{
			subMenu();
			action = UserInput.getInt();
			
			while (action < 1 || action > 4)
			{
				System.out.println("Invalid entry. Try again.");
				action = UserInput.getInt();
			}
			
			if (action == 1)
			{
				fightArmy(rebels, imperials);
			}
			if(action == 2)
			{
				attackWithForce(rebels, imperials);
			}
			if(action == 3)
			{
				healRebelList(rebels);
			}
			if(action == 4)
			{
				attackWithDroid(rebels, imperials);
			}
			
			printScores(rebels,imperials);
			System.out.println("\nYou are almost there!");
		}
		
		if(rebels[0].getActive() == false)
		{
			System.out.println(rebels[0].getName() + " has died. Game over!");
		}
		else
		{
			System.out.println(imperials[0].getName() + " has died. You save Princess Leila. The end.");
		}
	}
	
}
