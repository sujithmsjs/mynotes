package coll.util;

import java.lang.reflect.Field;


public class Local_Class_Demo {

    public static void main(String[] args) {
        Bot b = new Bot();
        //  Bot.Heart heart = b.getHeart();
        Bot.Cpu cpu = b.new Cpu();
        System.out.println(b.new Cpu());
        
        Zee zee = new Zee();
        Bot.Cpu cpu1 = b.new Cpu();
        
        Class bot = b.getClass();
        Class bot2 = Bot.class;
        if(bot==bot2){
            System.out.println("b.getClass()==Bot.class");
        }
       
        
        Field[] fs = bot.getFields();
        for (Field f : fs) {
            System.out.println("f = " + f);
        }
        
        Class[] cs = bot.getDeclaredClasses();
        for (Class c : cs) {
            System.out.println("c ="+c);
            boolean localClass = c.isLocalClass();
            System.out.println("localClass = " + localClass);
            boolean memberClass = c.isMemberClass();
            System.out.println("memberClass = " + memberClass);
            String canonicalName = c.getCanonicalName();
            System.out.println("canonicalName = " + canonicalName);
           
        }
        
    }

}
class Zee{
    
}

class Cpu{
    
}

class Bot{
    
    //Anonymous class.
    public Z z = new Z() {
        @Override
        public void showMe() {
            
        }
    };
    
    public int n;
    public static int m;
    public static final int HEY = 0;
    
    //Can't access private class from outside.
    public class Heart{   
    }
    
    class Cpu{
        
    }
    public static class Hand{
        
    }
    public void get(){
        int m=0;
        class Local{
            int n = 10;
            public void print(){
                System.out.println(m);
            }
        }
        boolean localClass = Local.class.isLocalClass();
        System.out.println("localClass = " + localClass);
        boolean memberClass = Local.class.isMemberClass();
        System.out.println("memberClass = " + memberClass);
        String canonicalName = Local.class.getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);
    }
    
    
    public Heart getHeart(){
        Heart h = new Heart();
        return h;        
    }
    
}

interface Z{
    void showMe();
}