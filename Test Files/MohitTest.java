/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mohit
 */
public class MohitTest {
    
    
//    famvalues.add(fam.getHusbID());
//    family.add(fam.getHusbName());
//    family.add(fam.getWifeID());
//    family.add(fam.getWifeName());
//    family.add(fam.getMarried());
//    family.add(fam.isDivorced());
    
    public MohitTest() {
    }

    /**
     * Test of birthBeforeMarriage method, of class Mohit.
     */
    //test for birth before death and test for marriage after 14
    @Test
    public void testBirthBeforeMarriageandMarriageAfter14() 
    {
        HashMap<String, ArrayList<String>> individual = new HashMap<>();
    HashMap<String, ArrayList<String>> family = new HashMap<>();
    ArrayList<String> famvalues=new ArrayList<>();
    ArrayList<String> indivalues=new ArrayList<>();
        Mohit m=new Mohit();
        String indid[]=new String[]{"I1","I2","I3","I4","I5","I6"};
        String indname[]=new String[]{"DAVID","JANICE","JON","PREETI","STEVE","SASHA"};
        String indgender[]=new String[]{"M","F","M","F","M","F"};
        String indbdate[]=new String[]{"1 JUN 1980","2 JUN 1979","3 JUL 2000","4 NOV 2000","21 JUL 2006","20 JUN 2007"};
        String indisalive[]=new String[]{"true","true","true","true","true","true"};
        String indddate[]=new String[]{"","","","","",""};
        String familyid[]=new String[]{"F1","F2","F3"};
        String husbid[]=new String[]{"I1","I3","I5"};
        String husbname[]=new String[]{"DAVID","JON","STEVE"};
        String wifeid[]=new String[]{"I2","I4","I6"};
        String wifename[]=new String[]{"JANICE","PREETI","SASHA"};
        String marrdate[]=new String[]{"1 JUN 1990","","20 JUL 2005"};
        String divdate[]=new String[]{"","15 JUL 2010",""};
        String expected1[]=new String[]{"valid","cannot say","invalid"};
        String expected2[]=new String[]{"invalid","cannot say","invalid"};
        String res[]=new String[]{};
        String res2[]=new String[]{};
        for(int i=0;i<6;i++)
        {
            //individual.clear();
           // indivalues.clear();
            indivalues.clear();
            indivalues.add(indid[i]);
            indivalues.add(indname[i]);
            indivalues.add(indgender[i]);
            indivalues.add(indbdate[i]);
            indivalues.add(indisalive[i]);
            indivalues.add(indddate[i]);
            
            individual.put(indid[i], new ArrayList(indivalues));
          
        }
        for(int i=0;i<3;i++)
        {
            famvalues.clear();
            famvalues.add(familyid[i]);
            famvalues.add(husbid[i]);
            famvalues.add(husbname[i]);
            famvalues.add(wifeid[i]);
            famvalues.add(wifename[i]);
            famvalues.add(marrdate[i]);
            famvalues.add(divdate[i]);
            family.put(familyid[i], new ArrayList(famvalues));
            
        }
        System.out.println("Individual entries:");
        for (String key : individual.keySet()) {
			//System.out.println(key);
			System.out.println(key+"\t"+individual.get(key));
		}
        System.out.println("Family entries:");
        for (String key : family.keySet()) {
			//System.out.println(key);
			System.out.println(key+"\t"+family.get(key));
		}
        
        res=m.birthBeforeMarriage(individual, family);
        res2=m.marriageAfter14(individual, family);
        Assert.assertArrayEquals( expected1, res);
        Assert.assertArrayEquals(expected2,res2);
        //Assert.assertEquals(expected1, res);
      
        
       //Assert.assertNotSame(new String[]{"valid","cannot say","invalid"}, m.birthBeforeMarriage(individual, family));
        //Assert.assertNotSame(new String[]{"invalid","cannot say","invalid"}, m.marriageAfter14(individual, family));
        Assert.assertNotNull(expected1);
        Assert.assertNotNull(res);
        Assert.assertNotNull(expected2);
        Assert.assertNotNull(res2);
    }
    //test for unique ids
    @Test
    public void testUniqueIDs()
    {
        Mohit m=new Mohit();
        String indid[]=new String[]{"I1","I2","I3","I1","I2","I6"};
        String familyid[]=new String[]{"F1","F2","F3","F4","F1","F3","F7"};
        String expected1[]=new String[]{"I1","I2","F1","F3"};
        String res[]=m.uniqueIDs(indid, familyid);
        Assert.assertArrayEquals(expected1,res);
    }
    //test for unique name and birthdate
    @Test
    public void testuniquenamebirthdate()
    {
        HashMap<String, ArrayList<String>> individual = new HashMap<>();
        ArrayList<String> indivalues=new ArrayList<>();
        Mohit m=new Mohit();
        String indid[]=new String[]{"I1","I2","I3","I4","I5","I6"};
        String indname[]=new String[]{"DAVID","JANICE","JON","PREETI","DAVID","SASHA"};
        String indgender[]=new String[]{"M","F","M","F","M","F"};
        String indbdate[]=new String[]{"1 JUN 1980","2 JUN 1979","3 JUL 2000","4 NOV 2000","1 JUN 1980","20 JUN 2007"};
        String indisalive[]=new String[]{"true","true","true","true","true","true"};
        String indddate[]=new String[]{"","","","","",""};
        
        for(int i=0;i<6;i++)
        {
            //individual.clear();
           // indivalues.clear();
            indivalues.clear();
            indivalues.add(indid[i]);
            indivalues.add(indname[i]);
            indivalues.add(indgender[i]);
            indivalues.add(indbdate[i]);
            indivalues.add(indisalive[i]);
            indivalues.add(indddate[i]);
            
            individual.put(indid[i], new ArrayList(indivalues));
          
        }
        String expected1[]=new String[]{"DAVID"+"\t"+"1 JUN 1980"};
        String res[]=m.uniquenamebirthdate(individual);
        //res2=m.marriageAfter14(individual, family);
        Assert.assertArrayEquals( expected1, res);
    }
    
