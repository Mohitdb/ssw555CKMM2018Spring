package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class CorrespondingEntries 
{
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void CorrespondingEntries(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US26: Corresponding Entries **********************\n");
		
		this.indiHash = indiHash;
		this.famHash = famHash;
		
		consistentEntries();
	}
	
	public void consistentEntries()
	{
		ArrayList<String> individuals = indiIds(indiHash);
		
		ArrayList<String> fams = famIds(famHash);
		
		String individualsInFam = indiInFam(fams, indiHash);
		
		String famsInIndi = famInIndi(individuals, famHash);
		
		if (!individualsInFam.equals(" "))
		{
			System.out.println(individualsInFam);
		}
		
		if (!famsInIndi.equals(" "))
		{
			System.out.println(famsInIndi);
		}
	}
	
	public ArrayList<String> indiIds(HashMap<String, ArrayList<String>> indiHash)
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		
		ArrayList<String> Ids = new ArrayList<String>();
		
		for (String indiKey : this.indiHash.keySet())
		{
			indiInfo = this.indiHash.get(indiKey);
			
			if (indiKey != null)
			{
				Ids.add(indiKey);
			}
		}
		
		return Ids;
	}
	
	public ArrayList<String> famIds(HashMap<String, ArrayList<String>> famHash)
	{
		ArrayList<String> famInfo = new ArrayList<String>();
		
		ArrayList<String> Ids = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			String hID = famInfo.get(1);
			String wID = famInfo.get(3);
			String[] cID = famInfo.get(7).split(" ");
			
			if (!hID.equals(" "))
			{
				Ids.add(hID);
			}
			
			if (!wID.equals(" "))
			{
				Ids.add(wID);
			}
			
			if (cID != null)
			{
				for (int i = 0; i < cID.length; i++)
				{
					Ids.add(cID[i]);
				}
			}
		}
		
		return Ids;
	}
	
	public String indiInFam(ArrayList<String> famIds, HashMap<String, ArrayList<String>> indiHash)
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		
		String result = "";
		
		for (String indiKey : this.indiHash.keySet())
		{
			indiInfo = this.indiHash.get(indiKey);
			
			if (!indiKey.equals(" "))
			{
				for (int i = 0; i < famIds.size(); i++)
				{
					if (indiKey.equals(famIds.get(i)))
					{
						result = "";
					}
				}
				
				result = "Individual " + indiKey + " named " + indiInfo.get(1) + " is not in the Families Table!";
			}
		}
		
		return result;
	}
	
	public String famInIndi(ArrayList<String> indiIds, HashMap<String, ArrayList<String>> famHash)
	{
		ArrayList<String> famInfo = new ArrayList<String>();
		
		String result = "";
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			String hID = famInfo.get(1);
			String wID = famInfo.get(3);
			String[] cID = famInfo.get(7).split(" ");
			
			if (!hID.equals(" "))
			{
				for (int i = 0; i < indiIds.size(); i++)
				{
					if (hID.equals(indiIds.get(i)))
					{
						result = "";
					}
				}
				
				result = "Individual " + hID + " named " + famInfo.get(2) + " is not in Individuals List!";
			}
			
			if (!wID.equals(" "))
			{
				for (int i = 0; i < indiIds.size(); i++)
				{
					if (wID.equals(indiIds.get(i)))
					{
						result = "";
					}
				}
				
				result = "Individual " + wID + " named " + famInfo.get(4) + " is not in Individuals List!";
			}
			
			
			for (int j = 0; j < cID.length; j++)
			{
				if (!cID[j].equals(" "))
				{
					for (int i = 0; i < indiIds.size(); i++)
					{
						if (cID[j].equals(indiIds.get(i)))
						{
							result = "";
						}
					}
					
					result = "Individual " + cID[j] + " named " + nameFromId(cID[j]) + " is not in Individuals List!";
				}
			}
		}
		
		return result;
	}
	
	public String nameFromId(String Id)
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		String name = "";
		
		for (String indiKey : this.indiHash.keySet())
		{
			indiInfo = this.indiHash.get(indiKey);
			
			if(indiKey.equals(Id))
			{
				name = indiInfo.get(1);
			}
		}
		
		return name;
	}
}
