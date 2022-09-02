package com.suji.generics;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface I {

    void ifI();
}

interface J {

    void ifJ();
}

class A implements I {

    void m1() {
    }

    void m2() {
    }

    @Override
    public void ifI() {
    }
}

class C {

}

class B extends A implements J {

    @Override
    public void ifJ() {
    }

    private void bpri() {

    }
}

public class MethodsTest {

    public static void main(String[] args) {
        Class c = ArrayList.class;
        Class sClass = c.getSuperclass();

        //All interfaces included.
        List<Class> classes = new ArrayList(Arrays.asList(c.getInterfaces()));
        classes.add(c.getSuperclass());

        for (Class cl : classes) {
            Method[] m = cl.getMethods();
            System.out.println(cl.getCanonicalName() + " isInterface: " + cl.isInterface());

            for (Method method : m) {
                
                System.out.println("\t"+method.getGenericReturnType()+"\t"+method.getName()+"("+extractGPrameters(method)+")");
            }
        }
    }

    
    public static String extractGPrameters(Method method) {
       Type[] type =  method.getGenericParameterTypes();
        StringBuilder st = new StringBuilder();
       for (Type type1 : type) {
            st.append(type1.getTypeName()+", ");
        }
        return st.toString();
    }
    
    public static String extractPrameters(Method method) {
        Pattern p = Pattern.compile("[.][^.\\s]*[(].*[)]");
        Matcher matt = p.matcher(method.toString());
        StringBuilder st = new StringBuilder();
        while (matt.find()) {
            st.append(matt.group()+",");
            st.deleteCharAt(0);
        }
        return st.toString();
    }
    
    public static String extractReturnType(Method method) {
        Type type = method.getGenericReturnType();
        System.out.println(type);
        Pattern p = Pattern.compile("[.][^.\\s]*[(].*[)]");
        Matcher matt = p.matcher(method.toString());
        StringBuilder st = new StringBuilder();
        while (matt.find()) {
            st.append(matt.group());
            st.deleteCharAt(0);
        }
        return st.toString();
    }
    

}
