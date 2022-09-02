package basic.literals;

import java.util.Random;

public class LeteralDemo {

    public static void main(String[] args) {
        int w = 300, h = 200, u = 10;
        int xBlocks = 0 ,yBlocks = 0, tBlocks =0;
        if (w % u != 0 && h % u != 0) {
            System.out.println("In-compatable unit values");
        } else {
            xBlocks = w / u;
            System.out.println("xBlocks = " + xBlocks);
            yBlocks = h / u;
            System.out.println("yBlocks = " + yBlocks);
            tBlocks = xBlocks * yBlocks;
            System.out.println("tBlocks = " + tBlocks);
        }
        Random random = new Random();
        int x1 = random.nextInt(xBlocks) * u; // Bounds 0 to 290
        
        int y1 = random.nextInt(yBlocks) * u; // Bounds 0 to 190
        System.out.println("Point: "+x1+","+y1);
        
        System.out.println(1/600*29*19);
        // 1/600*()
    }
}
