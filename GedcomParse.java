/*
 * Caroline Squillante
 * SSW 555 Project02 - GED parser
 * I pledge my honor that I have abided by the Stevens Honor code
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GedcomParse {

	static String reader = null;

	/*
	 * Method to parse the ged file and determine if the tag is valid
	 * prints out each line in the form <level>|<tag>|<valid?> : Y or N|<arguments>
	 */
	@SuppressWarnings("resource")
	public static void parse() throws IOException {
		//read file
		FileReader fileReader = new FileReader("C:\\Users\\Caroline Squillante\\workspace\\SSW555\\src\\proj02test.ged");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

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
				if(lst[1].contains("DATE")) {
					tag = lst[1];
					isValid = "Y";
					if(lst.length > 2) {
						for(int i = 2; i < lst.length; i++) { 
							arguments = arguments + lst[i] + " ";
						}
					}
				}else {
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
