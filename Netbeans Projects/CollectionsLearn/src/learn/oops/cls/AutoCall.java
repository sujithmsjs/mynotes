package learn.oops.cls;


public class AutoCall {

    public static void main(String[] args) {
        Tab5 t5 = new Tab5();
        t5.setTable(5);
        int sum = t5.getSum();
        System.out.println("sum = " + sum);
    }

}

class Tab5 extends Table{

    private int sum = 0;

    public int getSum() {
        return sum;
    }
     
    @Override
    public void getNext(int n) {
        sum+=n;
        System.out.println(n);
    }
    
}

abstract class Table{
    public abstract void getNext(int n);
    public void setTable(int n){
        for (int i = 1; i <= 10; i++) {
            getNext(n*i);
        }
    }
}