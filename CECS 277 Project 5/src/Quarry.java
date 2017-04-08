import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.*;

import java.io.*;
/**
 * This class represents a quarry and inherits from entity class
 * @author Amy Yang
 *
 */
public class Quarry extends Entity 
{
	/**Represents the type of quarry*/
	private String name;
	
	/**Represents the weight of the quarry*/
	private int weight;
	
	/**
	 * Constructor inherits data members from entity class
	 * @param p point of the quarry
	 * @param w width of the quarry
	 * @param h hieght
	 * @param hPts hit points of the quarry
	 * @param sp speed of the quarry
	 * @param dir direction of the quarry
	 * @param n name of the quarry
	 * @param wt weight of the quarry
	 *
	 */
	public Quarry(Point p, int w, int h, int hPts, int sp, int dir, String n, int wt) 
	{
		super(p, w, h, hPts, sp, dir);
		name = n;
		weight = wt;
	}

	/**
	 * Returns quarry's name
	 * @return quarry's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the quarry's weight
	 * @return the quarry's weight
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**
	 * DRAWS THE IMAGE OF THE quarry
	 * @param g Graphics object
	 * @param p point to draw the quarry at
	 * @param w width
	 * @param h height
	 * @param dir direction
	 */
	@Override
	public void draw(Graphics g, Point p, int w, int h, int dir) 
	{
		if(name.equalsIgnoreCase("squirrel") || name.equalsIgnoreCase("snake"))
		{
			try 
			{
				BufferedImage image = ImageIO.read(new File("poke" + dir + ".jpg"));
			    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
		    } catch (IOException ie) 
			{
			    ie.printStackTrace();
			}
		}
		if(name.equalsIgnoreCase("wolf") || name.equalsIgnoreCase("bear") || name.equalsIgnoreCase("bison"))
		{
			try 
			{
				BufferedImage image = ImageIO.read(new File("wolf" + dir + ".jpg"));
			    g.drawImage(image, (int) p.getX(), (int) p.getY(), w, h, null);
		    } catch (IOException ie) 
			{
			    ie.printStackTrace();
			}
		}
	}

}
