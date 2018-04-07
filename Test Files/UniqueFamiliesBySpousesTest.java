package GedcomParse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class UniqueFamiliesBySpousesTest {
	
	private HashMap<String, ArrayList<String>> famHash =  new HashMap<>();

	@Test
	void test1() {
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
		
		ArrayList<String> fam4 = new ArrayList<String>();
		fam3.add("F4");
		fam3.add("I6");
		fam3.add("John Sweeney");
		fam3.add("I7");
		fam3.add("Kristine Sweeney");
		fam3.add("7 Jul 1997");
		fam3.add("");
		fam3.add("I8");
		famHash.put("F4", fam4);
		
		ArrayList<String> fam5 = new ArrayList<String>();
		fam2.add("F5");
		fam2.add("I1");
		fam2.add("Justin Sweeney");
		fam2.add("I2");
		fam2.add("Pam Janson");
		fam2.add("7 Nov 1999");
		fam2.add("");
		fam2.add("");
		famHash.put("F5", fam5);
		
		ArrayList<String> results = new ArrayList<String>();
		
		results.add("Error: Family with Husband  I6 and Wife I7 named John Sweeney and Kristine Sweeney is a duplicate!");
		results.add("Error: Family F5 with Husband  I1 and Wife I2 named Justin Sweeney and Pam Janson is a duplicate!");
		
		UniqueFamiliesBySpouses ufs = new UniqueFamiliesBySpouses();
		
		assertEquals(results, ufs.uniqueFamilies(famHash));
		
		
	}

}
