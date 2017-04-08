/**
 * This  class contains the methods for a Song.
 * @author Amy Yang
 *
 */
public class Song implements Comparable <Song>
{
	/**
	 * Represents title of the song
	 */
	private String title;
	
	/**
	 * Represents the singer's name
	 */
	private String artist;
	
	/**
	 * Represents the name of the album that the song is in
	 */
	private String album;
	
	/**
	 * Represents the rating of the song
	 */
	private int rating;

	/**
	 * This constructor constructs a Song object
	 * @param t title of the song
	 * @param singer song's artist
	 * @param a album
	 * @param l Rating of the song
	 */
	public Song (String t, String singer, String a, int r)
	{
		title = t;
		artist = singer;
		album = a;
		rating = r;
	}
	
	/**
	 * This method returns the title of the song
	 * @return title of the song
	 */
	public String getTitle ()
	{
		return title;
	}
	
	/**
	 * This method returns the artist of the song
	 * @return artist of the song
	 */
	public String getArtist ()
	{
		return artist;
	}
	
	/**
	 * This method returns the album of the song
	 * @return album of the song
	 */
	public String getAlbum ()
	{
		return album;
	}
	
	/**
	 * This method returns the rating of the song
	 * @return rating of the song
	 */
	public int getRating ()
	{
		return rating;
	}
	
	/**
	 * Overrides to string method with song info
	 * @return a string with song info
	 */
	@Override
	public String toString ()
	{
		String s = "Title: " + title + "\t\tArtist: " + artist + "\t\t\tAlbum: " + album + "\t\tRating: "
				+ rating;
		return s;
	}
	
	/**
	 * This method compares 2 songs by rating.
	 * @param song2 the song to be compared
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or 
	 *             greater than the specified object.
	 */
	@Override
	public int compareTo(Song song2)
	{
		if (getRating() > song2.getRating())
		{
			return 1;
		}
		else if (getRating() < song2.getRating())
		{
			return -1;
		}
		else
		{
			return song2.getTitle().compareTo(getTitle());
			
		}
	}
}
