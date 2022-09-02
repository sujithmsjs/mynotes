
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class NewMain {
    public static void main(String[] args) throws ClassNotFoundException {
        
        String className = "java.util.Collection";
        Class cl = Class.forName(className);
        Method[] m = cl.getMethods();
        List<Method> p = Arrays.asList(m);
//        Vector<Method> parent = new Vector<Method>();
        Vector<String> parent = getVector(p);
        
        
        String className2 = "java.util.Deque";
        Class c2 = Class.forName(className2);
        Method[] m2 = c2.getDeclaredMethods();
        List<Method> c = Arrays.asList(m2);
        Vector<String> child = getVector(c);
        
        System.out.println("Child methods "+c2.getSimpleName());
        showAllMethods(child);
        
         System.out.println();
        System.out.println("Parent methods "+cl.getSimpleName());
       
        showAllMethods(parent);

        child.removeAll(parent);

            
        System.out.println();
        System.out.println("Child only have methods"+c2.getSimpleName());
        showAllMethods(child);

        
    }
    
    

    
    private static void showAllMethods(Method[] m) {
        for (Method method : m) {
            System.out.println(show2(method));
        }
    }

    private static void show(Method method) {
        
        System.out.println("Method name:"+method.getName());
        System.out.println("Generic name:"+method.toGenericString());
        Type t = method.getGenericReturnType();
        System.out.println("Return type:"+t.getTypeName());
        System.out.println("Paramenters");
        Type[] t2 = method.getGenericParameterTypes();
        System.out.println();
        System.out.println();
        
    }
    private static String show2(Method method){
        Type t = method.getGenericReturnType();
        String name = method.getName();
        Type[] t2 = method.getGenericParameterTypes();
        String para = Arrays.toString(t2);
        return t+"\t"+name+"\t"+para;
    }
    private static void show3(Method method){
        String str = method.toGenericString();
        StringTokenizer st = new StringTokenizer(str," ");
        
        System.out.println(str+" "+st.countTokens());
    }

    private static void showAllMethods(List<Method> p) {
        showAllMethods(p.toArray(new Method[]{}));
    }

    private static Vector<String> getVector(List<Method> p) {
       Vector<String> v = new Vector<String>();
        for (Method m : p) {
            v.add(show2(m));
        }
        return v;
    }

    private static void showAllMethods(Vector<String> child) {
        for (String string : child) {
            System.out.println(string);
        }
    }
    
    
}
