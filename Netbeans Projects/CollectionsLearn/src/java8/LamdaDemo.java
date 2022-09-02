package java8;

public class LamdaDemo {

    public static void main(String[] args) {
        Writable w = new Writable() {
            public void write() {
                System.out.println("Hey hello.");
            }
        };

        w.write();

        Writable w2;
        w2 = () -> System.out.println("Hellow lambda");

        w2.write();

        Killable k = (name) -> System.out.println("Hellow this is king " + name);
        Killable k2 = name -> System.out.println("Hellow this is king " + name);
        
        
        k.kill("Sujith");
        k.kill("Hellow");

        Adder a = (n, m) -> {
            System.out.println(n+m);
            return n+m;
        };
        a.add(4, 5);
        
        Adder a2 = (n,m) -> n+m;
        System.out.println(a2.add(10, 20));
        
        Adder a3 = (int n, int m) -> m+m;
        System.out.println(a3.add(20, 40));
    }

}

class Book {

}

@FunctionalInterface
interface Writable {

    public void write();
}

@FunctionalInterface
interface Killable {

    public void kill(String n);
}

@FunctionalInterface
interface Adder {

    public int add(int n, int m);
}
