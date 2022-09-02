package mypro.dbutil;

import java.util.ArrayList;

/**
 *
 * @author sujit
 */
public class TableData {
    
    private ArrayList<String> cols;
    private ArrayList<String> rows;
    
    private int colPointer;
    private int rowPointer;
    
    public TableData(){
        cols = new ArrayList();
        rows = new ArrayList();        
    }
    
    public boolean addColumn(String value) {
        colPointer++;
        return cols.add(value);
    }
    
    public void nextRow(){
        rowPointer++;
    }
    
    
    
    public static void main(String[] args) {
      
    }
    
    
    
}
