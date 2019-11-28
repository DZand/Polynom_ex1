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
		return 0;
	}

	@Override
	public function initFromString(String s) 
	{
		return null;
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
