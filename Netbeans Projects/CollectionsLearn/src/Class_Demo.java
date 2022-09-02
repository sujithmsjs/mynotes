
import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import util.obj.ReadyData;


public class Class_Demo {

    public static void main(String[] args) {
        //Class.forName("java.lang.Scanner");
        Class<X> x = X.class;
        
        //Printes members
        Field[] fs = x.getDeclaredFields();
        System.out.println();
        ReadyData.printIt(fs);
        
        //Print methods.
        
        Method[] methods = x.getDeclaredMethods();
        System.out.println();
        ReadyData.printIt(methods);
        
        //Print Constructors
        Constructor[] constructors = x.getConstructors();
        System.out.println();
        ReadyData.printIt(constructors);
        
        String s = x.getCanonicalName();
        System.out.println("s = " + s);
    }

}


class Y{

    public Y() {
    }

    public Y(int y_pub) {
        this.y_pub = y_pub;
    }
    
    public Y(int y_pri, int y_pub) {
        this.y_pri = y_pri;
        this.y_pub = y_pub;
    }
    
    private int y_pri;
    public int y_pub;
    protected int y_pro;
    int y_def;
    
    public void y_m1(){}
    private void y_m2(){}
    protected void y_m3(){}
    void y_m4(){}
    
}

class X extends Y{
    
    
    public X() {
    }

    public X(int x_pub) {
        this.x_pub = x_pub;
    }

    public X(int x_pri, int x_pub) {
        this.x_pri = x_pri;
        this.x_pub = x_pub;
    }
    
    
    @Override
    public void y_m1(){}
    void y_m2(){}
    
    private int x_pri;
    public int x_pub;
    protected int x_pro;
    int x_def;
    

    public void x_m1(){}
    private void x_m2(){}

    protected void x_m4(){}
    
    void x_m5(){}
}


interface I{
    int i_m5();
    int i_m6();
    int i_m7();
    int i_m8();
}