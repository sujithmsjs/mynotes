package coll.util;

public class InnerClass_Deep1 {

    public static void main(String[] args) {
        Man m = new Man("Sujith");
        m.show();
    }

}

class Man {
    private String name;
    private Heart h;
    private Brain b;
    
    public Man(String name) {
        this.name = name;
        b = new Brain();
        h = new Heart();
    }

    class Heart {
        
        String name = Man.this.name+"@Heart";
                
        public String getName() {
            return name;
        }
    }

    class Brain {
      //  String name = this.name+" Brain"; This reference to the Brain classes throws NullPointerException.
        String name = Man.this.name+"@Brain";
        
        public String getName() {
            return name;
        }
    }

    public void show() {
        System.out.println("Sujith is man, he has "+b.getName()+" and "+h.getName());
    }

}