    @Test
    public void testsiblingsShouldNotMarry()
    {
        Mohit m=new Mohit();
        String familyid[]=new String[]{"F1","F2","F3"};
        String husbid[]=new String[]{"I1","I3","I5"};
        String husbname[]=new String[]{"DAVID","JON","STEVE"};
        String wifeid[]=new String[]{"I2","I4","I6"};
        String wifename[]=new String[]{"JANICE","PREETI","SASHA"};
        String marrdate[]=new String[]{"1 JUN 1990","","20 JUL 2005"};
        String divdate[]=new String[]{"","15 JUL 2010",""};
        String childID[]=new String[]{"I3 I4 I5 I6","",""};
        String expected1[]=new String[]{"I3"+"\t"+"I4","I5\tI6"};
        HashMap<String, ArrayList<String>> family = new HashMap<>();
        ArrayList<String> famvalues=new ArrayList<>();
        for(int i=0;i<3;i++)
        {
            famvalues.clear();
            famvalues.add(familyid[i]);
            famvalues.add(husbid[i]);
            famvalues.add(husbname[i]);
            famvalues.add(wifeid[i]);
            famvalues.add(wifename[i]);
            famvalues.add(marrdate[i]);
            famvalues.add(divdate[i]);
            famvalues.add(childID[i]);
            family.put(familyid[i], new ArrayList(famvalues));
        }
        String res[]=m.siblingsShouldNotMarry(family);
        Assert.assertArrayEquals( expected1, res);
    }
    
    @Test
    public void testrejectIllegitimateDates()
    {
        HashMap<String, ArrayList<String>> individual = new HashMap<>();
        HashMap<String, ArrayList<String>> family = new HashMap<>();
        ArrayList<String> famvalues=new ArrayList<>();
        ArrayList<String> indivalues=new ArrayList<>();
        Mohit m=new Mohit();
        String indid[]=new String[]{"I1","I2","I3","I4","I5","I6"};
        String indname[]=new String[]{"DAVID","JANICE","JON","PREETI","STEVE","SASHA"};
        String indgender[]=new String[]{"M","F","M","F","M","F"};
        String indbdate[]=new String[]{"29 FEB 1981","29 FEB 1980","3 JUL 2000","4 NOV 2000","21 JUL 2006","20 JUN 2007"};
        String indisalive[]=new String[]{"true","true","true","true","true","true"};
        String indddate[]=new String[]{"","","","","",""};
        String familyid[]=new String[]{"F1","F2","F3"};
        String husbid[]=new String[]{"I1","I3","I5"};
        String husbname[]=new String[]{"DAVID","JON","STEVE"};
        String wifeid[]=new String[]{"I2","I4","I6"};
        String wifename[]=new String[]{"JANICE","PREETI","SASHA"};
        String marrdate[]=new String[]{"31 JUN 1990","","20 JUL 2005"};
        String divdate[]=new String[]{"","32 JUL 2010",""};
        String expected1[]=new String[]{"I1\t29 FEB 1981","F1\t31 JUN 1990","F2\t32 JUL 2010"};
        for(int i=0;i<6;i++)
        {
            indivalues.clear();
            indivalues.add(indid[i]);
            indivalues.add(indname[i]);
            indivalues.add(indgender[i]);
            indivalues.add(indbdate[i]);
            indivalues.add(indisalive[i]);
            indivalues.add(indddate[i]);
            individual.put(indid[i], new ArrayList(indivalues));
        }
        for(int i=0;i<3;i++)
        {
            famvalues.clear();
            famvalues.add(familyid[i]);
            famvalues.add(husbid[i]);
            famvalues.add(husbname[i]);
            famvalues.add(wifeid[i]);
            famvalues.add(wifename[i]);
            famvalues.add(marrdate[i]);
            famvalues.add(divdate[i]);
            family.put(familyid[i], new ArrayList(famvalues));
        }
        String res[]=m.rejectIllegitimateDates(individual,family);
        Assert.assertArrayEquals( expected1, res);
    }
    
}
