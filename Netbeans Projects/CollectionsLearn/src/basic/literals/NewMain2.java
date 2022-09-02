package basic.literals;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


public class NewMain2 {

    public static void main(String[] args) {
        
        String padded = String.format("%5s", "hey");
        String padded2 = String.format("%5s", "Hellow");
        String padded3 = String.format("%5s", "Hi there");
        
        System.out.println(padded);
        System.out.println(padded2);
        System.out.println(padded3);
       
    }
    
    public static String splitFixedLength(String str, int length) {
        Stack<String> stack = new Stack<String>();
        int l = str.length();
        int i = str.length();
        for (; i-length >= 0; i-=length) { 
            System.out.println(str.substring(i-length, i));
            stack.push(str.substring(i-length, i));
        }
        stack.push(str.substring(0, i));
        System.out.println(str.subSequence(0, i));
        
        StringBuilder output = new StringBuilder();
        while(!stack.isEmpty()){
            output.append(stack.pop()).append("_");
        }
        output.append("\b");
        return output.toString();
    }

}
