package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomTest 
{
	private static Monom excepted_Monom;
	private static Monom actual_Monom;

	@BeforeEach
	void setUp() throws Exception 
	{
		MonomTest.excepted_Monom = new Monom(3, 4);
		MonomTest.actual_Monom = new Monom(3, 4);
	}

	@Test
	void testMonomConstructorString() 
	{
		String TheWrongSstr = "0.2*x^-1 1.a5 1a*x^2 x^ 5a";
		String TheOkStr = "0.2x^1 1.5x^3 3.0x^2 x 5";
		String[] arrMonom = TheOkStr.split(" ");
		String[] arrMonomWrong = TheWrongSstr.split(" ");
		for (int i = 0; i < arrMonom.length; i++) 
		{
			try 
			{ // Test a valid input
				Monom testOk = new Monom(arrMonom[i]);
			} 
			catch (Exception e) 
			{
				fail("There is a valid input that not working");
			}
			try 
			{ // Test a wrong input
				Monom testWrong = new Monom(arrMonomWrong[i]);
				fail("There is a invalid input that working");
			} 
			catch (Exception e) 
			{

			}
		}
		MonomTest.excepted_Monom = new Monom("3x^4");
		assertEquals(actual_Monom, MonomTest.excepted_Monom);	
	}


	@Test
	void testMonomConstructorMonom() 
	{
		Monom testMonom = new Monom(excepted_Monom);
		assertEquals(actual_Monom, testMonom);
	}


	@Test
	void testDerivative() 
	{
		double excepted_coefficient = (MonomTest.actual_Monom.get_coefficient() * MonomTest.actual_Monom.get_power());
		int excepted_power = (MonomTest.actual_Monom.get_power() - 1);
		MonomTest.actual_Monom = MonomTest.actual_Monom.derivative();
		assertEquals(actual_Monom.get_coefficient(), excepted_coefficient);
		assertEquals(actual_Monom.get_power(), excepted_power);
	}

	@Test
	void testF() 
	{
		double x = 3;
		double excepted_FValue = (MonomTest.actual_Monom.get_coefficient() * Math.pow(x, MonomTest.actual_Monom.get_power()));
		assertEquals(excepted_FValue,MonomTest.actual_Monom.f(x));
	}


	@Test
	void testAdd() 
	{
		MonomTest.actual_Monom.add(new Monom(3, 4));
		assertEquals(actual_Monom.get_coefficient(),6);
		assertEquals(actual_Monom.get_power(),4);
	}

	@Test
	void testMultiply() 
	{
		double excepted_coefficient = MonomTest.actual_Monom.get_coefficient() * 3;
		int excepted_power = MonomTest.actual_Monom.get_power() + 5;
		MonomTest.actual_Monom.multipy(new Monom(3, 5));
		assertEquals(actual_Monom.get_coefficient(),excepted_coefficient);
		assertEquals(actual_Monom.get_power(),excepted_power);
	}


	@Test
	void testIs_equal() 
	{
		boolean ans = MonomTest.actual_Monom.equals(MonomTest.excepted_Monom);
		assertTrue(ans);
	}


	@Test
	void testIs_zero() 
	{
		MonomTest.actual_Monom = new Monom(0, 3);
		boolean ans = MonomTest.actual_Monom.isZero();
		assertTrue(ans);
	}

	@Test
	void testGet_power() 
	{
		assertEquals(MonomTest.actual_Monom.get_power(),4);
	}

	@Test
	void testGet_coefficient() 
	{
		assertEquals(MonomTest.actual_Monom.get_coefficient(),3);
	}

	@Test
	void testToStringConstructor() 
	{
		MonomTest.actual_Monom = new Monom(MonomTest.actual_Monom.toString());
		assertEquals(actual_Monom,excepted_Monom);
	}

}