package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

public class Manan
{

    ArrayList<String> tempArrayList;
    String res = "";
    HashMap<String, Integer> monthNumber = new HashMap<String, Integer>();

    public String birthBeforeDeath(HashMap<String, ArrayList<String>> hashIndi)
    {
        hashIndi.remove("");
        String res = "";
        int birthMonthNumber, deathMonthNumber;
        System.out.println("\nManan's User story US03:");
        System.out.println("BIRTH\t\tDEATH\t\tCORRECT/INCORRECT\tERROR MESSAGE(if any)");
        System.out.println("-----------\t-----------\t------------------\t-----------------------");
        
        for (String key : hashIndi.keySet())
        {
            tempArrayList = hashIndi.get(key);
            if (tempArrayList.get(3).equals(""))
            {
                res = "INCORRECT";
                if (!tempArrayList.get(4).equals(""))
                    System.out.println(tempArrayList.get(3) + "\t\t" + tempArrayList.get(4) + "\tINCORRECT\t\tError US03: Cannot determine without Birth Date");
                else
                    System.out.println(tempArrayList.get(3) + "\t\t" + tempArrayList.get(4) + "\t\tINCORRECT\t\tError US03: Cannot determine without Birth Date");
            }
            else if (!tempArrayList.get(4).equals(""))
            {
                String bday[] = tempArrayList.get(3).split(" ");
                String dday[] = tempArrayList.get(4).split(" ");
                birthMonthNumber = monthNumber(bday[1]);
                deathMonthNumber = monthNumber(dday[1]);
                if (Integer.parseInt(bday[2]) <= Integer.parseInt(dday[2]))
                {
                    if (Integer.parseInt(bday[2]) == Integer.parseInt(dday[2]))
                    {
                        if (birthMonthNumber <= deathMonthNumber)
                        {
                            if (Integer.parseInt(bday[0]) <= Integer.parseInt(dday[0]))
                            {
                                res = "CORRECT";
                                System.out.println(tempArrayList.get(3) + "\t" + tempArrayList.get(4) + "\tCORRECT\t\t\tN/A");
                            } else
                            {
                                res = "INCORRECT";
                                System.out.println(tempArrayList.get(3) + "\t" + tempArrayList.get(4) + "\tINCORRECT\t\tError US03: Death DAY cannot be before birth DAY");
                            }
                        } else
                        {
                            res = "INCORRECT";
                            System.out.println(tempArrayList.get(3) + "\t" + tempArrayList.get(4) + "INCORRECT\t\tError US03: Death MONTH cannot be before birth MONTH");
                        }
                    } else
                    {
                        res = "CORRECT";
                        System.out.println(tempArrayList.get(3) + "\t" + tempArrayList.get(4) + "\tCORRECT\t\t\tN/A");
                    }
                } else
                {
                    res = "INCORRECT";
                    System.out.println(tempArrayList.get(3) + "\t" + tempArrayList.get(4) + "\tINCORRECT\t\tError US03: Death YEAR cannot be before birth YEAR");
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
