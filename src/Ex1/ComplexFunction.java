package Ex1;

public class ComplexFunction implements complex_function 
{
	function right;
	function left;
	Operation op;
	
	public ComplexFunction(function left2, function right2, Operation op2) 
	{
		this.left = left2;
		this.right= right2;
		this.op = op2;
	}

	public ComplexFunction() 
	{
		this.left = null;
		this.right = null;
		this.op = Operation.None;
	}

	public ComplexFunction(Polynom p3) 
	{
		this.left = p3;
		this.right = null;
		this.op = Operation.None;
	}

	public ComplexFunction(String string, Polynom p1, Polynom p2) 
	{
		this.left = p1;
		this.right = p2;
		this.op = Operation.valueOf(string);
	}

	public ComplexFunction(String string, Polynom right2, ComplexFunction cf3) 
	{
		this.left = right2;
		this.right = cf3;
		this.op = Operation.valueOf(string);
		
	}

	public ComplexFunction(function function) throws Exception 
	{
		if(function instanceof ComplexFunction)
		{
			this.left=((ComplexFunction) function).left();
			this.right=((ComplexFunction) function).right();
			this.op=((ComplexFunction) function).getOp();
		}
		else if(function instanceof Polynom)
		{
			/**
			//ComplexFunction newPoly= new ComplexFunction(((Polynom) function));
			this.left = (Polynom)function;
			this.right = null;
			this.op = Operation.None; 
			Polynom newPoly= new Polynom(function);*/
			throw new Exception("function is not complex function");
		}
	}

	

	@Override
	public double f(double x) 
	{
		if(this instanceof ComplexFunction)
		{
			switch (this.op)
			{
			case Plus:
				return this.left().f(x)+this.right().f(x);
			case Times:
				return this.left().f(x)*this.right().f(x);
			case Divid:
				return this.left().f(x)/this.right().f(x);
			case Max:
				return Math.max(this.left().f(x),this.right().f(x));
			case Min:
				return Math.min(this.left().f(x),this.right().f(x));
			case Comp:
				return this.left().f(this.right().f(x));
			case None:
				return this.left.f(x);
			default:
				return 0;
			}
		}
		return 0;
	}
			
	@Override
	public function initFromString(String s) 
	{
		s = s.toLowerCase();
		//Polynom newPolynom= new Polynom(s);
		
		int firstOpen = s.indexOf('(');
		if (firstOpen <0) 
		{
			try 
			{
				Polynom newPoly = new Polynom(s);
				return newPoly;
			}
		    catch(Exception c)
			{
				
			}	
		}
		String st=null;
		int start = -1;
		int countBrace =0;
		ComplexFunction temp = new ComplexFunction();
		String operationTemp=s.substring(0,firstOpen-1);
		for (Operation c : Operation.values()) 
		 {
		        if (c.name().equals(operationTemp)) 
		        {
		            st=c.name();
		        }
		 }
		if (st!=null) 
		{
			start=s.indexOf(st);
		}
		if(start>=0 && s.endsWith(")")) 
		{
			String s1= s.substring(start+st.length(),s.length()-1);
			for(int i = 0; i<s1.length(); i++) 
			{
				if(s1.charAt(i)=='(') 
				{
					countBrace++;
				}
				else if(s1.charAt(i)==')') 
				{
					countBrace--;
				}
				if (countBrace==0) 
				{
					break;
				}
				if(countBrace==1) 
				{
					function leftFun=temp.initFromString(s1.substring(1, i+1));
					function rightFun=temp.initFromString((s1.substring(i+2,s1.length()-1)));
					return ((ComplexFunction)leftFun).createComplex(st, rightFun);
					//return new ComplexFunction(leftFun,rightFun,Operation.valueOf(st));
				}
			}
		}
		
		else
		{
			function leftPoly=new Polynom(s.substring(1,s.indexOf(',')));
			function rightPoly=new Polynom(s.substring(s.indexOf(',')+1,s.length()-1));
			return ((ComplexFunction)leftPoly).createComplex(st, rightPoly);
		}
		return null;
	}
	
	public function createComplex(String oper, function ff) 
	{
		switch (Operation.valueOf(oper))
		{
		case Plus:
			plus(ff);
		case Times:
			mul(ff);
		case Divid:
			div(ff);
		case Max:
			max(ff);
		case Min:
			min(ff);
		case Comp:
			comp(ff);
		case None:
			//toCheck
			break;
		default:
			break;
	}
		return this;
	}
	@Override
	public function copy() 
	{
		function copyFun =  new ComplexFunction(this.left, this.right,this.op);
		return copyFun;
	}

	@Override
	public void plus(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Plus;
	}

	@Override
	public void mul(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Times;
	}

	@Override
	public void div(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Divid;
		
	}

	@Override
	public void max(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Max;
		
	}

	@Override
	public void min(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
		this.left = temp;
		this.right = f1;
		this.op = Operation.Min;
		
	}

	@Override
	public void comp(function f1) 
	{
		ComplexFunction temp = new ComplexFunction(this.left, this.right,this.op);
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
		return this.op.name() + '(' + this.left().toString() +',' + this.right().toString() +')';
		
	}

	@Override
	public boolean equals(Object obj)
	{
		for (int i=0;i<100;i++) 
		{
			double j = Math.random()*100;
			if(obj instanceof ComplexFunction) 
			{
				if(!(this.f(j)==((ComplexFunction)obj).f(j))) 
				{
					return false;
				}
			}else 
			{
				if(!(this.f(j)==((Polynom)obj).f(j))) 
				{
					return false;
				}
			}
		}
		return true;
	}
}

