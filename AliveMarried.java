package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class AliveMarried {

	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	private ArrayList<ArrayList<String>> livMarried = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> livSingle = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> deceased = new ArrayList<ArrayList<String>>();

	AliveMarried(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash) {
		this.indiHash = indiHash;
		this.famHash = famHash;
		find();
	}
	
	private void find() {
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();

		for (String indiKey : this.indiHash.keySet()) {
			indiInfo = this.indiHash.get(indiKey);
			for (String famKey : this.famHash.keySet()) {
				famInfo = this.famHash.get(famKey);
				//System.out.println("id" + indiInfo);
				//System.out.println("fam" + famInfo);
				if((indiKey.equals(famInfo.get(1)) || indiKey.equals(famInfo.get(3))) && indiInfo.get(4) == "") {
					if(livSingle.contains(indiInfo)) {
						livSingle.remove(indiInfo);
					}
					livMarried.add(indiInfo);
					break;
				}else if (indiInfo.get(4) == ""  && Integer.parseInt(indiInfo.get(5)) > 30){
					if(!livSingle.contains(indiInfo)) {
						livSingle.add(indiInfo);
					}
				}else if(indiInfo.get(4) != ""){
					if(!deceased.contains(indiInfo)) {
						deceased.add(indiInfo);
					}
				}
			}
		}
	}
	
	public ArrayList<ArrayList<String>> getAliveMarried() {
		return livMarried;
	}
	
	public ArrayList<ArrayList<String>> getAliveSingle() {
		return livSingle;
	}
	
	public ArrayList<ArrayList<String>> getDeceased() {
		return deceased;
	}

}
