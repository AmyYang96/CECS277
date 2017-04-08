import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 * This main class runs the whoie game
 * @author Amy Yang
 */
public class Main 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner (System.in);
		
		Hero hero = null;
		Level map = new Level(); 
		ItemGenerator itemGenerator = new ItemGenerator();
		EnemyGenerator enemyGenerator = new EnemyGenerator();
		File f = new File( "hero.dat" );
		
		
		if( f.exists() ) 
		{
			try
			{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream( f ));
				hero = (Hero) in.readObject();
				in.close();
			}
			catch( IOException e ) 
			{
				System.out.println("Error processing file.");
			}
			catch( ClassNotFoundException e ) 
			{
				System.out.println("Could not find class.");
			}
			
			if (hero.getLevel() == 4)
			{
				System.out.println("Welcome back! You have completed all 3 levels. So, you will restart the gane.");
				map.generateLevel(1);
				hero = new Hero(hero.getName(), hero.getQuip(), 10, 1, 100, map.findStartlocation());
			}
		}
		else
		{
			String name;
			map.generateLevel(1);
			System.out.println("Welcome to the Dungeon of Dispair! Enter your name: ");
			name = input.nextLine();
			hero = new Hero (name, "Haaa Yaaa!", 20, 1, 100, map.findStartlocation());
		}
		
		map.generateLevel(hero.getLevel());
		
		while (hero.getHp() > 0 && hero.getLevel() <  4)
		{
			playLevel(hero, itemGenerator, enemyGenerator, map);
			
			if (hero.getHp() > 0)
			{
				saveProgress(hero,f);
			}
		}
		
		if ( hero.getLevel() == 4)
		{
			System.out.println("You have completed all 3 levels. You win!");
		}
		
	}
	
	/**
	 * Plays a level of the game
	 * @param hero the hero of the game (user)
	 * @param itemGenerator randomly generates items
	 * @param enemyGenerator randomly generates enemy monsters
	 * @param map map of the  level
	 */
	public static void playLevel (Hero hero, ItemGenerator itemGenerator, EnemyGenerator enemyGenerator, Level map )
	{
		int direction;
		char room = 'a';
		
		hero.setLocation(map.findStartlocation());
		System.out.println("May the odds be in your favor.\nYou are entering Level " + hero.getLevel() + ".");
		
		if ( !hero.getItems().isEmpty())
		{
			sellItems(hero);
		}
		
		while (hero.getHp() > 0 && room != 'f') 
		{
			direction = directionMenu(map, hero);
			room = goDirection(direction, hero, map);
			
			if(room == 'm')
			{
				int action, flee;
				Random generator = new Random();
				Enemy enemy = enemyGenerator.generateEnemy(hero.getLevel());
				
				System.out.println(hero.getName() +" encounters a " + enemy.getName() + ". " );
				
				enemy.attack(hero);			
				
				if(hero.getHp() > 0)
				{
					System.out.println(enemy.getName() + " has " + enemy.getHp() + " hp. ");
					
					System.out.println("\n What do you want to do?\n"
							+ "1. Run away \n2. Attack");
					action = UserInput.getInt();
					
					while (action <1 ||action > 2)
					{
						System.out.println("Invalid entry. Try again.");
						action = UserInput.getInt();
					}
					
					if (action == 1) //run away but only once at a time
					{
						flee = generator.nextInt(4)+1;
						room = goDirection(flee, hero, map);
						
						while (room == '0' || room == '*')
						{
							flee = generator.nextInt(4)+1;
							room = goDirection(flee, hero, map);
							
						}
						System.out.println(hero.getName() + " has successfully ran away.");
						if( room == 'm')
						{
							monsterFight(hero, enemy);
						}
					}
					else
					{
						monsterFight(hero, enemy);
					}
				}
				else
				{
					System.out.println(hero.getName() + " has died.");
				}
			}
			
			if(room == 'i')
			{
				itemRoom(hero, itemGenerator);
			}
			if (room == '0')
			{
				System.out.println("Cannor move in that direction. Try again.");
			}
			if (room == '*')
			{
				System.out.println("You have already been to that location.");
			}
		}
		
		if (hero.getHp() <= 0)
		{
			System.out.println("Game over!");
		}
		
		if (room == 'f')
		{
			System.out.println("Congratulations! You passed level " + hero.getLevel() + "!");
			
			hero.increaseLevel();
			hero.heal(20 * (int) (Math.pow(2,hero.getLevel()-1)));
			hero.display();
			
			if(hero.getLevel() < 4)
			{
				map.generateLevel(hero.getLevel());
			}
		}
	}

	/**
	 * Displays a list of directions and allows user input
	 * @param map the level map
	 * @param hero the hero
	 * @return the user's direction choice
	 */
	public static int directionMenu(Level map, Hero hero)
	{
		int i;
		map.displayMap(hero.getLocation());
		System.out.println("Choose a direction:");
		System.out.println("1. North \n2. South \n3. East \n4. West");
		i = UserInput.getInt();
		
		while (i < 1 || i > 4)
		{
			System.out.println("Invalid entry. Try again.");
			i = UserInput.getInt();
			
		}
		return i;
	}
	
	/**
	 * Goes to another location based on the direction choice and returns the char of the
	 * @param i choice of direction
	 * @param map the level map
	 * @param hero the hero
	 * @return a char of the room in the map
	 */
	public static char goDirection (int i, Hero hero, Level map )
	{
		char roomType;
		if (i == 1)
		{
			return roomType = hero.goNorth(map);
		}
		if (i == 2)
		{
			return roomType = hero.goSouth(map);
		}
		if (i == 3)
		{
			return roomType = hero.goEast(map);
		}
		if (i == 4)
		{
			return roomType = hero.goWest(map);
		}
		else
		{
		
			return '0';
		}
	}
	
	/**
	 * Activates a fight between the hero and the enemy
	 * @param hero the hero
	 * @param enemy monster to attack
	 */
	public static void monsterFight(Hero hero, Enemy enemy)
	{	
		while (hero.getHp() > 0 && enemy.getHp() > 0)
		{
			hero.attack(enemy);
			
			if(enemy.getHp() > 0)
			{
				enemy.attack(hero);
			}
			
			if (potionExists(hero) == true)
			{
				usePotion(hero);
			}
		}
		
		if (enemy.getHp() <= 0)
		{
			System.out.println(hero.getName() +" has successfully killed the " + enemy.getName() + ".");
			Item item = enemy.getItem();
			int gold = enemy.getGold();
			
			if(hero.pickUpItem(item) == false)
			{
				System.out.println("The inventory is full. The enemy's item will be sold.\n"
						+ "The item was sold for " + item.getValue() + "gold.\n"); 
				hero.collectGold(item.getValue());
			}
			System.out.println(hero.getName() +" sees gold from the " + enemy.getName() + "'s carcass.");
			hero.collectGold(gold);
		}
		else if(hero.getHp() <= 0)
		{
			System.out.println(hero.getName() + " has died");
		}
	}
	
	/**
	 * Allows the user to sell their items
	 * @param hero the items' owner
	 */
	public static void sellItems(Hero hero)
	{
		int index, action = 1;
		Item item;
		ArrayList <Item> list;
		
		System.out.println("Before you begin, you may sell your items.");
		
		while (action == 1 && !hero.getItems().isEmpty())
		{
			list = hero.getItems();
			System.out.println("Pick which item to sell:");
			for (int i = 0; i < list.size(); i++)
			{
				System.out.println( (i+1) + ". " + list.get(i).getName());
			}
			index = UserInput.getInt();
			item = hero.getItems().get(index-1);
			
			System.out.println("You have chosen to sell " + item.getName() + " for " + item.getValue() + " gold.");
			System.out.println("You currently have " + hero.getGold() + " gold. ");
			hero.collectGold(item.getValue());
			hero.removeItem(index-1);
			System.out.println("Do you want to sell another item? \n1. Yes \n2.No");
			action = UserInput.getInt();
			
			while (action <1 ||action > 2)
			{
				System.out.println("Invalid entry. Try again.");
				action = UserInput.getInt();
			}
		}
		
		if (hero.getItems().isEmpty())
		{
			System.out.println("You have sold all of your items.");
		}
		
	}
	
	/**
	 * Allows the hero use their potion
	 * @param hero the person who's going to use the potion
	 */
	public static void usePotion(Hero hero)
	{
		int action;
		System.out.println("Do you want to use your health potion?\n1. Yes \n2. No");
		action = UserInput.getInt();
		
		while (action <1 ||action > 2)
		{
			System.out.println("Invalid entry. Try again.");
			action = UserInput.getInt();
		}
		
		if (action == 1)
		{
			int index=0;
			ArrayList <Item> items = hero.getItems();
			
			for (int i=0; i< items.size(); i++)
			{
				if (items.get(i).getName().equalsIgnoreCase("Health Potion"))
				{
					index = i;
				}
			}
			
			hero.heal(20 * (int) (Math.pow(2,hero.getLevel()-1)));
			hero.removeItem(index);
			System.out.println(hero.getName() + " took the potion and now has " + hero.getHp() + " hp.");
		}
	}
	
	/**
	 * Returns true if the hero's item list has the potion
	 * @param hero the person who's going to use the potion
	 * @return true if potion exists, false otherwise
	 */
	public static boolean potionExists (Hero hero)
	{
		boolean exists = false;
		ArrayList <Item> items = hero.getItems();
		
		for (int i=0; i< items.size(); i++)
		{
			if (items.get(i).getName().equalsIgnoreCase("Health Potion"))
			{
				exists = true;
			}
		}
		return exists;
		
	}
	
	/**
	 * Runs when the user enter a room with an item. The item will be sold if inventory is full 
	 * @param hero the main character
	 * @param itemGenerator generates items
	 */
	public static void itemRoom(Hero hero, ItemGenerator itemGenerator)
	{
		int action;
		Item item = itemGenerator.generateItem();
		
		System.out.println(hero.getName() + " finds a " + item.getName());
		
		if (hero.getItems().size() < 5)
		{
			System.out.println("What do you want to do? \n1. Keep it \n2. Sell it");
			action = UserInput.getInt();
			
			while (action <1 ||action > 2)
			{
				System.out.println("Invalid entry. Try again.");
				action = UserInput.getInt();
			}
			
			if (action == 1) //keep it
			{
				hero.pickUpItem(item);
			}
			else
			{
				System.out.println("You decided to sell the item.");
				hero.collectGold(item.getValue());
			}	
		}
		else 
		{
			System.out.println("Your inventory is full. The item will be sold.");
			hero.collectGold(item.getValue());
		}
	}
	
	/**
	 * Saves Hero into a dat file
	 * @param hero the object to be saved
	 * @param f the file that object will save into
	 */
	public static void saveProgress(Hero hero, File f)
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream( f ));
			out.writeObject( hero );
			out.close();
			System.out.println("Saving progress...");
		}
		catch( IOException e ) 
		{
			System.out.println("Error processing file.");
		}
	}
}
