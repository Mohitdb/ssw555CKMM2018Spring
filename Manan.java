/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GedcomParse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MANAN MANASVI
 */
public class Manan
{
    ArrayList<String> tempArrayList;
    
    public void birthBeforeDeath(HashMap<String,ArrayList<String>> hashIndi)
    {
        hashIndi.remove("");
        System.out.println("BIRTH\t\tDEATH\t\tCORRECT/INCORRECT");
        System.out.println("-----------\t-----------\t------------------");
        for(String key:hashIndi.keySet())
        {
            tempArrayList = hashIndi.get(key);
            
            if(tempArrayList.get(4)!="")
            {
               String bday[]=tempArrayList.get(3).split(" ");
               String dday[]=tempArrayList.get(4).split(" ");
               if(Integer.parseInt(bday[2])<=Integer.parseInt(dday[2]))
               {
                   if(Integer.parseInt(bday[2])==Integer.parseInt(dday[2]))
                   {
                       if(Integer.parseInt(bday[1])<=Integer.parseInt(dday[1]))
                       {
                           if(Integer.parseInt(bday[0])<=Integer.parseInt(dday[0]))
                           {
                               System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4)+"\tCORRECT");
                           }
                           else
                           {
                               System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4)+"\tINCORRECT");
                           }
                       }
                       else
                       {
                           System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4)+"\tINCORRECT");
                       }
                   }
                   else
                   {
                       System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4)+"\tCORRECT");
                   }
               }
               else
               {
                   System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4)+"\tINCORRECT");
               }
                //System.out.println(tempArrayList.get(3)+"\t"+tempArrayList.get(4));
                
            }
            else
                System.out.println(tempArrayList.get(3)+"\t"+"N/A"+"\t\tCORRECT");
        }
        
    }
}
