import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;
/**
 * This class generates an enemy
 * @author Amy Yang
 *
 */
public class EnemyGenerator implements Serializable
{
	/**Represents a a list of enemies*/
	public ArrayList <Enemy> enemyList;
	
	/**
	 * Constructs the Enemy List by reading from file, then randomizes an item and gold for the enemy
	 */
	public EnemyGenerator ()
	{
		enemyList = new ArrayList <Enemy>();
		ItemGenerator itemList = new ItemGenerator();
		
		try
		{
			Scanner read=new Scanner(new File("EnemyList.txt"));
			
			do
			{
				Random generator = new Random();
				int gold = (generator.nextInt(10) +1) * 10; // randomizes by multiples of 10 up to 100
				
				Item item = itemList.generateItem();
				String line = read.nextLine();
				String [] tokens = line.split(","); //use commas to split up the data in the line
				
				Enemy enemy= new Enemy(tokens[0], tokens[1], Integer.parseInt(tokens[2]), 1,  gold, item);
				
				enemyList.add(enemy);
				
			}while(read.hasNextLine());
				
			read.close();
		} 
		catch(FileNotFoundException fnf){

		
			System.out.println("File was not found");
		}
	}
	
	/**
	 * Randomly generates and returns an enemy from list
	 * @param level level of the hero
	 * @return an enemy object
	 */
	public Enemy generateEnemy (int level)
	{

		Random generator = new Random();
		ItemGenerator itemList = new ItemGenerator();
		int gold = (generator.nextInt(10) +1) * 10; // randomizes by multiples of 10 up to 100
		
		Item item = itemList.generateItem();
		
		int index = generator.nextInt(enemyList.size());
		
		Enemy enemy = enemyList.get(index);
		Enemy copy = new Enemy (enemy.getName(), enemy.getQuip(), enemy.getHp()*level, enemy.getLevel(), gold, item );
		
		while (copy.getLevel() < level)
		{
			copy.increaseLevel();
		}
		return copy;
	}
}
