package com.suji.obj;


class A{
    int n, m;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.n;
        hash = 83 * hash + this.m;
        return hash;
    }

    @Override
    public String toString() {
        return "A{" + "n=" + n + ", m=" + m + '}';
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false; 
        }
        final A other = (A) obj;
        if (this.n != other.n) {
            return false;
        }
        if (this.m != other.m) {
            return false;
        }
        return true;
    }

    

    
    
}

public class ObjectDemo {

    public static void main(String[] args) {
        
        A a = new A();
        A a2 = new A();
       
        System.out.println(a==a2);
        System.out.println(a.equals(a2));
        System.out.println(a.hashCode());
        System.out.println(a2);
        System.out.println(a.getClass());
        
        Class c = a.getClass();
        Class c2 = a2.getClass();
        System.out.println(c.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c == c2);
        
        
    }

}
