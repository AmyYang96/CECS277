import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
/**
 * This class represents an hunter and inherits from the entity class
 * @author Amy Yang
 *
 */
public class Hunter extends Entity 
{
	/**Represents a bullet*/
	private Bullet bullet;
	
	/**
	 * Constructor inherits data members from entity class
	 * @param p point of the hunter
	 * @param w width of the hunter
	 * @param h hieght
	 * @param hPts hit points of the hunter
	 * @param sp speed of the hunter
	 * @param dir direction of the hunter
	 */
	public Hunter(Point p, int w, int h, int hPts, int sp, int dir) 
	{
		super(p, w, h, hPts, sp, dir);
		stopMoving();
	}

	/** 
	 * Fires a bullet by creating a new bullet and sets moving to true 
	 */
	public void fireBullet()
	{
		bullet = new Bullet (getLocation(), 15, 20, 10, 10, 1);
		bullet.toggleMoving();
	}
	
	/**
	 * Gets the hunter's bullet
	 * @return bullet object
	 */
	public Bullet getBullet()
	{
		if (!(bullet == null))
		{
			return bullet;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Tests whether or not the bullet hits another entity.
	 * True if it does, false otherwise
	 * @param e another entity
	 * @return True if it collides with another entity, false otherwise
	 */
	public boolean testHit(Entity e)
	{
		if (!(bullet == null))
		{
			return bullet.testCollision(e);
		}
		
		else
		{
			return false;
		}
	}

	/**
	 * Checks if the hunter runs into a quarry
	 * @param e a quarry
	 * @return true if the hunter collides with a quarry false otherwise
	 */
	public boolean ifMauled(Entity e)
	{
		if ( getRectangle().intersects( e.getRectangle() ) )
		{
			stopMoving();
			e.stopMoving();
			takeHit();
			return true;
		}
		else 
		{	
			return false;
		}
	}
	/**
	 * DRAWS THE IMAGE OF THE hunter: reads image from file
	 * @param g Graphics object
	 * @param p point to draw the hunter at
	 * @param w width
	 * @param h height
	 * @param dir direction
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) 
	{
		try 
		{
			BufferedImage image = ImageIO.read(new File("hunter" + dir + ".jpg"));
		    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
	    } catch (IOException ie) 
		{
		    ie.printStackTrace();
		}
        if (!(bullet == null))
        {
            if (bullet.getMoving() == false)
            {
                bullet = null;
            }
            else
            {
                bullet.update(g);
            }
        }		
	}
}
