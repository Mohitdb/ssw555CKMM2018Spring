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
		
		ArrayList<String> individualsInFam = indiInFam(fams, indiHash);
		
		ArrayList<String> famsInIndi = famInIndi(individuals, famHash);
		
		if (!individualsInFam.isEmpty())
		{
			for (String s: individualsInFam)
			{
				if (!s.equals(""))
				{
					System.out.println(s);
				}
			}
		}
		
		if (!famsInIndi.isEmpty())
		{
			for (String c: famsInIndi)
			{
				if (!c.equals(""))
				{
					System.out.println(c);
				}
			}
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
			String cIDs = famInfo.get(7);
			
			if (!hID.equals(" "))
			{
				Ids.add(hID);
			}
			
			if (!wID.equals(" "))
			{
				Ids.add(wID);
			}
			
			if (!cIDs.equals(""))
			{
				String[] cID = cIDs.split(" ");
				
				for (int i = 0; i < cID.length; i++)
				{
					Ids.add(cID[i]);
				}
			}
		}
		
		return Ids;
	}
	
	public ArrayList<String> indiInFam(ArrayList<String> famIds, HashMap<String, ArrayList<String>> indiHash)
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (String indiKey : this.indiHash.keySet())
		{
			indiInfo = this.indiHash.get(indiKey);
			
			if (!indiKey.equals(" "))
			{
				boolean inFam = false;
				for (int i = 0; i < famIds.size()-1; i++)
				{
					if (indiKey.equals(famIds.get(i)))
					{
						inFam = true;
						break;
					}
				}
				
				if (inFam == true)
				{
					result.add("");
				}
				
				else
				{
					result.add("Error: Individual " + indiKey + " named " + indiInfo.get(1) + " is not in the Families Table!");
				}
			}
		}
		
		return result;
	}
	
	public ArrayList<String> famInIndi(ArrayList<String> indiIds, HashMap<String, ArrayList<String>> famHash)
	{
		ArrayList<String> famInfo = new ArrayList<String>();
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			String hID = famInfo.get(1);
			String wID = famInfo.get(3);
			String cIDs = famInfo.get(7);
			
			if (!hID.equals(""))
			{
				boolean inIndi = false;
				for (int i = 0; i < indiIds.size()-1; i++)
				{
					if (hID.equals(indiIds.get(i)))
					{
						inIndi = true;
						break;
					}
					
				}
				
				if (inIndi == true)
				{
					result.add("");
				}
				
				else 
				{
					result.add("Error: Individual " + hID + " is not in the Individuals Table!");
				}
			}
			
			if (!wID.equals(""))
			{
				boolean inIndi = false;
				for (int i = 0; i < indiIds.size()-1; i++)
				{
					if (wID.equals(indiIds.get(i)))
					{
						inIndi = true;
						break;
					}
				}
				
				if (inIndi == true)
				{
					result.add("");
				}
				
				else
				{
					result.add("Error: Individual " + wID + " is not in the Individuals Table!");
				}
				
			}
			
			if (!cIDs.equals(" "))
			{
				String[] cID = cIDs.split(" ");
				for (int j = 0; j < cID.length; j++)
				{
					boolean inIndi = false;
					
					if (!cID[j].equals(""))
					{
						for (int i = 0; i < indiIds.size()-1; i++)
						{
							if (cID[j].equals(indiIds.get(i)))
							{
								inIndi = true;
								break;
							}
								
						}
						
						if (inIndi == true)
						{
							result.add("");
						}
						
						else 
						{
							result.add("Error: Individual " + cID[j] + " is not in the Individuals Table!");
						}
					}
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
