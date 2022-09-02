


public class Test {

    public static void main(String[] args) {
        int n = 0;
        int topmost = 10;
        
        for (int i = 0; i < 100; i++) {
            n = (n + 1) % topmost;
           // n %= topmost;
            
            System.out.println(n);
        }
    }

}
