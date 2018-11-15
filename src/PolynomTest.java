
import static org.junit.Assert.*;

import org.junit.Test;

public class PolynomTest {


	
	Monom m = new Monom(1,1);
	Monom m1=new Monom(m);
	Monom m2 =new Monom(-1,1);

	@Test
	public void testConstructor()
	{
		//Monom m1=new Monom(m);
		assertTrue(m.get_coefficient()==1);
		assertTrue(m.get_power()==1);
		assertTrue(m1.get_coefficient() == m.get_coefficient());
		//assert(condition);
	}

	@Test
	public void testMonomOperations()
	{
		
		Polynom p1 =new Polynom("1x^1");
		System.out.println(p1);
		p1.add(m2);
		System.out.println(p1);
		assertTrue(p1.zeroRemove());
		System.out.println(p1.zeroRemove());
		System.out.println(p1);
		System.out.println("*");
		System.out.println(p1.zeroRemove());
		


	}

}
