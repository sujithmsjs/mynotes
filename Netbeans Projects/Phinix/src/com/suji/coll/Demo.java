package com.suji.coll;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;


public class Demo {

    public static void main(String[] args) {
        Random r = new Random();
        
        Set<Std>  s1 = new HashSet<>();
        Set<Std>  s2 = new HashSet<>();
        Set<Std>  s3 = new TreeSet<>();
        
        for (int i = 0; i < 20; i++) {
            
            Std s = new Std(r.nextInt(10)+1,r.nextInt(10)+1,r.nextInt(10)+1);
            System.out.println(i+": "+s1.add(s));
        }
        for (int i = 5; i < 20; i++) {
            Std s = new Std(r.nextInt(10)+1,r.nextInt(10)+1,r.nextInt(10)+1);
            System.out.println(i+": "+s1.add(s));
        }
        
        //Intersection Sets.
        System.out.println(s1);
        System.out.println(s2);
        
       // s1.retainAll(s2);
       // System.out.println(s1); 
        
        s3.addAll(s1);
        
        for (Std std : s3) {
            System.out.println(std);
        }
        System.out.println("Hey");
    }

}

class Std implements Comparable<Std>{
    private int mat, sci, eng;

    public Std(int mat, int sci, int eng) {
        this.mat = mat;
        this.sci = sci;
        this.eng = eng;
    }


    @Override
    public boolean equals(Object obj) {
        
        System.out.println("Equals Method.");
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Std other = (Std) obj;
        if (this.mat != other.mat) {
            return false;
        }
        if (this.sci != other.sci) {
            return false;
        }
        if (this.eng != other.eng) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Std{" + "mat=" + mat + ", sci=" + sci + ", eng=" + eng + '}';
    }

    @Override
    public int compareTo(Std std) {
        if(mat > std.mat){
            return mat - std.mat;
        }
        if(sci > std.sci){
            return  sci - std.sci +(mat - std.mat);
        }
        if(eng > std.eng){
            return  (eng - std.eng) + (sci - std.sci) +(mat - std.mat);
        }
        return 0;
    }

    
    
    
    
    
}