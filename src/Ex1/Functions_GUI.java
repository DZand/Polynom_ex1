package Ex1;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.sun.org.glassfish.external.probe.provider.StatsProviderManagerDelegate;

import java.awt.Font;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		function tempFunction = new ComplexFunction();

		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) 
			{
				listOfFunction.add(tempFunction.initFromString(line));
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
			for (int i = 0; i < listOfFunction.size(); i++) 
			{
				sb.append(listOfFunction.get(i).toString());
				sb.append("\n");
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
		Random rand = new Random();
		StdDraw.clear(StdDraw.WHITE);
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		//vertical lines
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		for (double i = rx.get_min(); i <= rx.get_max(); i = i + 0.5) 
		{
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		// horizontal lines
		for (double i = ry.get_min(); i <= ry.get_max(); i = i + 0.5) 
		{
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		// x axis
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), 0, ry.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int i = (int) rx.get_min(); i <= rx.get_max(); i++) 
		{
			StdDraw.text(i, -0.07, Integer.toString(i));
		}
		// y axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (int k = (int)ry.get_min(); k <= ry.get_max(); k ++) 
		{
			StdDraw.text(-0.07, k, Integer.toString(k));
		}
		for (function fun : listOfFunction) 
		{
			float red = rand.nextFloat();
			float green = rand.nextFloat();
			float blue = rand.nextFloat();
			Color setColor = new Color(red, green, blue);
			StdDraw.setPenColor(setColor);
			double[] x = new double[resolution + 1];
			double[] y = new double[resolution + 1];
			for (int i = 1; i < resolution; i++) 
			{
				x[i] = i;
				try 
				{
					y[i] = fun.f(i);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			for (int i = 0; i < resolution; i++) 
			{
				StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
			}
		}
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		int widthJson=0,heightJson=0,resolutionJson=0;
		JsonArray rxJson = null,ryJson=null;
		JsonParser p = new JsonParser();
		Range rangeX,rangeY;
		
		try 
		{
			FileReader fileReader = new FileReader(json_file);
			JsonObject obj = (JsonObject) p.parse(fileReader);
			if (!(obj.get("Width").isJsonNull()) )
			{
				widthJson = (int) obj.get("Width").getAsInt();
			}
			if (!(obj.get("Height").isJsonNull()) )
			{
				heightJson = (int) obj.get("Height").getAsInt();
			}
			if (!(obj.get("Resolution").isJsonNull()) )
			{
				resolutionJson = (int) obj.get("Resolution").getAsInt();
			}
			if(!(obj.get("Range_X").isJsonNull()))
			{
				rxJson = (JsonArray) obj.get("Range_X");
			}
			if(!(obj.get("Range_Y").isJsonNull()))
			{
				ryJson = (JsonArray) obj.get("Range_Y");
			}
			
			rangeX= new Range (rxJson.get(0).getAsDouble(),rxJson.get(1).getAsDouble());
			rangeY= new Range (ryJson.get(0).getAsDouble(),ryJson.get(1).getAsDouble());
			drawFunctions(widthJson, heightJson, rangeX, rangeY, resolutionJson);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public function get(int i) 
	{
		return (function) listOfFunction.toArray()[i];
	}

}
