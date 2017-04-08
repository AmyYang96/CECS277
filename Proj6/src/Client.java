import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * This class represents the client in the network. It creates a GUI Panel
 * @author Amy Yang
 *
 */
public class Client extends JPanel implements  ActionListener
{
	/**Displays the user's input*/
	private JLabel selection;
	
	/**Displays the computer's move*/
	private JLabel computerPlay;
	
	/**Represents the socket connection*/
	private Socket sock;
	
	/**Represents user's input*/
	private String clientPlay;
	
	/**Represents the computer's prediction*/
	private String compPlay;
	
	/**Represents the user's wins*/
	private int playerWins;
	
	/**Represents computer's wins*/
	private int computerWins;
	
	/**Displays  the user & computer's score*/
	private JLabel winLabel;
	
	/**Displays who won each round*/
	private JLabel winMessage;
	
	/**
	 * Constuctor creates the panel for the game with JButtons and JLabels and establishes socket connection
	 */
	public Client()
	{
		try
		{
			sock = new Socket("localhost", 5002);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
		computerPlay = new JLabel ("Computer played:");
		winMessage = new JLabel ("Game has not been played yet.");
		
		playerWins = 0;
		computerWins = 0;
		clientPlay = "";
		compPlay = "";
		
		JButton rockButton =new JButton();
		rockButton.setIcon(new ImageIcon("rock.jpg"));
		rockButton.setActionCommand("rock");
		
		JButton paperButton =new JButton();
		paperButton.setIcon(new ImageIcon("paper.png"));
		paperButton.setActionCommand("paper");
		
		JButton scissorsButton =new JButton();
		scissorsButton.setIcon(new ImageIcon("scissors.jpg"));
		scissorsButton.setActionCommand("scissors");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		 winLabel = new JLabel("You: " + playerWins + "  vs.  Computer: " + computerWins);
		JLabel stats = new JLabel("Stats:");
		stats.setAlignmentX(CENTER_ALIGNMENT);
		winMessage.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		rockButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		rockButton.addActionListener(this);
		paperButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		paperButton.addActionListener(this);
		scissorsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		scissorsButton.addActionListener(this);
		
		selection = new JLabel("You played:");
		selection.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		computerPlay = new JLabel ("Computer played:");
		computerPlay.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		winLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		add(rockButton);
		add(paperButton);
		add(scissorsButton);
		add(stats);
		add(selection);
		add(computerPlay);
		add(winLabel);
		add(winMessage);
	}

	/**
	 * Sends message to the server when the button is clicked
	 * @param a an ActionEvent object
	 */
	public void actionPerformed(ActionEvent a)
	{
		if(a.getActionCommand()=="rock")
		{
			selection.setText("You played: rock");
			clientPlay = "r";
			getPrediction();
			
		}
		else if(a.getActionCommand()=="paper")
		{
			selection.setText("You played: paper");
			clientPlay = "p";
			getPrediction();
		}
		else if(a.getActionCommand()== "scissors")
		{
			selection.setText("You played: scissors");
			clientPlay = "s";
			getPrediction();
		}
	}
	
	/**
	 * Sends user's input to server and receives the server's message
	 */
	public void getPrediction()
	{
		try 
		{

			PrintStream out = new PrintStream(sock.getOutputStream());
			
			System.out.println("running...");
			out.println(clientPlay); 
			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			compPlay = in.readLine();
		
			computerPlay.setText("Computer played: " + compPlay);
			awardPoints();
		}
		catch( IOException e ) 
		{
			System.out.println(e );
		}
	
	}
	
	/**
	 * Awards points based on the plays of each player
	 */
	public void awardPoints()
	{
		if ( (clientPlay.equalsIgnoreCase("S") && compPlay.equalsIgnoreCase("scissors")) ||
				(clientPlay.equalsIgnoreCase("R") && compPlay.equalsIgnoreCase("rock")) ||
				(clientPlay.equalsIgnoreCase("P") && compPlay.equalsIgnoreCase("paper")) )
		{
			winMessage.setText("It's a tie!");
		}
		
		if ( (clientPlay.equalsIgnoreCase("R") && compPlay.equalsIgnoreCase("scissors")) ||
				(clientPlay.equalsIgnoreCase("P") && compPlay.equalsIgnoreCase("rock")) ||
				(clientPlay.equalsIgnoreCase("S") && compPlay.equalsIgnoreCase("paper")) )
		{
			playerWins++;
			winLabel.setText("You: " + playerWins + "  vs.  Computer: " + computerWins);
			winMessage.setText("You win!");
		
		}
		
		if ( (clientPlay.equalsIgnoreCase("P") && compPlay.equalsIgnoreCase("scissors")) ||
				(clientPlay.equalsIgnoreCase("S") && compPlay.equalsIgnoreCase("rock")) ||
				(clientPlay.equalsIgnoreCase("R") && compPlay.equalsIgnoreCase("paper")) )
		{
			computerWins++;
			winLabel.setText("You: " + playerWins + "  vs.  Computer: " + computerWins);
			winMessage.setText("Computer wins!");
		}
	}
}
