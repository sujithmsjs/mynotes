public class Inheritence_Test {

    public static void main(String[] args) {
        Reptile r = new Tode();
        System.out.println(r); //Reptile objected internally told the Tode object.
        
        System.out.println(r.scutes());
        
        r = new Snake();
        
        System.out.println(r.getLegs());
        System.out.println(r);
        
        Snake sn = (Snake) r;
        System.out.println(sn);
        
     //   Tode td = (Tode) r;
    //    System.out.println(td); // ClassCastException: Snake cannot be cast to Tode.
        
        Ani a = new Snake();
        System.out.println(a);
        
        
    }
    
}


interface Ani{
    public int getLegs();
    public boolean hasWinds();
    public int speed();
}

interface Reptile extends Ani{
    public int scutes();
}

class Tode implements Reptile{
    
    
    @Override
    public int scutes() {
        return 100;
    }

    @Override
    public int getLegs() {
        return 4;
    }

    @Override
    public boolean hasWinds() {
        return false;
    }

    @Override
    public int speed() {
        return 30;
    }    
}

class Snake implements Reptile{

    @Override
    public int scutes() {
        return 5000;
    }

    @Override
    public int getLegs() {
        return 0;
    }

    @Override
    public boolean hasWinds() {
        return false;
    }

    @Override
    public int speed() {
        return 80;
    }
    
}

