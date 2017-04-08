/**
 * This class is a subclass of Person and implements the interface Healable. It represents a Rebel. 
 * @author Amy Yang
 *
 */
public class Rebel extends Person implements  Healable
{
	/**
	 * Constructor constructs a Rebel with a name, weapon, Quip, and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 * @param w name of weapon
	 * @param q battle cry
	*/
	public Rebel (String n, int h, String w, String q )
	{
		super(n,h,w,q);
	}
	
	/**
	 * Allows the rebel to attack another entity
	 * @param e Entity to attack
	 */
	@Override
	public void doTask (Entity e)
	{
		attack(e);
	}
	
	/**
	 * Allows the rebel to attach an enemy, randomizes the success of attacks
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void attack (Entity e)
	{
		
		System.out.println(getName() + " shoots " + e.getName() + " with his " + getWeapon() + ". " );
		
		if((int)(Math.random()*2)==0)
		{
			System.out.println(getName() + " hits " + e.getName() + ". " + e.getName() + " loses 20 points.");
			e.modifyHp(e.getHp()-20);
			System.out.println(getName() + " says ");
			sayCatchPhrase();
		}
		else
		{
			System.out.println(e.getName() +" blocked the attack. Try again.");
		}
	}
	
	/**
	 * Rebel is healed by a medical droid for 50 points
	 * @param md Medical Droid that heals the rebel
	 */
	@Override
	public void heal(MedicalDroid md)
	{
		md.doTask(this);
	}
}
