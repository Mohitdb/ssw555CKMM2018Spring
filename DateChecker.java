package GedcomParse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DateChecker {

	HashMap<String, ArrayList<String>> famHash;
	HashMap<String, ArrayList<String>> indiHash;
	String curDate;
	String curDay;
	String curMonth;
	String curNumMonth;
	String curYear;
	
	public DateChecker(HashMap<String, ArrayList<String>> famHash, HashMap<String, ArrayList<String>> indiHash) {
		this.famHash = famHash;
		this.indiHash = indiHash;
		
		Date today = new Date();
        curMonth = today.toString().substring(4, 7).toUpperCase();
        curDay = today.toString().substring(8, 10);
        
        if(curDay.substring(0,1).compareTo("0") == 0) {
        	curDay = today.toString().substring(9, 10);
        }
        curYear = today.toString().substring(24, 28);
        curNumMonth= dateNumber(curMonth);
        
        //curDate = curDay + " " + curMonth + " " + curYear;
        
        System.out.println("\n===============Caroline's US01 - Dates before current date===============");
        
	}
	
	public static String dateNumber(String monthName)
	{
		switch (monthName)
		{
			case "JAN":
				return "1";
			case "FEB":
				return "2";
			case "MAR":
				return "3";
			case "APR":
				return "4";
			case "MAY":
				return "5";
			case "JUN":
				return "6";
			case "JUL":
				return "7";
			case "AUG":
				return "8";
			case "SEP":
				return "9";
			case "OCT":
				return "10";
			case "NOV":
				return "11";
			case "DEC":
				return "12";
			default:
				return "0";
		}
	}
	
	private boolean isAfterCurrentDate(String date) {
		
		String[] dateSplit = date.split(" ");
		
		if(Integer.parseInt(dateSplit[2]) > Integer.parseInt(curYear)) {
			return true;
		}else if((Integer.parseInt(dateSplit[2]) == Integer.parseInt(curYear)) && Integer.parseInt(dateNumber(dateSplit[1])) > Integer.parseInt(curNumMonth)) {
			return true;
		}else if((Integer.parseInt(dateSplit[2]) == Integer.parseInt(curYear)) && (Integer.parseInt(dateNumber(dateSplit[1])) == Integer.parseInt(curNumMonth)) && (Integer.parseInt(dateSplit[0]) > Integer.parseInt(curDay))) {
			return true;
		}else {
			return false;
		}
	}
	
	public void checkFamInfo() {
		ArrayList<String> famInfo = new ArrayList<String>();
		for (String famKey : this.famHash.keySet()) {
        	famInfo = this.famHash.get(famKey);
        	
        	if (famInfo.get(5) != "") {
        		if(isAfterCurrentDate(famInfo.get(5))) {
        			System.out.println("ERROR US01: Family ID " + famKey + " has a marriage date after the current date");
        		}
        	}

        	if (famInfo.get(6) != "") {
        		if(isAfterCurrentDate(famInfo.get(6))) {
        			System.out.println("ERROR US01: Family ID " + famKey + " has a divorce date after the current date" );
        		}
        	}
        }
	}
	
	public void checkIndivInfo() {
		ArrayList<String> indiInfo = new ArrayList<String>();
        for (String indiKey : this.indiHash.keySet()) {
       		indiInfo = this.indiHash.get(indiKey);
       		
       		if (indiInfo.get(3) != "") {
        		if(isAfterCurrentDate(indiInfo.get(3))) {
        			System.out.println("ERROR US01: Individual ID " + indiKey + " has a birthday after the current date");
        		}
        	}

        	if (indiInfo.get(4) != "") {
        		if(isAfterCurrentDate(indiInfo.get(4))) {
        			System.out.println("ERROR US01: Individual ID " + indiKey + " has a death date after the current date" );
        		}
        	}
       	}
	}

}
