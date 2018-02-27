package GedcomParse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ageTest {

	@Test
	void test() 
	{
		FindAge test = new FindAge();
		int test1 = test.FindAge("25 JUN 1971", "30 SEP 1977");
		assertEquals(6, test1);
		
		int test2 = test.FindAge("28 JAN 1985", "30 APR 1992");
		assertEquals(7, test2);
		
		int test3 = test.FindAge("5 JAN 1996", "13 FEB 2018");
		assertEquals(22, test3);
		
		int test4 = test.FindAge("10 SEP 1995", "13 FEB 2018");
		assertEquals(22, test4);
		
		int test5 = test.FindAge("20 OCT 1999", "15 JAN 2002");
		assertEquals(2, test5);
	}

}