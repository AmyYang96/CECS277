import java.util.*;

/**
 * This class contains static methods that check user input
 * @author Amy Yang
 *
 */
public class UserInput 
{
	/** 
	 * Method returns a valid integer input
	 * @return valid input 
	*/
	public static int getInt()
	{
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while( !valid) 
		{
			if( in.hasNextInt() ) 
			{
				validNum = in.nextInt();
				valid = true;
			} 
			else 
			{
				in.next();
				System.out.println("Invalid. Try again.");
			}
		}
		return validNum;
	}
	
	/** 
	 * Method returns a valid double input	
	 * @return valid input
	*/
	public static double getDecimal()
	{
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		double validNum = 0.0;
		
		while( !valid ) 
		{
			if(in.hasNextDouble())
			{
				validNum = in.nextDouble();
				valid = true;
			} 
			else 
			{
				in.next();
				System.out.println("Invalid decimal. Try again.");
			}
		}
		return validNum;
	}
}
