package com.suji.coll;


public class Box {

    private int m,n;

    public Box(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public Box(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "Box{" + "m=" + m + ", n=" + n + '}';
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Box other = (Box) obj;
        if (this.m != other.m) {
            return false;
        }
        if (this.n != other.n) {
            return false;
        }
        return true;
    }
    
    
    
    
}
