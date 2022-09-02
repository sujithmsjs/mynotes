package mypro.progs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mypro.dbutil.DBUtil;

public class ShowData {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<String> al = new ArrayList<String>();
    private static ArrayList<String> genders = new ArrayList<String>();
    private static Random r = new Random();
    static {
        setNames();
    }
    
    public static void main(String[] args) {
        for(int i=0;i<200;i++){
            String name = getName();
            String n = name.substring(0, name.length()-1);
            String gender = name.substring(name.length()-1, name.length());
            System.out.println(n+" "+gender);
            
        }
    }
    
    public static String getName(){
     return    al.get(r.nextInt(al.size()))+genders.get(r.nextInt(genders.size()));
    }
    

    private static void setNames() {
        Connection conn;
        PreparedStatement ps1;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getConnection();

            ps1 = conn.prepareStatement("select * from names");
            rs1 = ps1.executeQuery();
            
            while (rs1.next()) {
                al.add(rs1.getString(1));
                genders.add(rs1.getString(2));
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Successful");
    }

}
