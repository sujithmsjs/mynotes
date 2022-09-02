package mypro.util;

import mypro.dbutil.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Dict2 {

    public static void main(String[] args) {
        try {

            Connection conn = DBUtil.getConnection();
            
        //    String insertQuery = "insert into dict.dictionary(scut,word) value(?,?)";
        //    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            
        
            
        
                String retriveQuery = "select scut,word from dict.org order by scut limit 5";
            PreparedStatement preStmt = conn.prepareStatement(retriveQuery);
            
            if (preStmt.execute()) {
                ResultSet resSet = preStmt.executeQuery();
                while (resSet.next()) {
                    String shortCut = resSet.getString(1);
                    String word = resSet.getString(2);
                    
                    StringTokenizer st = new StringTokenizer(word,",");
                    
                    ArrayList<String> al = new ArrayList();
                    al.add(shortCut);
                    while(st.hasMoreTokens()){
                        String token = st.nextToken().trim();
                        
                        
        /*               insertStmt.setString(1, shortCut);
                       insertStmt.setString(2, token);
                       
                        if(insertStmt.executeUpdate()>0)
                            System.out.println(shortCut+" "+token+" Added");
                        else
                            System.out.println(shortCut+" "+token+" Not added");
                            */
                            al.add(token);
                            
                    }
                    // To get known word
                    System.out.println("Which of do you know?");
                    int input = showAL(al);
                    System.out.println("You Selected "+al.get(input));
                    
                    //To swap know word to the front.
                    pushToFirst(al,input);
                    System.out.println(al);
                    //
                }
            } else {
                System.out.println("Statement not executed.");
            }
            System.out.println("Execution completed.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static int showAL(ArrayList<String> al){
        int res = 0;
        for(int i=0;i<al.size();i++){
            System.out.println((i+1)+". "+al.get(i));
        }
        Scanner s = new Scanner(System.in);
        res = s.nextInt();
        return --res;
    }

    private static ArrayList<String> pushToFirst(ArrayList<String> al, int input) {
       String mid = al.get(input);
       String front = al.get(0);
       al.set(0, mid);
       al.set(input, front);
       return al;
    }
}
