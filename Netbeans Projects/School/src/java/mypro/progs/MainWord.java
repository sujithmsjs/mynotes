package mypro.progs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mypro.progs.ShowData.getName;
import mypro.dbutil.DBUtil;
import mypro.util.GetData;

public class MainWord {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            addRow();
        }

    }

    private static void addRow() {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into std (name,dob,gender,class,sec,mat,sci,soc,email,school) values (?,?,?,?,?,?,?,?,?,?)");

            
            String name = getName();
            String n = name.substring(0, name.length()-1);
            String gender = name.substring(name.length()-1, name.length());
            //System.out.println(n+" "+gender);
            
            //int sno = GetData.getRandomInt(1, 1000, false);
          //  String name = GetData.getName(5, 7, false);
            java.sql.Date sqlDate = GetData.getDate();
            int mat = GetData.getRandomInt(1, 100, true);
            int sci = GetData.getRandomInt(1, 100, true);
            int soc = GetData.getRandomInt(1, 100, true);
            int cl  = GetData.getRandomInt(8,10, true);
            String sec = GetData.getSection();
            //String sex = GetData.getGender();
            String email = GetData.getEmail();
            String school = GetData.getSchool();
            
            
            
            
            //ps.setInt(1, sno); 
            ps.setString(1, n);
            ps.setString(5, sec);
            ps.setDate(2, sqlDate);
            ps.setString(3, gender);
            ps.setString(9, email);
            ps.setInt(4,cl);
            ps.setInt(6, mat);
            ps.setInt(7, sci);
            ps.setInt(8, soc);
            ps.setString(10, school);

           // System.out.println(sno + " , " + name + "." + sqlDate + "." + mat + "." + sci + "." + soc + "." + sex + "." + email);

            if (ps.executeUpdate() > 0) {
                System.out.println("Successful, Added");
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
