/**
 * SSW555 GedcomParse.java
 * 
 * Main file for project
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dnl.utils.text.table.TextTable;

public class GedcomParse {

	static String reader = null;

	static HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	static HashMap<String, ArrayList<String>> famHash = new HashMap<>();

	@SuppressWarnings("resource")
	public static void parse() throws IOException {

		//read file
		// TODO: Change path for our testing file
		FileReader fileReader = new FileReader("C:\\Users\\Caroline Squillante\\workspace\\gedDistributor\\src\\ssw555project01.ged");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		// Creating Object to insert later into array list
		Family fam = new Family();
		Individual indi = new Individual();

		// Flag to check if date is birth date or death date
		boolean isBirth = true;

		// Flag to check if tag is individual
		boolean isIndi = true;

		// Flag to bypass empty objects
		boolean isEmpty = true;

		boolean firstPerson = true;
		ArrayList<String> hashValueIndi = new ArrayList<String>();
		ArrayList<String> hashValueFam = new ArrayList<String>();
		//while loop to check to see if each line is valid and formats the information accordingly
		while ((reader = bufferedReader.readLine()) != null) {
			hashValueIndi = new ArrayList<String>();
			hashValueFam = new ArrayList<String>();

			String[] lst = reader.split(" ", 0);

			String level = lst[0];
			String tag = "";
			Boolean isValid;
			String arguments = "";

			//check for invalid level # (cannot be greater than 2)
			if (Integer.parseInt(level) >= 3) {
				isValid = false;

			}

			//checks all level 0s; valid tag can be INDI, FAM, HEAD, TRLR, NOTE
			if (Integer.parseInt(level) == 0) {
				if (lst[1].contains("HEAD") || lst[1].contains("TRLR") || lst[1].contains("NOTE")) {
					tag = lst[1];
					isValid = true;
					if (lst.length > 2) {
						for (int i = 2; i < lst.length; i++) {
							arguments = arguments + lst[i] + " ";
						}
					}
					//checks special case for INDI and FAM
				} else if (lst[2].contains("INDI") || lst[2].contains("FAM")) {

					//Individual indi = new Individual();
					hashValueIndi.add(indi.getIndividualID().toString());
					hashValueIndi.add(indi.getName());
					hashValueIndi.add(indi.getGender());
					hashValueIndi.add(indi.getBirth());
					hashValueIndi.add(indi.getDeath().toString());

					indiHash.putIfAbsent(indi.getIndividualID(), hashValueIndi);

					//Family fam = new Family();

					//System.out.println(fam.famID);
					hashValueFam.add(fam.getFamID().toString());
					hashValueFam.add(fam.getHusbID());
					hashValueFam.add(fam.getHusbName());
					hashValueFam.add(fam.getWifeID());
					hashValueFam.add(fam.getWifeName());

					famHash.putIfAbsent(fam.getFamID(), hashValueFam);

					if(firstPerson) {
						firstPerson = false;

					}else {
						if (isIndi == true) {
							//individualArray.add(indi);
							//System.out.println("Inside if");
							// Reinitializing the Object

							indi.name = "";
							indi.age = 0;
							indi.alive = true;
							indi.birth = "";
							indi.child = new ArrayList<String>();
							indi.death = "";
							indi.gender = "";
							indi.individualID = "";
							indi.spouse = new ArrayList<String>();
						} else {
							//familyArray.add(fam);

							// Reinitializing the Object
							fam.famID = "";
							fam.married = "";
							fam.divorced = false;
							fam.husbID = "";
							fam.husbName = "";
							fam.wifeID = "";
							fam.wifeName = "";
							fam.children = new ArrayList<String>();
						}
					}

					if (lst[2].contains("INDI")) {
						indi.setIndividualID(lst[1].substring(1, lst[1].length() - 1));
						isIndi = true;
					}else {
						fam.setFamID(lst[1].substring(1, lst[1].length() - 1));
						isIndi = false;
					}

					tag = lst[2];
					arguments = lst[1];
					isValid = true;

				}else {
					tag = lst[1];
					arguments = lst[2];
					isValid = false;
				}
			}

			//checks all level 1s; valid tag can be NAME, SEX, BIRT, DEAT, FAMC, FAMS, MARR, HUSB, WIFE, CHIL, DIV
			String[] validTags
			= {
					"NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"
			};
			if (Integer.parseInt(level) == 1) {
				boolean val = false;
				for (String t : validTags) {
					if (lst[1].contains(t)) {
						val = true;

						if (lst[1].contains("NAME")) {
							indi.setName(lst[2] + " " + lst[3].replace("/", ""));

						} else if (lst[1].contains("SEX")) {
							indi.setGender(lst[2]);
							
						} else if (lst[1].contains("BIRT")) {
							isBirth = true;
							
						} else if (lst[1].contains("DEAT")) {
							indi.setAlive(false);
							isBirth = false;
							
						} else if (lst[1].contains("HUSB")) {
							fam.setHusbID(lst[2].replace("@", ""));
							//System.out.println(fam.husbID);

							//fam.husbID=fam.husbID.substring(0, fam.husbID.indexOf("@"));
							//System.out.println(fam.husbID);
							//hashValueFam = hashValueFam.concat("\t" + )
							for (String key : indiHash.keySet()) {
								//System.out.println(key);
								if (fam.husbID.equals(key)) {

									ArrayList<String> temp = indiHash.get(key);
									//System.out.println(temp);
									//temp = temp.substring(temp.indexOf("\t") + 1);
									//System.out.println(temp.get(1));
									fam.setHusbName(temp.get(1));
									//hashValueFam = hashValueFam.concat("\t" + fam.husbName);
									//famHash.put(fam.famID,temp);
								}
								//System.out.println(indiHash.get(key));
							}
						} else if (lst[1].contains("WIFE")) {
							fam.wifeID = lst[2];
							fam.wifeID = fam.wifeID.replace("@", "");

							//System.out.println(fam.wifeID);
							for (String key : indiHash.keySet()) {
								if (key.equals(fam.wifeID)) {
									ArrayList<String> temp = indiHash.get(key);
									fam.setWifeName(temp.get(1));
									//temp = temp.substring(temp.indexOf("\t") + 1);
									//fam.wifeName = temp.substring(0, temp.indexOf("\t"));
									//hashValueFam = hashValueFam.concat("\t" + fam.wifeName);
									//famHash.put(fam.famID,temp);
								}
								//System.out.println(indiHash.get(key));
							}
						}
						// TODO: Need to implement for rest of the tags
					}
				}

				tag = lst[1];
				if (lst.length > 2) {
					for (int i = 2; i < lst.length; i++) {
						arguments = arguments + lst[i] + " ";
					}
				}
				if (val == true) {
					isValid = true;
				} else {
					isValid = false;
				}
			}

			//checks all level 2s; valid tag can be DATE
			if (Integer.parseInt(level) == 2) {
				if (lst[1].contains("DATE")) {
					tag = lst[1];
					isValid = true;
					if (lst.length > 2) {
						for (int i = 2; i < lst.length; i++) {
							arguments = arguments + lst[i] + " ";
						}

						// Checks if date is birth date or death date and inserts date accordingly
						if (isBirth == true) {
							indi.birth = arguments;
						} else {
							indi.death = arguments;
						}
					}
				} else {
					tag = lst[1];
					arguments = lst[2];
					isValid = false;
				}
			}
			//            indiHash.putIfAbsent(indi.individualID, hashValueIndi);
			

		}
		
		hashValueFam.add(fam.getFamID().toString());
		hashValueFam.add(fam.getHusbID());
		hashValueFam.add(fam.getHusbName());
		hashValueFam.add(fam.getWifeID());
		hashValueFam.add(fam.getWifeName());

		famHash.putIfAbsent(fam.getFamID(), hashValueFam);

	}

	public static void main(String[] args) throws IOException {
		// TODO code application logic here
		parse();
		
		String[] indivColNames = { 
				"ID", 
				"Name", 
				"Gender", 
				"Birthday", 
				"Alive"};
		int indiColNum = indivColNames.length;
		int indiSize = indiHash.size();
		System.out.println("Individual's Entries:");
		Object[][] data = new Object[indiSize][indiColNum];

		TextTable tt = new TextTable(indivColNames, data); 
				// this adds the numbering on the left 
				// sort by the first column 
				//tt.setSort(0);
				//tt.
				//tt.
				
		//WORK IN PROGRESS
		indiHash.remove("");
		Object[] individual = new Object[20];
		
		int i = 0;
		for (String key : indiHash.keySet()) {
			 individual = indiHash.get(key).toArray();
			 for (int j = 0; i < individual.length; i++) {
				 data[i][j] = individual[j];
			 }
			 i++;
		}
		
		//tt.printTable(); 
		
		
		System.out.println("Family Entries:");
		System.out.println("ID" + "\t"+ "H ID" + "\t"+ "H Name" + "\t"+ "W ID" + "\t" + "W name");
		famHash.remove("");
		for (String key : famHash.keySet()) {
			
			System.out.println(famHash.get(key));
		}
	}

}
