/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author mohit
 */
public class Mohit {

    ArrayList<String> famvalues;
    ArrayList<String> indivalues;
    HashMap<String, Integer> monthmap = new HashMap<>();
        

    public String[] birthBeforeMarriage(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap) {
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
        boolean valid = false;
        String missing = "";
        indiHashmap.remove("");
        famHashmap.remove("");
        //String HBdate="";
        //String WBdate="";
        //String MDate="";
        String res[] = new String[famHashmap.size()];
        String MDate[] = new String[famHashmap.size()];
        String HBdate[] = new String[famHashmap.size()];
        String WBdate[] = new String[famHashmap.size()];
        String FId[] = new String[famHashmap.size()];
        System.out.println("Mohit Sprint 1 User Story 02:");
        System.out.println("FId\tMDate\t\tHBdate\t\tWBdate\t\tBirth before marriage");
        int i = 0;
        for (String key : famHashmap.keySet()) {
            famvalues = famHashmap.get(key);
            FId[i] = key;
            if (famvalues.get(5) == "") {
                MDate[i] = "\t" + famvalues.get(5);
            } else {
                MDate[i] = famvalues.get(5);
            }
            //MDate[i]=famvalues.get(5);
            //System.out.println(MDate[i]);
            for (String key2 : indiHashmap.keySet()) {
                indivalues = indiHashmap.get(key2);
                if (famvalues.get(1).equals(key2) || famvalues.get(3).equals(key2)) {
                    if (famvalues.get(1).equals(key2)) {
                        if (indivalues.get(3) == "") {
                            HBdate[i] = "\t" + indivalues.get(3);
                        } else {
                            HBdate[i] = indivalues.get(3);
                        }
                    }
                    if (famvalues.get(3).equals(key2)) {
                        if (indivalues.get(3) == "") {
                            WBdate[i] = "\t" + indivalues.get(3);
                        } else {
                            WBdate[i] = indivalues.get(3);
                        }
                    }
                }

            }
           
            i++;
        }
      

        for (int j = 0; j < i; j++) {
            if ((MDate[j].equals("\t" + "")) || (HBdate[j].equals("\t" + "")) || (WBdate[j].equals("\t" + ""))) {
                res[j] = "cannot say";
            } else {
                String mdate[] = MDate[j].split(" ");
                String hdate[] = HBdate[j].split(" ");
                String wdate[] = WBdate[j].split(" ");
                if (Integer.parseInt(mdate[2]) < Integer.parseInt(hdate[2])) {
                    res[j] = "invalid";
                } else if (Integer.parseInt(mdate[2]) == Integer.parseInt(hdate[2])) {
                    if (monthmap.get(mdate[1]) < monthmap.get(hdate[1])) {
                        res[j] = "invalid";
                    } else if (monthmap.get(mdate[1]) == monthmap.get(hdate[1])) {
                        if (Integer.parseInt(mdate[0]) <= Integer.parseInt(hdate[0])) {
                            res[j] = "invalid";
                        } else {
                            res[j] = "valid";
                        }
                    } else {
                        res[j] = "valid";
                    }
                } else {
                    res[j] = "valid";
                }

                if (res[j].equals("invalid")) {
                    res[j] = "invalid";
                } else {
                    if (Integer.parseInt(mdate[2]) < Integer.parseInt(wdate[2])) {
                        res[j] = "invalid";
                    } else if (Integer.parseInt(mdate[2]) == Integer.parseInt(wdate[2])) {
                        if (monthmap.get(mdate[1]) < monthmap.get(wdate[1])) {
                            res[j] = "invalid";
                        } else if (monthmap.get(mdate[1]) == monthmap.get(wdate[1])) {
                            if (Integer.parseInt(mdate[0]) <= Integer.parseInt(wdate[0])) {
                                res[j] = "invalid";
                            } else {
                                res[j] = "valid";
                            }
                        } else {
                            res[j] = "valid";
                        }
                    } else {
                        res[j] = "valid";
                    }
                }
                //System.out.println(MDate[]);
                
            }
            System.out.println(FId[j] + "\t" + MDate[j] + "\t" + HBdate[j] + "\t" + WBdate[j] + "\t" + res[j]);
        }
        return res;
    }
    public void marriageAfter14(HashMap<String, ArrayList<String>> indiHashmap, HashMap<String, ArrayList<String>> famHashmap)
    {
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
        String res[] = new String[famHashmap.size()];
        String MDate[] = new String[famHashmap.size()];
        String HBdate[] = new String[famHashmap.size()];
        String WBdate[] = new String[famHashmap.size()];
        String FId[] = new String[famHashmap.size()];
        System.out.println("Mohit Sprint 1 User Story 10:");
        System.out.println("FId\tMDate\t\tHBdate\t\tWBdate\t\tMarriage after 14");
        int i = 0;
        for (String key : famHashmap.keySet()) {
            famvalues = famHashmap.get(key);
            FId[i] = key;
            if (famvalues.get(5) == "") {
                MDate[i] = "\t" + famvalues.get(5);
            } else {
                MDate[i] = famvalues.get(5);
            }
            //MDate[i]=famvalues.get(5);
            //System.out.println(MDate[i]);
            for (String key2 : indiHashmap.keySet()) {
                indivalues = indiHashmap.get(key2);
                if (famvalues.get(1).equals(key2) || famvalues.get(3).equals(key2)) {
                    if (famvalues.get(1).equals(key2)) {
                        if (indivalues.get(3) == "") {
                            HBdate[i] = "\t" + indivalues.get(3);
                        } else {
                            HBdate[i] = indivalues.get(3);
                        }
                    }
                    if (famvalues.get(3).equals(key2)) {
                        if (indivalues.get(3) == "") {
                            WBdate[i] = "\t" + indivalues.get(3);
                        } else {
                            WBdate[i] = indivalues.get(3);
                        }
                    }
                }

            }
           
            i++;
        }
        
        for (int j = 0; j < i; j++) 
        {
            if ((MDate[j].equals("\t" + "")) || (HBdate[j].equals("\t" + "")) || (WBdate[j].equals("\t" + ""))) {
                res[j] = "cannot say";
            } 
            else 
            {
                String mdate[] = MDate[j].split(" ");
                String hdate[] = HBdate[j].split(" ");
                String wdate[] = WBdate[j].split(" ");
                if (Integer.parseInt(mdate[2]) < (Integer.parseInt(hdate[2])+14)) 
                {
                    res[j] = "invalid";
                } 
                else if (Integer.parseInt(mdate[2]) == (Integer.parseInt(hdate[2])+14)) {
                    if (monthmap.get(mdate[1]) < monthmap.get(hdate[1])) {
                            res[j] = "invalid";
                        }
                    else if (monthmap.get(mdate[1]) == monthmap.get(hdate[1])) {
                            if (Integer.parseInt(mdate[0]) <= Integer.parseInt(hdate[0])) {
                                res[j] = "invalid";
                            } else {
                                res[j] = "valid";
                            }
                        }
                    else
                    {
                        res[j]="valid";
                    }
                }
                else
                {
                    res[j]="valid";
                }
            
            if (res[j].equals("invalid")) {
                    res[j] = "invalid";
                }
            else
            {
                if (Integer.parseInt(mdate[2]) < (Integer.parseInt(wdate[2])+14)) 
                {
                    res[j] = "invalid";
                } 
                else if (Integer.parseInt(mdate[2]) == (Integer.parseInt(wdate[2])+14)) {
                    if (monthmap.get(mdate[1]) < monthmap.get(wdate[1])) {
                            res[j] = "invalid";
                        }
                    else if (monthmap.get(mdate[1]) == monthmap.get(wdate[1])) {
                            if (Integer.parseInt(mdate[0]) <= Integer.parseInt(wdate[0])) {
                                res[j] = "invalid";
                            } else {
                                res[j] = "valid";
                            }
                        }
                    else
                    {
                        res[j]="valid";
                    }
                }
                else
                {
                    res[j]="valid";
                }
            }
            }
            System.out.println(FId[j] + "\t" + MDate[j] + "\t" + HBdate[j] + "\t" + WBdate[j] + "\t" + res[j]);

        }
    }
}
