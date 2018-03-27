package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class Siblings {

	HashMap<String, ArrayList<String>> famHash;
	HashMap<String, ArrayList<String>> indiHash;
	
	public Siblings(HashMap<String, ArrayList<String>> famHash, HashMap<String, ArrayList<String>> indiHash) {
		this.famHash = famHash;
		this.indiHash = indiHash;
	}
	
	public void listMultBirths() {
		ArrayList<String> famInfo = new ArrayList<String>();
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> sameBday = new ArrayList<String>();
		ArrayList<ArrayList<String>> multBirths = new ArrayList<ArrayList<String>>();
		
		System.out.println("\n===============Caroline's US32 - List multiple births===============");
		
		for (String famKey : this.famHash.keySet()) {
			famInfo = this.famHash.get(famKey);
			String children = famInfo.get(7);
			String[] childrenIDs;
			childrenIDs = children.split(" ");
			for(int i = 0; i < childrenIDs.length; i++) {
				sameBday = new ArrayList<String>();
				if(!childrenIDs[i].toString().equals("")) {
					String child = childrenIDs[i];
					sameBday.add(indiHash.get(child).get(1));
					for(int j = i+1; j < childrenIDs.length; j++) {	
						if(indiHash.get(child).get(3).equals(indiHash.get(childrenIDs[j]).get(3))) {
							sameBday.add(indiHash.get(childrenIDs[j]).get(1));
							childrenIDs[j] = "";
						}
					}
				}
				if(sameBday.size() > 1) {
					multBirths.add(sameBday);
				}
			}
		}
		for (ArrayList<String> ppl: multBirths) {
			System.out.println(ppl);
		}
	}
	
	public void checkSiblings(){
		ArrayList<String> famInfo = new ArrayList<String>();
		System.out.println("\n===============Caroline's US15 - Fewer than 15 siblings===============");
		for (String famKey : this.famHash.keySet()) {
			famInfo = this.famHash.get(famKey);
			String [] numChil = famInfo.get(7).split(" ");
			if(numChil.length > 15) {
				System.out.println("ERROR US15: Family ID " + famKey + " has more than 15 children" );	
			}
		}
	}
}
