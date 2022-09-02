package coll.pri;

import util.obj.Box;


public class Final_Use {

    
    
    public static void main(String[] args) {
        Outer out = new Outer();
        out.showZ();
        
    }

}

class Outer{ //Enclosed class.
    private int outVar = 12;
    
    public class Inner{ //Nested class.1
        private int z = 15;
        
        private void showA(){
            System.out.println(outVar);
        }
    }
    
    public void showZ(){
       Inner in = new Inner();
       System.out.println(in.z);
       in.showA();
    }
    
    
}