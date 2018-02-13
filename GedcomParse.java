/**
 * SSW555 GedcomParse.java
 *
 * Main file for project
 */
package GedcomParse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//import dnl.utils.text.table.TextTable;
public class GedcomParse
{

    static String reader = null;
    static String[] validTags
            =
            {
                "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"
            };
    public static HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
    public static HashMap<String, ArrayList<String>> famHash = new HashMap<>();

    @SuppressWarnings("resource")
    public static void parse() throws IOException
    {

        String carolinePath = "C:\\Users\\Caroline Squillante\\workspace\\gedDistributor\\src\\ssw555project01.ged";
        String mananPath = "D:\\HIGHER STUDIES\\Stevens\\MS SEM 2\\CS 555 Agile methods for software dev\\GedcomParse\\src\\GedcomParse\\project1_MananSatra.ged";
        String mohitPath = "";
        String karanPath = "C:\\Users\\Class2018\\Desktop\\Agile\\Group Work\\ssw555CKMM2018Spring\\ssw555CKMM2018Spring\\project1_MananSatra.ged";
        FileReader fileReader = new FileReader(karanPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Creating Object to insert later into array list
        Family fam = new Family();
        Individual indi = new Individual();

        // Flag to check if date is birth date or death date
        boolean isBirth = true;

        // Flag to check if tag is individual
        boolean isIndi = true;

        // Flag to bypass empty objects
        boolean isEmpty = true;

        boolean firstPerson = true;
        ArrayList<String> hashValueIndi = new ArrayList<String>();
        ArrayList<String> hashValueFam = new ArrayList<String>();
        //while loop to check to see if each line is valid and formats the information accordingly
        while ((reader = bufferedReader.readLine()) != null)
        {
            hashValueIndi = new ArrayList<String>();
            hashValueFam = new ArrayList<String>();

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
                    hashValueIndi.add(indi.getIndividualID().toString());
                    hashValueIndi.add(indi.getName());
                    hashValueIndi.add(indi.getGender());
                    hashValueIndi.add(indi.getBirth());
                    hashValueIndi.add(indi.getisAlive());
                    hashValueIndi.add(indi.getDeath().toString());

                    indiHash.putIfAbsent(indi.getIndividualID(), hashValueIndi);

                    hashValueFam.add(fam.getFamID().toString());
                    hashValueFam.add(fam.getHusbID());
                    hashValueFam.add(fam.getHusbName());
                    hashValueFam.add(fam.getWifeID());
                    hashValueFam.add(fam.getWifeName());

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
                            fam.divorced = false;
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
                            isBirth = true;
                            indi.setisAlive("True");

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
                                //System.out.println(key);
                                if (fam.husbID.equals(key))
                                {

                                    ArrayList<String> temp = indiHash.get(key);
                                    fam.setHusbName(temp.get(1));
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
                                }
                            }
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

                        if (isBirth == true)
                        {
                            indi.birth = arguments;
                        } else
                        {
                            indi.death = arguments;
                        }
                    }
                } else
                {
                    tag = lst[1];
                    arguments = lst[2];
                    isValid = false;
                }
            }
        }

        hashValueFam.add(fam.getFamID().toString());
        hashValueFam.add(fam.getHusbID());
        hashValueFam.add(fam.getHusbName());
        hashValueFam.add(fam.getWifeID());
        hashValueFam.add(fam.getWifeName());

        famHash.putIfAbsent(fam.getFamID(), hashValueFam);
        
        

    }

    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        parse();

//        String[] indivColNames =
//        {
//            "ID",
//            "Name",
//            "Gender",
//            "Birthday",
//            "Alive"
//        };
//        int indiColNum = indivColNames.length;
//        int indiSize = indiHash.size();
        //Object[][] data = new Object[indiSize][indiColNum];
        //TextTable tt = new TextTable(indivColNames, data);
        // this adds the numbering on the left 
        // sort by the first column 
        //tt.setSort(0);
        //tt.
        //tt.
        indiHash.remove("");
        System.out.println("Individual's Entries:");
        //Object[] individual = new Object[20];
        int i = 0;
        for (String key : indiHash.keySet())
        {
//            individual = indiHash.get(key).toArray();
//            for (int j = 0; i < individual.length; i++)
//            {
//                data[i][j] = individual[j];
//            }
//            i++;
            System.out.println(indiHash.get(key));
        }

        //tt.printTable(); 
        System.out.println("Family Entries:");
//        System.out.println("ID" + "\t" + "H ID" + "\t" + "H Name" + "\t" + "W ID" + "\t" + "W name");
        famHash.remove("");
        for (String key : famHash.keySet())
        {
            System.out.println(famHash.get(key));
        }
    }

}
