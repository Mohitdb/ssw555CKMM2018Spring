package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DivorcedAfterDeath {
	
	SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
	Date deathDate = new Date();
	Date divorced = new Date();
	String[] newDate;
	String monthNumber;
	String formattedDate;
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	String divorcedDate;
	String dDate;
	
	public void divorcedAfterDeath(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap)
	{	
		System.out.println("\n******************** Karan's User story US06: Divorced before Death **********************");
		
		 int i = 0;
	        for (String key : famHashmap.keySet())
	        {
	        	tempFam = famHashmap.get(key);
	        	
	        	for (String ID : indiHashmap.keySet())
	        	{
	        		tempIndi = indiHashmap.get(ID);
	        		
	        		divorcedDate = tempFam.get(5);
	        		
	        		if (!divorcedDate.equals(""))
	        		{
	        			divorced = stringToDate(divorcedDate);
	        			
	        			String husID = tempFam.get(1);
	        			String wID = tempFam.get(3);
	        			
	        			if (husID.equals(key) || wID.equals(key))
	        			{
	        				dDate = tempIndi.get(4);
	        				
	        				if (!dDate.equals(""))
	        				{
	        					deathDate = stringToDate(dDate);
	        					
	        					divorcedDeathComparison(divorced, deathDate, tempIndi.get(1), tempIndi.get(0));
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
			divorced = simpleDateFormat.parse(formattedDate);
			
			return divorced;
		}
		
		catch (ParseException e) 
		{
			e.printStackTrace();
			
			return divorced;
		}

	}
	
	public void divorcedDeathComparison(Date date1, Date date2, String name, String ID)
	{
		double deathDateTime;
		double divorcedTime;
		
		deathDateTime = date2.getTime();
		divorcedTime = date1.getTime();
		
		if (deathDateTime < divorcedTime)
		{
			System.out.println("Error: Individual " + ID + " named " + name + " is divorced after the person has died!");
		}
	}
}
