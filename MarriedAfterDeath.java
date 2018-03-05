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
	String[] weddingDate;
	String[] death;
	String monthNumber;
	String month;
	String formattedWeddingDate;
	String formattedDeathDate;
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	String marriedDate;
	String dDate;
	
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void marriageAfterDeath(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US05: Marriage before Death **********************");
		
		this.indiHash = indiHash;
		this.famHash = famHash;
		
		marriageComparedtoDeath();
	}
	
	public void marriageComparedtoDeath()
	{
		
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			marriedDate = famInfo.get(5);
			
			if (!marriedDate.equals(""))
			{	
				for (String indiKey : this.indiHash.keySet())
				{
					indiInfo = this.indiHash.get(indiKey);
					
					String hID = famInfo.get(1);
					String wID = famInfo.get(3);
					
					if (indiKey.contains(hID) || indiKey.contains(wID))
					{
						dDate = indiInfo.get(4);
						
						if (!dDate.equals(""))
						{
							{
								weddingDate = marriedDate.split(" ");
								death = dDate.split(" ");
								
								monthNumber = dateNumber(weddingDate[1]);
								month = dateNumber(death[1]);
								
								formattedWeddingDate = weddingDate[2] + "-" + monthNumber + "-" + weddingDate[0];
								formattedDeathDate = death[2] + "-" + month + "-" + death[0];
								
								
								try {
									marriageDate = simpleDateFormat.parse(formattedWeddingDate);
									
									deathDate = simpleDateFormat.parse(formattedDeathDate);
									
									marriageDeathComparison(marriageDate, deathDate, indiInfo.get(1), indiInfo.get(0));
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