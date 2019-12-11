package Ex1;

public class ComplexFunction implements complex_function 
{
	function right;
	function left;
	Operation op;


	/**
	 * Default constructor create empty complex function with operation None and polynom zero
	 */
	public ComplexFunction() 
	{
		this.left = new ComplexFunction(new Polynom());
		this.right = null;
		this.op = Operation.None;
	}
	/**
	 * constructor that get polynom and initialize new complex function with none operator
	 * @param p3
	 */
	public ComplexFunction(Polynom p3) 
	{
		this.left = p3;
		this.right = null;
		this.op = Operation.None;
	}
	/**
	 * constructor that get string as operator, and 2 functions
	 * @param string
	 * @param p1
	 * @param p2
	 */
	public ComplexFunction( function p1, function p2, String string) 
	{
		this.left = p1;
		this.right = p2;
		this.op = Operation.valueOf(string);
	}
	/**
	 * constructor that get operator and 2 functions
	 * @param left2
	 * @param right2
	 * @param op2
	 */
	public ComplexFunction(function left2, function right2, Operation op2) 
	{
		this.left = left2;
		this.right = right2;
		this.op = op2;
	}

	/**
	 * Constructor that gets one function and if the function is complex function initialize.
	 * @param function
	 * @throws Exception in case the function is not complex function
	 */
	public ComplexFunction(function function) throws Exception
	{
		if (function instanceof ComplexFunction) 
		{
			this.left = ((ComplexFunction) function).left();
			this.right = ((ComplexFunction) function).right();
			this.op = ((ComplexFunction) function).getOp();
		} 
		else if (function instanceof Polynom) 
		{
			throw new Exception("function is not complex function");
		}
	}

	@Override
	public double f(double x) throws Exception 
	{
		if (this instanceof ComplexFunction) 
		{
			switch (this.op) 
			{
			case Plus:
				return this.left().f(x) + this.right().f(x);
			case Times:
				return this.left().f(x) * this.right().f(x);
			case Divid:
				if (this.right().f(x) == 0)
				{
					throw new ArithmeticException("Could not divid in zero");
				} 
				else 
				{
					return this.left().f(x) / this.right().f(x);
				}

			case Max:
				return Math.max(this.left().f(x), this.right().f(x));
			case Min:
				return Math.min(this.left().f(x), this.right().f(x));
			case Comp:
				return this.left().f(this.right().f(x));
			case None:
				return this.left.f(x);
			default:
				throw new RuntimeException("Operation unknown");
			}
		}
		else
		{
			throw new RuntimeException("Not a complex function object");
		}
	}

	@Override
	public function initFromString(String s) 
	{
		s=s.replaceAll(" ", "");
		int Open = s.indexOf('(');
		if (Open==-1)
		{
				function newPoly = new Polynom(s);
				return newPoly;
		}
		String stringOperator = null;
		int countBrace = 0;
		int indexSplit = -1;
		ComplexFunction temp = new ComplexFunction();
		for (Operation c : Operation.values())
		{
			if (c.name().equals(s.substring(0, Open))) 
			{
				stringOperator = c.name();
				break;
			}
		}
		
		if(stringOperator==null)
		{
			throw new RuntimeException("Operator is NULL or illegale ");
		}

		//count how many open brackets before the first close bracket
		//System.out.println("s.length()" + s.length());
	
		for (int i=Open+1; i<s.length()-1; i++) 
		{
			if (s.charAt(i)=='(') 
			{
				countBrace++;
			}
			
			if(s.charAt(i)==')')
			{
				countBrace--;
			}
			if(s.charAt(i) == ',' && countBrace == 0)
			{
				indexSplit = i;
				break;
			}
			if(countBrace == 0 && s.lastIndexOf(')')==1)
			{
				indexSplit= s.indexOf(',');
			}
				
		}
		
		if(Operation.valueOf(stringOperator)==Operation.None)
		{
			return new Polynom(s.substring(Open+1, s.length()-1));
		}
		
		if(indexSplit==-1)
		{
			throw new RuntimeException("Invalid string - missing comma");
		}

		String s1 = s.substring(Open+1,indexSplit);
		String s2  = s.substring(indexSplit+1, s.length()-1);
		function leftFun = temp.initFromString(s1);
		function rightFun = temp.initFromString(s2);
		return new ComplexFunction(leftFun,rightFun,Operation.valueOf(stringOperator));
		

}
	@Override
	public function copy() 
	{
		function copyFun = new ComplexFunction(this.left, this.right, this.op);
		return copyFun;
	}

	@Override
	public void plus(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Plus;
	}

	@Override
	public void mul(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Times;
	}

	@Override
	public void div(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Divid;

	}

	@Override
	public void max(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Max;

	}

	@Override
	public void min(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Min;

	}

	@Override
	public void comp(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right, this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Comp;

	}

	@Override
	public function left() 
	{
		return this.left;
	}

	@Override
	public function right() 
	{
		return this.right;
	}

	@Override
	public Operation getOp() 
	{
		return this.op;
	}

	@Override
	public String toString() 
	{
		if (this.right == null) 
		{
			return this.op.name() + '(' + this.left().toString()+')';
		}
		else 
		{
			return this.op.name() + '(' + this.left().toString() + ',' + this.right().toString() + ')';
		}
	}

	@Override
	public boolean equals(Object obj) 
	{
		for (int i = 0; i < 100; i++) 
		{
			double j = Math.random() * 100;
			if (obj instanceof ComplexFunction) 
			{
				try 
				{
					if(!equalsHelp(this.f(j),((ComplexFunction) obj).f(j)))
					{
						return false;
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			} 
			else 
			{
				try 
				{
					if(!equalsHelp(this.f(j),((Polynom) obj).f(j)))
					{
						return false;
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public boolean equalsHelp(double num1, double num2)
	{
		boolean equal=Math.abs(num1-num2) < Monom.EPSILON;
		return equal;
	}
}
