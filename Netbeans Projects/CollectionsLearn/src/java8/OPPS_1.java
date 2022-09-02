package java8;

import java.io.UnsupportedEncodingException;


public class OPPS_1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Vehicle b = new Car ();
        System.out.println(b instanceof Bike);
        System.out.println(b instanceof TwoWheeler);
        System.out.println(b instanceof Vehicle);
        System.out.println(b instanceof Truck);
        System.out.println(b instanceof Electric);
        System.out.println(b instanceof Flyable);
        System.out.println(b instanceof PetrolBased);
        System.out.println(b instanceof HumanPowered);
        System.out.println(b instanceof Transporter);
        
    }

}
class Vehicle implements PetrolBased{

    private int petrolLevel = 50;

    @Override
    public void setPetrolLevel(int level) {
        this.petrolLevel = level;
    }

    @Override
    public int getPetrolLevel() {
       return petrolLevel;
    }
    
}


class TwoWheeler extends Vehicle implements Electric {}
class FourWheeler extends Transporter{}
class Car extends FourWheeler implements Flyable{}
class Bus extends Vehicle{}
class Bike extends TwoWheeler{}
class Bycle extends TwoWheeler implements HumanPowered, PetrolBased{}
class Truck extends Vehicle{}

interface Electric{}
interface Flyable{}

interface PetrolBased{
    public void setPetrolLevel(int level);
    public int getPetrolLevel();
}

interface HumanPowered{}

abstract class Transporter extends Vehicle{
    
} 