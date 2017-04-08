import java.io.Serializable;
import java.util.Random;
/**
 * This class inherits the Character class and represents an enemy
 * @author Amy Yang
 *
 */
public class Enemy extends Character implements Serializable
{
	/**Represents the enemy's item*/
	private Item item;
	
	/**
	 * Constructor constructs a enemy with the necessary info
	 * @param n the enemy's name
	 * @param q the enemy's quip
	 * @param h the enemy's hit points
	 * @param l the enemy's level
	 * @param g the enemy's gold
	 * @param i the enemy's item
	 */
	public Enemy (String n, String q, int h, int l, int g, Item i)
	{
		super(n, q, h, l, g);
		item = i;
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
		
		System.out.println("\"" + getQuip() + "!\" " + getName() + " attacks " + c.getName() + " for " + attackPoints + " hp.");
		System.out.println(c.getName() + " now has " + c.getHp() + " hp.\n");
	}
	
	/**
	 * Returns the enemy's item
	 * @return the enemy's item
	 */
	public Item getItem()
	{
		return item;
	}
}
