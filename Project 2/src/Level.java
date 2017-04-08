import java.io.*;

import java.util.Scanner;
import java.awt.Point;

/**
 * This class represents a level. It can generate a level map by reading from a text file
 * @author Amy Yang
 *
 */
public class Level implements Serializable
{
	/**Represents a level of the game*/
	public char [][] level;
	
	/**
	 * Constructor initializes the array
	 */
	public Level ()
	{
		level = new char [4][4];
	}
	
	/**
	 * Generate a new array for the level
	 * @param levelNum the hero's level
	 */
	public void generateLevel (int levelNum)
	{
		String lev = Integer.toString(levelNum);
		
		try
		{
			Scanner read=new Scanner(new File("Level" + lev + ".txt"));
			
			do
			{
				String line = read.nextLine();
				String [] tokens = line.split(",");
				for(int i=0; i<tokens.length; i++)
				{
					for(int j = 0; j < tokens[i].length(); j++)
					{
						level[i][j] = tokens[i].charAt(j);
					}
				}
				
			}
			while(read.hasNextLine());
			
			read.close();
		}
		catch (FileNotFoundException fnf)
		{
			System.out.println("File was not found");
		}
	}
	
	/**
	 * Returns the character at the point
	 * @param p the location of the room
	 * @return a character of the room
	 */
	public char getRoom (Point p)
	{
		try
		{
			int row = (int) p.getY();
			int col = (int) p.getX();
			
			return level[row][col];
		}
		catch(ArrayIndexOutOfBoundsException oob)
		{
			return '0' ;
		}
	}
	
	/**
	 * Displays the level map
	 * @param p current location
	 */
	public void displayMap (Point p)
	{
		int row = (int) p.getY();
		int col = (int) p.getX();
		level[row][col] = '*'; 
		System.out.println("__________");
		for(int i = 0; i<level.length; i++)
		{
			System.out.print("|");
		    for(int j = 0; j<level[0].length; j++)
		    {
		        System.out.print(level[i][j] + " ");
		    }
		    System.out.print("|");
		    System.out.println();
		}
		System.out.println("__________\n");
	}
	
	/**
	 * Finds the starting location of the map
	 * @return a location as a point
	 */
	public Point findStartlocation ()
	{
		int x=0;
		int y=0;

		for(int i = 0; i<level.length; i++)
		{
		    for(int j = 0; j<level[0].length; j++)
		    {
		        if(level[i][j] == 's')
		        {
		        	x = j;
		        	y = i;
		        }
		    }
		}
		Point start = new Point(x,y);
		return start;
	}
}
