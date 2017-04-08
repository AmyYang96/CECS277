import java.net.*;
import java.io.*;

/**
 * This class represents a server that has a computer object
 * @author AMY's SUITE
 *
 */
public class Computini 
{
		public static void main (String[] args)
	{
		System.out.println("Connecting....");
		
		Computer computer = new Computer();
		File f = new File( "computer.dat" );
	
		if( f.exists() ) 
		{
			try
			{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream( f ));
				computer = (Computer) in.readObject();
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
		}
		
		
		try 
		{
			String pattern = "";
			ServerSocket server = new ServerSocket(5002);
			Socket	socket = server.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			while(true ) 
			{
				String predict = computer.makePrediction(pattern);
				String clientPlay = in.readLine();				
				pattern = pattern + clientPlay;
				
				System.out.println("You play: " +clientPlay);
				//store different pattern length
				if(pattern.length() == 3 || pattern.length() == 4)
				{
					computer.storePattern(pattern);
					System.out.println("Pattern stored");
				}
				if (pattern.length() > 4)
				{
					pattern = pattern.substring(1); //last 4 moves 
					computer.storePattern(pattern);
					System.out.println("Pattern stored");
				}
				
				out.println(predict);
				
				try
				{
					ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream( f ));
					outFile.writeObject( computer );
					outFile.close();
					System.out.println("\nSaving pattern...\n\n");
				}
				catch( IOException e ) 
				{
					System.out.println("Error processing file.");
				}	
			}
		}
		catch( IOException e ) 
		{
			System.out.println(e);
		}
	
	}
}
