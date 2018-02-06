package gedDistributor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GedcomParse {
	
	static String reader = null;
	
	// Create ArrayList for Individuals
	
	static ArrayList<Individual> individualArray = new ArrayList<Individual>();
			
	// Creates ArrayList for Families
			
	static ArrayList<Family> familyArray = new ArrayList<Family>();

	/*
	 * Method to parse the ged file and determine if the tag is valid
	 * prints out each line in the form <level>|<tag>|<valid?> : Y or N|<arguments>
	 */
	@SuppressWarnings("resource")
	public static void parse() throws IOException {
		
		//read file
		FileReader fileReader = new FileReader("C:\\Users\\Caroline Squillante\\workspace\\SSW555\\src\\proj02test.ged"); // TODO: Change path for our testing file
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

		//while loop to check to see if each line is valid and formats the information accordingly
		while ((reader = bufferedReader.readLine())!= null) {
			System.out.println("--> " + reader);
			
			String [] lst = reader.split(" ",0);
			
			String level = lst[0];
			String tag = "";
			String isValid ="";
			String arguments ="";
			
			
			//check for invalid level # (cannot be greater than 2)
			if(Integer.parseInt(level) >= 3) {
				isValid = "N";
				tag = lst[1];
				if(lst.length > 2) {
					for(int i = 2; i < lst.length; i++) { 
						arguments = arguments + lst[i] + " ";
					}
				}	
			}
			
			//checks all level 0s; valid tag can be INDI, FAM, HEAD, TRLR, NOTE
			if(Integer.parseInt(level) == 0) {
				if(lst[1].contains("HEAD") || lst[1].contains("TRLR") || lst[1].contains("NOTE")) {
					tag = lst[1];
					isValid = "Y";
					if(lst.length > 2) {
						for(int i = 2; i < lst.length; i++) { 
							arguments = arguments + lst[i] + " ";
						}
					}
					//checks special case for INDI and FAM
				}else if(lst[2].contains("INDI") || lst[2].contains("FAM")) {
					
					if (isEmpty)
					{
						//don't add to arraylist coz objects are empty
						isEmpty = false;
					}
					else
					{
						//add to arraylist
						
						// Insert Data into ArrayLists
						 if (isIndi == true)
						 {
							 individualArray.add(indi);
							 
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
						 }
						 
						 else
						 {
							 familyArray.add(fam);
							 
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
					
						// Inserts Individual ID to individual object
						if (lst[2].contains("INDI"))
						{
							indi.individualID = lst[1].substring(1, lst[1].length() - 1);
							isIndi = true;
						}
						
						// Inserts Family ID to Family object
						else if (lst[2].contains("FAM"))
						{
							fam.famID = lst[1].substring(1, lst[1].length() - 1);
							isIndi = false;
						}
						
						tag = lst[2];
						arguments = lst[1];
						isValid = "Y";
				
				}else {
					tag = lst[1];
					arguments = lst[2];
					isValid = "N";
				}
			}
			
			//checks all level 1s; valid tag can be NAME, SEX, BIRT, DEAT, FAMC, FAMS, MARR, HUSB, WIFE, CHIL, DIV
			String [] validTags = {"NAME","SEX","BIRT","DEAT","FAMC","FAMS","MARR","HUSB","WIFE","CHIL","DIV"};
			if(Integer.parseInt(level) == 1) {
				boolean val = false;
				for(String t: validTags) {
					if(lst[1].contains(t)) {
						val = true;
						
						if (lst[1].contains("NAME"))
						{
							indi.name = lst[2];
						}
						
						else if ( lst[1].contains("SEX"))
						{
							indi.gender = lst[2];
						}
						
						else if (lst[1].contains("BIRT"))
						{
							isBirth = true;
						}
						
						else if (lst[1].contains("DEAT"))
						{
							indi.alive = false;
							isBirth = false;
						}
						
						// TODO: Need to implement for rest of the tags
						
					}
				}
				tag = lst[1];
				if(lst.length > 2) {
					for(int i = 2; i < lst.length; i++) { 
						arguments = arguments + lst[i] + " ";
					}
				}
				if(val == true) {
					isValid = "Y";
				}else {
					isValid = "N";
				}	
			}
			
			//checks all level 2s; valid tag can be DATE
			if(Integer.parseInt(level) == 2) {
				if(lst[1].contains("DATE")) 
				{
					tag = lst[1];
					isValid = "Y";
					if(lst.length > 2) 
					{
						for(int i = 2; i < lst.length; i++) 
						{ 
							arguments = arguments + lst[i] + " ";
						}
						
						// Checks if date is birth date or death date and inserts date accordingly
						if (isBirth == true)
						{
							indi.birth = arguments;
						}
						
						else
						{
							indi.death = arguments;
						}
					}
				}
				else {
					tag = lst[1];
					arguments = lst[2];
					isValid = "N";
				}	
			}
			//outputs the data in the correct format
			
			System.out.println("<-- " + level + "|" + tag + "|" + isValid + "|" + arguments);
			
		}
	}

	public static void main(String[] args) throws IOException {
		parse();
	}

}
