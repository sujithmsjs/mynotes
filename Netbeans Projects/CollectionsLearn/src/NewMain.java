
import java.awt.Color;
import java.util.ListIterator;
import java.util.*;


public class NewMain {
    
    
    public static void main(String[] args) {
    
        ArrayList<Car> al = new ArrayList();
        al.add(new Car(1,Color.RED));
        al.add(new Car(2,Color.RED));
        al.add(new Car(1,Color.BLUE));
        al.add(new Car(2,Color.YELLOW));
        al.add(new Car(1,Color.GREEN));
        al.add(new Car(3,Color.RED));
        
        if(al.contains(new Car(1,Color.BLUE))){
            System.out.println("Car found.");
        }else{
            System.out.println("Car not found.");
        }
        
       ListIterator list = al.listIterator(al.size());
       Collections.swap(al, 0, al.size()-1);
       
        while(list.hasPrevious()){     
            System.out.println(list.previous());
        }
        
    }

}

class Car{
    int modelNo;
    Color color;

    public Car(int modelNo, Color color) {
        this.modelNo = modelNo;
        this.color = color;
    }

    public int getModelNo() {
        return modelNo;
    }

    public void setModelNo(int modelNo) {
        this.modelNo = modelNo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" + "modelNo=" + modelNo + ", color= " + color.getRGB() + '}';
    }

    @Override
    public int hashCode() {
        System.out.println("Hash Code");
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.modelNo != other.modelNo) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
    
}
