
package myMath1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Danielle and Tehila
 *
 */
public class Monom implements function
{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	
	public static Comparator<Monom> getComp() 
	{
		return _Comp;
	}
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() 
	{
		return this._coefficient;
	}
	public int get_power() 
	{
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() 
	{
		if(this.get_power()==0) 
		{
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	
	public double f(double x) 
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************

	/**
	 * This constructor gets the shape ax^b and take the coefficient and the power of the Monom.
	 * @param s String.
	 * @throws _Exception for a wrong Monom.
	 */
	
	public Monom(String s) throws _Exception  
	{
				isMonomStringValid(s);
				if(s.isEmpty())
				{
					throw new _Exception("Empty string");
				}
				s = s.toUpperCase();
				if(s.contains("X")) 
				{ // to check if has power.
					if(s.charAt(0) == 'X') 
					{
						s = '1' + s;
					}
					if(s.charAt(0) == '-') 
					{
						if(s.charAt(1) == 'X') 
						{
							s  = s.replace('-', '1');
							s = '-' + s;
						}
					}
					String[] arr = s.split("X"); // I divided by x.
					if(arr[0].charAt(arr[0].length()-1)=='*')
					{
						arr[0] = arr[0].substring(0,arr[0].length()-1); // Remove the multiply.
					}
	
					this._coefficient = Double.parseDouble(arr[0]); // Change coefficient from String to double.
		
				//	if(arr[0].equals("x")) 
					//{
						//this._coefficient = 1;
					//}
					if(arr.length > 1) 
					{
						if(arr[1].charAt(0)=='^')
						{
							arr[1] = arr[1].substring(1,arr[1].length()); // Remove the power.
							if(arr[1].charAt(0) == '-') 
							{ // Check if the power is a negative number.
								throw new _Exception("Negative number.");
							}
							else 
							{
								this._power = Integer.parseInt(arr[1]); // Change power from String to Integer.
							}
						}
					}
					else 
					{
						this._power = 1;
					}
			    }
			    else 
			    {
				this._power = 0;
				this._coefficient = Double.parseDouble(s);
			    }

	}
	
	/**
	 * 
	 * @param m
	 *  This function check if the power of 2 Monoms equals.
	 *	if yes: add.
	 *	else: _Exception.
	 * @throws _Exception 
	 */
	public void add(Monom m) throws _Exception 
	{
		if(this._power==m._power)
		{
			this._coefficient=this._coefficient+m._coefficient;
		}
		else
		{
			throw new _Exception("Different power.");
		}	
	}
	/**
	 *
	 * @param d
	 * This function multiply 2 Monoms.
	 */
	public void multipy(Monom d) 
	{
		this._coefficient=this._coefficient*d._coefficient;
		this._power=this._power+d._power;
	}
	
	/**
	 * This function calculate the string of the monom
	 * Concate the coefficient and the power with the monom syntax ax^b
	 * @return new string
	 */
	@Override
	public String toString() 
	{
		String ans = this._coefficient + "x^" + this._power;
		return ans;
	}


	/**
	 * This function check if 2 Monoms are equal.
	 * @param m Monom.
	 * @return true/false.
	 */
	public boolean equals(Monom m)
	{
		return this.get_coefficient() == m.get_coefficient() && this.get_power() == m.get_power();
	}
	
	/**
	 * this function set the coefficient
	 * @param a
	 */
	public void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	
	/**
	 * this function set the power
	 * @param p
	 */
	public void set_power(int p) 
	{
		if(p<0) 
		{
			throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);
		}
		this._power = p;
	}
	
	//****************** Private Methods and Data *****************
	
	private double _coefficient; 
	private int _power;
	
	/**
	 * This function is not on used
	 * @return
	 */
	private static Monom getNewZeroMonom() 
	{
		return new Monom(ZERO);
	}
	
	private static void isMonomStringValid(String str) throws _Exception  
	{ 
	    if (str == null || str.equals("")) 
	    { 
	    	throw new _Exception("Empty Monom");
	    } 
	    for (int i = 0; i < str.length(); i++) 
	    { 
	        char ch = str.charAt(i); 
	        if ((ch!='X') && (ch!='^') && !(ch >= '0' && ch <= '9') && ch!='-' && ch!='.')
	        { 
	        	throw new _Exception("Invalid Monom, illegal characters"); 
	        } 
	    } 
	} 
	
	
	
}
