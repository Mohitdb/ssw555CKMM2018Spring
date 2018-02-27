package GedcomParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindAgesRefactored {

	public int ageFinder(String date1, String date2) 
	{
		
		Date deathDate = new Date();
		Date birthDate = new Date();
		int age;
		String [] birth;
		String [] death;
		String monthNumber;
		String month;
		String formattedBirthDate;
		String FormattedDeathDate;
		
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		
		if (!date2.equals("") && !date1.equals(""))
		{
			
			birth = date1.split(" ");
			death = date2.split(" ");
			
			monthNumber = dateNumber(birth[1]);
			month = dateNumber(death[1]); 
			
			formattedBirthDate = birth[2] + "-" + monthNumber + "-" + birth[0];
			FormattedDeathDate = death[2] + "-" + month + "-" + death[0];
			
			try {
				birthDate = simpleDateFormat.parse(formattedBirthDate);
				deathDate = simpleDateFormat.parse(FormattedDeathDate);
				
				age = Age(birthDate, deathDate);
				
				return age;
				
			} catch (ParseException e) {
				e.printStackTrace();
				
				return 0;
				
			}
			
		}
		
		else if (date2.equals(""))
		{
			birth = date1.split(" ");
			
			monthNumber = dateNumber(birth[1]);
			
			formattedBirthDate = birth[2] + "-" + monthNumber + "-" + birth[0];
			
			try {
				birthDate = simpleDateFormat.parse(formattedBirthDate);
				
				age = Age(birthDate, deathDate);
				
				return age;
				
			} catch (ParseException e) {
				e.printStackTrace();
				
				return 0;
				
			}
			
		}
		
		else
		{
			return 0;
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
	
	public int Age(Date birth, Date death)
	{
		double Age;
		
		double diff = Math.abs(death.getTime() - birth.getTime());

		Age = diff / (24.0 * 60.0 * 60.0 * 1000.0 * 365.0);
			
		return (int) Age;
	}
}
