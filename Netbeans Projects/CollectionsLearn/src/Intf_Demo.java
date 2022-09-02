public class Intf_Demo {

    public static void main(String[] args) {

        Fly f = new Dove();
        System.out.println(f);
        /*
            f.getWings(); illegal static interface method call.
            Static methods in interface should called by class name only.
        */      
        Dove d = (Dove) f;
        Dove.show();
        
        //Static methods can't be orverridden.
        Fly.getWings();
        Dove.getWings();
        /*
        Class<Fly> fly = Fly.class;
        boolean aInterface = fly.isInterface();
        System.out.println("aInterface = " + aInterface);

        // Printing Fields
        Field[] fs = fly.getDeclaredFields();
        ReadyData.printIt(fs);

        //printing Methods.
        Method[] ms = fly.getDeclaredMethods();
        ReadyData.printIt(ms);
         */
    }

}

class Dove implements Fly {

    @Override
    public boolean hasWinds() {
        return true;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    public static void show(){
        System.out.println("hehe, I am dove");
    }
    @Override
    public int getWindsCount() {
        return 2;
    }

    static void getWings() {
        System.out.println("Non static methods of the Dove class.");
    }

}

interface Fly {

    // Members are by default public static final 
    int WINGS = 4;

    /*
    we need not to implement this method in the implementation class Example.
    Methods are by default public abstract
     */
    // public default boolean Fly.hasWinds()
    default boolean hasWinds() {
        return true;
    }

    //public abstract int getSpeed()
    int getSpeed();

    //public abstract int Fly.getWindsCount()
    int getWindsCount();

    //public static void Fly.getWinds()
    static void getWings() {
        /*
         We cannot override Static methods in the implementation classes.
         Static methods in the interface can access static final members.
         */
        System.out.println("This is the static methods of interface");
    }
}
