
package GedcomParse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class GedcomParse
{

    static String reader = null;
    static Mohit s1 = new Mohit();

    static HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
    static HashMap<String, ArrayList<String>> famHash = new HashMap<>();
    static String[] validTags
            =
            {
                "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"
            };

    @SuppressWarnings("resource")
    public static void parse() throws IOException
    {
        String carolinePath = "C:\\Users\\Caroline Squillante\\workspace\\gedDistributor\\src\\ssw555project01.ged";
        String mananPath = "D:\\HIGHER STUDIES\\Stevens\\MS SEM 2\\CS 555 Agile methods for software dev\\GedcomParse\\src\\GedcomParse\\project1_MananSatra.ged";
        String mohitPath = "C:\\Users\\mohit\\Documents\\NetBeansProjects\\GedcomParse\\build\\classes\\gedcomparse\\project1_MananSatra.ged";
        String karanPath = "C:\\Users\\Class2018\\Desktop\\Agile\\Group Work\\ssw555CKMM2018Spring\\ssw555CKMM2018Spring\\project1_MananSatra.ged";
        FileReader fileReader = new FileReader(mananPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Family fam = new Family();
        Individual indi = new Individual();

        Manan m = new Manan();

        // Flag to check if date is birth date or death date
        boolean isBirth = true;
        // Flag to check if tag is individual
        boolean isIndi = true;
        // Flag to bypass empty objects
        boolean isEmpty = true;
        // Flag to check if date is marriage date or divorce date
        boolean isMarried = true;
        //Flag so that immediate correct date after MARR gets printed and not the date after an invalid tag
        boolean immDate = true;
        boolean firstPerson = true;
        ArrayList<String> hashValueIndi = new ArrayList<>();
        ArrayList<String> hashValueFam = new ArrayList<>();
        //while loop to check to see if each line is valid and formats the information accordingly
        while ((reader = bufferedReader.readLine()) != null)
        {
            hashValueIndi = new ArrayList<>();
            hashValueFam = new ArrayList<>();
            String[] lst = reader.split(" ", 0);
            String level = lst[0];
            String tag = "";
            Boolean isValid;
            String arguments = "";

            //check for invalid level # (cannot be greater than 2)
            if (Integer.parseInt(level) >= 3)
            {
                isValid = false;
            }

            //checks all level 0s; valid tag can be INDI, FAM, HEAD, TRLR, NOTE
            if (Integer.parseInt(level) == 0)
            {
                if (lst[1].contains("HEAD") || lst[1].contains("TRLR") || lst[1].contains("NOTE"))
                {
                    tag = lst[1];
                    isValid = true;
                    if (lst.length > 2)
                    {
                        for (int i = 2; i < lst.length; i++)
                        {
                            arguments = arguments + lst[i] + " ";
                        }
                    }
                    //checks special case for INDI and FAM
                } else if (lst[2].contains("INDI") || lst[2].contains("FAM"))
                {

                    //Individual indi = new Individual();
                    hashValueIndi.add(indi.getIndividualID().toString());
                    hashValueIndi.add(indi.getName());
                    hashValueIndi.add(indi.getGender());
                    hashValueIndi.add(indi.getBirth());
                    hashValueIndi.add(indi.getDeath().toString());
                    hashValueIndi.add(indi.getAge());
                    hashValueIndi.add(indi.getisAlive());

                    indiHash.putIfAbsent(indi.getIndividualID(), hashValueIndi);

                    //Family fam = new Family();
                    //System.out.println(fam.famID);
                    hashValueFam.add(fam.getFamID().toString());
                    hashValueFam.add(fam.getHusbID());
                    hashValueFam.add(fam.getHusbName());
                    hashValueFam.add(fam.getWifeID());
                    hashValueFam.add(fam.getWifeName());
                    hashValueFam.add(fam.getMarried());
                    hashValueFam.add(fam.isDivorced());

                    famHash.putIfAbsent(fam.getFamID(), hashValueFam);

                    if (firstPerson)
                    {
                        firstPerson = false;

                    } else
                    {
                        if (isIndi == true)
                        {
                            indi.name = "";
                            indi.age = 0;
                            indi.alive = true;
                            indi.birth = "";
                            indi.child = new ArrayList<String>();
                            indi.death = "";
                            indi.gender = "";
                            indi.individualID = "";
                            indi.spouse = new ArrayList<String>();
                        } else
                        {
                            fam.famID = "";
                            fam.married = "";
                            fam.divorced = "";
                            fam.husbID = "";
                            fam.husbName = "";
                            fam.wifeID = "";
                            fam.wifeName = "";
                            fam.children = new ArrayList<String>();
                        }
                    }

                    if (lst[2].contains("INDI"))
                    {
                        indi.setIndividualID(lst[1].substring(1, lst[1].length() - 1));
                        isIndi = true;
                    } else
                    {
                        fam.setFamID(lst[1].substring(1, lst[1].length() - 1));
                        isIndi = false;
                    }

                    tag = lst[2];
                    arguments = lst[1];
                    isValid = true;

                } else
                {
                    tag = lst[1];
                    arguments = lst[2];
                    isValid = false;
                }
            }

            if (Integer.parseInt(level) == 1)
            {
                boolean val = false;
                for (String t : validTags)
                {
                    if (lst[1].contains(t))
                    {
                        val = true;

                        if (lst[1].contains("NAME"))
                        {
                            indi.setName(lst[2] + " " + lst[3].replace("/", ""));

                        } else if (lst[1].contains("SEX"))
                        {
                            indi.setGender(lst[2]);

                        } else if (lst[1].contains("BIRT"))
                        {
                            indi.setisAlive("True");
                            isBirth = true;

                        } else if (lst[1].contains("DEAT"))
                        {
                            indi.setAlive(false);
                            indi.setisAlive("False");
                            isBirth = false;

                        } else if (lst[1].contains("HUSB"))
                        {
                            fam.setHusbID(lst[2].replace("@", ""));
                            for (String key : indiHash.keySet())
                            {
                                if (fam.husbID.equals(key))
                                {

                                    ArrayList<String> temp = indiHash.get(key);
                                    fam.setHusbName(temp.get(1));
                                    String gender = temp.get(2);

                                    if (gender == "F")
                                    {
                                        System.out.println("Individual " + temp.get(0) + " is registered as an Husband but is a female.");
                                    }
                                }
                            }
                        } else if (lst[1].contains("WIFE"))
                        {

                            fam.wifeID = lst[2];
                            fam.wifeID = fam.wifeID.replace("@", "");
                            for (String key : indiHash.keySet())
                            {
                                if (key.equals(fam.wifeID))
                                {
                                    ArrayList<String> temp = indiHash.get(key);
                                    fam.setWifeName(temp.get(1));
                                    String gender = temp.get(2);

                                    if (gender == "M")
                                    {
                                        System.out.println("Individual " + temp.get(0) + " is registered as an Wife but is a male.");
                                    }
                                }
                            }
                        } else if (lst[1].contains("MARR"))
                        {

                            isMarried = true;
                            immDate = true;
                        } else if (lst[1].contains("DIV"))
                        {
                            isMarried = false;
                        }
                    }
                }

                tag = lst[1];
                if (lst.length > 2)
                {
                    for (int i = 2; i < lst.length; i++)
                    {
                        arguments = arguments + lst[i] + " ";
                    }
                }
                if (val == true)
                {
                    isValid = true;
                } else
                {
                    isValid = false;
                }
            }

            //checks all level 2s; valid tag can be DATE
            if (Integer.parseInt(level) == 2)
            {
                if (lst[1].contains("DATE"))
                {
                    tag = lst[1];
                    isValid = true;
                    if (lst.length > 2)
                    {
                        for (int i = 2; i < lst.length; i++)
                        {
                            arguments = arguments + lst[i] + " ";
                        }

                        // Checks if date is birth date or death date and inserts date accordingly
                        if (isIndi == true && isBirth == true)
                        {
                            indi.birth = arguments;
                        } else if (isIndi == true && isBirth == false)
                        {
                            indi.death = arguments;
                        } else if (isIndi == false && isMarried == true && immDate == true)
                        {
                            fam.married = arguments;
                            immDate = false;
                        } else if (isIndi == false && isMarried == false)
                        {
                            fam.divorced = arguments;
                        }
                    } else
                    {
                        tag = lst[1];
                        arguments = lst[2];
                        isValid = false;
                    }
                }
            }
        }
        hashValueFam.add(fam.getFamID().toString());
        hashValueFam.add(fam.getHusbID());
        hashValueFam.add(fam.getHusbName());
        hashValueFam.add(fam.getWifeID());
        hashValueFam.add(fam.getWifeName());
        hashValueFam.add(fam.getMarried());
        hashValueFam.add(fam.isDivorced());

        famHash.putIfAbsent(fam.getFamID(), hashValueFam);
        String res[] = s1.birthBeforeMarriage(indiHash, famHash);
        s1.marriageAfter14(indiHash, famHash);
        String resBirthBeforeDeath = m.birthBeforeDeath(indiHash);

        //------------Mohit's Space-------------------
        //-------------Manan's Space-------------------
        //-------------Caroline's Space----------------
        //-------------Karan's Space--------------------
    }

    public static void main(String[] args) throws IOException
    {

        parse();

        System.out.println("Individual's Entries:");
        indiHash.remove("");
        for (String key : indiHash.keySet())
        {
            System.out.println(indiHash.get(key));
        }

        System.out.println("Family Entries:");
        System.out.println("ID" + "\t" + "H ID" + "\t" + "H Name" + "\t" + "W ID" + "\t" + "W name" + "\t" + "Marriage date" + "\t" + "Divorce date");
        famHash.remove("");
        for (String key : famHash.keySet())
        {

            System.out.println(famHash.get(key));

            //------------Mohit's Space---------------------
            //-------------Manan's Space--------------------
            //-------------Caroline's Space-----------------
            //-------------Karan's Space--------------------
        }
    }

}
