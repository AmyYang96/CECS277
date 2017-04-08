import java.io.*;
import java.util.*;

/**
 * This class runs the game
 * @author Amy Yang
 *
 */
public class Main 
{
	public static void main(String[] args) 
	{
		Computer computer = new Computer();
		File f = new File( "computer.dat" );
		String action, pattern = "", prediction;
		int playerPts = 0, compPts = 0;
		int round = 1;
		
		if( f.exists() ) 
		{
			int choice; 
			System.out.println("Choose a game mode: \n" + "1. Beginner \n2. Veteran");
			choice = UserInput.getInt();
			while (choice < 1 || choice > 2)
			{
				System.out.println("Invalid. Try again. ");
				choice = UserInput.getInt();
			}
			
			if (choice == 2)
			{
				try
				{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream( f ));
					computer = (Computer) in.readObject();
					in.close();
				}
				catch( IOException e ) 
				{
					System.out.println("Error processing file.");
				}
				catch( ClassNotFoundException e ) 
				{
					System.out.println("Could not find class.");
				}
			}
		}
		System.out.println("Round: " + round);
		round++;
		action = getInput();
		prediction = computer.makePrediction(pattern);
		while (!action.equalsIgnoreCase("0"))
		{
			pattern = pattern + action;
			
			//store different pattern length
			if(pattern.length() == 3 || pattern.length() == 4)
			{
				computer.storePattern(pattern);
			}
			if (pattern.length() > 4)
			{
				pattern = pattern.substring(1); //last 4 moves 
				computer.storePattern(pattern);
			}
			
			
			if (action.equalsIgnoreCase("S"))	
			{
				System.out.println("Player plays scissors.");
			}
			if (action.equalsIgnoreCase("P"))	
			{
				System.out.println("Player plays paper.");
			}
			if (action.equalsIgnoreCase("R"))	
			{
				System.out.println("Player plays rock.");
			}
			
			System.out.println("Computer plays " + prediction + ".");
			
			if ( (action.equalsIgnoreCase("S") && prediction.equalsIgnoreCase("scissors")) ||
					(action.equalsIgnoreCase("R") && prediction.equalsIgnoreCase("rock")) ||
					(action.equalsIgnoreCase("P") && prediction.equalsIgnoreCase("paper")) )
			{
				System.out.println("\nIt's a tie!");
			}
			
			if ( (action.equalsIgnoreCase("R") && prediction.equalsIgnoreCase("scissors")) ||
					(action.equalsIgnoreCase("P") && prediction.equalsIgnoreCase("rock")) ||
					(action.equalsIgnoreCase("S") && prediction.equalsIgnoreCase("paper")) )
			{
				System.out.println("\nPlayer wins!");
				playerPts++;
			}
			
			if ( (action.equalsIgnoreCase("P") && prediction.equalsIgnoreCase("scissors")) ||
					(action.equalsIgnoreCase("S") && prediction.equalsIgnoreCase("rock")) ||
					(action.equalsIgnoreCase("R") && prediction.equalsIgnoreCase("paper")) )
			{
				System.out.println("\nComputer wins!");
				compPts++;
			}
			
			System.out.println("\nPlayer: " + playerPts);
			System.out.println("Computer: " + compPts);
			
			
			savePattern(computer, f);
		
			System.out.println("Round: " + round);
			round++;
			action = getInput();
			prediction = computer.makePrediction(pattern);
		}

		if (playerPts > compPts)
		{
			System.out.println("\nPlayer wins the game!");
		}
		else if (playerPts < compPts)
		{
			System.out.println("\nComputer wins the game!");
		}
		else
		{
			System.out.println("\nIt's a tie game!");
		}
	}	

	/**
	 * Method displays manu and asks fur user input
	 * @return user input
	 */
	public static String getInput()
	{
		String move;
		Scanner in = new Scanner (System.in);
		System.out.println("Please select a weapon (0 to quit): \n" + " R: Rock\n P: Paper\n S: Scissors");
		move = in.nextLine();
		
		while (!( (move.equalsIgnoreCase("S")) || (move.equalsIgnoreCase("R")) || (move.equalsIgnoreCase("0")) ||  (move.equalsIgnoreCase("P")) ) )
		{
			System.out.println("Invalid entry! Please try again." );
			move = in.nextLine();
		}
		return move.toLowerCase();
	}
	
	/**
	 * Saves computer object into a dat file
	 * @param comp the object to be saved
	 * @param f the file that object will save into
	 */
	public static void savePattern(Computer comp, File f)
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream( f ));
			out.writeObject( comp );
			out.close();
			System.out.println("\nSaving pattern...\n\n");
		}
		catch( IOException e ) 
		{
			System.out.println("Error processing file.");
		}
	}

}
