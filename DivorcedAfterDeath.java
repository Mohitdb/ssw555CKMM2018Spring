package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DivorcedAfterDeath {
	
	SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
	Date deathDate = new Date();
	Date divorcedDate = new Date();
	String[] diDate;
	String[] death;
	String monthNumber;
	String month;
	String formattedDivorceDate;
	String formattedDeathDate;
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	String divorceDate;
	String dDate;

	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void divorcedComparedtoDeath(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US06: Divorced After Death **********************\n");
		
		this.indiHash = indiHash;
		this.famHash = famHash;
		
		divorcedAfterDeath();
	}
	
	public void divorcedAfterDeath()
	{	
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			divorceDate = famInfo.get(6);
			
			if (!divorceDate.equals(""))
			{	
				for (String indiKey : this.indiHash.keySet())
				{
					indiInfo = this.indiHash.get(indiKey);
					
					String hID = famInfo.get(1);
					String wID = famInfo.get(3);
					
					if (indiKey.equals(hID) || indiKey.equals(wID))
					{
						dDate = indiInfo.get(4);
						
						if (!dDate.equals(""))
						{
								diDate = divorceDate.split(" ");
								death = dDate.split(" ");
								
								monthNumber = dateNumber(diDate[1]);
								month = dateNumber(death[1]);
								
								formattedDivorceDate = diDate[2] + "-" + monthNumber + "-" + diDate[0];
								formattedDeathDate = death[2] + "-" + month + "-" + death[0];
								
								
								try {
									divorcedDate = simpleDateFormat.parse(formattedDivorceDate);
									
									deathDate = simpleDateFormat.parse(formattedDeathDate);
									
									divorcedDeathComparison(divorcedDate, deathDate, indiInfo.get(1), indiInfo.get(0));
									
								}
								
								catch (ParseException e) 
								{
									e.printStackTrace();
								}
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
