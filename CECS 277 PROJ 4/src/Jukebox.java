import java.util.Scanner;
import java.io.*;

/**
 * This class represents a JukeBox object. It does not have a real main
 * @author Amy Yang
 *
 */
public class Jukebox 
{
	/**Represent a list of songs sorted in order by rating*/
	private Heap <Song> playList;

	
	/**
	 * The constructor initializes the heap
	 */
	public Jukebox()
	{
		playList = new Heap <Song>();
		
	}
	
	/**
	 * The method mimics main. When the object is  called from main, it only uses this method
	 */
	public void main()
	{
		readSongs();
		int choice = menu();
		
		while (choice >=1 && choice <=5)
		{
			if (choice == 1) //display playlist
			{
				if (playList.isEmpty())
				{
					System.out.println("There are no songs in the playlist.\n");
				}
				else
				{
					playList.printHeap();
				}
			}
			
			if (choice == 2) //display top song
			{
				if (playList.isEmpty())
				{
					System.out.println("There are no songs in the playlist.\n");
				}
				else
				{
					System.out.println(playList.getObjectAt(0));
				}
			}
			
			if (choice == 3) //add new song
			{
				addSong();
			}
			
			if (choice == 4) //remove current song display new song
			{
				if (playList.isEmpty())
				{
					System.out.println("There are no songs in the playlist.\n");
				}
				else
				{
					Song current = playList.removeMax();
					
					if (playList.isEmpty())
					{
						System.out.println("There are no songs in the playlist.\n");
					}
					else
					{
						System.out.println("Removed:\n" + current);
						
						System.out.println("\nNext: \n" + playList.getObjectAt(0));
					}
				}
			}
			if (choice == 5)//rerate next song
			{
				reRateSong();
			}
			
			choice = menu();
		}
	
	}
	/**
	 * Read in from file the list of songs
	 */
	private void readSongs()
	{
		try
		{
			Scanner read=new Scanner(new File("playlist.txt"));
			
			do
			{		
				String line = read.nextLine();
				String [] tokens = line.split(","); //use commas to split up the data in the line
				
				Song song = new Song(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]) ); // input data in constructor
				
				
					playList.addMaxObject(song);
			}while(read.hasNextLine());
				
			read.close();
		} 
		catch(FileNotFoundException fnf){
		
			System.out.println("File was not found");
		}
	}
	
	/**
	 * Prints the menu and gets user input
	 * @return integer of input
	 */
	private int menu()
	{
		int choice;
		System.out.println("Please choose a function: ");
		System.out.println("1. Display the list of songs");
		System.out.println("2. Display the current song");
		System.out.println("3. Add a new song to the list");
		System.out.println("4. Play the next song");
		System.out.println("5. Re-rate next song");
		System.out.println("6. Quit");
		
		choice = UserInput.getInt();
		
		while (choice <1 || choice > 6)
		{
			System.out.println("Invalid entry. Please choose a function: ");
			choice = UserInput.getInt();
		}
		return choice;
	}
	private void addSong()
	{
		String title, artist, album;
		int rating;
		Scanner scan = new Scanner (System.in);
		
		System.out.print("Enter song title: ");
		title = scan.nextLine();
		
		System.out.print("Enter artist: ");
		artist = scan.nextLine();
		
		System.out.print("Enter album name: ");
		album = scan.nextLine();
		
		System.out.print("Enter rating: ");
		rating = UserInput.getInt();
		
		while(rating < 1 || rating > 5)
		{
			System.out.print("Invalid entry. Enter rating: ");
			rating = UserInput.getInt();
		}
		
		playList.addMaxObject(new Song(title, artist, album, rating ));
		
	}
	
	/**
	 * rerates the next song in the heap
	 */
	private void reRateSong()
	{
		if (playList.isEmpty())
		{
			System.out.println("There are no songs in the playlist.\n");
		}
		else
		{
			int rating;
			Song current = playList.removeMax();
			System.out.println("Removed current song:\n" + current);
			Song next = playList.removeMax();
			System.out.println("\nNext up: \n" + next );
			
			System.out.print("Enter new rating: ");
			rating = UserInput.getInt();
			
			while(rating < 1 || rating > 5)
			{
				System.out.print("Invalid entry. Enter new rating: ");
				rating = UserInput.getInt();
			}
			
			playList.addMaxObject(new Song(next.getTitle(), next.getArtist(), next.getAlbum(), rating));
		}
	}
}
