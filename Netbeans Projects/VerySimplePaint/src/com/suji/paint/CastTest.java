package com.suji.paint;


public class CastTest {

    public static void main(String[] args) {
        
        Vehicle car = new Car();

        System.out.println(isTwoWheeler(car));
    }
    
    public static boolean isTwoWheeler(Object obj){
        boolean isTwoWheeler = false;
        try{
            TwoWheeler two = (TwoWheeler) obj;
            isTwoWheeler = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return isTwoWheeler;
    }


}



class Vehicle{
    
}

class TwoWheeler extends Vehicle{
    
}

class FourWheeler extends Vehicle{
    
}

class Bike extends TwoWheeler{
    
}

class Cycle extends TwoWheeler{
    
}

class Car extends FourWheeler{
    
}

class Jeep extends FourWheeler{
    
}