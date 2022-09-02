
import com.suji.cls.GetMethods;
import com.suji.cls.Met;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Woking {

    public static void main(String[] args) {
        
       List<Met> list =  GetMethods.getMethods(Deque.class);
        
        for (Met met : list) {
            if(met.getModifier().contains("public")){
                System.out.println(met);
            }
            
        }
    }

}
