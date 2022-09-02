package util.obj;

import java.util.Comparator;

public class Box implements Comparator<Box> {
    private String item;
    private int count;

    public Box(String item, int count) {
        this.item = item;
        this.count = count;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public String toString() {
        return "Box{" + "item=" + item + ", count=" + count + '}';
    }

    @Override
    public int compare(Box b1, Box b2) {
        
        if(b1.getItem().equals(b2.getItem())){
            if(b1.getCount()==b2.getCount()){
                return 0;
            }else if(b1.getCount()>b2.getCount()){
                return 1;
            }else{
                return -1;
            }
        }else{
            return b1.getItem().compareTo(b2.getItem());
        }
    }
}
