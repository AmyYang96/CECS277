/**
 * This class is a subclass of Person and implements the interface Healable and HasForce. It represents a Jedi. 
 * @author Amy Yang
 *
 */
public class Jedi extends Person implements HasForce, Healable
{
	/**Represents Jedi's saber color*/
	public String saberColor;
	
	
	/**
	 * Constructor constructs a Jedi with a name, weapon, quip, saber color, and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 * @param w name of weapon
	 * @param q battle cry
	 * @param c saber color
	*/
	public Jedi (String n, int h, String w, String q, String c)
	{
		super(n,h,w,q);
		saberColor = c;
	}
	
	/**
	 * Allows the Jedo to attack another entity
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void doTask (Entity e)
	{
		attack(e);
	}
	
	/**
	 * Allows the Jedi to attack an enemy with his saber, randomizes the success of attacks
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void attack (Entity e)
	{
		
		System.out.println(getName() + " attacks " + e.getName() + " with his " + saberColor + " saber. " );
		
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
	 * Jedi is healed by a medical droid for 50 points
	 * @param md the Medical Droid that will heal the Jedi
	 */
	@Override
	public void heal(MedicalDroid md)
	{
		md.doTask(this);
	}
	
	/**
	 * Jedi uses force on an enemy
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void useForce(Entity e)
	{
		System.out.println(getName() + " uses force on " + e.getName() + ". " );
		
		if((int)(Math.random()*2)==0)
		{
			System.out.println(getName() + " hits " + e.getName() + ". " + e.getName() + " loses 20 points.");
			e.modifyHp(e.getHp()-20);
			System.out.println(getName() + " says, ");
			sayCatchPhrase();
		}
		else
		{
			System.out.println(e.getName() +" blocked the attack. Try again.");
		}
		
	}
}
