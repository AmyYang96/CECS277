import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This class represents a Frame and uses a panel that invokes the hunting game
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
		Panel panel = new Panel(); 
		Thread t = new Thread(panel);
		add(panel);
		setTitle("Oregon Trail Hunting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,850);
		t.start();
		setVisible(true);
	
	}
	
	/**main*/
	public static void main(String[] args) 
	{
		Frame frame = new Frame();
		
	}

}
