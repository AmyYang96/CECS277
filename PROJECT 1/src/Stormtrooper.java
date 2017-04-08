/**
 * This class is a subclass of Person and represents a Stormtrooper. 
 * @author Amy Yang
 *
 */
public class Stormtrooper extends Person 
{
	/**
	 * Constructor constructs a Stormtrooper with a name, weapon, quip, and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 * @param w name of weapon
	 * @param q battle cry
	*/
	public Stormtrooper (String n, int h, String w, String q )
	{
		super(n,h,w,q);
	}
	
	/**
	 * Allows the Stormtrooper to attack another entity
	 *@param e the Entity that will be attacked by
	 */
	@Override
	public void doTask (Entity e)
	{
		attack(e);
	}
	
	/**
	 * Allows the Stormtrooper to attack an enemy, randomizes the success of attacks
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void attack (Entity e)
	{
		
		System.out.println(getName() + " blasts " + e.getName() + ". " + e.getName() + " loses 20 points.");
		
		if((int)(Math.random()*2)==0)
		{
			System.out.println(getName() + " hits " + e.getName() + ". " + e.getName() + " loses 20 points.");
			e.modifyHp(e.getHp()-20);
			System.out.println(getName() + " says ");
			sayCatchPhrase();
		}
		else
		{
			System.out.println(getName() +" misses. ");
		}
	}
}
