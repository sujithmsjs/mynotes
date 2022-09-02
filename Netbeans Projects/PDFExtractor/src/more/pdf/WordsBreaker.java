package more.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordsBreaker {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        StringBuilder sb = getText();
        System.out.println(sb);
    }

    public static StringBuilder getText() throws FileNotFoundException, IOException {
        File f = new File("S:\\Work\\How to say No Org.txt");

        FileReader fr = new FileReader(f);
        StringBuilder sb = new StringBuilder();

        int ch = 0;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }

        return sb;
    }

}
