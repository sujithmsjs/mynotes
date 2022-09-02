package com.suji.cls;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface I<T>{ void i1(); void i2();}
interface J<T>{ void j1(); void j2();}

class A<T> implements I { void a1(){} void a2(){}

    @Override
    public void i1() {}
    
    @Override
    public void i2() {}
}

class B<T> extends A<T>{void b1(){} void b2(){}}
class C<T> extends B<T> implements J{void c1(int n, int m){} void c2(){}
    @Override
    public void j1() {}
    @Override
    public void j2() {}
}

public class ClassMethods {

    public static Set<Met> getMethods(Class cls) {
        //Class cls = Collection.class;
        
        Class sup = cls.getSuperclass();
        
        Set<Met> metsList = new HashSet<>();
        Method[] mets= cls.getDeclaredMethods();
        for (Method met : mets) {
            //System.out.println(met);
            String s = met.getGenericReturnType().getTypeName();
            Met m = new Met(ext(s),met.getName(),getParas(met),Modifier.toString(met.getModifiers()));
            metsList.add(m);
           // System.out.println(Modifier.toString(met.getModifiers())+" "+ m);
            
        }
        return  metsList;
    }

    private static String getParas(Method met) {
        StringBuilder sb = new StringBuilder();
        
        if(met.getParameterCount()>0){
            Type[] type = met.getGenericParameterTypes();
            
            for (Type t : type) {
                String s = t.getTypeName();
                
                sb.append( ext(s) +",");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
    
    public static String ext(String str){
        
        Pattern p = Pattern.compile("[.][^.]*$");
        Matcher m = p.matcher(str);
        StringBuilder out = new StringBuilder();
        if(m.find()){
            out.append(m.group());
            out.deleteCharAt(0);
        }else{
            out.append(str);
        }
        
        return out.toString();
    }
    

}
