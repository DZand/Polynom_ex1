package myMath1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able 
{

	private ArrayList<Monom> listOfMonom;
	/**
	 * Zero (empty polynom)
	 * @throws _Exception 
	 */
	public Polynom() throws _Exception 
	{
		this.listOfMonom=new ArrayList<Monom>();
		this.add(Monom.ZERO);
		
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 * @throws _Exception 
	 */
	public Polynom(String s) throws _Exception 
	{
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		s=s.toUpperCase();
		isPolynomStringValid(s);
		this.listOfMonom=new ArrayList<Monom>() ;
		s = s.replaceAll("-", "+-");
		String [] splitPolynom =s.split ("+");
		for(int i=0;i<splitPolynom.length;i++)
		{
			this.add(new Monom(splitPolynom[i]));
		}
		this.listOfMonom.add(new Monom(0,0));
		this.listOfMonom.sort(comperatorTemp);
		//this.zero_union();
	}
	@Override
	public double f(double x) 
	{
		double sum=0;
		Iterator<Monom> iter=iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			sum=sum+temp.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) throws _Exception 
	{
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			this.add(temp);
		}
		this.listOfMonom.sort(comperatorTemp);
	}

	@Override
	public void add(Monom m1) throws _Exception 
	{
		boolean powerExist=false;
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		Iterator<Monom> iter=iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			if(comperatorTemp.compare(temp, m1)==0)
			{
				temp.set_coefficient(m1.get_coefficient()+temp.get_coefficient());
				powerExist=true;
			}
			if(!powerExist)
			{
				listOfMonom.add(m1);
			}
		}
		this.listOfMonom.sort(comperatorTemp);
	}

	@Override
	public void substract(Polynom_able p1) throws _Exception 
	{
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			temp.set_coefficient(-1*temp.get_coefficient());
			this.add(temp);
		}
		this.listOfMonom.sort(comperatorTemp);
		
		
	}

	@Override
	public void multiply(Polynom_able p1) throws _Exception 
	{
		Polynom tempPolynom= new Polynom();
		Iterator<Monom> p1Iter=p1.iteretor();
		Iterator<Monom> thisIter=iteretor();
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		while(p1Iter.hasNext())
		{
			Monom p1Temp= p1Iter.next();
			thisIter=iteretor();
			while(thisIter.hasNext())
			{
				Monom tempThis= thisIter.next();
				tempPolynom.add(new Monom(p1Temp.get_coefficient()*tempThis.get_coefficient(),p1Temp.get_power()+tempThis.get_power()));
			}
		}
		
		Iterator<Monom> tempPolynomIter=tempPolynom.iteretor();
		tempPolynom.listOfMonom.sort(comperatorTemp);
		while(tempPolynomIter.hasNext())
		{
			Monom tempIter1= tempPolynomIter.next();
			Monom tempIter2= tempPolynomIter.next();
			if(comperatorTemp.compare(tempIter1,tempIter2)==0)
			{
				tempPolynom.add(new Monom(tempIter1.get_coefficient()+tempIter2.get_coefficient(),tempIter1.get_power()));
				tempPolynom.listOfMonom.remove(tempIter1);
				tempPolynom.listOfMonom.remove(tempIter2);
			}
		}
		
		this.listOfMonom=tempPolynom.listOfMonom;
		this.listOfMonom.sort(comperatorTemp);
		
		
	}

	@Override
	public boolean equals(Polynom_able p1) 
	{
		Monom_Comperator comperatorTemp=new Monom_Comperator();
		Iterator<Monom> iter1=p1.iteretor();
		Iterator<Monom> iter2=iteretor();
		boolean flag=true;
		while(iter1.hasNext() && iter2.hasNext())
		{
			if(!iter1.next().equals(iter2.next()))
			{
				flag=false;
			}
		}
		if(!flag || (flag && (iter1.hasNext() || iter2.hasNext())))
		{
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isZero() 
	{
		Iterator <Monom> iter = iteretor();
		while (iter.hasNext()) 
		{
			Monom temp = iter.next();
			if (!temp.isZero())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Monom> iteretor() 
	{
		return this.listOfMonom.iterator();
	}
	@Override
	public void multiply(Monom m1) throws _Exception
	{
		Iterator<Monom> iter = iteretor();
		Polynom  mulyPloynom = new Polynom();
		while (iter.hasNext()) 
		{
			Monom temp = iter.next();
			mulyPloynom.listOfMonom.add(new Monom(temp.get_coefficient()*m1.get_coefficient(),temp.get_power()+m1.get_power()));
		}
		
	}
	public static Polynom_able parse(String s1) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static void isPolynomStringValid(String str) throws _Exception  
	{ 
	    if (str == null || str.equals("")) 
	    { 
	    	throw new _Exception("Invalid Polynom");
	    } 
	    for (int i = 0; i < str.length(); i++) 
	    { 
	        char ch = str.charAt(i); 
	        if ((ch!='X') && (ch!='^') && !(ch >= '0' && ch <= '9') && ch!='+' && ch!='-' && ch!='.')
	        { 
	        	throw new _Exception("Invalid Polynom"); 
	        } 
	    } 
	} 

	
}
