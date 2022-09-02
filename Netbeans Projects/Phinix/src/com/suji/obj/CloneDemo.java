package com.suji.obj;

class D implements Cloneable{

    int n;

    public D(int n) {
        this.n = n;
    }
    
    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
}

public class CloneDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        D d = new D(65536);
        D d2 = (D) d.clone();
        System.out.println(d2.getN());
        System.out.println(d == d2);
            
    }

}
