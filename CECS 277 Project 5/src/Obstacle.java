import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.*;

import java.io.*;

/**
 * This class represents an obstacle and inherits from the entity class
 * @author Amy Yang
 *
 */

public class Obstacle extends Entity 
{
	/**Type of obstacle*/
	private int type;
	
	/**
	 * Constructor inherits data members from entity class
	 * @param p point of the obstacle
	 * @param w width of the obstacle
	 * @param h hieght
	 * @param hPts hit points of the obstacle
	 * @param sp speed of the obstacle
	 * @param dir direction of the obstacle
	 * @param t type of obstcle
	 */
	public Obstacle(Point p, int w, int h, int hPts, int sp, int dir, int t) 
	{
		super(p, w, h, hPts, sp, dir);
		type = t;
		this.stopMoving();
	}
	
	/**
	 * Tests whether or not the obstacle collides another entity.
	 * True if it does, false otherwise
	 * @param e another entity
	 * @return True if it collides with another entity, false otherwise
	 */
	public boolean testCollision (Entity e)
	{
		if ( getRectangle().intersects( e.getRectangle()) == true )
		{
			
			e.stopMoving();
			
			if(e instanceof Hunter || e instanceof Quarry)
			{
				for(int spin =0; spin <4; spin++)
				{
					e.spinCCW();//turn opposite direction
				}
				
				e.toggleMoving();
				e.move();
			}
			return true;
		}
		else 
		{
			return false;
		}
	}

	/**
	 * DRAWS THE IMAGE OF THE obstacle
	 * @param g Graphics object
	 * @param p point to draw the obstacle at
	 * @param w width
	 * @param h height
	 * @param dir direction
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) 
	{
		if (type == 1 )//tree 
		{
			try 
			{
				BufferedImage image = ImageIO.read(new File("tree.png"));
			    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
		    } catch (IOException ie) 
			{
			    ie.printStackTrace();
			}
		}
		else //fallen quarry
		{
			try 
			{
				BufferedImage image = ImageIO.read(new File("skull.png"));
			    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
		    } catch (IOException ie) 
			{
			    ie.printStackTrace();
			}
		}

	}

}
