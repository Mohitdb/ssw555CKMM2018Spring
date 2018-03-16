package GedcomParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListLargeAgeDifferences {
	
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	int husAge;
	int wifeAge;
	String comparison;
	int difference;
	
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void ListLargeAgeDifferences(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US34: List Large Age Differences **********************\n");
		
		this.indiHash = indiHash;
		this.famHash = famHash;
		
		ageDifferencesCompare();
	}
	
	public void ageDifferencesCompare() 
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			for (String indiKey : this.indiHash.keySet())
			{
				indiInfo = this.indiHash.get(indiKey);
				
				String hID = famInfo.get(1);
				String wID = famInfo.get(3);
				
				if (indiKey.equals(hID))
				{
					husAge = Integer.valueOf(indiInfo.get(5));
					String husName = famInfo.get(2);
					
					if (indiKey.equals(wID))
					{
						wifeAge = Integer.valueOf(indiInfo.get(5));
						String wifeName = famInfo.get(4);
						
						comparison = birthDateComparison(wifeAge, husAge);
						
						difference = returnAgeDifference(wifeAge, husAge);
						
						if (comparison.equals("Wife Too Old!"))
						{
							System.out.println("The age difference between Individuals " + hID + " and " + wID + " named " + husName + ", " + wifeName + " respectively is " + difference +". Where age of " + wifeName + " is more than double the age of " + husName + "!");
						}
						
						if (comparison.equals("Hus Too Old!"))
						{
							System.out.println("The age difference between Individuals " + hID + " and " + wID + " named " + husName + ", " + wifeName + " respectively is " + difference +". Where age of " + husName + " is more than double the age of " + wifeName + "!");
						}
					}
				}
				
			}
		}
	}
	
	public String birthDateComparison(int wifeAge, int husAge)
	{
		String tooOld = "";
		
		if (wifeAge > husAge)
		{
			if (wifeAge > husAge*2)
			{
				tooOld = "Wife Too Old!";
			}
		}
		
		if (husAge > wifeAge)
		{
			if (husAge > wifeAge*2)
			{
				tooOld = "Hus Too Old!";
			}
		}
		
		return tooOld;
	}
	
	public int returnAgeDifference(int wifeAge, int husAge)
	{
		int diff = 0;
		
		diff = Math.abs(wifeAge - husAge);
		
		return diff;
	}
}
