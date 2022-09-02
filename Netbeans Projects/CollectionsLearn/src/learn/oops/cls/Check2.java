package learn.oops.cls;

import java.util.*;


public class Check2 implements ArrayLoader {

    public static void main(String[] args) {
        
        Check2 c = new Check2();
        Functions f = new Functions(c);
        int[] a = f.doubleIt();
        for (int i : a) {
            System.out.println(i);
        }
    }

    @Override
    public void loadIt(int[] a) {
        System.out.println(a);
    }



}


class Functions{
    
    private int[] a;
    
    public Functions(ArrayLoader al) {
        a = new int[];
        al.loadIt(a);
    }
    
    public int[] doubleIt(){
        for (int i = 0; i < a.length; i++) {
            a[i] *= 2;
        }
        
        return a;
    }
    
    
    
    
    
}

interface ArrayLoader{
    public void loadIt(List a);  
}