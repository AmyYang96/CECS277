
import java.io.Serializable;
/**
 * This class represents a Pattern
 * @author Amy Yang
 *
 */
public class Pattern implements Serializable
{
	/**Represents a pattern*/
	public String pattern;
	
	/**
	 * Constructs Pattern with a string of characters that represents a pattern
	 * @param p a string of characters that represents a pattern
	 */
	public Pattern (String p)
	{
		pattern = p;
	}
	
	/**
	 * Method returns the pattern as a string
	 * @return the pattern as a string
	 */
	public String getPattern ()
	{
		return pattern;
	}
	
	/**
	 * Method overrides the hashCode method for this class
	 * @return the hash code
	 */
	@Override
	public int hashCode()
	{
		return pattern.hashCode();
	}
	
	/**
	 * Compares to pattern to see if they are equal
	 * @return a boolean value, true if they are equal, false otherwise
	 */
	@Override 
	public boolean equals( Object o ) 
	{  
		if( o == this ) 
		{ 
			return true; 
		}  
		if(!(o instanceof Pattern))
		{ 
			return false; 
		}  
		
		Pattern p = (Pattern) o;    
		return pattern.equals( p.getPattern() ); 
	}
}
