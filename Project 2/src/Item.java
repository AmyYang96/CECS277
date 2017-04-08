import java.io.Serializable;
/**
 * This class represents an Item in the game
 * @author Amy Yang
 *
 */
public class Item implements Serializable
{
	/**Represents the name of the item*/
	public String name;

	/**Represents the item's gold value*/
	public int goldValue;
	
	/**
	 * Constructor constructs an item with a name and value
	 * @param n the item's name
	 * @param v the item's value
	 */
	public Item (String n, int v)
	{
		name = n;
		goldValue = v;
	}
	
	/**
	 * Returns the item's name
	 * @return the item's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the item's value
	 * @return the item's value
	 */
	public int getValue()
	{
		return goldValue;
	}
	
}
