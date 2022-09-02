// Java program to demonstrate getMethods() method

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Methods_1 {

    public static void main(String[] args)
            throws ClassNotFoundException {

        // returns the Class object for this class
        //Scanner scan = new Scanner(System.in);
        
        String className = "java.util.Queue";
        Class myClass = Class.forName(className);

        Method[] methods = myClass.getMethods();
        
        
        Method[] declaredMethods = myClass.getDeclaredMethods();
        for (Method m : declaredMethods) {
            
           // System.out.println(m.getName()+"\t"+m.getDefaultValue()+"\t"+m.getReturnType()+"\t Paramters:"+m.getParameterCount()+"\t "+Arrays.toString(parameters));
           // System.out.println(m.getDefaultValue());
        }
        
    }
}
