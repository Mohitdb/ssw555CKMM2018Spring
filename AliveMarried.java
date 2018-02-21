package GedcomParse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AliveMarried {

	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	private ArrayList<ArrayList<String>> livMarried = new ArrayList<ArrayList<String>>();

	AliveMarried(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash) {
		this.indiHash = indiHash;
		this.famHash = famHash;
	}


	public ArrayList<ArrayList<String>> getAliveMarried() {
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();

		for (String indiKey : indiHash.keySet()) {
			indiInfo = indiHash.get(indiKey);
			for (String famKey : famHash.keySet()) {
				famInfo = famHash.get(famKey);
				if((indiKey.equals(famInfo.get(1)) || indiKey.equals(famInfo.get(3))) && indiInfo.get(4) == "") {
					livMarried.add(indiInfo);
					break;
				}
			}
		}
		return livMarried;
	}

}
