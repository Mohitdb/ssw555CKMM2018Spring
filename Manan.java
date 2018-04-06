package GedcomParse;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

public class Manan
{

    ArrayList<String> tempArrayList;

    public String birthBeforeDeath(HashMap<String, ArrayList<String>> hashIndi)
    {
        hashIndi.remove("");
        String res = "";
        System.out.println("\n====================== Manan's User story US03:Birth Before Death ======================");
        System.out.println("BIRTH\t\tDEATH\t\tERROR MESSAGE");
        System.out.println("-----------\t-----------\t-----------------------");

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
                    //System.out.println("N/A\t\t" + deathDate + "\tError US03: Cannot determine without Birth Date");
                } else
                {
                    //System.out.println( "N/A\t\tN/A\t" + deathDate + "\tError US03: Cannot determine without Birth Date");
                }
            } else if (!deathDate.equals(""))
            {
                String result = dateCal(birthDate, deathDate);
                if (result.equals("year"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tError US03: Death YEAR cannot be before Birth YEAR");
                } else if (result.equals("month"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tError US03: Death MONTH cannot be before Birth MONTH for same year");
                } else if (result.equals("day"))
                {
                    res = "INCORRECT";
                    System.out.println(birthDate + "\t" + deathDate + "\tError US03: Death DAY cannot be before Birth DAY for same month and year");
                } else
                {
                    res = "CORRECT";
                    //System.out.println(birthDate + "\t" + deathDate + "\tN/A");
                }

            } else
            {
                res = "CORRECT";
                //System.out.println(tempArrayList.get(3) + "\t" + "\t\tN/A");
            }
        }
        System.out.println();
        return res;
    }

    public ArrayList<String> mariageBeforeDivorce(HashMap<String, ArrayList<String>> famHash)
    {
        famHash.remove("");
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> tempArrayList = new ArrayList<>();
        System.out.println("\n====================== Manan's User story US04:Marriage Before Divorce ======================");
        System.out.println("MARRIAGE\tDIVORCE\t\tERROR MESSAGE");
        System.out.println("-----------\t-------------\t----------------------");
        for (String key : famHash.keySet())
        {
            tempArrayList = famHash.get(key);
            String marriageDate = tempArrayList.get(5);
            String divorceDate = tempArrayList.get(6);
            if (marriageDate.equals(""))
            {
                if (!divorceDate.equals(""))
                {
                    //System.out.println("N/A\t\t" + divorceDate + "\tError US04: Cannot determine without Marriage Date");
                } else
                {
                    //System.out.println("N/A\t\tN/A\t" + "\tError US04: Cannot determine without Marriage Date");
                }
            } else if (!divorceDate.equals(""))
            {
                String result = dateCal(marriageDate, divorceDate);
                if (result.equals("year"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tError US04: Divorce YEAR cannot be before Marriage YEAR");
                    res.add("INCORRECT");
                } else if (result.equals("month"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tError US04: Divorce MONTH cannot be before Marriage MONTH for same year");
                    res.add("INCORRECT");
                } else if (result.equals("day"))
                {
                    System.out.println(marriageDate + "\t" + divorceDate + "\tError US04: Divorce DAY cannot be before Marriage DAY for same month and year");
                    res.add("INCORRECT");
                } else
                {
                    //System.out.println(marriageDate + "\t" + divorceDate + "\t\tN/A");
                }
            } else
            {
                //System.out.println(tempArrayList.get(5) + "\t" + "N/A" + "\t\tCORRECT\t\t\tN/A");
            }
        }
        return res;
    }

    public String lessThan150(HashMap<String, ArrayList<String>> hashIndi)
    {
        System.out.println("\n====================== Manan's User story US07:Less Than 15 Years Old ======================");
        hashIndi.remove("");
        String resLessThan150 = "";
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        for (String key : hashIndi.keySet())
        {
            tempArrayList = hashIndi.get(key);
            int age = Integer.parseInt(tempArrayList.get(5));
            String birthDate = tempArrayList.get(3);
            String deathDate = tempArrayList.get(4);
            if (age >= 150)
            {
                int birthYear = Integer.parseInt(birthDate.split(" ")[2]);
                if (tempArrayList.get(4).equals(""))
                {
                    System.out.println();
                    System.out.println(birthDate + "<-Birth\tDeath->" + deathDate);
                    System.out.println("ERROR US07: Birth year " + birthYear + " should be less than 150 years from current year " + currentYear);
                    resLessThan150 = "INCORRECT";
                } else
                {
                    int deathYear = Integer.parseInt(deathDate.split(" ")[2]);
                    System.out.println();
                    System.out.println(birthDate + "<-Birth\tDeath->" + deathDate);
                    System.out.println("ERROR US07: Death year " + deathYear + " should be less than 150 years from birth year " + birthYear);
                    resLessThan150 = "INCORRECT";
                }
            } else
            {
                resLessThan150 = "CORRECT";
            }
        }
        return resLessThan150;
    }

    public int[] siblingsByAge(HashMap<String, ArrayList<String>> famHash, HashMap<String, ArrayList<String>> indiHash)
    {
        indiHash.remove("");
        famHash.remove("");
        int[] ageCopy =
        {
        };
        System.out.println("\n====================== Manan's User story US28:Order Siblings of Families by Age ======================");
        for (String s : famHash.keySet())
        {

            int i = 0;
            if (!famHash.get(s).get(7).equals(""))
            {
                System.out.println("\nFor Family " + s);
                System.out.println("Name\t\tAge");
                System.out.println("-------------  --------");
                String[] splitted = famHash.get(s).get(7).split(" ");
                int[] age = new int[splitted.length];
                String[] name = new String[splitted.length];
                for (String s1 : splitted)
                {
                    if (!s1.equals(""))
                    {
                        age[i] = Integer.parseInt(indiHash.get(s1).get(5));
                        name[i] = indiHash.get(s1).get(1);
                        i++;
                    }
                }
                for (int k = 0; k < age.length - 1; k++)
                {
                    for (int j = 0; j < age.length - 1 - k; j++)
                    {
                        if (age[j] < age[j + 1])
                        {
                            int temp = age[j];
                            age[j] = age[j + 1];
                            age[j + 1] = temp;

                            String tempString = name[j];
                            name[j] = name[j + 1];
                            name[j + 1] = tempString;
                        }
                    }
                }
                for (int j = 0; j < age.length; j++)
                {
                    System.out.println(name[j] + "\t" + age[j]);
                }
                ageCopy = age;
            }
        }

        return ageCopy;
    }

    ArrayList<String> maleLastNames(HashMap<String, ArrayList<String>> famHash, HashMap<String, ArrayList<String>> indiHash)
    {
        System.out.println("\n====================== Manan's User story US16:Male Last Names ======================");
        famHash.remove("");
        String surname = "";
        ArrayList<String> res = new ArrayList<>();
        for (String key : famHash.keySet())
        {
            if (!famHash.get(key).get(2).equals(""))
            {
                surname = famHash.get(key).get(2).split(" ")[1];
            } else
            {
                String tempSiblingID = famHash.get(key).get(7).split(" ")[0];
                for (String id : indiHash.keySet())
                {
                    if (id.equals(tempSiblingID))
                    {
                        surname = indiHash.get(id).get(1).split(" ")[1];
                    }
                }

            }
            if (!famHash.get(key).get(7).equals(""))
            {
                String[] siblingIDs = famHash.get(key).get(7).split(" ");
                for (String s : siblingIDs)
                {
                    for (String key1 : indiHash.keySet())
                    {
                        if (key1.equals(s))
                        {
                            if (indiHash.get(key1).get(2).equals("M"))
                            {
                                if (!indiHash.get(key1).get(1).split(" ")[1].equals(surname))
                                {
                                    System.out.println("ERROR US16: FAMILY " + key + " : Males in the family should have same last name!");
                                    System.out.println("CONFLICTING LAST NAMES: " + surname + " & " + indiHash.get(key1).get(1).split(" ")[1]);
                                    System.out.println();
                                    res.add("INCORRECT");
                                } else
                                {
                                    res.add("CORRECT");
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    ArrayList<String> uniqueFirstNames(HashMap<String, ArrayList<String>> famHash, HashMap<String, ArrayList<String>> indiHash)
    {
        System.out.println("\n====================== Manan's User story US25:Unique First Names in Families ======================");
        indiHash.remove("");
        famHash.remove("");
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> exists = new ArrayList<>();
        HashMap<String, String> tempHash = new HashMap<>();
        for (String key : famHash.keySet())
        {
            if (!famHash.get(key).get(7).equals(""))
            {
                String[] childIDs = famHash.get(key).get(7).split(" ");

                String childname = "";
                String childDOB = "";
                int i = 0;
                if (childIDs.length > 1)
                {

                    for (String s : childIDs)
                    {
                        for (String key1 : indiHash.keySet())
                        {
                            if (key1.equals(s))
                            {

                                if (tempHash.containsKey(indiHash.get(key1).get(1)))
                                {

                                    if (indiHash.get(key1).get(3).equals(tempHash.get(indiHash.get(key1).get(1))))
                                    {
                                        String temp = indiHash.get(key1).get(1) + indiHash.get(key1).get(3);
                                        if (!exists.contains(temp))
                                        {
                                            System.out.println("ERROR US25: FAMILY: " + key + ": No more than one child with the same name and birth date should appear in a family!");
                                            System.out.println("SAME CHILD NAME: " + indiHash.get(key1).get(1) + " \tSAME BIRTH DATE: " + indiHash.get(key1).get(3));
                                            System.out.println();
                                            res.add("INCORRECT");
                                            exists.add(temp);
                                        } else
                                        {
                                            exists.add(temp);
                                            res.add("INCORRECT");
                                        }
                                    } else
                                    {
                                        res.add("CORRECT");
                                    }

                                } else
                                {
                                    tempHash.put(indiHash.get(key1).get(1), indiHash.get(key1).get(3));
                                }
                            }
                        }
                    }
                } else
                {
                    res.add("CORRECT");
                }
            }
        }
        return res;
    }

    ArrayList<String> recentBirth(HashMap<String, ArrayList<String>> indiHash) throws ParseException
    {
        indiHash.remove("");
        ArrayList<String> res = new ArrayList<>();
        System.out.println("\n====================== Manan's User story US35:List Recent Births ======================");
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        System.out.println("Today is:\t\t" + today);
        c.add(Calendar.DATE, -30);
        Date dateBefore30Days = c.getTime();
        System.out.println("Date Before 30 days:\t" + dateBefore30Days + "\n");
        String[] dateString = dateBefore30Days.toString().split(" ");
        for (String key : indiHash.keySet())
        {
            String bdayYear = "";
            String bdayMonth = "";
            String bdayDate = "";
            try
            {
                bdayYear = indiHash.get(key).get(3).split(" ")[2];
                bdayMonth = indiHash.get(key).get(3).split(" ")[1];
                bdayDate = indiHash.get(key).get(3).split(" ")[0];
            } catch (ArrayIndexOutOfBoundsException ne)
            {
                continue;
            }
            String time = Arrays.toString(dateString).split(" ")[3];
            String sDate6 = bdayDate + "-" + bdayMonth + "-" + bdayYear + " " + time;
            SimpleDateFormat formatter6 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date date6 = formatter6.parse(sDate6);
            if (!date6.after(today))
            {
                if (date6.after(dateBefore30Days))
                {
                    System.out.println("Name: " + indiHash.get(key).get(1) + "\t\tBirth Date:" + bdayMonth + " " + bdayDate + " " + bdayYear);
                    res.add("CORRECT");
                } else
                {
                    res.add("INCORRECT");
                }
            }
        }
        return res;
    }

    ArrayList<String> recentDeath(HashMap<String, ArrayList<String>> indiHash) throws ParseException
    {
        ArrayList<String> res = new ArrayList<>();
        indiHash.remove("");
        System.out.println("\n====================== Manan's User story US36:List Recent Deaths ======================");
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        System.out.println("Today is:\t\t" + today);
        c.add(Calendar.DATE, -30);
        Date dateBefore30Days = c.getTime();
        System.out.println("Date Before 30 days:\t" + dateBefore30Days + "\n");
        String[] dateString = dateBefore30Days.toString().split(" ");
        for (String key : indiHash.keySet())
        {
            String ddayYear = "";
            String ddayMonth = "";
            String ddayDate = "";
            try
            {
                ddayYear = indiHash.get(key).get(4).split(" ")[2];
                ddayMonth = indiHash.get(key).get(4).split(" ")[1];
                ddayDate = indiHash.get(key).get(4).split(" ")[0];
            } catch (ArrayIndexOutOfBoundsException ai)
            {
                continue;
            }
            String time = Arrays.toString(dateString).split(" ")[3];
            String sDate6 = ddayDate + "-" + ddayMonth + "-" + ddayYear + " " + time;
            SimpleDateFormat formatter6 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date date6 = formatter6.parse(sDate6);
            if (!date6.after(today))
            {
                if (date6.after(dateBefore30Days))
                {
                    System.out.println("Name: " + indiHash.get(key).get(1) + "\t\tDeath Date:" + ddayMonth + " " + ddayDate + " " + ddayYear);
                    res.add("CORRECT");
                } else
                {
                    res.add("INCORRECT");
                }
            }
        }
        return res;
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
                    if (firstMonthNumber == secondMonthNumber)
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
                        result = "CORRECT";
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
