package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class Manan
{

    ArrayList<String> tempArrayList;

    public String birthBeforeDeath(HashMap<String, ArrayList<String>> hashIndi)
    {
        hashIndi.remove("");
        String res = "";
        System.out.println("\n******************** Manan's User story US03:Birth Before Death ********************");
        System.out.println("BIRTH\t\tDEATH\t\tCORRECT/INCORRECT\tERROR MESSAGE(if any)");
        System.out.println("-----------\t-----------\t------------------\t-----------------------");

        for (String key : hashIndi.keySet())
        {
            tempArrayList = hashIndi.get(key);
            String birthDate = tempArrayList.get(3);
            String deathDate = tempArrayList.get(4);
            if (birthDate.equals(""))
            {
                res = "INCORRECT";
                if (!deathDate.equals(""))
                {
                    System.out.println(birthDate + "\t\t" + deathDate + "\tINCORRECT\t\tError US03: Cannot determine without Birth Date");
                } else
                {
                    System.out.println(birthDate + "\t\t" + deathDate + "\t\tINCORRECT\t\tError US03: Cannot determine without Birth Date");
                }
            } else if (!deathDate.equals(""))
            {
                String result = dateCal(birthDate, deathDate);
                if (result.equals("year"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tINCORRECT\t\tError US04: Divorce YEAR cannot be before Marriage YEAR");
                } else if (result.equals("month"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "INCORRECT\t\tError US04: Divorce MONTH cannot be before Marriage MONTH");
                } else if (result.equals("day"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tINCORRECT\t\tError US04: Divorce DAY cannot be before Marriage DAY");
                } else
                {
                    res = "CORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tCORRECT\t\t\tN/A");
                }

            } else
            {
                res = "CORRECT";
                System.out.println(tempArrayList.get(3) + "\t" + "N/A" + "\t\tCORRECT\t\t\tN/A");
            }
        }
        System.out.println();
        return res;
    }

    public void mariageBeforeDivorce(HashMap<String, ArrayList<String>> famHash)
    {
        famHash.remove("");
        System.out.println("\n******************** Manan's User story US04:Marriage Before Divorce ********************");
        System.out.println("MARRIAGE\tDIVORCE\t\tCORRECT/INCORRECT\tERROR MESSAGE(if any)");
        System.out.println("-----------\t-------------\t-----------------\t----------------------");
        for (String key : famHash.keySet())
        {
            tempArrayList = famHash.get(key);
            String marriageDate = tempArrayList.get(5);
            String divorceDate = tempArrayList.get(6);
            if (marriageDate.equals(""))
            {
                if (!divorceDate.equals(""))
                {
                    System.out.println(marriageDate + "\t\t" + divorceDate + "\tINCORRECT\t\tError US04: Cannot determine without Marriage Date");
                } else
                {
                    System.out.println(marriageDate + "\t\t" + divorceDate + "\t\tINCORRECT\t\tError US04: Cannot determine without Marriage Date");
                }
            } else if (!divorceDate.equals(""))
            {
                String result = dateCal(marriageDate, divorceDate);
                if (result.equals("year"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tINCORRECT\t\tError US04: Divorce YEAR cannot be before Marriage YEAR");
                } else if (result.equals("month"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "INCORRECT\t\tError US04: Divorce MONTH cannot be before Marriage MONTH");
                } else if (result.equals("day"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tINCORRECT\t\tError US04: Divorce DAY cannot be before Marriage DAY");
                } else
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tCORRECT\t\t\tN/A");
                }
            } else
            {
                System.out.println(tempArrayList.get(5) + "\t" + "N/A" + "\t\tCORRECT\t\t\tN/A");
            }
        }
    }

    public String dateCal(String firstFromList, String secondFromList)
    {
        String result = "";
        String firstDate[] = firstFromList.split(" ");
        String secondDate[] = secondFromList.split(" ");
        int firstMonthNumber = monthNumber(firstDate[1]);
        int secondMonthNumber = monthNumber(secondDate[1]);

        if (Integer.parseInt(firstDate[2]) <= Integer.parseInt(secondDate[2]))
        {
            if (Integer.parseInt(firstDate[2]) == Integer.parseInt(secondDate[2]))
            {
                if (firstMonthNumber <= secondMonthNumber)
                {
                    if (Integer.parseInt(firstDate[0]) <= Integer.parseInt(secondDate[0]))
                    {
                        result = "CORRECT";
                    } else
                    {
                        result = "day";
                    }
                } else
                {
                    result = "month";
                }
            } else
            {
                result = "CORRECT";
            }
        } else
        {
            result = "year";
        }
        return result;
    }

    public int monthNumber(String monthString)
    {
        int monthInt = 0;
        switch (monthString)
        {

            case "JAN":
                monthInt = 1;
                break;
            case "FEB":
                monthInt = 2;
                break;
            case "MAR":
                monthInt = 3;
                break;
            case "APR":
                monthInt = 4;
                break;
            case "MAY":
                monthInt = 5;
                break;
            case "JUN":
                monthInt = 6;
                break;
            case "JUL":
                monthInt = 7;
                break;
            case "AUG":
                monthInt = 8;
                break;
            case "SEP":
                monthInt = 9;
                break;
            case "OCT":
                monthInt = 10;
                break;
            case "NOV":
                monthInt = 11;
                break;
            case "DEC":
                monthInt = 12;
                break;
        }
        return monthInt;
    }
}
