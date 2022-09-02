package util.obj;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReadyData {

    public static ArrayList<Box> getBoxData(){
        ArrayList<Box> al = new ArrayList<>();
        Random r = new Random();
        al.add(new Box("C", 4));
        al.add(new Box("C", 2));
        al.add(new Box("A", 4));
        al.add(new Box("B", 7));
        al.add(new Box("B", 5));
        al.add(new Box("C", 1));
        return al;
    }
    
    
    
    public static String getRandomString(){
        Random random = new Random();
        int len = random.nextInt(5)+5;
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int ch = random.nextInt(26)+65;
            sbf=sbf.append((char)ch);
        }
        
        return sbf.toString().toLowerCase();
    }
    
    public static String[] getStrings(int size) {
        String[] str = new String[size];
        for (int i = 0; i < size; i++) {
            str[i] = getRandomString();
        }
        return str;
    }
    
    public static ArrayList<String> getStringAL(int size){
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            al.add(getRandomString());
        }
        return al;
    }

    public static Integer[] getRandomInts(int size, int bound) {
        Integer[] ints = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt(bound);
        }
        return ints;
    }
    
    public static Integer[] getRandomInts(int size) {
        return getRandomInts(size,10);
    }
    
    public static Integer[] getRandomInts() {
        return getRandomInts(10,10);
    }

    public static ArrayList<Integer> getRandomIntsAL(int size) {
        List<Integer> asList = Arrays.asList(getRandomInts(size));
        return new ArrayList<>(asList);
    }

    public static void printIt(Object[] object) {
        String s = object.getClass().getSimpleName();
        
        System.out.println("" + s);
        for (Object f : object) {
            System.out.println(f);
        }
    }
}
