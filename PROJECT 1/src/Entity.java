/**
 * This abstract class creates a general entity
 * @author Amy Yang
 *
 */
public abstract class Entity 
{
	/**Name of the entity*/
	private String name;
	
	/**Represents health points*/
	private int hp;
	
	/**Represents if the entity is active or not*/
	private boolean active;
	
	/**Represents the entity's job*/
	private String task;
	
	/**
	 * Constructor constructs an entity with a name and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 */
	public Entity (String n, int h)
	{
		name = n;
		hp = h;
		active = true;
	}
	
	/**
	 * Abstract method that does something to another entity
	 * @param e another entity
	 */
	public abstract void doTask (Entity e);

	/**
	 * Method returns name of the entity
	 * @return name of the entity
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Method returns health points of the entity
	 * @return health points of the entity
	 */
	public int getHp()
	{
		return hp;
	}
	
	/**
	 * Method sees if the entity is active or not
	 * @return true if it is active, false otherwise
	 */
	public boolean getActive()
	{
		if(getHp() >= 1)
		{
			active = true;
		}
		else
		{
			active = false; 
		}
		
		return active;
	}
	
	/**
	 * Method modifies the entiity's health points
	 * @param h new health points
	 */
	public void modifyHp (int h)
	{
		hp = h;
	}
	
	/**
	 * Method returns the entity's task
	 * @return the entity's task
	 */
	public String getTask ()
	{
		return task;
	}
	
	/**
	 * Method sets the entity's task
	 * @param t task to be set
	 */
	public void setTask (String t)
	{
		task = t;
	}
	
	/**
	 * Method makes entity inactive
	 */
	public void dies()
	{
		active = false;
	}
}
