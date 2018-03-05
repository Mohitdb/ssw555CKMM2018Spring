package GedcomParse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class aliveMarriedTest {
	
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash =  new HashMap<>();

	@Test
	void test1() {
		ArrayList<String> id1 = new ArrayList<String>();
		id1.add("I1");
		id1.add("Justin Sweeney");
		id1.add("M");
		id1.add("11 SEP 1963");
		id1.add("");
		id1.add("48");
		indiHash.put("I1", id1);
		
		ArrayList<String> id2 = new ArrayList<String>();
		id2.add("I12");
		id2.add("Lucas Gallen");
		id2.add("M");
		id2.add("7 JUL 1965");
		id2.add("11 SEP 2001");
		id2.add("45");
		indiHash.put("I12", id2);
		
		ArrayList<String> id3 = new ArrayList<String>();
		id3.add("I2");
		id3.add("Pam Janson");
		id3.add("F");
		id3.add("7 APR 1965");
		id3.add("");
		id3.add("45");
		indiHash.put("I2", id3);
		
		ArrayList<String> id4 = new ArrayList<String>();
		id4.add("I11");
		id4.add("Nicole Janson");
		id4.add("F");
		id4.add("5 NOV 1970");
		id4.add("");
		id4.add("45");
		indiHash.put("I11", id4);
		
		ArrayList<String> id5 = new ArrayList<String>();
		id5.add("I5");
		id5.add("Sean Sweeney");
		id5.add("M");
		id5.add("12 NOV 1965");
		id5.add("");
		id5.add("55");
		indiHash.put("I5", id5);
		
		ArrayList<String> id6 = new ArrayList<String>();
		id6.add("I6");
		id6.add("John Sweeney");
		id6.add("M");
		id6.add("3 NOV 1970");
		id6.add("11 SEP 2008");
		id6.add("50");
		indiHash.put("I6", id6);
		
		ArrayList<String> id7 = new ArrayList<String>();
		id7.add("I7");
		id7.add("Kristine Sweeney");
		id7.add("F");
		id7.add("20 NOV 1970");
		id7.add("11 SEP 2008");
		id7.add("50");
		indiHash.put("I7", id7);
		
		ArrayList<String> id8 = new ArrayList<String>();
		id8.add("I8");
		id8.add("Sammy Sweeney");
		id8.add("F");
		id8.add("18 Dec 2000");
		id8.add("");
		id8.add("14");
		indiHash.put("I8", id8);
		
		ArrayList<String> fam1 = new ArrayList<String>();
	
		fam1.add("F6");
		fam1.add("I12");
		fam1.add("Lucas Gallen");
		fam1.add("I11");
		fam1.add("Nicole Jason");
		fam1.add("19 Dec 1997");
		fam1.add("");
		fam1.add("");
		famHash.put("F6", fam1);
		
		ArrayList<String> fam2 = new ArrayList<String>();
		fam2.add("F1");
		fam2.add("I1");
		fam2.add("Justin Sweeney");
		fam2.add("I2");
		fam2.add("Pam Janson");
		fam2.add("7 Nov 1999");
		fam2.add("");
		fam2.add("");
		famHash.put("F1", fam2);
		
		ArrayList<String> fam3 = new ArrayList<String>();
		fam3.add("F3");
		fam3.add("I6");
		fam3.add("John Sweeney");
		fam3.add("I7");
		fam3.add("Kristine Sweeney");
		fam3.add("7 Jul 1997");
		fam3.add("");
		fam3.add("I8");
		famHash.put("F3", fam3);
		
		AliveMarried am = new AliveMarried(indiHash, famHash);
		
		//System.out.println(am.getAliveMarried());
		//System.out.println(am.getAliveSingle());

		//System.out.println(id2);
		
		assertEquals("[[I1, Justin Sweeney, M, 11 SEP 1963, , 48], [I11, Nicole Janson, F, 5 NOV 1970, , 45], [I2, Pam Janson, F, 7 APR 1965, , 45]]", am.getAliveMarried().toString());
		assertFalse(am.getAliveMarried().contains(id2)); 
		assertFalse(am.getAliveMarried().contains(id5)); 
		assertTrue(am.getAliveMarried().contains(id1)); 
		assertTrue(am.getAliveMarried().contains(id3)); 
		assertEquals("[[I5, Sean Sweeney, M, 12 NOV 1965, , 55]]", am.getAliveSingle().toString());
		assertTrue(am.getAliveSingle().contains(id5)); 
		assertEquals("[[I12, Lucas Gallen, M, 7 JUL 1965, 11 SEP 2001, 45], [I6, John Sweeney, M, 3 NOV 1970, 11 SEP 2008, 50], [I7, Kristine Sweeney, F, 20 NOV 1970, 11 SEP 2008, 50]]", am.getDeceased().toString());
		assertTrue(am.getDeceased().contains(id2)); 
		assertTrue(am.getDeceased().contains(id6)); 
		assertTrue(am.getDeceased().contains(id7)); 
		assertEquals("[[I8, Sammy Sweeney, F, 18 Dec 2000, , 14]]", am.getOrphans().toString());
		assertTrue(am.getOrphans().contains(id8)); 
	}

}
