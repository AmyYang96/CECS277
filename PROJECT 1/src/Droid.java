/**
 * This abstract class inherits Entity and represents a droid
 * @author AMY's SUITE
 *
 */
public abstract class Droid extends Entity 
{
	/**the number of helps*/
	private int numTasks;
	
	/**
	 * Constructs a droid with a name and health points. Droid only has 2 tasks to help
	 * @param n name
	 * @param hp health points
	 */
	public Droid (String n, int hp)
	{
		super(n,hp);
		numTasks = 2;
	}
	
	/**
	 * Gets how many helps the droid has left
	 * @return number of helps
	 */
	public int getNumTasks()
	{
		return numTasks;
	}
	
	/**
	 * Method decrements the number of helps left.
	 */
	public void useTask()
	{
		if (numTasks == 0)
		{
			System.out.println("This droid cannot be used because you used it 2 times.");
		}
		else
		{
			numTasks = numTasks -1;
		}
	}
}
