package learn.str;


public class NewMain {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.reverse(); //It will reverse the string
        System.out.println(sb);
        
        String str = "java hey java hellow, java is like a java coffee.java";
        
        int indexOf = str.indexOf("java", 28);
        System.out.println("indexOf = " + indexOf); // 'j' is at 12.
        char charAt = str.charAt(12);
        System.out.println("charAt = " + charAt);
        
        int count = wordOccurance(str, "java");
        System.out.println(count);
       
        String name = "  Suj   ith    \t";
        ///String spaceTrim = spaceTrim(name);
        removeDoubleSpace(name);
        
    }
    
    public static void removeDoubleSpace(String text){
        StringBuilder str  = new StringBuilder(text);
        int index = str.indexOf(" ", 0);
        int count = 0;
        
        while(index>-1){
            if(str.charAt(index+1)==' '){
                count++;
               // char charAt = str.charAt(index+1);
                System.out.println("Index = " + (index+1));
            }
                
            index = str.indexOf(" ", index+1);
        }
        
        System.out.println("count = " + count);
    }
    
    public static String spaceTrim(String str){
        
        
        
        
        return str.trim();
    }
    
    //Count the word occurence
    public static int wordOccurance(String str, String word){
        int index = str.indexOf(word, 0);
        int count = 0;
        while (index > -1) {
            count++;
            index = str.indexOf(word, index + 1);
        }
        return count;
    }

}
