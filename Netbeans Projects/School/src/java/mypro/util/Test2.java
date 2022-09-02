/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypro.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import mypro.dbutil.DBUtil;
import static mypro.util.Dict7.add;

/**
 *
 * @author sujit
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        main2();
    }
    
    public static void main3(){
        Stack<String> words = new Stack<>();
        words.add("elephant");
        words.add("ant");
        words.add("Zebra");
        words.add("hippopotamus");
        words.add("tiger");
        words.add("eagle");
        words.add("lion");
        words.add("Owl");
        //   words.add("Gazzle");
        words.add("deer");

        Token t = new Token(words);
        t.showDict();
        Stack<Word> dict = t.getDict();
      //  store(dict);


    }
    
    public static void main2() throws SQLException {
        String sqlQuery = "select word from uni where sno>300";   
        ArrayList<String> al = DBUtil.getColumnValues(sqlQuery);

        for (int i = 0; i < al.size(); i++) {
            //System.out.println(al.get(i));
            String sqlQuery2 = "select word from org where scut='" + al.get(i) + "'";
            
            ArrayList<String> al2 = DBUtil.getColumnValues(sqlQuery2);
            al2.add(0,al.get(i));
            
            Stack<String> tokens = new Stack<>();
            tokens.addAll(al2);
            Token t = new Token(tokens);
            t.showDict();
            Stack<Word> dict = t.getDict();
            
                    // WordBox wb = new WordBox(al2);
                    // wb.work();
                    // add(wb.getDict());
                    store(dict);
                    System.out.println();
        }
    }
    
    
    
    
    
    
    
    public static void main() {
        String sqlQuery = "select distinct scut from org";
        ArrayList<String> al = DBUtil.getColumnValues(sqlQuery);
        
        for (int i = 0; i < al.size(); i++) {
            //System.out.println(al.get(i));
            String sqlQuery2 = "select word from org where scut='" + al.get(i) + "'";
            ArrayList<String> al2 = DBUtil.getColumnValues(sqlQuery2);
            al2.add(al.get(i));
            // show(al2);
           // WordBox wb = new WordBox(al2);
          //  wb.work();
            //add(wb.getDict());
        }
    }

    private static void store(Word dict) throws SQLException {
        String sqlQuery = "insert into personaldict(sno,token,word,sys) values(?,?,?,?)";
        PreparedStatement preStmt = DBUtil.getPreStmt(sqlQuery);
        preStmt.setInt(1, dict.getSno()+1);
        preStmt.setString(2, dict.getWord());
        preStmt.setString(3, dict.getToken());
        preStmt.setString(4, dict.getSys());
        
        if(preStmt.executeUpdate()>0){
            System.out.println("Added :"+dict.toString2());
        }else{
            System.out.println("Not updated: "+dict);
        }
    }

    private static void store(Stack<Word> dict) throws SQLException {
        for (int i = 0; i < dict.size(); i++) {
            store(dict.get(i));
        }
    }
    

}
