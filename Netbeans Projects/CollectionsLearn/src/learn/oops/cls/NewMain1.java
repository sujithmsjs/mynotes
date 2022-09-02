package learn.oops.cls;
/*
Constructors are executed from top most class to this class. present class has
to call Super class constructor implicitly or explectly.
*/

public class NewMain1 {

    public static void main(String[] args) {
        //Ananymous class.
        A a = new A(5) {
            @Override
            String getSomething() {
               return "It's somethings.";
            }
        };   
        a.showMe();
    }
}

class B extends A{

    public B(int n) {
        super(n);
        System.out.println("Subclass constructor.");
    }

    @Override
    String getSomething() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

class C{

    public C() {
        System.out.println("class C constructor.");
    }
    
}

abstract class  A extends C implements I7 {
    private int n;

    public A(int n) {
        this.n = n;
        System.out.println("Super/Abstract constructor.");
    }
    
    @Override
    public void showMe() {
          System.out.println("Abstract class default implementation.");      
    }
    
    abstract String getSomething();

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "A{" + "n=" + n + '}';
    }
}

interface I7{
    void showMe();
}