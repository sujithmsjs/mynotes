package basic.literals;

import java.awt.Color;

public class Colors600 {

    public static void main(String[] args) {
        
    }
    
    public static int[] colors750() {
        float hue = 1F / 30;
        float sat = 1F / 5;
        float bri = 1F / 5;

        System.out.println(30 * 5 * 5);
        int n = 0;
        int[] color = new int[750];
        for (int i = 1; i <= 30; i++) { //0 - 30
            for (int j = 1; j <= 5; j++) {
                for (int k = 1; k <= 5; k++) {
                    color[n++] = Color.HSBtoRGB(i * hue, j * sat, k * bri);
                    //System.out.println(++n+" hue"+i+"; sat "+j+"; bri"+k);
                }
            }
        }
        return color;
    }

    public static void colorSet() {
        int w = 30, h = 20;

        float f = 1F / (30 * 20);

        int n = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                System.out.println(++n);
            }
        }
    }
}
