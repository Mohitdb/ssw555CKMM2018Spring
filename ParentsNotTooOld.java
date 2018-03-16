package GedcomParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ParentsNotTooOld {
	ArrayList<String> tempIndi;
	ArrayList<String> tempFam;
	int fatherAge;
	int motherAge;
	int childAge;
	String children;
	String[] childIds;
	String comparison;
	
	private HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
	private HashMap<String, ArrayList<String>> famHash = new HashMap<>();
	
	public void ParentsNotTooOld(HashMap<String, ArrayList<String>> indiHash, HashMap<String, ArrayList<String>> famHash)
	{	
		System.out.println("\n******************** Karan's User story US12: Parents Not Too Old **********************\n");
		
		this.indiHash = indiHash;
		this.famHash = famHash;
		
		parentsChildrenCompare();
	}
	
	public void parentsChildrenCompare() 
	{
		ArrayList<String> indiInfo = new ArrayList<String>();
		ArrayList<String> famInfo = new ArrayList<String>();
		String youngestChild;
		
		for (String famKey : this.famHash.keySet())
		{
			famInfo = this.famHash.get(famKey);
			
			for (String indiKey : this.indiHash.keySet())
			{
				
				indiInfo = this.indiHash.get(indiKey);
				
				children = famInfo.get(7);
				String hID = famInfo.get(1);
				String wID = famInfo.get(3);
				
				childIds = children.split(" ");
				
				youngestChild = findYoungest(childIds, indiHash);
				
				if (indiKey.equals(youngestChild))
				{
					childAge = Integer.valueOf(indiInfo.get(5));
					
					if (indiKey.equals(hID))
					{
						fatherAge = Integer.valueOf(indiInfo.get(5));
						String fatherName = famInfo.get(2);
						
						if (indiKey.equals(wID))
						{
							motherAge = Integer.valueOf(indiInfo.get(5));
							String motherName = famInfo.get(4);
							
							comparison = parentChildAgeCompare(childAge, motherAge, fatherAge);
							
							int motherChild = motherChildDiff(childAge, motherAge);
							int fatherChild = fatherChildDiff(childAge, fatherAge);
							
							if (comparison.equals("Child is older than Mother!"))
							{
								System.out.println("Error: Individual " + wID + " named " + motherName + " is younger than her children!");
							}
							
							if (comparison.equals("Child is older than Father!"))
							{
								System.out.println("Error: Individual " + hID + " named " + fatherName + " is younger than his children!");
							}
							
							if (comparison.equals("Mother is 60 years older than child!"))
							{
								System.out.println("Error: Individual " + wID + " named " + motherName + " is " + motherChild + " years older than her youngest child!");
							}
							
							if (comparison.equals("Father is 80 years older than child!"))
							{
								System.out.println("Error: Individual " + hID + " named " + fatherName + " is " + fatherChild + " years older than his youngest child!");
							}
						}
					}
				}
			}
	}

}
	
	public String findYoungest(String[] childIds, HashMap<String, ArrayList<String>> indiHash)
	{
		int arrayLen = childIds.length;
		
		int lowestAge;
		
		String youngestId = "";
		
		ArrayList<String> indiInfo = new ArrayList<String>();
		
		if (arrayLen != 0)
		{
			if (arrayLen == 1)
			{
				youngestId = childIds[0];
			}
			
			else 
			{
				for (String indiKey : this.indiHash.keySet())
				{
					indiInfo = this.indiHash.get(indiKey);
					
					for (int i = 0; i < arrayLen; i++)
					{
						
						if (indiKey.equals(childIds[0]))
						{
							lowestAge = Integer.valueOf(indiInfo.get(5));
							
							if (indiKey.equals(childIds[i]))
							{
								int age = Integer.valueOf(indiInfo.get(5));
								
								if (age < lowestAge)
								{
									lowestAge = age;
									
									youngestId = childIds[i];
								}
							}
						}
					
					}
					
					
				}
			}
		}
		
		return youngestId;
	}
	
	public String parentChildAgeCompare(int childAge, int motherAge, int fatherAge)
	{
		String result = "";
		int motherChild = motherChildDiff(childAge, motherAge);
		int fatherChild = fatherChildDiff(childAge, fatherAge);
		
		if (motherAge < childAge)
		{
			result = "Child is older than Mother!";
		}
		
		if (fatherAge < childAge)
		{
			result = "Child is older than Father!";
		}
		
		if (motherChild > 60)
		{
			result = "Mother is 60 years older than child!";
		}
		
		if (fatherChild > 80)
		{
			result = "Father is 80 years older than child!";
		}
		
		return result;
		
	}
	
	public int motherChildDiff(int childAge, int motherAge)
	{
		int diff;
		
		diff = Math.abs(motherAge - childAge);
		
		return diff;
	}
	
	public int fatherChildDiff(int childAge, int fatherAge)
	{
		int diff;
		
		diff = Math.abs(fatherAge - childAge);
		
		return diff;
	}
}
