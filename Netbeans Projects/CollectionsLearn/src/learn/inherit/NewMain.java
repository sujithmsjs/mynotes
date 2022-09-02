package learn.inherit;


public class NewMain extends Adder {

    public static void main(String[] args) {
        Adder a = new NewMain();
        //a.add(34, 34);
        a.receiveTable(5);
    }

    @Override
    public void writeTable(int[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(args[i]);
        }
    }
    
    
    @Override
    public void getSum(int n) {
        System.out.println("Sum is sent back to us : "+n);
    }

}


abstract class Adder{
    int sum;
    int[] tab = new int[10];
    public abstract void getSum(int n);
    
    public abstract void writeTable(int[] args);
    
    public void receiveTable(int n){
        for (int i = 0; i < 10; i++) {
            tab[i] = (i+1)*n;
        }
        writeTable(tab);
    }
    
    public void add(int n, int m){
        sum = n+m;
        getSum(sum);
    }
}