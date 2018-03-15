package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class MananTest
{
    
    Manan instance = new Manan();
    
    @Test
    public void testBirthBeforeDeath()
    {
        String res = "";
        System.out.println("==================Test for US03: Birth Before Marriage==================");

        String testID[] =
        {
            "T1", "T2", "T3",
        };
        String testName[] =
        {
            "ABC", "DEF", "GHI",
        };
        String testGender[] =
        {
            "M", "M", "F",
        };
        String testBirth[] =
        {
            "09 FEB 2000", "12 MAR 2007", "15 SEP 1990",
        };
        String testDeath[] =
        {
            "08 JAN 1999", "25 FEB 2007", "14 SEP 1990",
        };
        String expected[] =
        {
            "INCORRECT", "INCORRECT", "INCORRECT",
        };
        String[] resArray = new String[testID.length];
        HashMap<String, ArrayList<String>> hashIndi = new HashMap<>();
        ArrayList<String> tempArrayList = new ArrayList<>();
        for (int i = 0; i < testID.length; i++)
        {
            hashIndi.clear();
            tempArrayList.clear();
            tempArrayList.add(testID[i]);
            tempArrayList.add(testName[i]);
            tempArrayList.add(testGender[i]);
            tempArrayList.add(testBirth[i]);
            tempArrayList.add(testDeath[i]);
            hashIndi.putIfAbsent(testID[i], tempArrayList);
            res = instance.birthBeforeDeath(hashIndi);
            resArray[i] = res;
        }
        Assert.assertArrayEquals(expected, resArray);

    }

    @Test
    public void testLessThan150()
    {
        String resLessThan150 = "";
        System.out.println("==================Test for US07: lessThan150==================");
        FindAges fa = new FindAges();
        String testID[] =
        {
            "T1", "T2", "T3"
        };
        String testName[] =
        {
            "ABC", "DEF", "GHI"
        };
        String testGender[] =
        {
            "M", "M", "F"
        };
        String testBirth[] =
        {
            "9 FEB 1800", "12 MAR 1700", "1 JUN 1802"
        };
        String testDeath[] =
        {
            "8 JAN 2000", "1 JAN 2001", "",
        };

        // had to add this section of code in test file after refractoring
        String testAge[] = new String[testID.length];
        for (int i = 0; i < testBirth.length; i++)
        {
            Integer age = fa.FindAge(testBirth[i], testDeath[i]);
            testAge[i] = age.toString();
        }
        //end of section that needed to be added

        String expectedLT150[] =
        {
            "INCORRECT", "INCORRECT", "INCORRECT"
        };
        String[] resArrayLT150 = new String[testID.length];
        HashMap<String, ArrayList<String>> hashIndi = new HashMap<>();
        ArrayList<String> tempArrayList = new ArrayList<>();
        for (int i = 0; i < testID.length; i++)
        {
            hashIndi.clear();
            tempArrayList.clear();
            tempArrayList.add(testID[i]);
            tempArrayList.add(testName[i]);
            tempArrayList.add(testGender[i]);
            tempArrayList.add(testBirth[i]);
            tempArrayList.add(testDeath[i]);
            tempArrayList.add(testAge[i]); //this line needed to be added
            hashIndi.putIfAbsent(testID[i], tempArrayList);
            resLessThan150 = instance.lessThan150(hashIndi);
            resArrayLT150[i] = resLessThan150;
        }
        Assert.assertArrayEquals(expectedLT150, resArrayLT150);

    }

    @Test
    public void testMariageBeforeDivorce()
    {
        ArrayList<String> res = new ArrayList<>();
        System.out.println("==================Test for US04: Marriage Before Divorce==================");
        HashMap<String, ArrayList<String>> hashFam = new HashMap<>();
        ArrayList<String> tempArrayList1 = new ArrayList<>();
        ArrayList<String> tempArrayList2 = new ArrayList<>();
        ArrayList<String> tempArrayList3 = new ArrayList<>();
        
        tempArrayList1.add("F1");
        tempArrayList1.add("5");
        tempArrayList1.add("E");
        tempArrayList1.add("6");
        tempArrayList1.add("F");
        tempArrayList1.add("09 FEB 2000");
        tempArrayList1.add("08 JAN 1999");
        hashFam.put("F1", tempArrayList1);
        
        tempArrayList2.add("F2");
        tempArrayList2.add("9");
        tempArrayList2.add("W");
        tempArrayList2.add("1");
        tempArrayList2.add("M");
        tempArrayList2.add("12 MAR 2007");
        tempArrayList2.add("25 FEB 2007");
        hashFam.put("F2", tempArrayList2);
        
        tempArrayList3.add("F3");
        tempArrayList3.add("7");
        tempArrayList3.add("T");
        tempArrayList3.add("13");
        tempArrayList3.add("K");
        tempArrayList3.add("15 SEP 1990");
        tempArrayList3.add("14 SEP 1990");
        hashFam.put("F3", tempArrayList3);

        ArrayList<String> expected=new ArrayList<>();
        expected.add("INCORRECT");
        expected.add("INCORRECT");
        expected.add("INCORRECT");
        res=instance.mariageBeforeDivorce(hashFam);
        Assert.assertEquals(expected, res);
        
    }

    @Test
    public void testSiblingsByAge()
    {
        HashMap<String, ArrayList<String>> hashIndi = new HashMap<>();
        ArrayList<String> tempArrayList1 = new ArrayList<>();
        ArrayList<String> tempArrayList2 = new ArrayList<>();
        ArrayList<String> tempArrayList3 = new ArrayList<>();
        ArrayList<String> tempArrayList4 = new ArrayList<>();
        ArrayList<String> tempArrayList5 = new ArrayList<>();
        ArrayList<String> tempArrayList6 = new ArrayList<>();
        ArrayList<String> tempArrayList7 = new ArrayList<>();
        
        tempArrayList1.add("1");
        tempArrayList1.add("A");
        tempArrayList1.add("M");
        tempArrayList1.add("");
        tempArrayList1.add("");
        tempArrayList1.add("6");
        hashIndi.put("1", tempArrayList1);

        tempArrayList2.add("2");
        tempArrayList2.add("B");
        tempArrayList2.add("M");
        tempArrayList2.add("");
        tempArrayList2.add("");
        tempArrayList2.add("20");
        hashIndi.put("2", tempArrayList2);

        tempArrayList3.add("3");
        tempArrayList3.add("C");
        tempArrayList3.add("M");
        tempArrayList3.add("");
        tempArrayList3.add("");
        tempArrayList3.add("6");
        hashIndi.put("3", tempArrayList3);

        tempArrayList4.add("4");
        tempArrayList4.add("D");
        tempArrayList4.add("M");
        tempArrayList4.add("");
        tempArrayList4.add("");
        tempArrayList4.add("11");
        hashIndi.put("4", tempArrayList4);

        tempArrayList5.add("5");
        tempArrayList5.add("E");
        tempArrayList5.add("M");
        tempArrayList5.add("");
        tempArrayList5.add("");
        tempArrayList5.add("50");
        hashIndi.put("5", tempArrayList5);

        tempArrayList6.add("6");
        tempArrayList6.add("F");
        tempArrayList6.add("F");
        tempArrayList6.add("");
        tempArrayList6.add("");
        tempArrayList6.add("45");
        hashIndi.put("6", tempArrayList6);

        tempArrayList7.add("F1");
        tempArrayList7.add("5");
        tempArrayList7.add("E");
        tempArrayList7.add("6");
        tempArrayList7.add("F");
        tempArrayList7.add("20 DEC 1996");
        tempArrayList7.add("");
        tempArrayList7.add("1 2 3 4");
        HashMap<String, ArrayList<String>> hashFam = new HashMap<>();
        hashFam.put("F1", tempArrayList7);

        int[] expected =
        {
            20, 11, 6, 6
        };
        int[] ageFromFunction =
        {
        };
        ageFromFunction = instance.siblingsByAge(hashFam, hashIndi);
        Assert.assertArrayEquals(ageFromFunction, expected);
    }
    
    @Test
    public void testMalesLastNames()
    {
        HashMap<String, ArrayList<String>> hashIndi = new HashMap<>();
        ArrayList<String> tempArrayList1 = new ArrayList<>();
        ArrayList<String> tempArrayList2 = new ArrayList<>();
        ArrayList<String> tempArrayList3 = new ArrayList<>();
        ArrayList<String> tempArrayList4 = new ArrayList<>();
        ArrayList<String> tempArrayList5 = new ArrayList<>();
        ArrayList<String> tempArrayList6 = new ArrayList<>();
        ArrayList<String> tempArrayList7 = new ArrayList<>();
        ArrayList<String> tempArrayList8 = new ArrayList<>();
        
        tempArrayList1.add("1");
        tempArrayList1.add("Mahesh Shah");
        tempArrayList1.add("M");
        tempArrayList1.add("");
        tempArrayList1.add("");
        tempArrayList1.add("");
        hashIndi.put("1", tempArrayList1);

        tempArrayList2.add("2");
        tempArrayList2.add("Madhav Shah");
        tempArrayList2.add("M");
        tempArrayList2.add("");
        tempArrayList2.add("");
        tempArrayList2.add("");
        hashIndi.put("2", tempArrayList2);

        tempArrayList3.add("3");
        tempArrayList3.add("Mohak Mehta");
        tempArrayList3.add("M");
        tempArrayList3.add("");
        tempArrayList3.add("");
        tempArrayList3.add("");
        hashIndi.put("3", tempArrayList3);

        tempArrayList4.add("4");
        tempArrayList4.add("Adam Doshi");
        tempArrayList4.add("M");
        tempArrayList4.add("");
        tempArrayList4.add("");
        tempArrayList4.add("11");
        hashIndi.put("4", tempArrayList4);

        tempArrayList5.add("5");
        tempArrayList5.add("Jake Doshi");
        tempArrayList5.add("M");
        tempArrayList5.add("");
        tempArrayList5.add("");
        tempArrayList5.add("50");
        hashIndi.put("5", tempArrayList5);

        tempArrayList6.add("6");
        tempArrayList6.add("Smith Williams");
        tempArrayList6.add("M");
        tempArrayList6.add("");
        tempArrayList6.add("");
        tempArrayList6.add("");
        hashIndi.put("6", tempArrayList6);
        
        tempArrayList7.add("F1");
        tempArrayList7.add("1");
        tempArrayList7.add("Mahesh Shah");
        tempArrayList7.add("");
        tempArrayList7.add("");
        tempArrayList7.add("");
        tempArrayList7.add("");
        tempArrayList7.add("2 3");
        HashMap<String, ArrayList<String>> hashFam = new HashMap<>();
        hashFam.put("F1", tempArrayList7);
        
        tempArrayList8.add("F2");
        tempArrayList8.add("4");
        tempArrayList8.add("Adam Doshi");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("5 6");
        hashFam.put("F2", tempArrayList7);
        
        ArrayList<String> actual=instance.maleLastNames(hashFam, hashIndi);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CORRECT");
        expected.add("INCORRECT");
        expected.add("CORRECT");
        expected.add("INCORRECT");

        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testUniqueFirstNames()
    {
        HashMap<String, ArrayList<String>> hashIndi = new HashMap<>();
        ArrayList<String> tempArrayList1 = new ArrayList<>();
        ArrayList<String> tempArrayList2 = new ArrayList<>();
        ArrayList<String> tempArrayList3 = new ArrayList<>();
        ArrayList<String> tempArrayList4 = new ArrayList<>();
        ArrayList<String> tempArrayList5 = new ArrayList<>();
        ArrayList<String> tempArrayList6 = new ArrayList<>();
        ArrayList<String> tempArrayList7 = new ArrayList<>();
        ArrayList<String> tempArrayList8 = new ArrayList<>();
        ArrayList<String> tempArrayList9 = new ArrayList<>();
        
        tempArrayList1.add("1");
        tempArrayList1.add("Mahesh Shah");
        tempArrayList1.add("M");
        tempArrayList1.add("");
        tempArrayList1.add("");
        tempArrayList1.add("");
        hashIndi.put("1", tempArrayList1);

        tempArrayList2.add("2");
        tempArrayList2.add("Madhav Shah");
        tempArrayList2.add("M");
        tempArrayList2.add("1 FEB 1996");
        tempArrayList2.add("");
        tempArrayList2.add("");
        hashIndi.put("2", tempArrayList2);

        tempArrayList3.add("3");
        tempArrayList3.add("Madhav Shah");
        tempArrayList3.add("M");
        tempArrayList3.add("2 FEB 1996");
        tempArrayList3.add("");
        tempArrayList3.add("");
        hashIndi.put("3", tempArrayList3);

        tempArrayList4.add("4");
        tempArrayList4.add("Adam Doshi");
        tempArrayList4.add("M");
        tempArrayList4.add("");
        tempArrayList4.add("");
        tempArrayList4.add("");
        hashIndi.put("4", tempArrayList4);

        tempArrayList5.add("5");
        tempArrayList5.add("Jake Blanc");
        tempArrayList5.add("M");
        tempArrayList5.add("25 AUG 2001");
        tempArrayList5.add("");
        tempArrayList5.add("");
        hashIndi.put("5", tempArrayList5);

        tempArrayList6.add("6");
        tempArrayList6.add("Smith Williams");
        tempArrayList6.add("M");
        tempArrayList6.add("");
        tempArrayList6.add("");
        tempArrayList6.add("45");
        hashIndi.put("6", tempArrayList6);
        
        tempArrayList7.add("7");
        tempArrayList7.add("Jake Blanc");
        tempArrayList7.add("M");
        tempArrayList7.add("25 AUG 2001");
        tempArrayList7.add("");
        tempArrayList7.add("");
        hashIndi.put("7", tempArrayList7);
        
        tempArrayList8.add("F1");
        tempArrayList8.add("1");
        tempArrayList8.add("Mahesh Shah");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("");
        tempArrayList8.add("2 3");
        HashMap<String, ArrayList<String>> hashFam = new HashMap<>();
        hashFam.put("F1", tempArrayList8);
        
        tempArrayList9.add("F2");
        tempArrayList9.add("4");
        tempArrayList9.add("Adam Doshi");
        tempArrayList9.add("");
        tempArrayList9.add("");
        tempArrayList9.add("");
        tempArrayList9.add("");
        tempArrayList9.add("5 6 7");
        hashFam.put("F2", tempArrayList9);
        
        ArrayList<String> actual=instance.uniqueFirstNames(hashFam, hashIndi);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CORRECT");
        expected.add("INCORRECT");
        Assert.assertEquals(expected, actual);
    }

}
