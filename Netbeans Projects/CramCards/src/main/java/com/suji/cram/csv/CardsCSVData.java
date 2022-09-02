
package com.suji.cram.csv;

import com.opencsv.CSVReader;

import com.suji.cram.model.Card;
import com.suji.cram.util.FileDialougeUtil;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class CardsCSVData {
    
    public static void main(String[] args) throws IOException, FileNotFoundException{
        File file = FileDialougeUtil.openFile(null, "");
        getCards(file);
    }

    public static List<Card> getCards(File file) throws FileNotFoundException, IOException{

        List<Card> cardsList = new ArrayList<>();
        FileReader fr = new FileReader(file);

        CSVReader csvReader = new CSVReader(fr);

        String[] nextRecord;

        // we are going to read data line by line
        while ((nextRecord = csvReader.readNext()) != null) {
            
            String data[] = new String[3];
            for (int i = 0; i < 3; i++) {
                try {
                    data[i] = nextRecord[i];
                } catch (Exception e) {
                    data[i] = "";
                }
            }
            
            
            
            Card card = new Card(data[0],data[1],data[2]); 
            cardsList.add(card);
        }
        return cardsList;
        
    }

}
