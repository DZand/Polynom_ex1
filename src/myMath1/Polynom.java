package myMath1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Danielle and Theila
 *
 */
public class Polynom implements Polynom_able 
{
	public static final Comparator<Monom> _PolynomComp = new Monom_Comperator();

	private ArrayList<Monom> listOfMonom;
	/**
	 * Zero (empty polynom). Constructor with no parameters, create the zero polynom
	 */
	public Polynom()  
	{
		this.listOfMonom=new ArrayList<Monom>();
		this.add(new Monom(0,0));
	}
	/**
	 * This is a constructor, that init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x"} with no complex polynom
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		s=s.toUpperCase();
		isPolynomStringValid(s);
		this.listOfMonom=new ArrayList<Monom>() ;
		s = s.replaceAll("-", "+-");
		String [] splitPolynom =s.split ("\\+");
		for(int i=0;i<splitPolynom.length;i++)
		{
			this.add(new Monom(splitPolynom[i]));
		}
		this.listOfMonom.sort(_PolynomComp);
	}
	
	/**
	 * This function calculate the y value after filling x in f(x) polynom.
	 * user the f function of the monom class
	 * @param x: value of x to be put in f(x)
	 * @return sum: return the polynom value in specific x
	 * 
	 */
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
	/**
	*This function add a Polynom to exisiting polynom 
 	* @param p1: Polynom to be added
 	*/
	@Override
	public void add(Polynom_able p1)  
	{
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			this.add(temp);
		}
		this.removeZZeroMonoms();
		this.listOfMonom.sort(_PolynomComp);
	}

	/**
	 * This function add one monom to existing polynom
	 * @param m1: the monom that need to be added
	 */
	@Override
	public void add(Monom m1)  
	{
		boolean flag = true;
		for(Monom it: this.listOfMonom) 
		{
			if(it.get_power() == m1.get_power()) 
			{
				it.add(m1);
				if(it.get_coefficient()==0)
				{
					it.set_power(0);
				}
				flag = false;
			}
		}
		this.removeZZeroMonoms();
		if(flag)
		{
			this.listOfMonom.add(m1);
		}
		this.listOfMonom.sort(_PolynomComp);
	}

	/**
	 * This function substract(minus) polynom from existing polynom
	 * The substract function use the add function by sending the monom with minus one (-1) before
	 * @param p1: the polynom that need to be substracted
	 */
	@Override
	public void substract(Polynom_able p1) 
	{
		Iterator<Monom> iter=p1.iteretor();
		while(iter.hasNext())
		{
			Monom temp= iter.next();
			this.add(new Monom(-1*temp.get_coefficient(),temp.get_power()));
			//This is for scenario of substract the polynom from itself
			if(((Polynom)p1).listOfMonom.isEmpty() )
			{
				if(this.listOfMonom.isEmpty())
				{
					this.listOfMonom.add(new Monom(0,0));
				}
				return;
			}
			iter.remove();
			RemoveSamePower(this);
		}
		//This if check is relevant for removing different polynoms with same values 
		if(this.listOfMonom.isEmpty())
		{
			this.listOfMonom.add(new Monom(0,0));
		}
		this.listOfMonom.sort(_PolynomComp);
	}

	/**
	 * This function multiply existing polynom with a new polyonm. 
	 * run over each monom in the new and the existing polynoms and calculate the new monom.
	 * insert the new calculated monon to a new polynom
	 * send the polynom to a remove function to union same power monoms(if those were created during multiply)  
	 * @param
	 */
	@Override
	public void multiply(Polynom_able p1) 
	{
		Polynom tempPolynom= new Polynom();
		Iterator<Monom> p1Iter=p1.iteretor();
		Iterator<Monom> thisIter=iteretor();
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
		RemoveSamePower(tempPolynom);
		this.removeZZeroMonoms();
		this.listOfMonom=tempPolynom.listOfMonom;
	}

	/**
	 * This function is a boolean function that compare two polynoms. 
	 * Use comperator.
	 * @param p1 represent the polynom that we want to compare with the existing polynom.
	 * @return true if both polynoms are equal otherwise false.
	 */
	@Override
	public boolean equals(Polynom_able p1) 
	{
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

	/**
	 * this function is a boolean function that check every monom in the polynom and
	 *  return true if all the monoms are zero monoms. 
	 * @return true if it is the zero polynom otherwise false.
	 */
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

	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0  represent a starting point
	 * @param x1  represent a end point
	 * @param eps represent the step (positive) value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	@Override
	public double root(double x0, double x1, double eps) 
	{
		double midPoint=(x0+x1)/2;
		boolean flagRecursivType=false;
		if(this.f(x0)*this.f(x1)>0)
		{
			throw new RuntimeException("Both x0 and x1 are positive/negative");
		}
		if(this.f(x0)==0 || this.f(x1)==0)
		{
			if(this.f(x0)==0)
			{
				return x0;
			}
			return x1;
		}
		else if(Math.abs(x1-x0)<eps)
		{
			return ((x1+x0)/2);
		}
		if(this.f(x0)*this.f(midPoint)<0)
		{
			flagRecursivType=true;
		}
		if(flagRecursivType)
		{
			return root(x0,midPoint,eps);
		}
		else
		{
			return root(midPoint,x1,eps);
		}
	}

	/**
	 * This function create a deep copy of current Polynom.
	 * No parameters
	 * @return new copied Polynom
	 * @throws _Exception 
	 */
	@Override
	public Polynom_able copy() throws Exception 
	{
		Iterator<Monom> iter=iteretor();
		Polynom_able  newPolynom= new Polynom();
		while(iter.hasNext())
		{
			Monom iterTemp=iter.next();
			newPolynom.add(new Monom(iterTemp.get_coefficient(),iterTemp.get_power()));
		}
		return newPolynom;
	}

	
	/**
	 * Compute a new Polynom which is the derivative(Nigzeret) of this Polynom
	 * @return newPolynom
	 * @throws Exception 
	 */
	@Override
	public Polynom_able derivative() throws Exception 
	{
		Iterator<Monom> iter=iteretor();
		Polynom_able  newPolynom= new Polynom();
		while(iter.hasNext())
		{
			Monom iterTemp=iter.next();
			newPolynom.add(new Monom(iterTemp.derivative()));
		}
		return newPolynom;
	}

	
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	@Override
	public double area(double x0, double x1, double eps) 
	{
		double areaSum= 0;
		int numOfSteps=(int)Math.abs((x1-x0)/eps);
		for(int i=0;i<numOfSteps;i++)
		{
			if(this.f(x0)>0)
			{
				areaSum=areaSum+(eps*this.f(x0));
			}
			x0=x0+eps;
		}
		return areaSum;
	}

	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return iterator
	 */
	@Override
	public Iterator<Monom> iteretor()
	{
		return listOfMonom.iterator();
	}
	
	/**
	 * Multiply this Polynom by p1
	 * @param p1
	 */
	@Override
	public void multiply(Monom m1)
	{
		Iterator<Monom> iter = iteretor();
		Polynom  mulyPloynom = new Polynom();
		while (iter.hasNext()) 
		{
			Monom temp = iter.next();
			mulyPloynom.listOfMonom.add(new Monom(temp.get_coefficient()*m1.get_coefficient(),temp.get_power()+m1.get_power()));
		}
		
	}
	
	/**
	 * This function check if the polynom contains only valid characters
	 * @param str
	 * @throws _Exception if not
	 */
	private static void isPolynomStringValid(String str)   
	{ 
	    if (str == null || str.equals("")) 
	    { 
	    	throw new RuntimeException("Empty Polynom");
	    } 
	    for (int i = 0; i < str.length(); i++) 
	    { 
	        char ch = str.charAt(i); 
	        if ((ch!='X') && (ch!='^') && !(ch >= '0' && ch <= '9') && ch!='+' && ch!='-' && ch!='.')
	        { 
	        	throw new RuntimeException("Invalid Polynom, illegal characters"); 
	        } 
	    } 
	} 
	
	/**
	 * This function get a polynom and in case there are duplicate powers, union the monoms.
	 * used in multiply function
	 * @param tempPolynom
	 */
	private void RemoveSamePower(Polynom tempPolynom) 
	{
		
	for(int i=0;i<tempPolynom.listOfMonom.size();)
	{
		if(i==tempPolynom.listOfMonom.size()-1)
		{
			return;
		}
		if(_PolynomComp.compare(tempPolynom.listOfMonom.get(i),tempPolynom.listOfMonom.get(i+1))==0)
		{
			tempPolynom.listOfMonom.get(i).set_coefficient(tempPolynom.listOfMonom.get(i).get_coefficient()+tempPolynom.listOfMonom.get(i+1).get_coefficient());
			tempPolynom.listOfMonom.remove(i+1);
			tempPolynom.listOfMonom.sort(_PolynomComp);	
		}
		else
		{
			i++;
		}
		
	}
}
	
	/**
	 * This function remove the zero monom that were added using the default constructor
	 */
	private void removeZZeroMonoms() 
	{
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext())
		{
			Monom runner = iter.next();
			if (runner.get_coefficient() == 0)
				iter.remove();
		}
		this.listOfMonom.sort(_PolynomComp);
	}
	
	/**
	 * This function calculate the string of the polynom
	 * move over all the monoms and concatenated them to string
	 * @return new string
	 */
	@Override
	public String toString() 
	{
		String returnedString = "";
		Iterator<Monom> monomIter= this.listOfMonom.iterator();
		if(listOfMonom.size()==1) 
		{
			returnedString =returnedString+monomIter.next().toString();
			return returnedString;
		}
		while(monomIter.hasNext()) 
		{
			Monom temp=monomIter.next();
			if(returnedString.isEmpty())
			{
				returnedString=returnedString + temp.toString();
			}
			else if(temp.get_coefficient()<0)
			{
				returnedString=returnedString + temp.toString();
			}
			else
			{
				returnedString=returnedString + "+" + temp.toString();
			}
			
		}
		return returnedString;
	}
}
