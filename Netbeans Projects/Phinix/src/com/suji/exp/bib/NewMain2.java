package com.suji.exp.bib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.suji.util.DBUtil1;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class NewMain2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\sujit\\OneDrive\\Desktop\\God Promises.txt");
        System.out.println(file.exists());
        FileReader fr = new FileReader(file);
        StringBuilder sb = new StringBuilder();

        int ch = 0;

        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        //System.out.println(sb);

        Pattern p = Pattern.compile("PROMISE +#+[0-9]+");
        Matcher m = p.matcher(sb);
        int inx = 1;

        while (m.find()) {
            // Order checking....
            String g[] = m.group().split("#");
            int n = Integer.parseInt(g[1]);
            if (inx == n) {
                System.out.println(g[1] + " Working...");
                inx++;
            } else {
                System.out.println("Errorrrrrrrr");
            }
        }

        // Spliting...
        String verses[] = sb.toString().split("PROMISE +#+[0-9]+");

        System.out.println("Total verses: " + verses.length);
        
        StringBuilder sbv = new StringBuilder();
        
        
        for (int i = 1; i < verses.length; i++) {

            //System.out.println(verses[i]);
            String[] vr = verses[i].trim().split("[.]");
            try {
                
                int vno = i;
                String name = vr[0].trim().replaceAll("\n", " ").replaceAll("[ ]+", " ")+".";
                String ref = vr[1].trim();
                
                System.out.println(vno+","+name+","+ref);
                
                if(vno == 35){
                    for (int j = 0; j < name.length(); j++) {
                        System.out.println((int)name.charAt(j)+" : "+name.charAt(j));
                    }
                }
                
//                Connection conn = DBUtil1.getConnection();
//                
//                PreparedStatement ps = conn.prepareStatement("insert into promises(verse,ref) values(?,?)");
//             
//                ps.setString(1, name);
//                ps.setString(2, ref);
//                
//                
//                if(ps.executeUpdate()>0){
//                    System.out.println("Updated....");
//                }
                
            } catch (Exception ex) {
                System.out.println("----------------------------------------------------Error at "+(i+1));
                ex.printStackTrace();
                //return;
            }

        }

        fr.close();

    }

}
