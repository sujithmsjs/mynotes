package learn.inherit;


public class NewMain1 {

    public static void main(String[] args) {
        /* X x = new X();
        x.plzCallShow();
        x.show();*/
        
        Y y = new Y();
       // y.show();
        y.xClassMethod();
    }

}

class X{
    void plzCallShow(){
        System.out.println("X plzCallShow method.");
        show();
    }
    
    void show(){
        System.out.println("X show method.");
    }
    
    void xClassMethod(){
        System.out.println("Call show from X");
        show();
    }
}

class Y extends X{
    
    @Override
    void show(){
        System.out.println("Y show method.");
    }
    
    @Override
    void plzCallShow(){
        System.out.println("Y plzCallShow method.");
        show();
    }
    
    void yClassMethod(){
        System.out.println("Call show from Y");
        show();
    }
}
