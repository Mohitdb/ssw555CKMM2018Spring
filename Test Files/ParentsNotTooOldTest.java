package GedcomParse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParentsNotTooOldTest {

	@Test
	void test() 
	{
		ParentsNotTooOld test = new ParentsNotTooOld();
		String test1 = test.parentChildAgeCompare(10, 70, 90);
		assertEquals("Both Parents Too Old!", test1);
		
		String test2 = test.parentChildAgeCompare(50, 50, 50);
		assertEquals("Error!", test2);
		
		String test3 = test.parentChildAgeCompare(30, 70, 90);
		assertEquals("Error!", test3);
		
		String test4 = test.parentChildAgeCompare(10, 40, 90);
		assertEquals("Father is 80 years older than child!", test4);
		
		String test5 = test.parentChildAgeCompare(10, 70, 80);
		assertEquals("Mother is 60 years older than child!", test5);
		
		String test6 = test.parentChildAgeCompare(50, 40, 30);
		assertEquals("Child Older than both Parents!", test6);
		
		String test7 = test.parentChildAgeCompare(50, 60, 30);
		assertEquals("Child is older than Father!", test7);
		
		String test8 = test.parentChildAgeCompare(50, 40, 70);
		assertEquals("Child is older than Mother!", test8);
	}

}