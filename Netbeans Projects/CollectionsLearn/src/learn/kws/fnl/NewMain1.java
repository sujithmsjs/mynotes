package learn.kws.fnl;


public class NewMain1 {

    public static void main(String[] args) {
        Super s = new SubClass();
        s.publicMethod();
    }

}

class Super{
    
    private void privateMethod(){
        System.out.println("Private Super");
    }
    
    public void publicMethod(){
        System.out.println("Publie Super");
    }
    
    
}

class SubClass extends Super{
    void privateMethod(){
        
    }
    
    @Override
    public void publicMethod() {
        
    }
}