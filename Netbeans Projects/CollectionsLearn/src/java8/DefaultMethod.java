package java8;

import java.lang.reflect.Method;


public class DefaultMethod {

    public static void main(String[] args) {

        Method m[] = Jumpable.class.getMethods();
        
        for (Method method : m) {
            System.out.println("method = " + method);
        }
        
        //<editor-fold defaultstate="collapsed" desc="Process 1">
        /* Jumpable t = new Jumpable() {
        @Override
        public void jump() {
        
        }
        };
        
        Method m4[] = t.getClass().getMethods();
        Method m2[] = Toad.class.getMethods();
        Method m[] = Frog.class.getMethods();
        Method m3[] = Jumpable.class.getMethods();
        
        
        
        for (Method method : m2) {
        
        System.out.println("method = " + method);
        }
        
        System.out.println(m);*/
        
//</editor-fold>
    }
    
    

}

class Lizard {
    public static void getColor(){
        System.out.println("getColor in Lizad");
    }
}
class Toad extends Lizard implements Jumpable, Rollable  {

    public static void getColor(){
        System.out.println("getColor in Toad");
    }
    
//     static void show(){
//          System.out.println("show in Jumpable Toad class");
//     }
//    
    @Override
    public void jump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void height() {
        Jumpable.super.height();
    }
    
}

//abstract class Frog implements Jumpable, Rollable{
//
//    void sayhello(){
//        
//    }
//    
//}

interface Jumpable{
   abstract void jump();
   default void height(){
       
   }
   static void show(){
       System.out.println("show in Jumpable interface");
   }
}

interface Rollable{
    default void height(){
        
    }
}

@FunctionalInterface
interface Barkable{
    void show();
}