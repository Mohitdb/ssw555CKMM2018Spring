
package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class MananTest
{
//    mananRefractored instance=new mananRefractored();
    Manan instance=new Manan();
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
}
