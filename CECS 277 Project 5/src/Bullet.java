import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents a bullet and inherits from entity class
 * @author Amy Yang
 *
 */
public class Bullet extends Entity
{
	/**
	 * Constructor inherits data members from entity class
	 * @param p point of the bullet
	 * @param w width of the bullet
	 * @param h hieght
	 * @param hPts hit points of the bullet
	 * @param sp speed of the bullet
	 * @param dir direction of the bullet
	 */
	public Bullet(Point p, int w, int h, int hPts, int sp, int dir)
	{
		super(p,w,h,hPts,sp,dir);
	}
	
	/**
	 * Tests whether or not the bullet hits another entity.
	 * True if it does, false otherwise
	 * @param e another entity
	 * @return True if it collides with another entity, false otherwise
	 */
	public boolean testCollision (Entity e)
	{
		if ( getRectangle().intersects( e.getRectangle() ) )
		{
			this.stopMoving();
			e.stopMoving();
			e.takeHit();
			return true;
		}
		else 
		{	
			return false;
		}
	}
	
	/**
	 * DRAWS THE IMAGE OF THE bullet
	 * @param g Graphics object
	 * @param p point to draw the bullet at
	 * @param w width
	 * @param h height
	 * @param dir direction
	 */
	@Override
	public void draw (Graphics g, Point p, int w, int h, int dir)
	{
		try 
		{
			BufferedImage image = ImageIO.read(new File("bullet" + dir + ".jpg"));
		    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
	    } catch (IOException ie) 
		{
		    ie.printStackTrace();
		}
	}
}
