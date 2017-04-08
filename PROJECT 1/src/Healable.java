/**
 * This interface allows a Jedi or Rebel to heal
 * @author Amy Yang
 *
 */
public interface Healable 
{
	/**
	 * Method heals a Jedi or Rebel
	 * @param md Medical Droid used to heal
	 */
	void heal(MedicalDroid md);
}
