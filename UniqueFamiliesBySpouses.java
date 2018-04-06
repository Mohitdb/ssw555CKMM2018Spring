package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class UniqueFamiliesBySpouses
{
	
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void uniqueFamiliesBySpouses(HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US24: Unique Families By Spouses **********************\n");
		
		ArrayList<String> result = new ArrayList<String>();
		
		this.famHash = famHash;
		
		result = uniqueFamilies(famHash);
		
		if (!result.isEmpty())
		{
			for (String s: result) 
			{
				System.out.println(s);
			}
		}
	}
	
	public ArrayList<String> uniqueFamilies(HashMap<String, ArrayList<String>> famHash)
	{
		ArrayList<String> famInfo = new ArrayList<String>();
		ArrayList<String> famIds = new ArrayList<String>();
		ArrayList<String> hIds = new ArrayList<String>();
		ArrayList<String> wIds = new ArrayList<String>();
		ArrayList<String> weddingDates = new ArrayList<String>();
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			String famID = famInfo.get(0);
			String hID = famInfo.get(1);
			String wID = famInfo.get(3);
			
			String husName = famInfo.get(2);
			String wifeName = famInfo.get(4);
			
			String marriedDate = famInfo.get(5);
			
			for (int i = 0; i < weddingDates.size(); i++)
			{
				if (marriedDate.equals(weddingDates.get(i)) && hID.equals(hIds.get(i)) && wID.equals(wIds.get(i)))
				{
					result.add("Error: Family ID " + famID + " with Husband " + hID + " and Wife " + wID + " is a duplicate of " + famIds.get(i) + "!");
				}
				
				else 
				{
					famIds.add(famID);
					hIds.add(hID);
					wIds.add(wID);
					weddingDates.add(marriedDate);
				}
			}
			
		}
		
		return result;
	}
	
}
