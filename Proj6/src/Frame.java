import javax.swing.JFrame;

/**
 * This class represents a Frame and uses a panel. This runs the game in GUI.
 * @author Amy Yang
 *
 */
public class Frame extends JFrame
{
	/**
	 * Constructor adds game panel to the frame
	 */
	public Frame()
	{
		Client client = new Client();
		add(client);
		setTitle("Rock Paper Scissors Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	
	/**main*/
	public static void main(String[] args) 
	{
		new Frame();
		
	}

}
 