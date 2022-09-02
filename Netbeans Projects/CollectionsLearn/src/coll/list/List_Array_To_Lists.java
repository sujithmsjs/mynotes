package coll.list;

import coll.util.CPrinter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import util.obj.ReadyData;

public class List_Array_To_Lists {

    public static void main(String[] args) {   
        
        //Getting data
        ArrayList<String> arrayList = ReadyData.getStringAL(10);
        
        //Converting List to Array.
        String[] strings = arrayList.toArray(new String[]{});
        
        //Again converting Array to List.
        List<String> asList = Arrays.asList(strings);
        
        System.out.println("asList = " + asList.getClass());
        
        //Adding values to ArrayList from list.
        ArrayList al = new ArrayList();
        al.addAll(asList);
        System.out.println(al);
        
        //Adding values to Vector from list.
        Vector v = new Vector();
        v.addAll(asList);
        System.out.println(v);
        
        //Addint Vector list and ArrayList
        v.addAll(al);   
        System.out.println("v after ading al = " + v);
        
        //Sending List throught constructors.
        ArrayList al2 = new ArrayList(asList);
        Vector vet = new Vector(asList);
        
        //Printing data.
        System.out.println("al.getClass().getName() = " + al.getClass().getName());
        CPrinter.printAL(al);
        System.out.println(vet);
        
    }
}
