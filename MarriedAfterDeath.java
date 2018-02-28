package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MarriedAfterDeath {
	
	SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
	Date deathDate = new Date();
	Date marriageDate = new Date();
	String[] newDate;
	String monthNumber;
	String formattedDate;
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	String marriedDate;
	String dDate;
	
	public void marriageAfterDeath(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap)
	{	
		System.out.println("\n******************** Karan's User story US05: Marriage before Death **********************");
		
		 int i = 0;
	        for (String key : famHashmap.keySet())
	        {
	        	tempFam = famHashmap.get(key);
	        	
	        	for (String ID : indiHashmap.keySet())
	        	{
	        		tempIndi = indiHashmap.get(ID);
	        		
	        		marriedDate = tempFam.get(5);
	        		
	        		if (!marriedDate.equals(""))
	        		{
	        			marriageDate = stringToDate(marriedDate);
	        			
	        			String husID = tempFam.get(1);
	        			String wID = tempFam.get(3);
	        			
	        			if (husID.equals(key) || wID.equals(key))
	        			{
	        				dDate = tempIndi.get(4);
	        				
	        				if (!dDate.equals(""))
	        				{
	        					deathDate = stringToDate(dDate);
	        					
	        					marriageDeathComparison(marriageDate, deathDate, tempIndi.get(1), tempIndi.get(0));
	        				}
	        				
	        			}
	        			
	        		}
	        	}
	        	
	        	
	        	
	        }
		
	}
	
	public static String dateNumber(String monthName)
	{
		switch (monthName)
		{
			case "JAN":
				return "01";
			case "FEB":
				return "02";
			case "MAR":
				return "03";
			case "APR":
				return "04";
			case "MAY":
				return "05";
			case "JUN":
				return "06";
			case "JUL":
				return "07";
			case "AUG":
				return "08";
			case "SEP":
				return "09";
			case "OCT":
				return "10";
			case "NOV":
				return "11";
			case "DEC":
				return "12";
			default:
				return "00";
		}
	}
	
	public Date stringToDate(String date)
	{
		newDate = date.split(" ");
		
		monthNumber = dateNumber(newDate[1]);
		
		formattedDate = newDate[2] + "-" + monthNumber + "-" + newDate[0];
		
		try {
			marriageDate = simpleDateFormat.parse(formattedDate);
			
			return marriageDate;
		}
		
		catch (ParseException e) 
		{
			e.printStackTrace();
			
			return marriageDate;
		}

	}
	
	public void marriageDeathComparison(Date date1, Date date2, String name, String ID)
	{
		double deathDateTime;
		double marriageDateTime;
		
		deathDateTime = date2.getTime();
		marriageDateTime = date1.getTime();
		
		if (deathDateTime < marriageDateTime)
		{
			System.out.println("Error: Individual " + ID + " named " + name + " is married after the person has died!");
		}
	}
}