
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;


public class Methods_3 {

    public static void main(String[] args) throws ClassNotFoundException {
        String className = "java.util.List";
        Class cl = Class.forName(className);
        System.out.println("Class name: "+cl.getSimpleName());
       // System.out.println(cl.getName());
       // System.out.println(cl.getTypeName());
         Method[] dm = cl.getDeclaredMethods();
     //   Method[] m = cl.getMethods();
   //     System.out.println(dm[0].getGenericParameterTypes());
     //   System.out.println(Arrays.toString(dm[4].getParameters()));
     
   //    descMethod(dm[12]);
       show(dm);
      //  Parameter[] p = dm[0].getParameters();
        //  System.out.println(dm.length);
        //  System.out.println(m.length);
     //   System.out.println(p.length);
    }
    
    private static void descMethod(Method m){
        System.out.println("Method name: "+m.getName());
        Class<?> t = m.getReturnType();
        System.out.println(t);
    }

    private static void show(Method[] dm) {
     //   for (Method m : dm) {
           // System.out.println(m);
           // System.out.println(m.getName());
          // String name = m.getReturnType().getSimpleName() + "\t" + m.getName() + "(";
     //       System.out.println(m);
        //   Parameter[] parameter = m.getParameters();
          //  System.out.println(Arrays);
       //    for (Parameter p: parameter) {
       //         name+=p.getType().getSimpleName()+",";
       //     }
       //     name+=")";
       //     System.out.println(name);
    //    }
        for (int i = 0; i < dm.length; i++) {
            System.out.println((i+1)+". "+dm[i]);
        }
    }
    
}
