package learn.oops.cls;

import java.util.*;


public class ReferenceDemo {
   
    private static Doo fd, ld;
    
    public static void main(String[] args) {
       @SuppressWarnings("unchecked")
       List<D> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new D(new Doo(i)));
        }
        
        fd = list.get(0).n;
        ld = list.get(list.size()-1).n;
        
        if(fd.n==1){
            return;
        }
        
        modify();
        
        System.out.println(list);
        System.out.println("f: "+fd.n+";  l:"+ld.n);
    }

    private static void modify() {
        fd.n = 10;
        ld.n = 100;
    }

}

class D{
    Doo n;

    public D(Doo n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "D{" + "n=" + n + '}';
    }
}



class Doo{
    int n;

    public Doo(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Doo{" + "n=" + n + '}';
    }
    
}