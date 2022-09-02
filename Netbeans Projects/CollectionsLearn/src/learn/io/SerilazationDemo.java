package learn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class SerilazationDemo {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        get();
        
    }
    
    
    
    private static void get() throws FileNotFoundException, IOException, ClassNotFoundException{
        String fileName = "C:\\Users\\sujit\\OneDrive\\Desktop\\obj";
        File file = new File(fileName);
        FileInputStream fos = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(fos);
        Object object = oos.readObject();
        Person p = (Person) object;
        System.out.println(p);
        
        
        
        oos.close();
        fos.close();
    }
    
    private static void save(Person p) throws FileNotFoundException, IOException{
        String fileName = "C:\\Users\\sujit\\OneDrive\\Desktop\\obj";
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        oos.close();
        fos.close();
    }

}


class Person implements Serializable{
    Integer i;
    String name;
    int aga;
    boolean isMale;

    public Person() {
    }
    
    

    public Person(String name, int aga, boolean isMale) {
        this.name = name;
        this.aga = aga;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", aga=" + aga + ", isMale=" + isMale + '}';
    }
}
