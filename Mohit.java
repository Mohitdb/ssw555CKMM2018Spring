/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
            for (int i = 0; i < indid.length-1; i++) {
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
        for(int q=0;q<dup.length;q++)
        {
            dup[q]=null;
        }
        try {
            for (int k = 0; k < famid.length-1; k++) {
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
        for (String temp2 : mySet2) {
           System.out.println("ERROR: US22: Family ID: " + temp2 + " is not unique");
           res[r]=temp2;
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
    public String[] siblingsShouldNotMarry(HashMap<String, ArrayList<String>> famHashmap)
    {
        System.out.println("\n\n=>Mohit Sprint 3 User Story 18: Siblings should not marry");
        int r=0;
        String res[]=new String[256];
        for (String key : famHashmap.keySet()) {
            famvalues = famHashmap.get(key);
            String[] child = famvalues.get(7).split(" ");
            if(child.length>1)
            {
                for(int i=0;i<child.length-1;i++)
                {
                    for(int j=i+1;j<child.length;j++)
                    {
                        for(String key2: famHashmap.keySet()){
                            famvalues=famHashmap.get(key2);
                            if((child[i].matches(famvalues.get(1))&& child[j].matches(famvalues.get(3))) || ((child[j].matches(famvalues.get(1))&& child[i].matches(famvalues.get(3)))))
                            {
                                System.out.println("ERROR: US18: Siblings with IDs "+child[i]+" "+child[j]+" are married");
                                res[r++]=child[i]+"\t"+child[j];
                            }
                        }
                    }
                }
            }
        }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
    
    public boolean checkDate(String s){
        try{
            s=s.trim();
        if(!s.matches("")){
            
            if(s.contains("NA"))
            {
                return true;
            }
            
        String temp[]=s.split(" ");
        if((temp[1].matches("JAN"))||(temp[1].matches("MAR"))||(temp[1].matches("MAY"))||(temp[1].matches("JUL"))||(temp[1].matches("AUG"))||(temp[1].matches("OCT"))||(temp[1].matches("DEC")))
            {
                if(Integer.parseInt(temp[0])>31)
                {
                    return false;
                }
            }
        else if((temp[1].matches("APR"))||(temp[1].matches("JUN")||(temp[1].matches("SEP"))||(temp[1].matches("NOV"))))
            {
                if(Integer.parseInt(temp[0])>30)
                {
                    return false;
                }
            }
        else if(temp[1].matches("FEB")){
            {
                if(Integer.parseInt(temp[2])%4==0)
                {
                    if(Integer.parseInt(temp[0])>29)
                    {
                        return false;
                    }
                }
                else{
                    if(Integer.parseInt(temp[0])>28)
                    {
                        return false;
                    }
                }
            }
        }
        }
        }
        catch(ArrayIndexOutOfBoundsException ae)
        {
            
        }
        return true;
    }
    
    public String[] rejectIllegitimateDates(HashMap<String, ArrayList<String>> indiHashmap,HashMap<String, ArrayList<String>> famHashmap)
    {
        int r=0;
        String res[]=new String[256];
        System.out.println("\n\n=>Mohit Sprint 3 User Story 42: Reject Illegitimate Dates");
        for (String key : indiHashmap.keySet()) {
            indivalues=indiHashmap.get(key);
            boolean bdate=checkDate(indivalues.get(3));
            if(bdate==false)
            {
                System.out.println("ERROR: US42: Individual ID "+indivalues.get(0)+" has birth date "+indivalues.get(3)+" which is illegitimate");
                res[r++]=indivalues.get(0)+"\t"+indivalues.get(3);
            }
            boolean ddate=checkDate(indivalues.get(4));
            if(ddate==false)
            {
                System.out.println("ERROR: US42: Individual ID "+indivalues.get(0)+" has death date "+indivalues.get(4)+" which is illegitimate");
                res[r++]=indivalues.get(0)+"\t"+indivalues.get(4);
            }
        }
        for (String key : famHashmap.keySet()) {
            famvalues=famHashmap.get(key);
            boolean mdate=checkDate(famvalues.get(5));
            if(mdate==false)
            {
                System.out.println("ERROR: US42: Family ID "+famvalues.get(0)+" has marriage date "+famvalues.get(5)+" which is illegitimate");
                res[r++]=famvalues.get(0)+"\t"+famvalues.get(5);
            }
            boolean divdate=checkDate(famvalues.get(6));
            if(divdate==false)
            {
                System.out.println("ERROR: US42: Family ID "+famvalues.get(0)+" has divorce date "+famvalues.get(6)+" which is illegitimate");
                res[r++]=famvalues.get(0)+"\t"+famvalues.get(6);
            }
            
        }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
    
    public static List<String> ListOfUpcoming30Days(java.util.Date fromDate,
                                                             java.util.Date toDate) {
    List<String> listOfDates = new ArrayList();
    Calendar startC = Calendar.getInstance(Locale.ENGLISH);
    startC.setTime(fromDate);
    Calendar endC = Calendar.getInstance(Locale.ENGLISH);
    endC.setTime(toDate);
    while (startC.getTimeInMillis() <= endC.getTimeInMillis()){
        java.util.Date date = startC.getTime();
        listOfDates.add(new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date).trim());
        startC.add(Calendar.DATE, 1);
    }
    return listOfDates;
}
    
    public String[] listUpcomingBirthdays(HashMap<String, ArrayList<String>> indiHashmap)
    {
        int r=0;
        String res[]=new String[256];
        System.out.println("\n\n=>Mohit Sprint 4 User Story 38: List upcoming Birthdays");
        {
                Calendar c = Calendar.getInstance(Locale.ENGLISH);
                c.setTime(new Date());
                c.add(Calendar.DATE, 30);
                Date futureDate = c.getTime();
                List<String> next30Dates = ListOfUpcoming30Days(new Date(), futureDate);
            for (String key : indiHashmap.keySet()) 
            {
                indivalues=indiHashmap.get(key);
                String a=indivalues.get(3);
                if(a.matches("") || a.matches("NA"))
                {
                    continue;
                }
                String temp[]=a.split(" ");
                for (String dates: next30Dates )
                {
                    String temp2[]=dates.split(" ");
                    if(Integer.parseInt(temp[0])==Integer.parseInt(temp2[0]) && temp[1].matches(temp2[1].toUpperCase()) && indivalues.get(6).matches("True"))
                    {
                        System.out.println("Individual with ID "+indivalues.get(0)+ " is alive and has birthdate "+indivalues.get(3)+". Thus, birthday within the next 30 days");
                        res[r++]=indivalues.get(0)+"\t"+indivalues.get(3);
                        break;
                    }
                }
            } 
        }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
    
    public String[] listUpcomingAnniversaries(HashMap<String, ArrayList<String>> indiHashmap,HashMap<String, ArrayList<String>> famHashmap)
    {
        int r=0;
        String res[]=new String[256];
        System.out.println("\n\n=>Mohit Sprint 4 User Story 39: List upcoming anniversaries");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.setTime(new Date());
        c.add(Calendar.DATE, 30);
        Date futureDate = c.getTime();
        List<String> next30Dates = ListOfUpcoming30Days(new Date(), futureDate);
        for (String key : famHashmap.keySet()) 
            {
                famvalues=famHashmap.get(key);
                String a=famvalues.get(5);
                if(a.matches("") || a.matches("NA"))
                {
                    continue;
                }
                String b=famvalues.get(1);
                String d=famvalues.get(3);
                String temp[]=a.split(" ");
                boolean flag=true;
                for (String dates: next30Dates )
                {
                    String temp2[]=dates.split(" ");
                    for(String key2:indiHashmap.keySet())
                    {
                        if(b.matches(key2))
                        {
                            indivalues=indiHashmap.get(key2);
                            if(indivalues.get(6).matches("True"))
                            {
                                if(flag==false)
                                {
                                    flag=false;
                                }
                                else{
                                    flag=true;
                                }
                            }
                            if(indivalues.get(6).matches("False"))
                            {
                                flag=false;
                            }
                        }
                        if(d.matches(key2))
                        {
                            indivalues=indiHashmap.get(key2);
                            if(indivalues.get(6).matches("True"))
                            {
                                if(flag==false)
                                {
                                    flag=false;
                                }
                                else{
                                    flag=true;
                                }
                            }
                            if(indivalues.get(6).matches("False"))
                            {
                                flag=false;
                            }
                        }
                    }
                    if(famvalues.get(6).matches("") || famvalues.get(6).matches("NA"))
                    {
                        flag=true;
                    }
                    else{
                        flag=false;
                    }
                    if(Integer.parseInt(temp[0])==Integer.parseInt(temp2[0]) && temp[1].matches(temp2[1].toUpperCase()) && flag==true)// && indivalues.get(6).matches("True"))
                    {
                        System.out.println("Family with ID "+famvalues.get(0)+ " has a living married couple having marriage date "+famvalues.get(5)+". Thus, marriage anniversary within the next 30 days");
                        res[r++]=famvalues.get(0)+"\t"+famvalues.get(5);
                        break;
                    }
                }
            }
        String temp[]=new String[r];
        for(int l=0;l<r;l++)
        {
            temp[l]=res[l];
        }
        return temp;
    }
    
    
}
