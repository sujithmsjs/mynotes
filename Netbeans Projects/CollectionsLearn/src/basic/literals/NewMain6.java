package basic.literals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import util.file.FileUtil;


public class NewMain6 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
       String filePath = "G:\\[English (auto-generated)] 10 Mistakes _Nice Guys_ Always Make [DownSub.com].txt";
       
       File file = new File(filePath);
        System.out.println(file.exists());
       FileReader f = new FileReader(file);
       int ch = 0;
       while((ch = f.read())!=-1){
//           if(Character.isLetterOrDigit(ch) || Character.is){
//               System.out.print((char)ch+":"+ch+" ");
//           }else{
//               System.out.println((char)ch+" "+ch);
//           }
            if(ch != 32){
                System.out.print((char)ch);
            }else{
                System.out.println(" ");
            }
       }
    }

}
