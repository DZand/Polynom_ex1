package myMath1;

public class PolynomTest 
{
	public static void main(String[] args) throws Exception 
	{
		//test1();
		//test2();
		positiveTests();

	}
	public static void test1() throws Exception 
	{
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) 
		{
		Monom m = new Monom(monoms[i]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
		}
		//p1.RemoveSamePower(p1);
		System.out.println(p1);
		
	}
	public static void test2() throws Exception 
	{
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) 
		{
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) 
		{
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		//p1.add(p2);
		//System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		//Polynom_able pp1 = Polynom.parse(s1);
		//System.out.println("from string: "+pp1);
	}
	public static void positiveTests() throws Exception
	{
		Polynom p1 = new Polynom("x^2-2x+1");
		Polynom p3 = new Polynom();
		Monom m1 = new Monom("x^3");
		Monom m2 = new Monom(2, 2);

		System.out.println("This is the Polynom p1: " + p1.toString());
		System.out.println("This is the Polynom p3: " + p3.toString());

		System.out.println("ans for f(4) : " + p1.f(4));

		Polynom p2 = new Polynom("2x+1");
		p1.add(p2);
		System.out.println("p1 + p2 = " + p1.toString());

		p1.add(m1);
		p2.add(m2);
		System.out.println(m1 + " m1 add to p1 : " + p1.toString());
		System.out.println(m2 + " m2 add to p2 : " + p2.toString());

		System.out.println("here");
		
		
		Polynom p4 = new Polynom("5x^3+2x+1");
		Polynom p5 = new Polynom("2x^2+1");
		System.out.println("This is the Polynom p4: " + p4.toString());
		p4.substract(p5);
		System.out.println(p5 + "p5 substract from the Polynom p4: " + p4.toString());
		
		System.out.println("here");

		Polynom p6 = new Polynom("x^2+x");
		Polynom p7 = new Polynom("2x+1");
		p6.multiply(p7);
		System.out.println("The ans should be : 2x^3 + 3x^2 + x ->" + p6.toString());
		
		

		Polynom p8 = new Polynom("x^2+x");
		Polynom p9 = new Polynom("2x+1");
		System.out.println("The ans should be no -->" + p8.equals(p9));

		Polynom p10 = new Polynom("0");
		System.out.println("The ans should be yes -->" + p10.isZero());

		
		
		Polynom p11 = new Polynom("x^2-2");
		System.out.println("The ans should be : sqrt(2) -->" + p11.root(-1, 3, 0.001));

		
		
		Polynom p12 = new Polynom("x^2-2x+1");
		Polynom_able p13 = p12.copy();
		System.out.println("This is the Polynom should be : x^2 - 2x + 1 --> " + p13.toString());

		
		
		Polynom p14 = new Polynom("x^2-2x+1");
		System.out.println("The ans should be : 2x - 2 --> " + p14.derivative().toString());

		Polynom p15 = new Polynom("x^2");
		System.out.println("The ans should be : 80.9999 --> " + p15.area(-6, 3, 0.001));
		
		Polynom p16= new Polynom("y^3");
	}

}
