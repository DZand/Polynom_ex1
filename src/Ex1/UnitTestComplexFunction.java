package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnitTestComplexFunction 
{

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		
	}


	
	@Test
	void testCopyAndEquals() 
	{
		String[] funcs = UnitTestFunctions_GUI.functString;
		for (int i = 0; i < funcs.length; i++) {
			ComplexFunction tmp = new ComplexFunction();
			function cf = tmp.initFromString(funcs[i]);
			function copy = cf.copy();
			assertEquals(cf, copy);
		}
	}

}
