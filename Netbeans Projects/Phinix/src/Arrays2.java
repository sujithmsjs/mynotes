
public class Arrays2 {

    public static void main(String[] args) {
        int a[][] = {}; // Valid
        
        /*
        int b[][] = {1, 2, 3, 4};  Incompatible types : int cannot be converted to int[]
        int c[][] = {{}, {}, {}};Valid
        int d[][] = {{1, 2, 3,}};Valid
        int e[][] = {{1, 2, 3,},}; Valid
         */
        
        int e[][] = {{1, 2, 3,}, {1, 2, 3, 6, 7}, {1, 2,}};
        System.out.println("Jagged Array");
        printDoubleArray(e);
        
        /*
            int f[][] = new int[2][2]; Valid
        */
        int f[][] = new int[2][2]; 
        f[0][0] = 1;
        f[0][1] = 2;
        f[1][0] = 3;
        f[1][1] = 4;   
        System.out.println("Double Array");
        printDoubleArray(f);

        /*
        int h[][] = new int[][3]; Invalid
        int i[][] = new int[0][3];
        System.out.println(i[0][1]); ArrayIndexOutOfBoundsException
        */
        
        //Jagged Array
        int g[][] = new int[3][]; //Valid
        g[0] = new int[2];
        g[1] = new int[5];
        g[2] = new int[3];
        System.out.println("Jagged Array 2");
        printDoubleArray(g);
        
        
       
        
        
        
    }
    
    
    
    
    
    
    public static void printDoubleArray(int[][] a){
        for (int[] is : a) {
            for (int i : is) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
