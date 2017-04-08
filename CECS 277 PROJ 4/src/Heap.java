import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class represents a generic heap
 * @author Amy Yang
 *
 * @param <T> generic object
 */
public class Heap <T extends Comparable <T>> 
{
	/**Represents a heap*/
	private ArrayList<T> heap; 
	
	/**
	 * Constructs a heap with an empty arraylist
	 */
	public Heap()
	{
		heap = new ArrayList< T >();
	}
	
	/**
	 * Gets the size of the heap
	 * @return size of the heap
	 */
	public int getSize()
	{
		return heap.size();
	}
	
	/**
	 * Checks if the heap is empty
	 * @return true if empty
	 */
	public boolean isEmpty()
	{
		return heap.isEmpty();
	}
	
	/**
	 * Returns the parent's index
	 * @param i child's index
	 * @return parent's index
	 */
	public int getPLoc(int i)
	{
		return (i-1)/2;
	}
	
	/**
	 * Returns left child's index
	 * @param i current index
	 * @return left child's index
	 */
	public int getLCLoc(int i)
	{
		return 2*i+1;
	}
	
	/**
	 * Returns right child's index
	 * @param i current index
	 * @return right child's index
	 */
	public int getRCLoc(int i)
	{
		return 2*i+2;
	}
	
	/**
	 * Gets a generic object at a certain index
	 * @param i index
	 * @return generic object
	 */
	public T getObjectAt(int i)
	{
		if(heap.get(i) == null)
		{
			System.out.println("Item does not exist.");
			return null;
		}
		else
		{
			return heap.get(i);
		}
	}
	
	/**
	 * Adds a object to the min heap
	 * @param generic object
	 */
	public void addMinObject(T t)
	{
		heap.add(null);
		int index = heap.size() - 1;
		while(index > 0 && getObjectAt(getPLoc(index)).compareTo(t) > 0)
		{
			heap.set(index, getObjectAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, t);
	}

	/**
	 * Adds a object to the max heap
	 * @param generic object
	 */
	public void addMaxObject(T t)
	{
		heap.add(null);
		int index = heap.size() - 1;
		while(index > 0 && getObjectAt(getPLoc(index)).compareTo(t) < 0)
		{
			heap.set(index, getObjectAt(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, t);
	}	
	
	/**
	 * Removes the minimum value in the heap
	 * @return generic object with the min value
	 */
	public T removeMin()
	{
		T min = heap.get(0);
		int index = heap.size()-1;
		T last = heap.remove(index);
		
		if(index > 0)
		{
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size()-1;
			index = 0;
			boolean done = false;
			
			while(!done)
			{
				if(getLCLoc(index) <= end)
				{
					//left exists
					T child = getObjectAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if(getRCLoc(index) <= end) 
					{
						//right exists
						if(getObjectAt(getRCLoc(index)).compareTo(child) <0 )
						{
							child = getObjectAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if(child.compareTo( root ) < 0)
					{
						heap.set(index, child);
						index = childLoc;
					}
					else
					{
						done = true;
					}
				}
				else
				{
					//no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return min;
	}

	/**
	 * Removes the maximum value in the heap
	 * @return generic object with the max value
	 */
	public T removeMax()
	{
		T max = heap.get(0);
		int index = heap.size()-1;
		T last = heap.remove(index);
		
		if(index > 0)
		{
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size()-1;
			index = 0;
			boolean done = false;
			
			while(!done)
			{
				if(getLCLoc(index) <= end)
				{
					//left exists
					T child = getObjectAt(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if(getRCLoc(index) <= end) 
					{
						//right exists
						if(getObjectAt(getRCLoc(index)).compareTo(child) >0 )
						{
							child = getObjectAt(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if(child.compareTo( root ) > 0)
					{
						heap.set(index, child);
						index = childLoc;
					}
					else
					{
						done = true;
					}
				}
				else
				{
					//no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return max;
	}	
	/**
	 * Prints the items in the list
	 */
	public void printHeap()
	{
		for(int i=0;i<heap.size();i++)
		{
			System.out.println((i+1)+". " +heap.get(i)+" ");
		}
		System.out.println();
	}
}
