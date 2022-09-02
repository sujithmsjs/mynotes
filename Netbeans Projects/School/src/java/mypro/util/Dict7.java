package mypro.util;

import java.util.ArrayList;
import mypro.dbutil.DBUtil;

public class Dict7 {

    public static void main(String[] args) {
        String sqlQuery = "select distinct scut from org";
        ArrayList<String> al = DBUtil.getColumnValues(sqlQuery);
        for(int i=0;i<al.size();i++){
            //System.out.println(al.get(i));
            String sqlQuery2 = "select word from org where scut='"+al.get(i)+"'";
            ArrayList<String> al2 = DBUtil.getColumnValues(sqlQuery2);
            al2.add(al.get(i));
           // show(al2);
            WordBox wb = new WordBox(al2);
            wb.work();
            add(wb.getDict());
        }
            
    }//foamy
    
    public static void show(ArrayList<String> al){
        for(int i=0;i<al.size();i++){
            System.out.println((i+1)+". "+al.get(i));
        }
    }
    
    public static void add(ArrayList<String> al){
        for(int i=0;i<al.size();i++){
            String str = al.get(i);
            int n = DBUtil.updateQuery("insert into mydict values('"+str+"')");
        }
        System.out.println("Successful\n");
    }
}
