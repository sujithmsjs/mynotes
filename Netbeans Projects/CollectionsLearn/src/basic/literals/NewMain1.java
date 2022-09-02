package basic.literals;

import java.util.Scanner;

public class NewMain1 {

    public static void main1(String[] args) {
        byte b = Byte.MAX_VALUE;
        int n = 0b1111_1111;
        System.out.println(b >> 6);
        System.out.println(n << 24);
    }

    public static void show(byte b) {

    }

    public static void main(String[] args) {
        // System.out.println(n>>>2);

        Integer m = 0b0011___1100;
        System.out.println(m);
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int n = 0;
        do {
            System.out.println(Integer.toBinaryString(n)+"    N:" + n);
            System.out.println("Enter your option: 1>> 2<< 3>>> 4+ 5-");
            input = scan.nextInt();
            switch (input) {
                case 1:
                    n >>= 1;
                    break;
                case 2:
                    n <<= 1;
                    break;
                case 3:
                    n >>>= 1;
                    break;
                case 4:
                    n += 1;
                    break;
                case 5:
                    n -= 1;
                    break;
            }

        } while (input != 0);

    }

}
