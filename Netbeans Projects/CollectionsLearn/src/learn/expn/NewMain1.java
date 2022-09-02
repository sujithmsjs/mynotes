package learn.expn;


public class NewMain1 {

    public static void main(java.lang.String[] args) {
        Object obj = "Sujith";
        String name = new String("Manchala");
        Object obj2 = 3;
        
        System.out.println(obj.getClass());
        System.out.println(name.getClass());
        System.out.println(obj2.getClass());
        
        boolean equals = obj.getClass().equals(name.getClass());
        System.out.println("equals = " + equals);
    }

}

class String{

    public String(java.lang.String name) {
    }
    
}

