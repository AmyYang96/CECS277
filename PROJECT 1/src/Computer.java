/**
 * This class is a subclass of Entity and represents a computer
 * @author Amy Yang
 *
 */
public class Computer extends Entity 
{
	/**
	 * Constructs a computer with a name and health points
	 * @param n name
	 * @param hp health points
	 */
	public Computer (String n, int hp)
	{
		super(n,hp);
	}

	/**
	 * Computer hacks another entity and deducts 20 hp
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void doTask(Entity e) 
	{
		System.out.println(getName() + " hacks " + e.getName() + ". " + e.getName() + " loses 20 hp.");
		e.modifyHp(e.getHp()-20);
	}
}
