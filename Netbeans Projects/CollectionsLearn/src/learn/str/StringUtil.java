package learn.str;

public class StringUtil {

    public static void main(String[] args) {
       


        
    }

    private static String removeDubpicateSpaces(String str) {
        StringBuilder sb = new StringBuilder(str);
        int in = sb.indexOf(" ", 0);
        while (in > -1) {
            if (sb.length() > in + 1) {
                char charAt = sb.charAt(in + 1);
                if (charAt == ' ') {
                    sb.deleteCharAt(in);
                } else {
                    in = sb.indexOf(" ", in + 1);
                }
            }else{
                break;
            }
        }

        return sb.toString();

    }

    private static String titilize(String title) {
        String text = title.toUpperCase();
        int l = text.length();
        int pad = 3;

        
        String head = pad(text, '*', pad);
        String line = padEnd("", '*',head.length());
        
         return line+"\n"+head+"\n"+line;
    }
    
    public static String pad(String str,char ch,int length){
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < length; i++) {
            sb.append(ch);
            sb.insert(0,ch);
        }
        return sb.toString();
    }
    
    public static String padEnd(String str,char ch,int length){
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < length; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
