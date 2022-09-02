
import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        Vector<String> v = new Vector();
        v.add("Ele 1");
        v.add("Ele 2");
        v.add("Ele 3");
        v.add("Ele 4");
        String[] str = v.toArray(new String[]{});
        System.out.println(str.length);
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }
    
}
