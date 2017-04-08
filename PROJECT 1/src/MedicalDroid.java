/**
 * This class inherits from Droid and represents a medical droid
 * @author Amy Yang
 */
public class MedicalDroid extends Droid
{
	/**
	 * Constructs a medical droid with a name and health points. Droid only has 2 tasks to help
	 * @param n name
	 * @param hp health points
	 */
	public MedicalDroid (String n, int hp)
	{
		super(n,hp);
	}
	
	/**
	 * Droid will heal another entity for at most 2 times
	 * @param e the Entity that will be healed
	 */
	@Override
	public void doTask(Entity e)
	{
		if (getNumTasks() > 0)
		{
			useTask();
			e.modifyHp(e.getHp()+50);
			System.out.println(getName() + " heals "+ e.getName() + ". " + e.getName() + " now has " + e.getHp() + " hp.");
		}
		else
		{
			useTask();
			dies();
			modifyHp(0);
		}
	}

}
