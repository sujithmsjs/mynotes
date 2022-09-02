package oracle.imgs;


public class NewMain {

    public static void main(String[] args) {
        int n = 500;
        int p = 30;
        int a = n/p;
        System.out.println("a = " + a);
        int a2 = a*p;
        System.out.println("a2 = " + a2);
        
        float m = 500;
        int q = 30;
        float qu = m/q;
        System.out.println("qu = " + qu);
        float qu2 = q*qu;
        System.out.println("qu2 = " + qu2);
        
        System.out.println(n/p);
        System.out.println(m/q);
        
        int people = 5;
        int val = 800;
        
        int part = val/7;
        
        System.out.println("Summary");
        for (int i = 1; i <= people; i++) {
            
            int ans = val*i/people;
            int sum = part*i;
            
            System.out.println("ans = " + ans);
            
            System.out.println("part = " + sum);
        }
    }

}
