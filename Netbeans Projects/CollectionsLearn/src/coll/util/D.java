package coll.util;


public class D{

    public static void main(String[] args) {
        Animal a = new Hen();
        
        System.out.println(a.getClass().getName());
        Hen h = (Hen) a;
        Cat c = (Cat) a;
        System.out.println(h.getName());
    }

}

class Cat implements Animal{

    @Override
    public String getName() {
        return "Cat";
    }
    
    
}
class Hen implements Animal{

    @Override
    public String getName() {
        return "Hen";
    }
    
}