/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mohit
 */
public class Mohit {

    ArrayList<String> famvalues;
    ArrayList<String> indivalues;
    
    
    static HashMap<String, Integer> monthmap = new HashMap<>();
    static{
        monthmap.put("JAN", 1);
        monthmap.put("FEB", 2);
        monthmap.put("MAR", 3);
        monthmap.put("APR", 4);
        monthmap.put("MAY", 5);
        monthmap.put("JUN", 6);
        monthmap.put("JUL", 7);
        monthmap.put("AUG", 8);
        monthmap.put("SEP", 9);
        monthmap.put("OCT", 10);
        monthmap.put("NOV", 11);
        monthmap.put("DEC", 12);
    }
    public String comparedate(String mdate[],String hwdate[])
    {
        String res;
        if (Integer.parseInt(mdate[2]) < Integer.parseInt(hwdate[2])) {
                        res = "invalid";
                    } else if (Integer.parseInt(mdate[2]) == Integer.parseInt(hwdate[2])) {
                        if (monthmap.get(mdate[1]) < monthmap.get(hwdate[1])) {
                            res = "invalid";
                        } else if (monthmap.get(mdate[1]) == monthmap.get(hwdate[1])) {
                            if (Integer.parseInt(mdate[0]) <= Integer.parseInt(hwdate[0])) {
                                res = "invalid";
                            } else {
                                res = "valid";
                            }
                        } else {
                            res = "valid";
                        }
                    } else {
                        res = "valid";
                    }
        return res;
    }
    public String[] birthBeforeMarriage(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap) {
        indiHashmap.remove("");
        famHashmap.remove("");
        String res[] = new String[famHashmap.size()];
        String MDate[] = new String[famHashmap.size()];
        String HBdate[] = new String[famHashmap.size()];
        String WBdate[] = new String[famHashmap.size()];
        String FId[] = new String[famHashmap.size()];
        System.out.println("\n\n=> Mohit Sprint 1 User Story 02: Birth Before Marriage");int i = 0;
        try{
        for (String key : famHashmap.keySet()) {
            famvalues = famHashmap.get(key);
            FId[i] = key;
            if (famvalues.get(5).equals("")) {
                MDate[i] = "N/A";
            } else {
                MDate[i] = famvalues.get(5);
            }
            for (String key2 : indiHashmap.keySet()) {
                indivalues = indiHashmap.get(key2);
                if (famvalues.get(1).equals(key2) || famvalues.get(3).equals(key2)) {
                    if (famvalues.get(1).equals(key2)) {
                        if (indivalues.get(3).equals("")) {
                            HBdate[i] = "N/A";
                        } else {
                            HBdate[i] = indivalues.get(3);
                        }
                    }
                    if (famvalues.get(3).equals(key2)) {
                        if (indivalues.get(3).equals("")) {
                            WBdate[i] = "N/A";
                        } else {
                            WBdate[i] = indivalues.get(3);
                        }
                    }
                }
            }
            i++;
        }
        }
        catch(NullPointerException ne){
        }
        try {
            for (int j = 0; j < i; j++) {

                if ((MDate[j].equals("N/A")) || (HBdate[j].equals("N/A")) || (WBdate[j].equals("N/A"))) {
                    res[j] = "cannot say";
                } else {
                    String mdate[] = MDate[j].split(" ");
                    String hdate[] = HBdate[j].split(" ");
                    String wdate[] = WBdate[j].split(" ");
                    res[j]=comparedate(mdate,hdate);
                    if (res[j].equals("invalid")) {
                        res[j] = "invalid";
                    } else {
                        res[j]=comparedate(mdate,wdate);
                    }
                }
                if (res[j].equals("invalid")) {
                    System.out.println("ERROR: US02: Family ID: " + FId[j] + " has marriage date: " + MDate[j] + " with Husband birthdate: " + HBdate[j] + " and Wife birthdate: " + WBdate[j] + ". Thus, birth not before marriage.");
                }
            }
        } catch (NullPointerException ne) {

        }
        return res;
    }

