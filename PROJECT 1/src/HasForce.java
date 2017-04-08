/**
 * This interface allows Person objects to use force
 * @author Amy Yang
 *
 */
public interface HasForce 
{
	/**
	 * Method allows a Person to use force on another Entity
	 * @param e the entity that the person is using force on
	 */
	public void useForce(Entity e);
}
