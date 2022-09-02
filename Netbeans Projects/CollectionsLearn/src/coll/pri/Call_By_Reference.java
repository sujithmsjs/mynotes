package coll.pri;

import util.obj.Box;

public class Call_By_Reference {

    public static void main(String[] args) {
       
        Box b  = new Box("Apple",5);
        Box b2 = b;
        System.out.println("In the begining.");
        System.out.println(b);
        System.out.println(b);
        System.out.println(b.hashCode());
        System.out.println(b2.hashCode());
        
       b2.setItem("Pineapple");
       b2.setCount(3);
        
        System.out.println("End");
        System.out.println(b);
        System.out.println(b2);
        editBox(b);
        System.out.println(b);
        System.out.println("Show box: "+showBox(b));
    }
    
    public static Box showBox(Box b){
        Box box = b;
        b.setItem("Banana");
        return box;
    }
    
    private static void editBox(Box b){
        b.setItem("Dragon fruit");
    }

    
    
}