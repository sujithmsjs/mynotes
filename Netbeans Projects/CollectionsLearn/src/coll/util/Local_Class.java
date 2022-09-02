package coll.util;


public class Local_Class {

    public static void main(String[] args) {
        Cube c = new Cube();
        Cube.Box allAreas = c.allAreas();
     //   Cube.Box b3 = new Cube.Box(); An enclosing instance Cube.Box is required.
    }

}

class Box{
    String name;
}

class Cube{
    int n = 10;
    int m = 20;
    int p = 30;
    
    /*
    Class Box and Cube.Box both are different.
    Box is the general class where as Cube.Box is the inner class of the Cude.
    And create Box class in Cube, it always give priority to the Cube.Box class.
    To avoid this ambiguity, we have to mention all the reference of the class along with package.
    */
    class Box{
        int box = 100;
    }
    
    class Rect{
        int box = 100;
    }
    
    /*
    Need to add Full name in order to avoid ambiguity.
    */
    public coll.util.Box getGeneralBox(){
        coll.util.Box b = new coll.util.Box();
        return b;
    }
    
    public Box getInnerBox(){
        Box b = new Box();
        return b;
    }
    
    public Box allAreas(){
        // Rule: Local variable can't be private, public or protected.
        class Box{            
            private int n, m;
            public Box(int n, int m) {
                this.n = n;
                this.m = m;
            }
            private void printArea(){
                System.out.println("Area: "+m*n);
            }
        }
        
        
        Box b = new Box(n,m);
        Box b2 = new Box(m,p);
        // Cube.Box b3 = new Box(3,2); Both are different Box varables not compatable with each other.
        // We can access to private varables outside a Local class.
        
        // Note: Cube.Box b3 = new Cube.Box(); An enclosing instance Cube.Box is required.
        // Note: Use class name to ambiguity between Inner and Outer class varables.
        Cube.Box b3 = new Cube.Box();
        Rect r = new Rect();
       
        b.printArea();
        b2.printArea();
        
        return b3;
    }
}
