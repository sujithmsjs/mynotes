/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.str;

import java.util.StringTokenizer;

/**
 *
 * @author sujit
 */
public class StrUtil {
    
    public static String replaceEverything(String text,String first, String last){
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer s = new StringTokenizer(text,first);
        while(s.hasMoreTokens()){
            String token = s.nextToken();
            sb = sb.append(token).append(last);
        }
        
        return sb.toString();
    }
    
}