    public String[] marriageAfter14(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap) {

        String res[] = new String[famHashmap.size()];
        String MDate[] = new String[famHashmap.size()];
        String HBdate[] = new String[famHashmap.size()];
        String WBdate[] = new String[famHashmap.size()];
        String FId[] = new String[famHashmap.size()];
        System.out.println("\n\n=>Mohit Sprint 1 User Story 10: Marriage after 14");
        int i = 0;
        for (String key : famHashmap.keySet()) {
            famvalues = famHashmap.get(key);
            FId[i] = key;
            if (famvalues.get(5).equals("")) {
                MDate[i] = "N/A";
            } else {
                MDate[i] = famvalues.get(5);
            }
            for (String key2 : indiHashmap.keySet()) {
                indivalues = indiHashmap.get(key2);
                if (famvalues.get(1).equals(key2) || famvalues.get(3).equals(key2)) {
                    if (famvalues.get(1).equals(key2)) {
                        if (indivalues.get(3).equals("")) {
                            HBdate[i] = "N/A";
                        } else {
                            HBdate[i] = indivalues.get(3);
                        }
                    }
                    if (famvalues.get(3).equals(key2)) {
                        if (indivalues.get(3).equals("")) {
                            WBdate[i] = "N/A";
                        } else {
                            WBdate[i] = indivalues.get(3);
                        }
                    }
                }
            }
            i++;
        }
        try {
            for (int j = 0; j < i; j++) {
                if ((MDate[j].equals("N/A")) || (HBdate[j].equals("N/A")) || (WBdate[j].equals("N/A"))) {
                    res[j] = "cannot say";
                } else {
                    String mdate[] = MDate[j].split(" ");
                    String hdate[] = HBdate[j].split(" ");
                    String wdate[] = WBdate[j].split(" ");
                    int h=Integer.parseInt(hdate[2])+14;
                    String thdate=hdate[0]+" "+hdate[1]+" "+h;
                    String newhdate[]=thdate.split(" ");
                    int w=Integer.parseInt(wdate[2])+14;
                    String twdate=wdate[0]+" "+wdate[1]+" "+w;
                    String newwdate[]=twdate.split(" ");
                    res[j]=comparedate(mdate,newhdate);
                    if (res[j].equals("invalid")) {
                        res[j] = "invalid";
                    } else {
                        res[j]=comparedate(mdate,newwdate);
                    }
                }
                if (res[j].equals("invalid")) {
                    System.out.println("ERROR: US10: Family ID: " + FId[j] + " has marriage date: " + MDate[j] + " with Husband birthdate: " + HBdate[j] + " and Wife birthdate: " + WBdate[j] + ". Thus, marriage not after 14.");
                }
            }
        } catch (NullPointerException ne) {

        }
        return res;
    }

    public String[] uniqueIDs(String indid[], String famid[]) {
        String dup[]=new String [256];
        String res[]=new String[256];
        int m=0;
        int r=0;
        System.out.println("\n\n=>Mohit Sprint 2 User Story 22: UniqueIDs");
        try {
            for (int i = 0; i < indid.length; i++) {
                for (int j = i + 1; j < indid.length; j++) {
                    if (indid[i].equals(indid[j])) {
                        dup[m++]=indid[i];
                        break;
                        }
                }
            }
        }
        catch (NullPointerException ne) {
        }
        Set<String> mySet = new HashSet<String>(Arrays.asList(dup));
        mySet.remove(null);
        for (String temp : mySet) {
            System.out.println("ERROR: US22: Individual ID: " + temp + " is not unique");
            res[r]=temp;
            r++;
        }
        m=0;
        try {
            for (int k = 0; k < famid.length; k++) {
                for (int l = k + 1; l < famid.length; l++) {

                    if (famid[k].equals(famid[l])) {
                        dup[m++]=famid[k];
                        break;
                    }
                }
            }
        } catch (NullPointerException ne) {
        }
        Set<String> mySet2 = new HashSet<String>(Arrays.asList(dup));
        mySet2.remove(null);
        for (String temp : mySet2) {
           System.out.println("ERROR: US22: Family ID: " + temp + " is not unique");
           res[r]=temp;
           r++;
        }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
    
    public String[] uniquenamebirthdate(HashMap<String, ArrayList<String>> indiHashmap){
        String dup[]=new String [256];
        String res[]=new String[256];
        int m=0;
        int r=0;
        System.out.println("\n\n=>Mohit Sprint 2 User Story 23: Unique name and birth date");
        indiHashmap.remove("");
        String name[] = new String[indiHashmap.size()];
        String BDate[] = new String[indiHashmap.size()];
        int i=0;
        for (String key : indiHashmap.keySet()) {
            indivalues = indiHashmap.get(key);
            name[i]=indivalues.get(1);
            BDate[i]=indivalues.get(3);
            i++;
        }
        try{
        for (int k = 0; k < name.length; k++) {
                for (int j = k + 1; j < name.length; j++) {
                   if(name[k].equals(name[j]) && BDate[k].equals(BDate[j])) {
                       dup[m++]=name[k]+"\t"+BDate[k];
                       break;
                   }
                }
        }
        }
        catch(NullPointerException ne){
            
        }
        Set<String> mySet = new HashSet<String>(Arrays.asList(dup));
        mySet.remove(null);
        for (String temp : mySet) {
           System.out.println("ANOMALY: US23: Name - " + temp.substring(0,temp.indexOf("\t")) + " and Birth Date - "+temp.substring(temp.indexOf("\t")+1)+" together occur more than once. Hence, not unique.");
           res[r]=temp;
           r++;
        }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
}
