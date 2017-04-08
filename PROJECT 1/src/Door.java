/**
 * This class is a subclass of Entity and represents a door
 * @author Amy Yang
 *
 */
public class Door extends Entity 
{
	/**
	 * Constructs a door with a name and health points
	 * @param n name
	 * @param hp health points
	 */
	public Door (String n, int hp)
	{
		super(n,hp);
	}

	/**
	 * Door blocks entity
	 * @param e the Entity that will be blocked
	 */
	@Override
	public void doTask(Entity e) 
	{
		System.out.println(e.getName() + " blocks " + getName() + ". ");
	}
}