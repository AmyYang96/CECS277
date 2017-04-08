import java.io.Serializable;
import java.util.*;

/**
 * This class represents a computer that stores the user's patterns
 * @author Amy Yang
 *
 */
public class Computer implements Serializable
{
	/**Represents a hashmap of patterns*/
	public HashMap <Pattern, Integer> patterns;
	
	/**
	 * Constructor initializes hashmap
	 */
	public Computer ()
	{
		patterns = new HashMap <Pattern, Integer> ();
	}
	
	/**
	 * Stores the pattern into hash map. If the map already has the pattern then value will increment
	 * @param s pattern to be stored
	 */
	public void storePattern(String s)
	{
		s.toLowerCase();
		Pattern p = new Pattern (s);
		if (patterns.containsKey(p))
		{
			int value = patterns.get(p);
			value++;
			patterns.put(p, value);
		}
		else
		{
			patterns.put(p, 1);
		}
	}

	/**
	 * Method will make a move based on the prediction that is based on the user's patterns
	 * If there are no patterns, then it will be random
	 * @param pattern user's pattern as string
	 * @return a string of the computer's move
	 */
	public String makePrediction(String pattern)
	{
		String prediction="";
		
		if(pattern.length() >=2)
		{
			pattern = pattern.substring(1);
		}
		
		ArrayList <Integer> count = new ArrayList <Integer>();
		String rock = pattern + "r";
		String paper = pattern + "p";
		String sisscors = pattern + "s";
		

		Pattern r = new Pattern (rock);
		Pattern p = new Pattern (paper);
		Pattern s = new Pattern (sisscors);

		if (patterns.containsKey(r) || patterns.containsKey(p) || patterns.containsKey(s) )
		{
			
			if (patterns.containsKey(r))
			{
				count.add(patterns.get(r));
			}
			else
			{
				count.add(0);
			}
	
			if (patterns.containsKey(p))
			{
				count.add(patterns.get(p));
			}
			else
			{
				count.add(0);
			}
	
			if (patterns.containsKey(s))
			{
				count.add(patterns.get(s));
			}
			else
			{
				count.add(0);
			}
	
			int max = 0, index = 0;
			for(int i=0; i< count.size(); i++)
			{
				if(count.get(i) >= max)
				{
					max = count.get(i);
					index = i;
				}
			}
		
			if(index == 0) //rock
			{
			prediction = "paper";
			}
			if(index == 1)//paper
			{
			prediction = "scissors";
			}
			if(index == 2) // scissor
			{
			prediction = "rock";
			}

		} 
		else
		{
			Random generator = new Random();
			int num = generator.nextInt(3);
					
			switch (num)
			{
				case 0:
				prediction = "rock";
				break;
				case 1:
				prediction = "paper";
				break;
				case 2:
				prediction = "scissors";
				break;	
			}


		}

		
		return prediction;
	}
}
