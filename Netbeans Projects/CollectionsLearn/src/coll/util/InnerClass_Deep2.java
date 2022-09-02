package coll.util;

public class InnerClass_Deep2 {

    public static void main(String[] args) {
        Human suji = new Human("Sujith");
        suji.show();

        Human devid = new Human("Devid");
        devid.show();
        
        // Human.Heart h = new Human.Heart("Power"); Enclosing instance required.
        
        // Getting Devid Heart with the enclosed instance method.
        Human.Heart devid_heart = devid.getHeart();
        
        //Change the heart.
        suji.setHeart(devid_heart);
        
        // Static inner class is not dependable on Enclosing instance, and it is indipendent to outter class.
        Human.Brain solmon = new Human.Brain("Solmon brain");
        
        //Changing the brain
        suji.setBrain(solmon);
        
        suji.show();     
    }

}

class Human {

    private String name;
    private Heart h;
    private Brain b;

    public Human(String name) {
        this.name = name;
        b = new Brain();
        h = new Heart();
    }
    
    public Heart getHeart(){
        return h;
    }
    
    public void setHeart(Heart heart){
        this.h = heart;
    }
    
    public Brain getBrain(){
        return b;
    }
    
    public void setBrain(Brain b){
        this.b = b;
    }

    class Heart {

        private String name;

        Heart() {
            
            //We can accss the outer instance variables which are same name.
            name = Human.this.name + "@Heart";
        }

        public Heart(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Brain {
        //  String name = this.name+" Brain"; This reference to the Brain classes throws NullPointerException.

        private String name;

        public Brain() {
            name = "A Random brain";
          //  name = Human.this.name + "@Brain"; Non static content not accessable.
        }

        public Brain(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public void show() {
        System.out.println("Sujith is man, he has " + b.getName() + " and " + h.getName());
    }

}
