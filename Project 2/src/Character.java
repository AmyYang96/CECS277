import java.io.Serializable;

/**
 * This class represents an abstract character
 * @author Amy Yang
 *
 */
public abstract class Character implements Serializable 
{
	/**Represents the character's name*/
	private String name;

	/**Represents the character's battle cry*/
	private String quip;
	
	/**Represents the character's level*/
	private int level;

	/**Represents the character's hit points*/
	private int hp;
	
	/**Represents the character's wealth*/
	private int gold;
	
	/**
	 * Constructor constructs a Character with the necessary info
	 * @param n the character's name
	 * @param q the character's quip
	 * @param h the character's hit points
	 * @param l the character's level
	 * @param g the character's gold
	 */
	public Character(String n, String q, int h, int l, int g)
	{
		name = n;
		quip = q;
		hp = h;
		level = l;
		gold = g;
	}
	
	/**
	 * Method allows a character to attack another character 
	 * @param c the character that recieves the attack
	 */
	public abstract void attack(Character c);
	
	/**
	 * Gets the character's name
	 * @return character's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the character's quip
	 * @return character's quip
	 */
	public String getQuip()
	{
		return quip;
	}
	
	/**
	 * Gets the character's hit points
	 * @return character's hit points
	 */
	public int getHp()
	{
		if (hp > 0)
		{
			return hp;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * Gets the character's level
	 * @return character's level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gets the character's gold
	 * @return character's gold
	 */
	public int getGold()
	{
		return gold;
	}
	
	/**
	 * Increments the character's level by 1
	 */
	public void increaseLevel()
	{
		level++;
	}
	
	/**
	 * Adds points to the character's hit points to heal
	 * @param h points to heal
	 */
	public void heal (int h)
	{
		hp = h;
	}
	
	/**
	 * Subtracts points from the character's hit points to take damage
	 * @param h points of damage
	 */
	public void takeDamage (int h)
	{
		hp = hp - h;
	}
	
	/**
	 * Adds gold to the character's wealth
	 * @param g gold to be added
	 */
	public void collectGold (int g)
	{
		gold = gold + g;
		System.out.println(name + " has collected " + g + " gold. " + name + " now has " + gold + " gold.");
	}
	
	/**
	 * Displays the character's condition
	 */
	public void display()
	{
		System.out.println(getName() + "'s level is now at Level " + level);
		System.out.println(getName() + " has " + gold + " gold");
		System.out.println(getName() + " now has " + hp + " hp");
	}
}
