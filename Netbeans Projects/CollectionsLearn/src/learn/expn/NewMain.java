package learn.expn;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NewMain {

    public static void main(String[] args) {
        Cals c = new Cals();
        try {
            int sum = c.substract(400, 200);
            int add = c.add(5, -10);
            System.out.println("sum = " + sum);
            System.out.println("add = " + add);
            c.show(add);
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
        }
        System.out.println("clear");
    }

}

class Cals {

    //Checked Exception
    public int substract(int n, int m) throws InvalidInputException {
        if (n < m) {
            throw new InvalidInputException("First number should be greater then Secound number");
        } else {
            return n - m;
        }
    }

    //Unchecked Excetption
    public int add(int n, int m) {
        try {
            if (n < 0 || m < 0) {
                throw new InvalidInputException("Numbers should not be lessthen Zero.");
            } else {

            }
        } catch (InvalidInputException e) {
            System.out.println("e = " + e);

        }
        return n + m;
    }

    public void show(int n) {
        if (n < 0) {
            throw new NullPointerException("Show num number should be greater then ZERO");
        }
        System.out.println("show(int n) :" + n);
    }
    
    public void show2(int n) throws InvalidInputException {
        if (n < 0) {
            throw new InvalidInputException("Show num number should be greater then ZERO");
        }
        System.out.println("show(int n) :" + n);
    }
    
}

class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
