/**
* This abstract class inherits the class Entity and represents a Person
* @author Amy Yang
*/
public abstract class Person extends Entity
{
	/**Represents the name of the weapon*/
	private String weapon;

	/**Represents a battle cry*/
	private String quip;

	/**
	 * Constructor constructs an person with a name, weapon, equipment, and number of health points
	 * @param n name of the entity 
	 * @param h number of health points
	 * @param w name of weapon
	 * @param q battle cry
	*/
	public Person (String n, int h, String w, String q)
	{
		super(n,h);
		weapon = w;
		quip = q;
	}
	
	/**
	* Method prints out a catch phrase
	*/
	public void sayCatchPhrase ()
	{
		System.out.print(quip);
	}
	
	/**
	 * Method that allows another person to attach another entity
	 * @param e entity to be attacked
	 */
	public abstract void attack (Entity e);
	
	/**
	 * Method returns the person's weapon
	 * @return weapon
	 */
	public String getWeapon ()
	{
		return weapon;
	}
}
