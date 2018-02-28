/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

/**
 *
 * @author mohit
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Date;

//import dnl.utils.text.table.+import java.text.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class GedcomParse
{

    static String reader = null;
    static Mohit s1 = new Mohit();
    static AliveMarried am;

    static HashMap<String, ArrayList<String>> indiHash = new HashMap<>();
    static HashMap<String, ArrayList<String>> famHash = new HashMap<>();
    //checks all level 1s; valid tag can be NAME, SEX, BIRT, DEAT, FAMC, FAMS, MARR, HUSB, WIFE, CHIL, DIV
    static String[] validTags
            =
            {
                "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"
            };

    @SuppressWarnings("resource")
    public static void parse() throws IOException
    {

        //read file
        // TODO: Change path for our testing file
        String carolinePath = "C:\\Users\\Caroline Squillante\\workspace\\gedDistributor\\src\\ssw555project01.ged";
        String mananPath = "D:\\HIGHER STUDIES\\Stevens\\MS SEM 2\\CS 555 Agile methods for software dev\\GedcomParse\\Gedcom Files\\mananSprint1.ged";
        String mohitPath = "C:\\Users\\mohit\\Documents\\NetBeansProjects\\GedcomParse\\build\\classes\\gedcomparse\\MananMohitSprint1.ged";
        String karanPath = "C:\\Users\\Class2018\\Desktop\\Agile\\Group Work\\GitHub Here\\ssw555CKMM2018Spring\\Gedcom Files\\sprint1.ged";
        
       FileReader fileReader = new FileReader(karanPath);
 //       FileReader fileReader = new FileReader(mananPath);
//        FileReader fileReader = new FileReader(mohitPath);
//       FileReader fileReader = new FileReader(carolinePath);
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        System.out.println("******************** Karan's User story US21: Correct Gender for Role ********************");

        // Creating Object to insert later into array list
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
        String indid[]=new String[256];
        String famid[]=new String[256];
        int ui=0,uf=0;
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
                    hashValueIndi.add(indi.getIndividualID().toString());
                    hashValueIndi.add(indi.getName());
                    hashValueIndi.add(indi.getGender());
                    hashValueIndi.add(indi.getBirth());
                    hashValueIndi.add(indi.getDeath().toString());
                    hashValueIndi.add(indi.getAge());
                    hashValueIndi.add(indi.getisAlive());

                    indiHash.putIfAbsent(indi.getIndividualID(), hashValueIndi);

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
                            //familyArray.add(fam);

                            // Reinitializing the Object
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
                        indid[ui]=lst[1].substring(1, lst[1].length() - 1);
                        ui++;
                        isIndi = true;
                    } else
                    {
                        fam.setFamID(lst[1].substring(1, lst[1].length() - 1));
                        famid[uf]=(lst[1].substring(1, lst[1].length() - 1));
                        uf++;
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
                                //System.out.println(key);
                                if (fam.husbID.equals(key))
                                {

                                    ArrayList<String> temp = indiHash.get(key);
                                    fam.setHusbName(temp.get(1));
                                    String name = temp.get(1);
                                    String gender = temp.get(2);

                                    if (gender.equals("F"))
                                    {
                                    	System.out.println("Error: Individual " + temp.get(0) + " named " + temp.get(1) + " is registered as a Husband but is a Female.");
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
                                    String name = temp.get(1);
                                    String gender = temp.get(2);

                                    if (gender.equals("M"))
                                    {
                                        System.out.println("Error: Individual " + temp.get(0) + " named " + temp.get(1) + " is registered as a Wife but is a Male.");
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
                            
                            FindAges fa = new FindAges();
                            
                            int Age = fa.FindAge(indi.birth, indi.death);
                            
                            indi.age = Age;
                            
                        } else if (isIndi == true && isBirth == false)
                        {
                            indi.death = arguments;
                            
                            FindAges fa = new FindAges();
                            
                            int Age = fa.FindAge(indi.birth, indi.death);
                            
                            indi.age = Age;

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
        
        //--------------------------Mohits Space----------------------------
        String res[]=s1.birthBeforeMarriage(indiHash,famHash);
        s1.marriageAfter14(indiHash, famHash);
        //s1.uniqueIDs(indid,famid);
        //--------------------------Manans Space----------------------------
        String resBirthBeforeDeath = m.birthBeforeDeath(indiHash);
        m.mariageBeforeDivorce(famHash);
        // m.lessThan150(indiHash);

        //--------------------------Karans Space----------------------------
        MarriedAfterDeath md = new MarriedAfterDeath();
        
        md.marriageAfterDeath(indiHash, famHash);
        
        DivorcedAfterDeath dd = new DivorcedAfterDeath();
        
        dd.divorcedAfterDeath(indiHash, famHash);
        
        //--------------------------Carolines Space-------------------------
        
        
        
        
    }

    public static void main(String[] args) throws IOException
    {
        parse();
        System.out.println("\n******************** Karan's User story US27: Individual Ages in Table ********************");
        System.out.println("\n******************** Individual's Entries:********************");
        System.out.println("ID" + "\t\t\t" + "Name" + "\t\t" + "\tGender" + "\t\t" + "Birthday" + "\t\t" + "Deathday" + "\t\t" + "Age" + "\t\t" + "Alive");
        indiHash.remove("");
        for (String key : indiHash.keySet())
        {
            ArrayList temp=indiHash.get(key);
            if(indiHash.get(key).get(4).equals("") && indiHash.get(key).get(3).equals(""))
            {
                for(int i=0;i<temp.size();i++)
                {
                    if(i==3)
                        System.out.print("NA\t\t\t");
                    else if(i==4)
                        System.out.print("NA\t\t\t");
                    else System.out.print(temp.get(i)+"\t\t");
                }
                System.out.println();
            }
            else if(indiHash.get(key).get(4).equals("") || indiHash.get(key).get(3).equals(""))
            {
				int temp1;
                if(indiHash.get(key).get(4).equals(""))
                {
                    temp1=4;
                }
                else
                {
                    temp1=3;
                }
                for(int i=0;i<temp.size();i++)
                {
                    if(i==temp1)
                        System.out.print("NA\t\t\t");
                    else System.out.print(temp.get(i)+"\t\t");
                }
                System.out.println();
            }
            else
            {
                for(int i=0;i<temp.size();i++)
                {
                    System.out.print(temp.get(i)+"\t\t");
                }
                System.out.println();
            }
        }

        System.out.println("\n******************** Family Entries: ********************");
        System.out.println("ID" + "\t\t\t" + "H ID" + "\t\t\t" + "H Name" + "\t\t\t\t" + "W ID" + "\t\t\t" + "W name" + "\t\t\t" + "Marriage date" + "\t\t\t" + "Divorce date");
        famHash.remove("");
        for (String key : famHash.keySet())
        {

            for(String i: famHash.get(key)) {
            	System.out.print(i + "\t\t\t");
            }
            System.out.println();
        }
        
        //--------------------------Mohits Space----------------------------

        
        //--------------------------Manans Space----------------------------        
        
        
        //--------------------------Karans Space----------------------------
        
        
        //--------------------------Carolines Space-------------------------
        
        am = new AliveMarried(indiHash, famHash);
        
        System.out.println("===============Caroline's US30 - List Living Married===============");

        ArrayList<ArrayList<String>> livMarried = am.getAliveMarried();
        
        for (ArrayList<String> key : livMarried)
        {
        	System.out.print(key.get(1));
        	System.out.println();
            
        }
        
        
        System.out.println("===============Caroline's US31 - List Living Single===============");
        ArrayList<ArrayList<String>> livSingle = am.getAliveSingle();
        
        for (ArrayList<String> key : livSingle)
        {
        	System.out.print(key.get(1));
        	System.out.println();
            
        }        
    }

}
