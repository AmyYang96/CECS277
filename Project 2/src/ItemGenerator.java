import java.util.*;
import java.io.*;

/**
 * This class reads a list of items from a file and generates items to be used in the game
 * @author Amy Yang
 *
 */
public class ItemGenerator implements Serializable
{
	/**Represents a list of items*/
	public ArrayList <Item> itemList;
	
	/**
	 * Constructor reads in a list of items from file and adds them to an arraylist
	 */
	public ItemGenerator()
	{
		itemList = new ArrayList <Item>();
		try
		{
			Scanner read=new Scanner(new File("ItemList.txt"));
			
			do
			{
				String line = read.nextLine();
				String [] tokens = line.split(","); //use commas to split up the data in the line
				int gold =Integer.parseInt(tokens[1]);
				Item item = new Item(tokens[0], gold ); // input data in constructor
				
				itemList.add(item);
				
			}while(read.hasNextLine());
				
			read.close();
		} 
		catch(FileNotFoundException fnf){
		
			System.out.println("File was not found");
		}
	}
	
	/**
	 * Randomly gets an item from the list
	 * @return an item from the ArrayList
	 */
	public Item generateItem()
	{
		Random generator = new Random();
		int index = generator.nextInt(itemList.size());
		
		return itemList.get(index);
	}
}
