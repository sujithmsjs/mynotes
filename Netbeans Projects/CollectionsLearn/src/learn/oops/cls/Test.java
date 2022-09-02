package learn.oops.cls;

public class Test {
    public static void main(String[] args) {
        X x = new X();
        x.work();
        int sum = x.getSum();
        System.out.println("sum = " + sum);
    }
}

class X extends Mobile {

    int sum;
    
    int getSum(){
       return sum; 
    }
    
    public void work() {
        setSumInts(20, 29);
    }

    @Override
    void loadSum(int n) {
        sum = n;
        System.out.println("Addition has done in super calls and result sent here as parameter: "+n);
    }

}

abstract class Mobile {

    public void setSumInts(int n, int m) {
        loadSum(n + m);
    }
    
    abstract void loadSum(int n);
}
