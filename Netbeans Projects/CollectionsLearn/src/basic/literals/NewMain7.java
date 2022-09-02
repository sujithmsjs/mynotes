package basic.literals;


public class NewMain7 {

    public static void main(String[] args) {
       int n = 100;
       int level = 0;
       long timeGap = 1_000_000_000;
       
       
        for (int i = 0; i < 1000; i++) {
            if (i % 5 == 0) {
                level = i / 5;
                timeGap -= level * 1_00_000L;
                if(timeGap<0){
                    System.exit(0);
                }
                System.out.println("Level Changed.");
            }
            
            System.out.println("Level: "+level+" Time Gap: "+timeGap);
            
        }
       
      
       
       
           
       
        System.out.println(level+" : "+timeGap );
               
    }

}
