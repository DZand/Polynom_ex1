package Ex1;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;
/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest 
{
	public static void main(String[] a) throws Exception 
	{
		functions data = FunctionsFactory();
	//	int w=1000, h=600, res=200;
	//	Range rx = new Range(-10,10);
	//	Range ry = new Range(-5,15);
//		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try 
		{
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);
	}
	
	private Functions_GUI origin_data = null;
	
    @BeforeEach
    void setUp() throws Exception 
    {
    	origin_data = FunctionsFactory();
    }
	
	public static String[] _data =  
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
	void testFunctions_GUI() 
	{
		Functions_GUI funct1 = new Functions_GUI();
		ComplexFunction cf = new ComplexFunction();
		for (int i = 0; i < _data.length; i++) 
		{
			funct1.add(cf.initFromString(_data[i]));
		}
		
		try 
		{
			funct1.saveToFile("UnitTestFunctions_GUI.txt");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Functions_GUI funct2 = new Functions_GUI();
		try 
		{
			funct2.initFromFile("UnitTestFunctions_GUI.txt");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < funct1.size(); i++) 
		{
			assertEquals(funct1.get(i), funct2.get(i));	
		}
		
		try
		{
			funct1.drawFunctions("GUI_params.txt");
		}
		catch(Exception e)
		{
			fail("e");
		}
	}


	public static Functions_GUI FunctionsFactory() throws Exception 
	{
		Functions_GUI ans = new Functions_GUI();
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction(p2, p1,Operation.Plus);
		ComplexFunction cf4 = new ComplexFunction(new Polynom("x+1"),cf3,"Divid");
		cf4.plus(new Monom("2"));
		
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}
}
