
public class Local_Block {

    /*
    A block defined inside a method, block, or constructor is called local block in Java.
    It is also called inner block in Java.
    */
    
    public static void main(String[] args) {
        int n = 10;
        
        A a = new A();
        A a2 = new A(5);
        A a3 = new A(5,10);
        // Note: Constructor logic is specific to an object but IIB logic is common for all objects.
        {
      //      int n =30; Can't create same name as outer block.
            int m = 20;
            System.out.println("Hey world");
        }
      //  System.out.println(m); they can be accessed only within that block.
        
    }

}

class A{
    private int n,m;
    
    static {
        /*
        Static block is used for initializing the static variables.
        This block gets executed when the class is loaded in the memory.
        A class can have multiple Static blocks, which will execute in the
        same sequence in which they have been written into the program.
        */
        System.out.println("Top This is static block");
    }

    
    {
        /*
        We can declare multiple instance initialization blocks inside a class.
        The order of the execution of instance block will always be towards the top to bottom.
        Non-Static bloack Execute during the object creation.
        */
        System.out.println("Top Non-static block");
    }
 
    {
        System.out.println("Bottom Non-static block");
    }
    
    static{
        System.out.println("Bottom This is static block");
    }
    public A() {
        System.out.println("This is 0 para constructor.");
    }

    public A(int n) {
        this.n = n;
        System.out.println("This is 1 para constructor.");
    }

    public A(int n, int m) {
        this.n = n;
        this.m = m;
        System.out.println("This is 2 para constructor.");
    }
    
    

    
    
    
}
