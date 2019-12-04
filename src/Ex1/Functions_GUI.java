package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions
{
	private ArrayList<function> listOfFunction;
	
	public Functions_GUI()
	{
		listOfFunction = new ArrayList<function>();
	}

	@Override
	public int size() 
	{
		return listOfFunction.size();
	}

	@Override
	public boolean isEmpty() 
	{
		return listOfFunction.isEmpty();
	}

	@Override
	public boolean contains(Object o) 
	{
		return listOfFunction.contains(o);
	}

	@Override
	public Iterator<function> iterator() 
	{
		return listOfFunction.iterator();
	}

	@Override
	public Object[] toArray() 
	{
		return listOfFunction.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) 
	{
		return listOfFunction.toArray(a);
	}

	@Override
	public boolean add(function e) 
	{
		if (listOfFunction.add(e)) 
		{
			return true;
		}
		return false;
}
	@Override
	public boolean remove(Object o) 
	{
		return listOfFunction.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		return listOfFunction.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) 
	{	
		return listOfFunction.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		return listOfFunction.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		return listOfFunction.retainAll(c);
	}

	@Override
	public void clear() 
	{
		listOfFunction.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException 
	{
		
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		
		
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		
		
	}

	public function get(int i)
	{
		return (function) listOfFunction.toArray()[i];
	}

	/**public Polynom get(int i) 
	{
		
		return (Polynom)listOfFunction.toArray()[i];
	}*/
	

}
