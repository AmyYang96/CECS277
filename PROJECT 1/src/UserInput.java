import java.util.*;

/**
 * This class contains static methods that check user input
 * @author Amy Yang
 *
 */
public class UserInput 
{
	/** Method returns a valid integer input
	@return valid input */
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
}
