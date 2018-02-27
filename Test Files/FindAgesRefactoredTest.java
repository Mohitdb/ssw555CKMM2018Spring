package GedcomParse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindAgesRefactoredTest {

	@Test
	void test() 
	{
		FindAgesRefactored test = new FindAgesRefactored();
		int test1 = test.ageFinder("25 JUN 1971", "30 SEP 1977");
		assertEquals(6, test1);
		
		int test2 = test.ageFinder("28 JAN 1985", "30 APR 1992");
		assertEquals(7, test2);
		
		int test3 = test.ageFinder("5 JAN 1996", "13 FEB 2018");
		assertEquals(22, test3);
		
		int test4 = test.ageFinder("10 SEP 1995", "13 FEB 2018");
		assertEquals(22, test4);
		
		int test5 = test.ageFinder("20 OCT 1999", "15 JAN 2002");
		assertEquals(2, test5);
	}

}