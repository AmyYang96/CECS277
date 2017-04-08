import java.io.*;
import java.util.*;
import java.awt.Point;

/**
 * This class inherits from the Character class and represents a Hero
 * @author Amy Yang
 *
 */
public class Hero extends Character implements Serializable
{
	/**Represents the hero's items inventory*/
	private ArrayList <Item> items;
	
	/**Represents the hero's location*/
	private Point location;
	
	/**
	 * Constructor constructs a hero with the necessary info
	 * @param n the hero's name
	 * @param q the hero's quip
	 * @param h the hero's hit points
	 * @param l the hero's level
	 * @param g the hero's gold
	 * @param start the hero's starting point
	 */
	public Hero (String n, String q, int h, int l, int g, Point start)
	{
		super(n, q, h, l, g);
		items = new ArrayList <Item> ();
		location = start;
	}

	/**
	 * Attacks another character
	 * @param the character to attack
	 */
	@Override
	public void attack(Character c)
	{
		Random generator = new Random();
		int attackPoints = generator.nextInt(getHp()) + 1;
		c.takeDamage(attackPoints);
		
		System.out.println("\"" + getQuip() + "!\" " + getName() + " attacks the " + c.getName() + " for " + attackPoints + " hp.");
		System.out.println("The " + c.getName() + " now has " + c.getHp() + " hp.\n");
	}
	
	/**
	 * Returns the list of items
	 * @return ArrayList of Items
	 */
	public ArrayList <Item> getItems()
	{
		return items;
	}
	
	/**
	 * Adds the item to inventory and returns true if inventory has less than 5 items
	 * @param item item to be added
	 * @return true if inventory has less than 5 items, false otherwise
	 */
	public boolean pickUpItem(Item item)
	{
		if (items.size()<5)
		{
			items.add(item);
			System.out.println(getName() + " has successfully picked up " + item.getName() + ".");
			return true;
		}
		else return false;
	}
	
	/**
	 * Removes the item from the list
	 * @param item the item to be removed
	 */
	public void removeItem (Item item)
	{
		items.remove(item);
	}
	
	/**
	 * Removes the item from the list by index
	 * @param i the index of the item to be removed
	 */
	public void removeItem (int i)
	{
		items.remove(i);
	}
	
	/**
	 * Returns the hero's location
	 * @return the hero's location
	 */
	public Point getLocation ()
	{
		return location;
	}
	
	/**
	 * Sets the hero's new location
	 * @param p the location to be set
	 */
	public void setLocation (Point p)
	{
		location = p;
	}
	
	/**
	 * Gets the char at the north location & update location, will return 0 if out of bounds
	 * @param l level
	 * @return the char at that location
	 */
	public char goNorth (Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		Point north = new Point(x,y-1);
		char c = l.getRoom(north);
		
		if (c != '0' && c != '*')
		{
			setLocation(north);
		}
		
		return c;
	}
	
	/**
	 * Gets the char at the south location & update location, will return 0 if out of bounds
	 * @param l level
	 * @return the char at that location
	 */
	public char goSouth (Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		Point south = new Point(x,y+1);
		char c = l.getRoom(south);
		
		if (c != '0' && c != '*')
		{
			setLocation(south);
		}
		
		return c;
	}
	
	/**
	 * Gets the char at the east location & update location, will return 0 if out of bounds
	 * @param l level
	 * @return the char at that location
	 */
	public char goEast (Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		Point east = new Point(x+1,y);
		char c = l.getRoom(east);
		
		if (c != '0' && c != '*')
		{
			setLocation(east);
		}
		
		return c;
	}
	
	/**
	 * Gets the char at the west location & update location, will return 0 if out of bounds
	 * @param l level
	 * @return the char at that location
	 */
	public char goWest (Level l)
	{
		int x = (int) location.getX();
		int y = (int) location.getY();
		Point west = new Point(x-1,y);
		char c = l.getRoom(west);
		
		if (c != '0' && c != '*')
		{
			setLocation(west);
		}
		
		return c;
	}
}
