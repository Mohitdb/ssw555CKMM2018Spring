package GedcomParse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MarriageAfterDeathTest {
	
	ArrayList<String> res = new ArrayList<>();
	
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash =  new HashMap<>();

	@Test
	void test1() {
		ArrayList<String> id1 = new ArrayList<String>();
		id1.add("I3");
		id1.add("Rocky Shah");
		id1.add("M");
		id1.add("15 JUN 1950");
		id1.add("");
		id1.add("67");
		indiHash.put("I3", id1);
		
		ArrayList<String> id2 = new ArrayList<String>();
		id2.add("I5");
		id2.add("Ahan Sikri");
		id2.add("M");
		id2.add("10 MAY 1969");
		id2.add("29 AUG 2000");
		id2.add("31");
		indiHash.put("I5", id2);
		
		ArrayList<String> id3 = new ArrayList<String>();
		id3.add("I4");
		id3.add("Rekha Shah");
		id3.add("F");
		id3.add("14 MAY 1952");
		id3.add("");
		id3.add("65");
		indiHash.put("I4", id3);
		
		ArrayList<String> id4 = new ArrayList<String>();
		id4.add("I7");
		id4.add("Mansi Shah");
		id4.add("F");
		id4.add("7 MAY 1952");
		id4.add("");
		id4.add("65");
		indiHash.put("I7", id4);
		
		ArrayList<String> id5 = new ArrayList<String>();
		id5.add("I6");
		id5.add("Kartik Shah");
		id5.add("M");
		id5.add("9 JUL 1950");
		id5.add("12 JUN 1984");
		id5.add("33");
		indiHash.put("I6", id5);
		
		ArrayList<String> id6 = new ArrayList<String>();
		id6.add("I8");
		id6.add("Randy Sikri");
		id6.add("M");
		id6.add("19 JUN 1943");
		id6.add("6 JUL 1990");
		id6.add("47");
		indiHash.put("I8", id6);
		
		ArrayList<String> id7 = new ArrayList<String>();
		id7.add("I9");
		id7.add("Fahra Sikri");
		id7.add("F");
		id7.add("16 JUL 1945");
		id7.add("");
		id7.add("72");
		indiHash.put("I9", id7);
		
		ArrayList<String> id8 = new ArrayList<String>();
		id8.add("I11");
		id8.add("Sanjana Sikri");
		id8.add("F");
		id8.add("11 MAY 1974");
		id8.add("");
		id8.add("43");
		indiHash.put("I11", id8);
		
		ArrayList<String> id9 = new ArrayList<String>();
		id2.add("I2");
		id2.add("Pretty Shah");
		id2.add("M");
		id2.add("5 JUL 1975");
		id2.add("");
		id2.add("42");
		indiHash.put("I2", id9);
		
		ArrayList<String> id10 = new ArrayList<String>();
		id2.add("I1");
		id2.add("Jack Shah");
		id2.add("M");
		id2.add("5 JAN 1973");
		id2.add("26 MAR 2001");
		id2.add("28");
		indiHash.put("I1", id10);
		
		ArrayList<String> fam1 = new ArrayList<String>();
		
		ArrayList<String> id101 = new ArrayList<String>();
		id2.add("I10");
		id2.add("Rakan Sikri");
		id2.add("M");
		id2.add("10 MAY 1972");
		id2.add("11 APR 1990");
		id2.add("17");
		indiHash.put("I10", id101);
		
		ArrayList<String> fam11 = new ArrayList<String>();
	
		fam11.add("F6");
		fam11.add("I10");
		fam11.add("Rakan Sikri");
		fam11.add("I11");
		fam11.add("Sanjana Sikri");
		fam11.add("8 OCT 1994");
		fam11.add("");
		fam11.add("");
		famHash.put("F6", fam11);
		
		ArrayList<String> fam2 = new ArrayList<String>();
		fam2.add("F1");
		fam2.add("I1");
		fam2.add("Jack Shah");
		fam2.add("I2");
		fam2.add("Pretty Shah");
		fam2.add("28 MAY 2008");
		fam2.add("");
		fam2.add("");
		famHash.put("F1", fam2);
		
		ArrayList<String> fam3 = new ArrayList<String>();
		fam3.add("F2");
		fam3.add("I3");
		fam3.add("Rocky Shah");
		fam3.add("I4");
		fam3.add("Rekha Shah");
		fam3.add("26 OCT 1969");
		fam3.add("");
		fam3.add("I1");
		famHash.put("F2", fam3);
		
		ArrayList<String> fam4 = new ArrayList<String>();
		fam3.add("F3");
		fam3.add("I5");
		fam3.add("Ahan Sikri");
		fam3.add("I2");
		fam3.add("Pretty Shah");
		fam3.add("11 JUN 2002");
		fam3.add("12 MAY 2004");
		fam3.add("");
		famHash.put("F3", fam4);
		
		MarriedAfterDeath md = new MarriedAfterDeath();
		
		DivorcedAfterDeath dd = new DivorcedAfterDeath();
		
		
		//System.out.println(am.getAliveMarried());
		//System.out.println(am.getAliveSingle());

		//System.out.println(id2);
		
		int expected = 0;
		
		int res = md.marriageAfterDeath(indiHash, famHash);
		
		assertEquals(res, expected);
		
	}

}
