package learn.kws.fnl;

public class NewMain {

    public static void main(String[] args) {
            final Rect r = new Rect(5,10);
            final Rect r2 = new Rect(10,20);

            r.setL(1);
            r.setB(200);

            B a = new A(8);
            a.show();
    }

}

class B{
    public void show(){
        System.out.println("Class B");
    }
}

class A extends B{
    
     static final int PI=2;

    public A(int p) {
        
    }


    @Override
    public void show(){
        final int n;
        n = 10;
        System.out.println(n);
    }
}

