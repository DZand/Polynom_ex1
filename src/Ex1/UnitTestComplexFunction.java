package Ex1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTestComplexFunction 
{
	public static String[] data =  
		{
			"Comp(Comp(-4.0x^2+5.0x^3-2.0x^4,-2.0+x^7),0)",
			"Max(Plus(0,0),6.0x^7)",
			"Divid(Divid(3.0x^8,2.0+x^3-12.0x^7+x^9),-2.0x^3)",
			"Max(Comp(4.0x^8,-x-2.0x^2+4.0x^6),7.0x^5-x^8)",
			"Max(Max(3.0x^6-4.0x^9,-4.0-3.0x^9),0)",
			"Max(-2.0x^3,0)",
			"Times(2.0x^4,-2.0x^2-x^5)",
			"Min(Times(x-2.0x^3-5.0x^6,5.0x^3-5.0x^4-3.0x^7),x-x^4)",
			"Divid(4.0x^5,-2.0x^6+4.0x^8)",
			"Plus(Plus(-9.0x^6+4.0x^8,0),-2.0x^3)",
			"Times(Comp(-4.0,0),-2.0x^3)",
			"Comp(Comp(0,-x^3),4.0x^3+x^7+x^9)",
			"Plus(Max(-1+x^3+x^7,0),3.0x^9)",
			"Comp(-3.0x^2+3.0x^4-5.0x^8,x^3-x^4-9.0x^7)",
			"Comp(3.0x^6+7.0x^7,0)",
			"Plus(-3.0x^3,-2.0x^6+2.0x^7)",
			"Divid(Comp(0,0),3.0x)",
			"Times(Max(-4.0x^6-x^8,0),5.0x^2+4.0x^3)",
			"Plus(3.0x^8,4.0x^4)"
		};
	
	@Test
	void testCopyAndEquals() 
	{
		String[] funcs = data;
		for (int i = 0; i < funcs.length; i++) 
		{
			ComplexFunction tmp = new ComplexFunction();
			function cf = tmp.initFromString(funcs[i]);
			function copy = cf.copy();
			assertEquals(cf, copy);
		}
	}
	
	@Test 
	void testPlus() throws Exception
	{
		Polynom p1=new Polynom("6.0x^7+1.0x^1");
		Polynom p2=new Polynom("1.0x^3-2.0x^2");
		p1.add(p2);
		ComplexFunction tmp = new ComplexFunction(p1,p2,Operation.Plus);
		assertTrue(tmp.equals(p1));
	}
	
	@Test 
	void testMul() throws Exception
	{
		String s1 = "3-x^4";
		String s2 = "5+2x";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s1);
		Polynom p3 = new Polynom(s2);
		Polynom p4 = new Polynom(s2);
		ComplexFunction cf = new ComplexFunction(p1,p3,Operation.Times);
		p2.multiply(p4);
		if(!cf.equals(p2))
		{
			fail("multiply is not working");
		}
	}
	
	
	@Test 
	void testMulByZero() throws Exception
	{
		String s1 = "3-x^4";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom();
		ComplexFunction cf = new ComplexFunction(p1,p2,Operation.Times);
		p1.multiply(p2);
		assertTrue(cf.equals(p2));
		assertTrue(p1.equals(p2));
		
	}
	
	@Test
	public void testDivByZero() 
	{
		String s1 = "3.0x^0-1.0x^4";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom();
		ComplexFunction cf = new ComplexFunction(p1,p2,Operation.Divid);
		assertThrows(ArithmeticException.class, () -> { cf.f(2);;  });
	}
	
	
	@Test 
	void testDivid()
	{
		String left = "8.0x^2+6.0x^1";
		String right = "2.0x^1";
		String result="4.0x^1+3.0x^0";
		Polynom p1 = new Polynom(left);
		Polynom p2 = new Polynom(right);
		Polynom p3 = new Polynom(result);
		ComplexFunction cf = new ComplexFunction(p1,p2,Operation.Divid);
		assertEquals(cf, p3);
	}
	
	@Test
	void testGetOpTest()
	{
		Polynom p1= new Polynom("5.0x^2-2.0x^0");
		Polynom p2= new Polynom("4.0x^7");
		String opString="Divid";
		ComplexFunction f1= new ComplexFunction(p1, p2,opString);
		assertEquals(f1.getOp().toString(), opString.toString());
	}
	
	@Test
	void testGetRightLeft()
	{
		Polynom p1= new Polynom("5.0x^2-2.0x^0");
		Polynom p2= new Polynom("4.0x^7");
		String opString="Divid";
		ComplexFunction f1= new ComplexFunction(p1, p2,opString);
		assertEquals(f1.left().toString(), "5.0x^2-2.0x^0");
		assertEquals(f1.right().toString(), "4.0x^7");
	}
	
	@Test
	void testinitFromStringAndFiles()
	{
		Functions_GUI funct1 = new Functions_GUI();
		ComplexFunction cf = new ComplexFunction();
		for (int i = 0; i < data.length; i++) 
		{
			try
			{
				funct1.add(cf.initFromString(data[i]));
			}
			catch(Exception e)
			{
				fail("init from string is not working" + e);
			}
		}
		
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		Functions_GUI funct2=null;
		try 
		{
			funct1.saveToFile(file);
			funct2 = new Functions_GUI();
			funct2.initFromFile(file);
			funct1.saveToFile(file2);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < funct1.size(); i++) 
		{
			assertEquals(funct1.get(i), funct2.get(i));	
		}
		
		String JSON_param_file = "GUI_params.txt";
		funct1.drawFunctions(JSON_param_file);
	}
	
	@Test
	void testComp()
	{
		Polynom p1= new Polynom("5.0x^2-2.0x^0");
		Polynom p2= new Polynom("4.0x^7");
		String opString="Comp";
		ComplexFunction f1= new ComplexFunction(p1, p2,opString);
		assertEquals(f1.toString(), "Comp(5.0x^2-2.0x^0,4.0x^7)");
	}
	
	@Test
	void testMaxMin()
	{
		Polynom p1= new Polynom("5.0x^2-2.0x^0");
		Polynom p2= new Polynom("4.0x^7");
		Polynom p3= new Polynom("3.0x^6");
		String opString="Max";
		ComplexFunction f1= new ComplexFunction(p1, p2,opString);
		assertEquals(f1.toString(), "Max(5.0x^2-2.0x^0,4.0x^7)");
		opString="Min";
		ComplexFunction f2= new ComplexFunction(f1,p3,opString);
		assertEquals(f2.toString(), "Min(Max(5.0x^2-2.0x^0,4.0x^7),3.0x^6)");
	}
	
	
}
