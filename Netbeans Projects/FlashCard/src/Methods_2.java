
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Methods_2 {

    public static void main(String[] args) throws ClassNotFoundException {
        
        String parent = "java.util.Collection";
        String child = "java.util.Queue";

        Class pClass = Class.forName(parent);
        Class cClass = Class.forName(child);

        Method[] pM = pClass.getDeclaredMethods();
        Method[] cM = cClass.getDeclaredMethods();
        
        List<Method> asList = Arrays.asList(new Method[]{});
        List<Method> asList2 = Arrays.asList(new Method[]{});
        
        asList2.removeAll(asList);
        showMethods(asList2.toArray(cM));
        
        
        
        System.out.println("Completed.");
    }

    private static void show(Method m) {
        Parameter[] parameters = m.getParameters();
        System.out.println(m.getName() + "\t" + m.getDefaultValue() + "\t" + m.getReturnType() + "\t Paramters:" + m.getParameterCount() + "\t " + Arrays.toString(parameters));

    }

    private static void showM(Method m) {
        if (m != null) {
            try {
                Parameter[] parameters = m.getParameters();
                System.out.println(m.getReturnType() + "\t" + m.getName() + "\t(" + Arrays.toString(parameters) + ")");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("M is null here");
        }
    }

    private static void showMethods(Method[] pM) {
        for (Method m : pM) {
          //  showM(method);
           // System.out.println(m.);
            System.out.println(m);
        }
    }

}
