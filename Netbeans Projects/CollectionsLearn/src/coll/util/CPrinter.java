
package coll.util;

import java.util.ArrayList;
import java.util.Vector;

public class CPrinter {

    public static void printAL(ArrayList al){
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
    }
    
    public static void printVec(Vector al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
    }
    
}
