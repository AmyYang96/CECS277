/**
 * This class inherits from Droid and represents an astromech droid
 * @author Amy Yang
 */
public class AstromechDroid extends Droid
{
	/**
	 * Constructs an astromech droid with a name and health points. Droid only has 2 tasks to help
	 * @param n name
	 * @param hp health points
	 */
	public AstromechDroid (String n, int hp)
	{
		super(n,hp);
	}
	
	/**
	 * Droid will attack another entity for at most 2 times.
	 * @param e the Entity that will be attacked by
	 */
	@Override
	public void doTask(Entity e)
	{
		if (getNumTasks() > 0)
		{
			useTask();
			e.modifyHp(e.getHp()-e.getHp());
			System.out.println(getName() + "destroys "+ e.getName() + ". " + e.getName() + " now has " + e.getHp() + " hp.");
		}
		else
		{
			useTask();
			dies();
			modifyHp(0);
		}
	}
}
