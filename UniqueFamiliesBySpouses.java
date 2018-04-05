package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class UniqueFamiliesBySpouses
{
	
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void uniqueFamiliesBySpouses(HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US24: UniqueFamilies By Spouses **********************\n");
		
		this.famHash = famHash;
		
		uniqueFamilies();
	}
	
	public void uniqueFamilies()
	{
		ArrayList<String> famInfo = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			String hID = famInfo.get(1);
			String wID = famInfo.get(3);
			
			String husName = famInfo.get(2);
			String wifeName = famInfo.get(4);
			
			String marriedDate = famInfo.get(5);
			
		}
	}
	
}
