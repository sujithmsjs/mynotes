package learn.time;

import java.time.Instant;


public class NewMain1 {

    public static void main(String[] args) {
        
        //We can use System.nanoTime() to measure elapsed time with nanosecond precision. 
        long nanoTime = System.nanoTime();
        System.out.println("nanoTime = " + nanoTime);
    
        Instant now = Instant.now();
        long toEpochMilli = now.toEpochMilli();
        System.out.println("toEpochMilli = " + toEpochMilli);
    
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis = " + currentTimeMillis);
    }

}
