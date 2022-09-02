package learn.inherit;


public class ThreadDemo2 {

    public static void main(String[] args) {
        Z z = new Z();
        z.start();
    }

}
class Z extends Thread{
    public void run(){
        System.out.println(this.getClass().getName()+" run method.");
    }
}

interface Runnable{
    public void run();
}

class Thread implements Runnable{
    private Runnable run;
    
    Thread(){
        run = new Thread(this);
    }
    
    Thread(Runnable run){
        this.run = run;
    }
    
    public void start(){
        System.out.println(this.getClass().getName()+" Start method.");
        if(run!=null)
            run.run();
        else
            System.out.println("Runnable is not initialize.");
    }
    public void run(){
        System.out.println(this.getClass().getName()+" run method.");
    }
}
