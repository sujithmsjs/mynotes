package learn.kws.fnl;

public class Rect {

    private int l, b;

    public Rect(int l, int b) {
        this.l = l;
        this.b = b;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getArea() {
        return l * b;
    }

    @Override
    public String toString() {
        return "Rect{" + "l=" + l + ", b=" + b + '}';
    }
    
    @Override
    public boolean equals(Object obj){
        final Rect rect = (Rect) obj;
        final Rect rect2 = this;
        
        if(rect.b!=rect2.b)
            return false;
        if(rect.l!=rect2.l)
            return false;
        return true;
    }
}
