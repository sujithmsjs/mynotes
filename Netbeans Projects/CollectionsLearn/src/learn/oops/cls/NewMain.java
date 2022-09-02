package learn.oops.cls;


public class NewMain {

    public static void main(String[] args) {
        Pegion p = new Pegion("Power");
        p.fly();
        
    }

}

class Pegion extends Bird{
    
    public Pegion(String name) {
        super(name);
    }   
}

abstract class Bird implements Fly{
    private int wings;
    private int topHeight;
    private String name;

    public Bird(String name) {
        this.name = name;
    }
    
    
    
    void setName(String name){
        this.name = name;
    }
    
    @Override
    public void fly(){
        System.out.println(name+" is flying too high");
    }   
}
interface Fly{
    void fly();
}
