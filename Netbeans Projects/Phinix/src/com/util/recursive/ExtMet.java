package com.util.recursive;

import com.suji.cls.ClassMethods;
import static com.suji.cls.ClassMethods.ext;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtMet {
    
    public static Set<Met> getAllMethods(Class cls){
        Method[] mets = cls.getDeclaredMethods();
        List<Met> list = new ArrayList<>();
         
        for (Method method : mets) {
             Met met = getMet(method);
                list.add(met);
        }
        Set<Met> set = new HashSet<>(list);
        
        return set;
    }
    
    private static Met getMet(Method met) {
        String s = met.getGenericReturnType().getTypeName();
        Met m = new Met(ext(s),met.getName(),getParas(met),Modifier.toString(met.getModifiers()));
        return m;
    }
    
    private static String getParas(Method met) {
        StringBuilder sb = new StringBuilder();

        if (met.getParameterCount() > 0) {
            Type[] type = met.getGenericParameterTypes();

            for (Type t : type) {
                String s = t.getTypeName();

                sb.append(ext(s) + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
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
