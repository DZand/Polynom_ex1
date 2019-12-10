package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnitTestFunctions_GUI 
{

		static String[] functString =  
			{
				"comp(comp(-4.0x^2+5.0x^3-2.0x^4,-2.0+x^7),0)",
				"max(plus(0,0),6.0x^7)",
				"div(div(3.0x^8,2.0+x^3-12.0x^7+x^9),-2.0x^3)",
				"max(comp(4.0x^8,-x-2.0x^2+4.0x^6),7.0x^5-x^8)",
				"max(max(3.0x^6-4.0x^9,-4.0-3.0x^9),0)",
				"max(-2.0x^3,0)",
				"mul(2.0x^4,-2.0x^2-x^5)",
				"min(mul(x-2.0x^3-5.0x^6,5.0x^3-5.0x^4-3.0x^7),x-x^4)",
				"div(4.0x^5,-2.0x^6+4.0x^8)",
				"plus(plus(-9.0x^6+4.0x^8,0),-2.0x^3)",
				"mul(comp(-4.0,0),-2.0x^3)",
				"comp(comp(0,-x^3),4.0x^3+x^7+x^9)",
				"plus(max(-1+x^3+x^7,0),3.0x^9)",
				"comp(-3.0x^2+3.0x^4-5.0x^8,x^3-x^4-9.0x^7)",
				"comp(3.0x^6+7.0x^7,0)",
				"plus(-3.0x^3,-2.0x^6+2.0x^7)",
				"div(comp(0,0),3.0x)",
				"mul(max(-4.0x^6-x^8,0),5.0x^2+4.0x^3)",
				"plus(3.0x^8,4.0x^4)"
		};

	@Test
	void testSaveAndInitFileAndDrow() throws Exception 
	{
			Functions_GUI funct1 = new Functions_GUI();
			ComplexFunction cf = new ComplexFunction();
			for (int i = 0; i < functString.length; i++) 
			{
				funct1.add(cf.initFromString(functString[i]));
			}
			try 
			{
				funct1.saveToFile("UnitTestFunctions_GUI.txt");
			} catch (IOException e) 
			{
				fail(e.getMessage());
			}
			Functions_GUI funct2 = new Functions_GUI();
			try 
			{
				funct2.initFromFile("UnitTestFunctions_GUI.txt");
			} catch (IOException e) 
			{
				fail(e.getMessage());
			}
			for (int i = 0; i < funct1.size(); i++) 
			{
				assertEquals(funct1.get(i), funct2.get(i));	
			}
			
			// draw GUI
			funct1.drawFunctions("GUI_params.txt");
		}
	}
