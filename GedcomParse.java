/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gedcomparse;

/**
 *
 * @author mohit
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MANAN MANASVI
 */
public class GedcomParse {

    static String reader = null;
    static HashMap<String, String> indiHash = new HashMap<>();
    static HashMap<String, String> famHash = new HashMap<>();

    /*
	 * Method to parse the ged file and determine if the tag is valid
	 * prints out each line in the form <level>|<tag>|<valid?> : Y or N|<arguments>
     */
    @SuppressWarnings("resource")
    public static void parse() throws IOException {

        //read file
        //FileReader fileReader = new FileReader("D:\\HIGHER STUDIES\\Stevens\\MS SEM 2\\CS 555 Agile methods for software dev\\New Folder\\GedcomParse\\project1_MananSatra.ged"); // TODO: Change path for our testing file
        FileReader fileReader = new FileReader("C:\\Users\\Caroline Squillante\\workspace\\gedcom\\src\\ssw555project01.ged");
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
        String hashValueIndi = "";
        String hashValueFam = "";

        //while loop to check to see if each line is valid and formats the information accordingly
        while ((reader = bufferedReader.readLine()) != null) {
            //System.out.println("--> " + reader);

            String[] lst = reader.split(" ", 0);

            String level = lst[0];
            String tag = "";
            String isValid = "";
            String arguments = "";

            //check for invalid level # (cannot be greater than 2)
            if (Integer.parseInt(level) >= 3) {
                isValid = "N";
                tag = lst[1];
                if (lst.length > 2) {
                    for (int i = 2; i < lst.length; i++) {
                        arguments = arguments + lst[i] + " ";
                    }
                }
            }

            //checks all level 0s; valid tag can be INDI, FAM, HEAD, TRLR, NOTE
            if (Integer.parseInt(level) == 0) {
                if (lst[1].contains("HEAD") || lst[1].contains("TRLR") || lst[1].contains("NOTE")) {
                    tag = lst[1];
                    isValid = "Y";
                    if (lst.length > 2) {
                        for (int i = 2; i < lst.length; i++) {
                            arguments = arguments + lst[i] + " ";
                        }
                    }
                    //checks special case for INDI and FAM
                } else if (lst[2].contains("INDI") || lst[2].contains("FAM")) {

                    hashValueIndi = indi.individualID + "\t" + indi.name + "\t" + indi.gender + "\t" + indi.birth + "\t" + indi.alive;
                    indiHash.putIfAbsent(indi.individualID, hashValueIndi);
                    hashValueFam = fam.famID + "\t" + fam.husbID + "\t" +fam.husbName + "\t" + fam.wifeID + "\t" + fam.wifeName + "\t" ;
                    famHash.putIfAbsent(fam.famID, hashValueFam);

//                    hashValueIndi="";
                    if (isEmpty) {
                        //don't add to arraylist coz objects are empty
                        isEmpty = false;
                    } else {
                        //add to arraylist

                        // Insert Data into ArrayLists
                        if (isIndi == true) {
                            //individualArray.add(indi);
                            //System.out.println("Inside if");
                            // Reinitializing the Object

                            indi.name = "";
                            indi.age = 0;
                            indi.alive = true;
                            indi.birth = "";
                            indi.child = new ArrayList<String>();
                            indi.death = "";
                            indi.gender = "";
                            indi.individualID = "";
                            indi.spouse = new ArrayList<String>();
                        } else {
                            //familyArray.add(fam);

                            // Reinitializing the Object
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

                    // Inserts Individual ID to individual object
                    if (lst[2].contains("INDI")) {
                        indi.individualID = lst[1].substring(1, lst[1].length() - 1);
//                        System.out.println(indi.individualID);
                        isIndi = true;
                    } // Inserts Family ID to Family object
                    else if (lst[2].contains("FAM")) {
                        fam.famID = lst[1].substring(1, lst[1].length() - 1);
//                        System.out.println(fam.famID);
                        isIndi = false;
                    }

                    tag = lst[2];
                    arguments = lst[1];
                    isValid = "Y";

                } else {
                    tag = lst[1];
                    arguments = lst[2];
                    isValid = "N";
                }
            }

            //checks all level 1s; valid tag can be NAME, SEX, BIRT, DEAT, FAMC, FAMS, MARR, HUSB, WIFE, CHIL, DIV
            String[] validTags
                    = {
                        "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", "MARR", "HUSB", "WIFE", "CHIL", "DIV"
                    };
            if (Integer.parseInt(level) == 1) {
                boolean val = false;
                for (String t : validTags) {
                    if (lst[1].contains(t)) {
                        val = true;

                        if (lst[1].contains("NAME")) {
                            indi.name = lst[2];
                            hashValueIndi = hashValueIndi.concat("\t" + indi.name);
                        } else if (lst[1].contains("SEX")) {
                            indi.gender = lst[2];
                            hashValueIndi = hashValueIndi.concat("\t" + indi.gender);
                        } else if (lst[1].contains("BIRT")) {
                            isBirth = true;
                            hashValueIndi = hashValueIndi.concat("\t" + indi.birth);
                        } else if (lst[1].contains("DEAT")) {
                            indi.alive = false;
                            isBirth = false;
                            hashValueIndi = hashValueIndi.concat("\t" + indi.death);
                        } else if (lst[1].contains("HUSB")) {
                            fam.husbID = lst[2];
                            //System.out.println(fam.husbID);
                            fam.husbID = fam.husbID.replace("@", "");
                            
                            //fam.husbID=fam.husbID.substring(0, fam.husbID.indexOf("@"));
                            //System.out.println(fam.husbID);
                            //hashValueFam = hashValueFam.concat("\t" + )
                            for (String key : indiHash.keySet()) {
                                //System.out.println(key);
                                if (fam.husbID.equals(key)) {
                                    
                                    String temp = indiHash.get(key);
                                    //System.out.println(temp);
                                    temp = temp.substring(temp.indexOf("\t") + 1);
                                    //System.out.println(temp);
                                    fam.husbName = temp.substring(0, temp.indexOf("\t"));
                                    hashValueFam = hashValueFam.concat("\t" + fam.husbName);
                                    //famHash.put(fam.famID,temp);
                                }
                                //System.out.println(indiHash.get(key));
                            }
                        } else if (lst[1].contains("WIFE")) {
                            fam.wifeID = lst[2];
                            fam.wifeID = fam.wifeID.replace("@", "");
                            
                            //System.out.println(fam.wifeID);
                            for (String key : indiHash.keySet()) {
                                if (key.equals(fam.wifeID)) {
                                    String temp = indiHash.get(key);
                                    temp = temp.substring(temp.indexOf("\t") + 1);
                                    fam.wifeName = temp.substring(0, temp.indexOf("\t"));
                                    hashValueFam = hashValueFam.concat("\t" + fam.wifeName);
                                    //famHash.put(fam.famID,temp);
                                }
                                //System.out.println(indiHash.get(key));
                            }
                        }
                        // TODO: Need to implement for rest of the tags
                    }
                }

                tag = lst[1];
                if (lst.length > 2) {
                    for (int i = 2; i < lst.length; i++) {
                        arguments = arguments + lst[i] + " ";
                    }
                }
                if (val == true) {
                    isValid = "Y";
                } else {
                    isValid = "N";
                }
            }

            //checks all level 2s; valid tag can be DATE
            if (Integer.parseInt(level) == 2) {
                if (lst[1].contains("DATE")) {
                    tag = lst[1];
                    isValid = "Y";
                    if (lst.length > 2) {
                        for (int i = 2; i < lst.length; i++) {
                            arguments = arguments + lst[i] + " ";
                        }

                        // Checks if date is birth date or death date and inserts date accordingly
                        if (isBirth == true) {
                            indi.birth = arguments;
                        } else {
                            indi.death = arguments;
                        }
                    }
                } else {
                    tag = lst[1];
                    arguments = lst[2];
                    isValid = "N";
                }
            }
//            String hashValueIndi=indi.name+"\t"+indi.gender+"\t"+indi.birth+"\t"+indi.alive;

//            indiHash.putIfAbsent(indi.individualID, hashValueIndi);
            //outputs the data in the correct format
            //System.out.println("<-- " + level + "|" + tag + "|" + isValid + "|" + arguments);
        }

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        parse();
        System.out.println("Individual's Entries:");
        System.out.println("ID" + "\t"+ "Name" + "\t"+ "Gender" + "\t"+ "Birthday" + "\t" + "Alive");
        indiHash.remove("");
        
        for (String key : indiHash.keySet()) {
//            if(key!="")
            System.out.println(indiHash.get(key));
        }
        System.out.println("Family Entries:");
        System.out.println("ID" + "\t"+ "H ID" + "\t"+ "H Name" + "\t"+ "W ID" + "\t" + "W name");
        famHash.remove("");
        for (String key : famHash.keySet()) {
//            if(key!="")
            System.out.println(famHash.get(key));
        }
//        System.out.println("Family Entries:");
//        for (String key:famHash.keySet())
//        {
//            System.out.println(famHash.get(key));
//        }
    }

}
