package Ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.awt.Font;


//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;



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
        String line = "";
        String fileSplitBy = ",";
        String[] splited=null;
        ComplexFunction tempFunction = new ComplexFunction();

        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	
            while ((line = br.readLine()) != null) 
            {
            	splited = line.split(fileSplitBy);
                for (int i=0; i<splited.length;i++) 
                {
                	listOfFunction.add(tempFunction.initFromString(splited[i]));
                }
            }
            
            br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<listOfFunction.size();i++)
			{
				sb.append(listOfFunction.get(i).toString());
				sb.append(",");
			}
			pw.write(sb.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		StdDraw.clear(StdDraw.WHITE);
		StdDraw.setXscale(rx.get_min(),rx.get_min());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		////////vertical lines
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		for (int i =(int)rx.get_min(); i <= rx.get_max(); i=i+10) 
		{
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		//////// horizontal  lines
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) 
		{
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		//////// x axis
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), 0, ry.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int j = (int)rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.text(j, -0.07, Integer.toString(j));
		}
		//////// y axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (double k = ry.get_min(); i <= ry.get_max(); k=k+0.5) 
		{
			StdDraw.text(-0.07, k, Double.toString(k));
		}
		//until here
/**
			// plot the approximation to the function
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
			}
			StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.01);
			StdDraw.point(x[n/2], 1);
		}
		}
		while (this.iterator().hasNext()) 
		{
			function currentFun = this.iterator().next();
			for (int i=-10;i<9;i++) 
			{
				StdDraw.line(i, currentFun.f(i), i++, currentFun.f(i++));
			}
			*/
		}
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		JsonParser p =new JsonParser();
		try 
		{
			FileReader fileReader = new FileReader(json_file);
			JsonObject obj =(JsonObject)p.parse(fileReader);
			int widthJson=(int)obj.get("Width").getAsInt();
			int heightJson=(int)obj.get("Height").getAsInt();
			int resolutionJson=(int)obj.get("Resolution").getAsInt();
			JsonArray rxJson=(JsonArray)obj.get("Rx");
			JsonArray ryJson=(JsonArray)obj.get("Ry");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		drawFunctions(widthJson,heightJson,rxJson,ryJson,resolutionJson);
	}

	public function get(int i)
	{
		return (function) listOfFunction.toArray()[i];
	}


}
