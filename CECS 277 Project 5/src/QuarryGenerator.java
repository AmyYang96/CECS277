import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * This class reads in a list of quarries and randomly generates a quarry from the list
 * @author Amy Yang
 *
 */
public class QuarryGenerator 
{
	/**
	 * Represents a list of quarries
	 */
	public ArrayList<Quarry> quarry;
	
	/**
	 * Constructor reads in a list of quarries and adds to arrayList
	 */
	public QuarryGenerator()
	{
		quarry = new ArrayList <Quarry>();
		
		try
		{
			Scanner read=new Scanner(new File("quarryList.txt"));
			Random generator = new Random();
			
			do
			{
				String line = read.nextLine();
				String [] tokens = line.split(",");
				
				String name = tokens[0];
				int weight = Integer.parseInt(tokens[1]);
				int hp = Integer.parseInt(tokens[2]);
				int direction = Integer.parseInt(tokens[3]);
				int width = weight / 2;
				int height = width;
				int speed = Math.round( (20/weight) + 2); 
				int x = generator.nextInt(1100)+20;
				quarry.add(new Quarry(new Point(x,0), width, height, hp, speed, direction, name, weight));
			}while(read.hasNextLine());
				
			read.close();
		} 
		catch(FileNotFoundException fnf)
		{
		
			System.out.println("File was not found");
		}

	}
	
	/**
	 * Generates and returns a random quarry
	 * @return a random quarry
	 */
	public Quarry generateQuarry()
	{
		Random generator = new Random();
		int index = generator.nextInt(quarry.size());
		int direction = generator.nextInt(6)+1;
		
		while(direction == 1 || direction == 3)
		{
			direction = generator.nextInt(6)+1;
		}
		
		Quarry q = quarry.get(index);
		Quarry copy = new Quarry(q.getLocation(), q.getWidth(), q.getHeight(), q.getHp(), q.getSpeed(), direction, q.getName(), q.getWeight());
		return copy;
	}

}
