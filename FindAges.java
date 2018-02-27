package GedcomParse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindAges {

	public int FindAge(String date1, String date2) 
	{
		String monthNumber;
		String month;
		String formattedBirthDate;
		String formattedDeathDate;
		Date birthDate = new Date();
		Date deathDate = new Date();
		double Age = 0;
		String[] birth;
		String[] death;
		
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		
		if (date1 == "")
		{
			return 0;
		}
		
		else if (date2.equals(""))
		{
			birth = date1.split(" ");
			
			monthNumber = dateNumber(birth[1]);
			
			formattedBirthDate = birth[2] + "-" + monthNumber + "-" + birth[0];

			try {
				birthDate = simpleDateFormat.parse(formattedBirthDate);
				
				SimpleDateFormat DateFormat =  new SimpleDateFormat("yyyy-MM-dd");
				deathDate = new Date();
				DateFormat.format(deathDate);
					
				//in milliseconds
				double diff = Math.abs(deathDate.getTime() - birthDate.getTime());

				Age = diff / (24.0 * 60.0 * 60.0 * 1000.0 * 365.0);
					
				return (int) Age;
				
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		else
		{
			birth = date1.split(" ");
			death = date2.split(" ");
			
			monthNumber = dateNumber(birth[1]);
			month = dateNumber(death[1]);
			
			formattedBirthDate = birth[2] + "-" + monthNumber + "-" + birth[0];
			formattedDeathDate = death[2] + "-" + month + "-" + death[0];

			try {
				birthDate = simpleDateFormat.parse(formattedBirthDate);
				deathDate = simpleDateFormat.parse(formattedDeathDate);
				
				double diff = Math.abs(deathDate.getTime() - birthDate.getTime());

				Age = diff / (24.0 * 60.0 * 60.0 * 1000.0 * 365.0);
					
				return (int) Age;
				
				
			} catch (ParseException e) {
				e.printStackTrace();
				
				return 0;
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

}