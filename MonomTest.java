package myMath1;
import java.util.ArrayList;


/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest 
{
	public static void main(String[] args) throws Exception 
	{
		/**
		test1();
		test2();
		CopyConstructorTest();
		DerivativeTest();
		EqualsTest();
		MultiplyTest();
		AddTest();
		*/
		positiveTests();
	}
	
	
	
	private static void test1() throws _Exception 
	{
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) 
		{
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() throws _Exception 
	{
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) 
		{
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	
/**
 * 
 *This test checks the copy constructor function.
 * @throws _Exception
 */
	private static void CopyConstructorTest() throws _Exception 
	{
		Monom mn = new Monom(15,2);
		Monom c = mn;
		if(!c.equals(mn))
		{
			throw new _Exception("Error In Copy Constructor");
		}
		else
		{
			System.out.println("WOW");
		}
	}
	
	private static void DerivativeTest() throws _Exception 
	{
		Monom m = new Monom("x^2");
		Monom n = new Monom("2x");
		Monom c = m.derivative();
		if(!c.equals(n))
		{
			throw new _Exception("Wrong derivative");
		}
		else
		{
			System.out.println("WOW");
		}

	}
	private static void EqualsTest() throws _Exception 
	{
		Monom m1 = new Monom(5,2);
		Monom m2 = new Monom(5,2);
		if(!m1.equals(m2))
		{
			throw new _Exception("Not Equal");
		}
		else
		{
			System.out.println("WOW");
		}
	}
	
	private static void MultiplyTest() throws _Exception 
	{
		Monom m1 = new Monom(5,2);
		Monom m2 = new Monom(4,2);
		Monom m3 = new Monom(20,4);
		m1.multipy(m2);
		if(!m1.equals(m3))
		{
			throw new _Exception("Multiply Not Equal");
		}
		else
		{
			System.out.println("WOW");
		}
	}
	
	private static void AddTest() throws _Exception 
	{
		Monom m1 = new Monom(5,2);
		Monom m2 = new Monom(4,2);
		Monom m3 = new Monom(9,2);
		m1.add(m2);
		if(!m1.equals(m3))
		{
			
			throw new _Exception("Add Not Equal");
		}
		else
		{
			System.out.println("WOW");
		}
	}
	
	public static void positiveTests() throws Exception
	{
		Monom m = new Monom("2x^2");

		Monom new_copy_one = new Monom(m);

		Monom n = new Monom(2, 2);

		System.out.println("This is the Monom m: " + m.toString());
		System.out.println("This is the Monom new_copy_one: " + new_copy_one.toString());
		System.out.println("This is the Monom n: " + n.toString());

		n.add(m);
		System.out.println(m + " + 2*x^2 (n)= " + n.toString());

		Monom t = n.derivative();
		System.out.println("The derivative of " + n + " is: " + t.toString());

		System.out.println("ans for f(2): " + m.f(2)); // for f(double a) function

		m.multipy(n);
		System.out.println("2*x^2 * " + n + " = " + m.toString());
	}
	
}
