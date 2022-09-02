
public class Arrays {

    public static void main(String[] args) {
        int[] a; //Declaration
        a = new int[10]; //Instanciation.
    
        int b[] = {1,2,3,5};
        System.out.println(b); // [I@15db9742
        int c[] = null;
        float f[] = {1,2,4,6,7,6}; //Valid
        
        // c = {1,2,3,4}; Invalid.
        c = a; // Valid.
        c = b; // Valid.
        // c = f; incompatible types: float[] cannot be converted to int[]
        
        System.out.println(c);
        
        for (float n : f) {
            System.out.println(n);
        }
   
        
    }

}
