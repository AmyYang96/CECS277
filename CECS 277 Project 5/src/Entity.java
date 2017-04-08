import java.awt.*;

/**
 * This is abstract class that represents an Entity
 * @author Amy Yang
 *
 */
public abstract class Entity 
{
	/**Represents the entity's location*/
	private Rectangle location;
	
	/**Represents the entity's hit points*/
	private int hp;
	
	/**Represents the entity's speed*/
	private int speed;
	
	/**Represents the entity's direction*/
	private int direction;
	
	/**Represents whether or not the entity is moving*/
	private boolean moving;
	
	/**
	 * Constructor initializes data mambers and initializes Rectangle object
	 * @param p point of the entity
	 * @param w width of the entity
	 * @param h hieght
	 * @param hPts hit points of the entity
	 * @param sp speed of the entity
	 * @param dir direction of the entity
	 */
	public Entity (Point p, int w, int h, int hPts, int sp, int dir)
	{
		hp = hPts;
		speed = sp;
		direction = dir;
		location = new Rectangle ((int)p.getX(), (int) p.getY(), w, h);
		moving = true;
	}
	
	/**
	 * Returns the point where the entity is located
	 * @return point of location: top left corner of rectangle
	 */
	public Point getLocation ()
	{
		return location.getLocation();
	}
	
	/**
	 * Returns the width of the rectangle
	 * @return width as an int
	 */
	public int getWidth()
	{
		return (int) location.getWidth();
	}
	
	/**
	 * Returns the height of the rectangle
	 * @return height as an int
	 */
	public int getHeight()
	{
		return (int) location.getHeight();
	}
	
	/**
	 * Returns the hp of the entity
	 * @return hp as an int
	 */
	public int getHp()
	{
		return hp;
	}
	
	/**
	 * Returns the speed of the entity
	 * @return speed as an int
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * Returns the direction of the entity
	 * @return direction as an int
	 */
	public int getDirection()
	{
		return direction;
	}
	
	/**
	 * Returns true if the entity died, otherwise false
	 * @return true if the entity died, otherwise false
	 */
	public boolean isDead()
	{
		if (hp <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Once the entity is hit, it dies
	 */
	public void takeHit()
	{
		hp = 0;
	}
	
	/**
	 * Changes the direction clockwise by adding 1
	 */
	public void spinCW()
	{
		if (direction == 8) //northwest to north
		{
			setDirection(1);
		}
		else
		{
			direction++;
		}
	}
	
	/**
	 * Changes the direction counterclockwise by subtracting 1
	 */
	public void spinCCW()
	{
		if (direction == 1)//north to northwest
		{
			setDirection(8);
		}
		else
		{
			direction--;
		}		
	}
	
	public boolean getMoving()
	{
		return moving;
	}
	
	/**
	 * Sets a new direction
	 * @param d new direction
	 */
	public void setDirection (int d ) 
	{
		direction = d;
	}
	
	/**
		}
	 * Sets moving to true
	 */
	public void toggleMoving ()
	{
		moving = true;
	}
	
	/**
	 * Sets moving to false
	 */
	public void stopMoving ()
	{
		moving = false;
	}
	
	/**
	 * Moves and draws the entity
	 * @param g graphics object
	 */
	public void update(Graphics g)
	{
		
		move();
		draw(g, getLocation(), getWidth(), getHeight(), direction);
		
	}
	
	public void setNewLocation( Point p )
	{
		location.setLocation(p);
	}
	
	/**
	 * Moves the entity based in a direction
	 */
	public void move ()
	{
		int dx = speed, dy = speed;
		
		int x = (int) getLocation().getX();
		int y = (int) getLocation().getY();
		

		if (this instanceof Hunter) //prevents the hunter going off the screen
		{
			if ( (direction == 2 || direction == 3 || direction == 4) && (x + getWidth() >= 1200)) //right edge
			{
				stopMoving();
				Toolkit.getDefaultToolkit().beep();
				location.translate(-40, 0);
			}
			else if ((direction == 6 || direction == 7 || direction == 8) && (x <= 0))//left edge
			{
				stopMoving();
				Toolkit.getDefaultToolkit().beep();
			} 
	
			if ( (direction == 4 || direction == 5 || direction == 6) && (y + getHeight()+dy > 850))//bottom edge
			{
				stopMoving();
				Toolkit.getDefaultToolkit().beep();
				location.translate(0, -30);
			}
			else if ((direction == 1 || direction == 2 || direction == 8) && (y  <= 0)) //top edge
			{
				stopMoving();
				Toolkit.getDefaultToolkit().beep();
			}
		}
		
		if (moving == true)
		{
			if (direction == 1)
			{
				location.translate(0, -dy ); // north
			}
			if (direction == 2)
			{
				location.translate(dx, -dy); // NE
			}
			if (direction == 3)
			{
				location.translate(dx, 0); // east
			}
			if (direction == 4)
			{
				location.translate(dx, dy); // SE
			}
			if (direction == 5)
			{
				location.translate(0, dy); // south
			}
			if (direction == 6)
			{
				location.translate(-dx, dy); // SW
			}
			if (direction == 7)
			{
				location.translate(-dx, 0); // west
			}
			if (direction == 8)
			{
				location.translate(-dx, -dy); // NW
			}
		}	
	}
	
	/**
	 * Returns the rectangle of the entity
	 * @return a rectangle
	*/
	public Rectangle getRectangle()
	{
		return location;
	}
	
	/**
	 * DRAWS THE IMAGE OF THE ENTITY IN GUI
	 * @param g Graphics object
	 * @param p point to draw the entity at
	 * @param w width
	 * @param h height
	 * @param dir direction
	 */
	public abstract void draw (Graphics g, Point p, int w, int h, int dir);	
}
