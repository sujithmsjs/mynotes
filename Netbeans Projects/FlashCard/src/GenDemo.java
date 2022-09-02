
public class GenDemo {

    public static <E> E get(E a) {
        System.out.println(a);
        return a;
    }

    public static int get(int a) {
        System.out.println(a);
        return a;
    }

    public static Integer get(Integer a) {
        System.out.println(a);
        return a;
    }
}

class GenChild extends GenDemo{
    public static int get(int a) {
        System.out.println(a);
        return a;
    }
}
