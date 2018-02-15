package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class MananTest
{

    @Test
    public void testBirthBeforeDeath()
    {
        String res = "";
        System.out.println("birthBeforeDeath");

        Manan instance = new Manan();
        String testID[] =
        {
            "T1", "T2", "T3","T4","T5"
        };
        String testName[] =
        {
            "ABC", "DEF", "GHI","JKL","MNO"
        };
        String testGender[] =
        {
            "M", "M", "F","F","M"
        };
        String testBirth[] =
        {
            "09 FEB 2000", "25 FEB 2007", "15 SEP 1990","1 JAN 2017","17 MAY 2014"
        };
        String testDeath[] =
        {
            "08 JAN 1999", "12 MAR 2007", "14 SEP 1990","","18 JUN 2015"
        };
        String expected[] =
        {
            "INCORRECT", "INCORRECT", "INCORRECT","CORRECT","CORRECT"
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
            System.out.println("Expected Output: "+expected[i]);
            System.out.println("Actual Output :"+res);
            System.out.println();
            resArray[i] = res;
        }
        Assert.assertArrayEquals(expected, resArray);

    }

}
