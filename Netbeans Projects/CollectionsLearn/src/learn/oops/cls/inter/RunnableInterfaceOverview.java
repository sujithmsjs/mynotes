package learn.oops.cls.inter;


public class RunnableInterfaceOverview {

    public static void main(String[] args) {
        Q q = new Q();
        Thread t = new Thread(q);
        Thread t2 = new Thread();

        t.start();
      
    }

} 

class Q implements Runnable{
    @Override
    public void run(){

        System.out.println("This is Q class, Run method.");
    }
}

interface Runnable{
    public void run();
}

class Thread implements Runnable{
    private Runnable target;
    
    Thread(){

    }
    
    Thread(Runnable target){
        this.target = target;
    }
    
    @Override
    public void run() {
        System.out.println("Entered into Thread class run method.");
        if (target != null) {
            target.run();
        }else{
            System.out.println("This is Thread class, Empty run method.");
        }
    }
    
    public void start(){
        System.out.println("This is Thread class, Start method.");
        run();
    }
}
