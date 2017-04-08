import java.awt.event.KeyEvent;


import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.util.*;
import java.net.*;

import javax.sound.sampled.*;

/**
 * This class represents a Panel that contains the game 
 * @author Amy Yang
 *
 */
public class Panel extends JPanel implements Runnable, KeyListener, MouseMotionListener,
		MouseListener 
{
	/**Represents a generator that generates a random quarry*/
	private QuarryGenerator qGen;
	
	/**Represents a hunter*/
	private Hunter hunter;
	
	/**Represents a list of quarries added in the game*/
	private ArrayList<Quarry> quarries;
	
	/**Represents a list of obstacles added in the game */
	private ArrayList <Obstacle> obstacles;
	
	/**Represents a bullet counter that counts the number of shots fired*/
	private int bulletCounter = 0;
	
	/**Represents the number of quarries shot*/
	private int quarriesDead =0;
	
	/**Represents accuracy*/
	private double percentHit;
	
	/**
	 * Constructor sets up the panel by adding the necessary components
	 */
	public Panel()
	{
		setBackground(Color.BLACK);
		JOptionPane.showMessageDialog(this, "Welcome! \nYou have 10 bullets to shoot.");
		hunter = new Hunter(new Point(600,450), 50,50,50,25,1);

		obstacles = new ArrayList <Obstacle>();
		qGen =new QuarryGenerator();
		quarries = new ArrayList<Quarry>();
		
		Random generator = new Random ();
		int numObstacles = generator.nextInt(5) + 1;
		
		for(int i =0; i<numObstacles; i++) //randomize obstacle location
		{
			int xCoor = generator.nextInt(1100)+1;
			int yCoor = generator.nextInt(790) + 1;
			
			obstacles.add(new Obstacle(new Point(xCoor, yCoor), 64, 64, 1, 0, 1, 1));
		}


		addKeyListener(this);
		setFocusable(true);
		
		addMouseMotionListener(this);
		setFocusable(true);
		
		addMouseListener(this);
		setFocusable(true);
		

	}
	
	/**
	 * Tests the collisions between hunter, obstacle and quarries
	 */
	public void testCollisions()
	{
		
		for (int i=0; i<obstacles.size(); i++)
		{
			obstacles.get(i).testCollision(hunter); //obstacle and hunter
			
			if(!(hunter.getBullet() == null))//bullet and obstacles
			{
				hunter.testHit(obstacles.get(i)); 
				obstacles.get(i).testCollision(hunter.getBullet());
			}
			
			for (int j=0; j<quarries.size(); j++)
			{
				obstacles.get(i).testCollision(quarries.get(j)); //quarries bounce off from obstacles
				hunter.ifMauled(quarries.get(j)); //checks if hunter runs into a quarry --> dies if it does
				if(!(hunter.getBullet() == null))
				{
					if (hunter.testHit(quarries.get(j)))
					{ //quarry becomes an obstacle if shot and is removed from quarry list
						obstacles.add(new Obstacle(quarries.get(j).getLocation(), quarries.get(j).getWidth(), quarries.get(j).getHeight(), quarries.get(j).getHp(),1, 0, 2));
						quarries.remove(j);
						quarriesDead++;
					}
				}
			}
		}
		
	}
	
	/**
	 * Draws the components in the panel
	 * @param g Graphics object
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString("Shots fired: " + bulletCounter + " / 10 shots ", 20,20);
		g.drawString("Quarries shot: "+ quarriesDead, 20, 35);
		
		if (bulletCounter == 0)
		{
			g.drawString("Accuracy: "+ percentHit +  "%", 20, 48);
		}
		else if(bulletCounter > 0)
		{
			percentHit =  (double) quarriesDead / bulletCounter *100;
			g.drawString("Accuracy: "+ Math.round(percentHit) +  "%", 20, 48);
		}
		g.drawRect(10, 5, 150, 50);

		for (int i=0; i<obstacles.size(); i++)
		{
			obstacles.get(i).draw(g, obstacles.get(i).getLocation(), obstacles.get(i).getWidth(), obstacles.get(i).getHeight(), obstacles.get(i).getDirection());			
		}
		
		if (quarries.isEmpty())
		{
			quarries.add(qGen.generateQuarry());
			quarries.get(0).update(g);
			
		}
		else
		{//use the last one in the list
			quarries.get(quarries.size()-1).update(g);
			
			if (quarries.get(quarries.size()-1).getLocation().getX() < 0 || quarries.get(quarries.size()-1).getLocation().getY()<0||
					quarries.get(quarries.size()-1).getLocation().getX() > getWidth() || quarries.get(quarries.size()-1).getLocation().getY() > getHeight())
			{
				quarries.add(qGen.generateQuarry());	
				quarries.get(quarries.size()-1).update(g);
				
			}
		}
		
		hunter.update(g);
		
		if (!(hunter.getBullet() == null))
        {
            
			hunter.getBullet().update(g);
            
        }
	}
	
	/**
	 
		* From http://stackoverflow.com/questions/859691/a-fast-way-to-determine-whether-a-componet-is-found-in-jpanel
	 * @param c
	 * @return
	 */

	/**
	 * Runs the thread that enables animation
	 */
	public void run() 
	{
		while(hunter.isDead()==false)
		{
			repaint();
			testCollisions();
			try 
			{
				Thread.sleep(50);
			} catch ( InterruptedException e ) {}
		}
	}
	
	/**
	 * Press spacebar, enter, or arrow keys to shoot, move, or rotate
	 * @param e KeyEvevent
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if (hunter.isDead())
			{
				JOptionPane.showMessageDialog(this, "Game Over! You have died.");
			}
			else
			{
				hunter.toggleMoving();
			}
			
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (hunter.isDead())
			{
				JOptionPane.showMessageDialog(this, "Game Over! You have died.");
			}
			else
			{
				hunter.spinCW();
				System.out.println("Direction: " + hunter.getDirection());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (hunter.isDead())
			{
				JOptionPane.showMessageDialog(this, "Game Over! You have died.");
			}
			else
			{
				hunter.spinCCW();
				System.out.println("Direction: " + hunter.getDirection());
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (bulletCounter >= 10 || hunter.isDead())
			{
				hunter.takeHit();
				JOptionPane.showMessageDialog(this, "Game Over! You have used all your bullets.");
			}
			else
			{
				
				hunter.fireBullet();
				hunter.getBullet().setDirection(hunter.getDirection());
				play("Gunshot.wav");
				bulletCounter++;
			}
			
		}

	}

	/**
	 * Release enter key to stop moving
	 * @param e KeyEvent
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			hunter.stopMoving();
		}		
	}

	/**
	 * Not used
	 * @param e KeyEvent
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * Read in audio fille
	 * @param filename audio file name
	 */
	public static void play(String filename)
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
	
			clip.open( AudioSystem.getAudioInputStream(new File(filename)));
	
			clip.start();
	
		}catch(LineUnavailableException e){}
		 catch(IOException e){}
		 catch(UnsupportedAudioFileException e){}
	}

	/**
	 * Click to fire bullet
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (bulletCounter >= 10 || hunter.isDead())
		{
			hunter.takeHit();
			JOptionPane.showMessageDialog(this, "Game Over! You have used all your bullets.");
		}
		else
		{
			hunter.fireBullet();
			hunter.getBullet().setDirection(hunter.getDirection());
			play("Gunshot.wav"); // play Sound
			bulletCounter++;
		}
	}
	
	/**
	 * Not implemented
	 * @param e NouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Not implemented
	 * @param e NouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Not implemented
	 * @param e NouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * Not implemented
	 * @param e NouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * Not implemented
	 * @param e NouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent e) {}

	/**
	 * Changes the direction of hunter
	 * @param MouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent e) 
	{
		Point click =  e.getPoint();
		Point location = hunter.getLocation();
		int dx = (int) click.getX() - ((int) location.getX());
		int dy = (int) click.getY() - ((int) location.getY());
		double angle = Math.atan2(dy, dx) - Math.PI/2;
		double theta = Math.toDegrees(angle)+180;

		if (theta >= -20 && theta < 25 )
		{
			hunter.setDirection(1);//set north
		}
		else if (theta >= 25 && theta <70 )
		{
			hunter.setDirection(2);//set northeast
		}
		else if (theta >=70 && theta < 115 )
		{
			hunter.setDirection(3);//set east
		}
		else if (theta >= 115 && theta < 160 )
		{
			hunter.setDirection(4);//set southeast
		}
		else if (theta >= 160  && theta < 205 )
		{
			hunter.setDirection(5);//set south
		}
		else if (theta >= 205 && theta < 250 )
		{
			hunter.setDirection(6);//set southwest
		}
		else if ( theta >= 250 || theta < -65)
		{
			hunter.setDirection(7);//set west
		}
		else 
		{
			hunter.setDirection(8);//set northwest
		}	
	}
}
