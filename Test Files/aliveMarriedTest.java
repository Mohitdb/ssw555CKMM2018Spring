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
		indiHash.put("I1", id1);
		
		ArrayList<String> id2 = new ArrayList<String>();
		id2.add("I12");
		id2.add("Lucas Gallen");
		id2.add("M");
		id2.add("7 JUL 1965");
		id2.add("11 SEP 2001");
		indiHash.put("I12", id2);
		
		ArrayList<String> id3 = new ArrayList<String>();
		id3.add("I2");
		id3.add("Pam Janson");
		id3.add("F");
		id3.add("7 APR 1965");
		id3.add("");
		indiHash.put("I2", id3);
		
		ArrayList<String> id4 = new ArrayList<String>();
		id4.add("I11");
		id4.add("Nicole Janson");
		id4.add("F");
		id4.add("5 NOV 1970");
		id4.add("");
		indiHash.put("I11", id4);
		
		ArrayList<String> id5 = new ArrayList<String>();
		id5.add("I5");
		id5.add("Sean Sweeney");
		id5.add("M");
		id5.add("12 NOV 1935");
		id5.add("");
		indiHash.put("I5", id5);
		
		ArrayList<String> fam1 = new ArrayList<String>();
	
		fam1.add("F6");
		fam1.add("I12");
		fam1.add("Lucas Gallen");
		fam1.add("I11");
		fam1.add("Nicole Jason");
		famHash.put("F6", fam1);
		
		ArrayList<String> fam2 = new ArrayList<String>();
		fam2.add("F1");
		fam2.add("I1");
		fam2.add("Justin Sweeney");
		fam2.add("I2");
		fam2.add("Pam Janson");
		famHash.put("F1", fam2);
		
		AliveMarried am = new AliveMarried(indiHash, famHash);
		
		System.out.println(am.getAliveMarried());
		System.out.println(am.getAliveSingle());

		assertEquals("[[I1, Justin Sweeney, M, 11 SEP 1963, ], [I11, Nicole Janson, F, 5 NOV 1970, ], [I2, Pam Janson, F, 7 APR 1965, ]]", am.getAliveMarried().toString());
		assertFalse(am.getAliveMarried().contains(id2)); 
		assertFalse(am.getAliveMarried().contains(id5)); 
		assertTrue(am.getAliveMarried().contains(id1)); 
		assertTrue(am.getAliveMarried().contains(id4)); 
	}

}
