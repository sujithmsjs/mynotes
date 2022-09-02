package learn.multi.sync.inter;


public class NewMain {

    public static void main(String[] args) {
        Bag b = new Bag();
        Angel a = new Angel(b);
        Person p = new Person(b);
        a.start();
        p.start();
    }

}



class Bag{
    private Apple apple;
    
    
    public void place(){
        apple = new Apple();
        System.out.println("Apple is placed. "+apple);
    }
    
    public void taken(){
        apple = null;
        System.out.println("Apple is taken out.");
    }
}

class Angel extends Thread{
    Bag b;

    public Angel(Bag b) {
        b = b;
    }
    
    
    
    @Override
    public void run(){
        while(true){
            b.place();
        }
    }
}

class Person extends Thread{
    Bag b;

    public Person(Bag b) {
        this.b = b;
    }
    
    
    
    @Override
    public void run(){
        while(true){
            b.taken();
        }
    }
}

class Apple{
    
}