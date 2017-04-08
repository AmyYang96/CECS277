/**
 * This class is a subclass of Person and implements the interface HasForce
 * @author Amy Yang
 *
 */
public class SithLord extends Person implements HasForce 
{
	/**Represents SithLord's saber color*/
	private String saberColor;
	
	
	/**
	 * Constructor constructs a Sith Lord with a name, weapon, quip, saber color, and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 * @param w name of weapon
	 * @param q battle cry
	 * @param c saber color
	*/
	public SithLord (String n, int h, String w, String q, String c)
	{
		super(n,h,w,q);
		saberColor = c;
	}
	
	/**
	 * Allows the Sith Lord to attack another entity
	 */
	@Override
	public void doTask (Entity e)
	{
		attack(e);
	}
	
	/**
	 * Allows the Sith Lord to attack an enemy
	 */
	@Override
	public void attack (Entity e)
	{
		System.out.println(getName() + " slashes " + e.getName() + " with his " + saberColor 
				+ " saber. "+ e.getName() + " loses 40 points.");
		
		e.modifyHp(e.getHp()-40);
		System.out.println(getName() + " says ");
		sayCatchPhrase();
	}
	
	/**
	 * Hurts the enemy by using force
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void useForce(Entity e)
	{
		System.out.println(getName() + " slashes " + e.getName() + " with his Force." + e.getName() + " loses 40 points.");
		
		e.modifyHp(e.getHp()-50);
		System.out.println(getName() + " says ");
		sayCatchPhrase();
		
		
	}
	
}
