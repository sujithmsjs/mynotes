package coll.util;

public class Anonymous_Inner_Class2 {
    public static void main(String[] args) {
        Shed shed =  new Shed();
        shed.v.showName();
        shed.v2.showName();
    }
}

class Shed {

    public Van v = new Van();

    //Anonymous inner class
    public Van v2 = new Van() {
        @Override
        public void showName() {
            System.out.println("Upgraded Van.");
        }
    };
}

class Van {

    public void showName() {
        System.out.println("Van");
    }
}
