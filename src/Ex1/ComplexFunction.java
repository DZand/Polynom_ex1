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
				//ask yael
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
		function ans = null;
		s = s.toLowerCase();
		int firstOpen = s.indexOf('(');
		String st=null;
		int countComa = 0;
		int countBrace =0;
		for (int i =0; i<s.length()-1;i++)
		{
			if(s.indexOf(i)==',') 
			{
				countComa ++;
			}
		}
		String operationTemp=s.substring(0,firstOpen-1);
		for (Operation c : Operation.values()) 
		 {
		        if (c.name().equals(operationTemp)) 
		        {
		            st=c.name();
		        }
		 }
		int start=s.indexOf(st);
		if(start>=0 && s.endsWith(")")) 
		{
			String s1= s.substring(start+st.length(),s.length()-1);
			for(int i = 0; i<s1.length();i++) 
			{
				if(s1.charAt(i)=='(') 
				{
					countBrace++;
				}
				else if(s1.charAt(i)==')') 
				countBrace--;
				if (countBrace==0) 
				{
					break;
				}
				if(countBrace==1) 
				{
					function leftFun=initFromString(s1.substring(1, i-1));
					function rightFun=initFromString((s1.substring(i+1,s1.length()-2)));
					return new ComplexFunction(leftFun,rightFun,Operation.valueOf(st));
				}
			}
			function ff=initFromString(s1);
			switch (Operation.valueOf(st))
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
			//ans=new ComplexFunction();
			}
		}
		else
		{
			Polynom left=new Polynom(s.substring(1,s.indexOf(',')));
			Polynom right=new Polynom(s.substring(s.indexOf(',')+1,s.length()-1));
			ans=new ComplexFunction(left,right,Operation.valueOf(st));
		}
		return ans;

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


}
