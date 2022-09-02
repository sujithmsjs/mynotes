# CramCards


### File Structure
```pre
+ CramCards\ 
	|---  pom.xml
	+ \src\main\java\com\suji\cram\model
		|---  Card.java
		|---  CardDao.java
		|---  GroupDao.java
	+ \src\main\java\com\suji\cram\csv
		|---  CardsCSVData.java
	+ \src\main\java\com\suji\cram\db
		|---  DBUtil.java
	+ \src\main\java\com\suji\cram\ds
		|---  ReAsk.java
		|---  TestIterator.java
	+ \src\main\java\com\suji\cram\gui
		|---  AddFrame.java
		|---  IndexFrame.java
		|---  TestFrame.java
	+ \src\main\java\com\suji\cram\main
		|---  Cram.java
	+ \src\main\java\com\suji\cram\prac
		|---  Math.java
		|---  NewJFrame.java
	+ \src\main\java\com\suji\cram\util
		|---  DialogUtil.java
		|---  FileDialougeUtil.java
		|---  SaveCSVData.java
		|---  TextUtil.java
```
### Index
```pre
1. pom.xml
2. model\Card.java
3. model\CardDao.java
4. model\GroupDao.java
5. src\main\java\com\suji\cram\csv\CardsCSVData.java
6. src\main\java\com\suji\cram\db\DBUtil.java
7. src\main\java\com\suji\cram\ds\ReAsk.java
8. src\main\java\com\suji\cram\ds\TestIterator.java
9. src\main\java\com\suji\cram\gui\AddFrame.java
10. src\main\java\com\suji\cram\gui\IndexFrame.java
11. src\main\java\com\suji\cram\gui\TestFrame.java
12. src\main\java\com\suji\cram\main\Cram.java
13. src\main\java\com\suji\cram\prac\Math.java
14. src\main\java\com\suji\cram\prac\NewJFrame.java
15. src\main\java\com\suji\cram\util\DialogUtil.java
16. src\main\java\com\suji\cram\util\FileDialougeUtil.java
17. src\main\java\com\suji\cram\util\SaveCSVData.java
18. src\main\java\com\suji\cram\util\TextUtil.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.suji</groupId>
    <artifactId>CramCards</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    
     <dependencies>
        <!-- https://mvnrepository.com/artifact/com.opencsv/opencsv -->
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>4.6</version>
        </dependency>
        
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.27</version>
        </dependency>

        
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                    <archive>
                        <manifest>
                            <mainClass>com.suji.cram.main.Cram</mainClass>
                        </manifest>
                    </archive>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
    
    
</project>
```

---

### 2. Card.java

#### model\Card.java

```java

package com.suji.cram.model;

import com.suji.cram.util.DialogUtil;

public class Card {

    private String front;
    private String back;
    private String hint;
    private String group;
    private int score;
    private int sno;
    
    
    public static final String DEFAULT = "Default";

    public Card(String front, String back, String hint) {
        this(-1, front, back, hint, Card.DEFAULT, -4);

    }

    public Card(String front, String back, String hint, int score) {
        this(-1, front, back, hint, Card.DEFAULT, score);
    }

    public Card(String front, String back, String hint, String group) {
        this(-1, front, back, hint, group, -4);
    }

    public Card(int sno, String front, String back, String hint, String group, int score) {
        this.sno = sno;
        this.front = front;
        this.back = back;
        this.hint = hint;
        this.group = group;
        this.score = score;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getScore() {
        return score;
    }

    public int getSno() {
        return sno;
    }

    public boolean isValid(){
        return front.length() > 3 && back.length() > 3 && hint.length() > 3;
    }

    
    
    
    public void setScore(int score) {
        this.score = score;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Card{sno="+sno+", front=" + front + ", back=" + back + ", hint=" + hint + ", group=" + group + ", score=" + score +'}';
    }

    

}

```

---

### 3. CardDao.java

#### model\CardDao.java

```java

package com.suji.cram.model;


import com.suji.cram.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CardDao {
    

    public static String[] getAllGroups(){
        //select distinct  cards.group from cards
        String[] list = new String[]{"Default","Vocabulary","Acronym","Knowledge"};
        
        return list;
    }
    
    public static int addAllCards(List<Card> cardsList, String group){
        int count = 0;
        for (Card card : cardsList) {
        
            try {
                if(addCard(card.getFront(),card.getBack(),card.getHint(),group)){
                    count++;
                }
            } catch (Exception e) {
                System.out.println("Index:"+ count+"; Results "+e.getMessage());
            }
        }
        return count;
    }

    

    
    public static boolean addCard(String front, String back, String hint, String group) throws SQLException {
        boolean isSaved = false;

        String sqlQuery = "insert into cards(front,back,hint,cards.group) values(?,?,?,?)";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, front);
        ps.setString(2, back);
        ps.setString(3, hint);
        ps.setString(4, group);

        isSaved = ps.executeUpdate() > 0;

        return isSaved;
    }
    
    public static LinkedList<Card> getWords(int limit, int offset,String group) throws SQLException{
        String sqlQuery = "select * from cram.cards where cards.group=? order by score limit ? offset ?";
        LinkedList<Card> list = new LinkedList<>();
        
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, group);
        ps.setInt(2, limit);
        ps.setInt(3, offset);

        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Card word = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            list.add(word);
        }
        
        return list;
    }
/*
    public static List<Card> getWords(int limit, int offset) throws SQLException {

        String sqlQuery = "select * from cards order by score limit ? offset ?";
        List<Card> list = new ArrayList<>();
        
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Card word = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getInt(6));

                //System.out.println(word);
                list.add(word);
            }

        return list;

    }
*/
    public static boolean addCard(Card card) throws SQLException {
        return addCard(card.getFront(),card.getBack(),card.getHint(), card.getGroup());
    }
    
    
    public static boolean setScore(Card card, int n) throws SQLException{
        return setScore(card.getSno(), n);
    }
    
    
    
    public static boolean setScore(int sno, int n) throws SQLException{
        
        String q = "update cards set score=score+"+n+" where cid="+sno;
        Connection con = DBUtil.getConn();
        int count = con.createStatement().executeUpdate(q);
        //System.out.println(q);
        //PreparedStatement ps = con.prepareStatement(q);
        //ps.setInt(1, n);
        //ps.setInt(1, sno);

        return count>0;
    }
    
    public static void main(String[] args) throws SQLException {
        
        if(CardDao.setScore(13, 10)){
            System.out.println("Wroking...");
        }
    }


    

}

```

---

### 4. GroupDao.java

#### model\GroupDao.java

```java

package com.suji.cram.model;


import com.suji.cram.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    
    public static void main(String[] args) throws SQLException {
        for (String allGroup : GroupDao.getAllGroups()) {
            System.out.println(allGroup);
        }
    }

    public static boolean addGroup(String group) throws SQLException {
        boolean isSaved = false;

        String sqlQuery = "insert into cram.groups values(?)";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, group);

        isSaved = ps.executeUpdate() > 0;

        return isSaved;
    }

    public static String[] getAllGroups() throws SQLException {
        String sqlQuery = "select * from cram.groups order by gname";
        List<String> list = new ArrayList<String>();

        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(rs.getString(1));
        }

        return list.toArray(new String[0]);

    }

}

```

---

### 5. CardsCSVData.java

#### src\main\java\com\suji\cram\csv\CardsCSVData.java

```java


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

```

---

### 6. DBUtil.java

#### src\main\java\com\suji\cram\db\DBUtil.java

```java

package com.suji.cram.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static Connection con;

    static {
        if (con == null) {
            try {
                //  Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cram", "root", "apple");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static Connection getConn(){
        return con;
    }
    
  
}
```

---

### 7. ReAsk.java

#### src\main\java\com\suji\cram\ds\ReAsk.java

```java

package com.suji.cram.ds;

import com.suji.cram.model.Card;

public class ReAsk {

    private static int limit;
    private int count;

    public ReAsk(int times) {
        limit = times;
        count = 1;
    }

    
    public boolean checkAnswer(Card card, String input){
        boolean correct = card.getBack().equalsIgnoreCase(input);
        if(correct){
            count++;
        }else{
            count =1;
        }
        return correct;
    }
    
    public static void main(String[] args) {
        ReAsk ra = new ReAsk(2);
        Card card = new Card("Hi", "Hey", "hehe");
        ra.checkAnswer(card, "Hey");
        ra.checkAnswer(card, "Hey");
        System.out.println(ra.proceed());
       
    }
    
    public boolean proceed(){
        boolean flag = false;
        if(count>limit){
            count = 1;
            flag = true;
            System.out.println("proceeding...");
        }else{
            System.out.println("Can't proceed.");
        }
       return flag;
    }

}

```

---

### 8. TestIterator.java

#### src\main\java\com\suji\cram\ds\TestIterator.java

```java

package com.suji.cram.ds;




import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;
import com.suji.cram.model.GroupDao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;


public class TestIterator{

    private LinkedList<Card> cardsList;
    private int pageNo = 0;
    private static final int LIMIT = 10;
    private String group;

    public TestIterator(String group) {
        this.group = group;
    }
    
    public static void main(String[] args) throws SQLException {
        TestIterator tg = new TestIterator("Avoid Very");
        while(tg.hasNext()){
            System.out.println(tg.next());
        }
        
        tg.reset();
        System.out.println("Secound");
        while(tg.hasNext()){
            System.out.println(tg.next());
        }
        
    }

    private void loadCards() throws SQLException {
        System.out.println("\nPage: "+pageNo);
        cardsList = CardDao.getWords(LIMIT, LIMIT * pageNo, group);
        pageNo++;
    }


    public boolean hasNext() throws SQLException {
        
            if (cardsList == null || cardsList.isEmpty()) {
                loadCards();
            }
        return !cardsList.isEmpty();
    }


    public Card next() {
       return cardsList.poll();
    }

    public void reset() {
        pageNo = 0;
    }


  

}

```

---

### 9. AddFrame.java

#### src\main\java\com\suji\cram\gui\AddFrame.java

```java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suji.cram.gui;

import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;
import com.suji.cram.model.GroupDao;
import com.suji.cram.util.DialogUtil;
import com.suji.cram.util.TextUtil;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class AddFrame extends javax.swing.JFrame {
    
  


    AddFrame(JFrame parent, Object selectedItem) {
        initComponents();
        groupCb.setSelectedItem(selectedItem);
        setLocationRelativeTo(parent);
        setSize(parent.getSize());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frontTxt = new javax.swing.JTextField();
        backTxt = new javax.swing.JTextField();
        hintTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        backAgainTxt = new javax.swing.JPasswordField();
        groupCb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Frame");
        setMinimumSize(new java.awt.Dimension(600, 400));

        frontTxt.setColumns(50);
        frontTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));

        backTxt.setColumns(20);
        backTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));

        hintTxt.setColumns(30);
        hintTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));
        hintTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Front");

        jLabel2.setText("Back");

        jLabel3.setText("Back again");

        jLabel4.setText("Hint");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        backAgainTxt.setMargin(new java.awt.Insets(5, 5, 5, 5));

        groupCb.setModel(new javax.swing.DefaultComboBoxModel<>(fillComboBox()));
        groupCb.setSelectedItem(Card.DEFAULT);
        groupCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupCbActionPerformed(evt);
            }
        });

        jLabel6.setText("Group");

        jButton2.setText("Home");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(backAgainTxt))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hintTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                            .addComponent(frontTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(backTxt)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(groupCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(13, 13, 13)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frontTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(backAgainTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hintTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            addButtion();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void hintTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintTxtActionPerformed
        try {
            addButtion();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_hintTxtActionPerformed

    private void groupCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_groupCbActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        IndexFrame af = new IndexFrame(this);
        af.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField backAgainTxt;
    private javax.swing.JTextField backTxt;
    private javax.swing.JTextField frontTxt;
    private javax.swing.JComboBox<String> groupCb;
    private javax.swing.JTextField hintTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

    
    private Card getCard() {
        
        //Getting details.
        String front = TextUtil.clean(frontTxt.getText());
        String back = TextUtil.clean(backTxt.getText());
        String hint = TextUtil.clean(hintTxt.getText());
        String group = TextUtil.clean(groupCb.getSelectedItem().toString());
        String backAgain  = TextUtil.clean(String.valueOf(backAgainTxt.getPassword()));
        
        Card card  = new Card(front, back, hint, group);
        
        if (card.isValid()) {
            
            //Checking ANS's match.
            if (back.equalsIgnoreCase(backAgain)) {
                return card;
            } else {
                DialogUtil.showEx(this, "Not Matched", "Ans's are not matched.");
                backAgainTxt.setText("");
                backAgainTxt.requestFocus();
                card = null;
            }
            
        } else {
            DialogUtil.showEx(this, "Invalid Entry!", "Plase Enter valid entry.");
            return null;
        }
        
        return card;
    }
    
    private void addButtion() throws SQLException {
        //First get the card.
        Card card = getCard();
        System.out.println("card: "+card);
        //If card is not null
        if (card != null) {
            boolean isSaved = CardDao.addCard(card);

            if (isSaved) {
                // If card saved successully
                DialogUtil.showMsg(this, "Success", card.getBack() + " added successfully.");
                clearFields();

            } else {
                // If card not saved successfully.
                DialogUtil.showEx(this, "Failed", "Sorry, card can't be saved.");
            }
        }
    }

    private void clearFields() {
        frontTxt.setText("");
        backTxt.setText("");
        backAgainTxt.setText("");
        hintTxt.setText("");
        frontTxt.requestFocus();
    }

    private String[] fillComboBox() {
        String[] list = CardDao.getAllGroups();
        try {
            list = GroupDao.getAllGroups();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
}

```

---

### 10. IndexFrame.java

#### src\main\java\com\suji\cram\gui\IndexFrame.java

```java

package com.suji.cram.gui;



import com.suji.cram.csv.CardsCSVData;
import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;
import com.suji.cram.model.GroupDao;
import com.suji.cram.util.FileDialougeUtil;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class IndexFrame extends javax.swing.JFrame {

    public IndexFrame(JFrame parent) {
        initComponents();
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupTf = new javax.swing.JTextField();
        createGroupBtn = new javax.swing.JButton();
        groupCb = new javax.swing.JComboBox<>();
        startTestBtn = new javax.swing.JButton();
        addWrodBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        filePath = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome");
        setMinimumSize(new java.awt.Dimension(600, 400));

        groupTf.setMargin(new java.awt.Insets(5, 5, 5, 5));

        createGroupBtn.setText("Create Group");
        createGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupBtnActionPerformed(evt);
            }
        });

        groupCb.setModel(new javax.swing.DefaultComboBoxModel<>(fillComboBox()));
        groupCb.setActionCommand(Card.DEFAULT);

        startTestBtn.setText("Start Test");
        startTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTestBtnActionPerformed(evt);
            }
        });

        addWrodBtn.setText("Add Words");
        addWrodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWrodBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Choose CSV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        filePath.setEditable(false);
        filePath.setMargin(new java.awt.Insets(5, 5, 5, 5));

        saveBtn.setText("load CSV");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(startTestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(addWrodBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(groupCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filePath)
                            .addComponent(groupTf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createGroupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGroupBtn)
                    .addComponent(groupTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(groupCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTestBtn)
                    .addComponent(addWrodBtn)
                    .addComponent(saveBtn))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addWrodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWrodBtnActionPerformed
        AddFrame af = new AddFrame(this, groupCb.getSelectedItem());
        af.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_addWrodBtnActionPerformed

    private void startTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTestBtnActionPerformed
        TestFrame af = new TestFrame(this,groupCb.getSelectedItem().toString());
        af.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_startTestBtnActionPerformed

    private void createGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupBtnActionPerformed
        
        try {

            String groupName = groupTf.getText();
            if (groupName.length() < 5) {
                JOptionPane.showMessageDialog(this, "New Group Name > 3 Characters. ", "Check Group name.", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            System.out.println("Hehe, skipped return.");

            if (GroupDao.addGroup(groupName)) {
                JOptionPane.showMessageDialog(this, "New Group " + groupName + " added successfully.", "Added", JOptionPane.INFORMATION_MESSAGE);
                groupCb.addItem(groupName);
                groupCb.setSelectedItem(groupName);

            } else {
                JOptionPane.showMessageDialog(this, "Unaable to add new group.", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        groupTf.setText("");
        
    }//GEN-LAST:event_createGroupBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File file = FileDialougeUtil.openFile(this, "");
        //String path = file.getPath();
        try {
            
            if (file.exists()) {
                filePath.setText(file.getPath());
            } else {
                JOptionPane.showMessageDialog(this, "File not exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (Exception e) {
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String path = filePath.getText();
        File file = new File(path);
        if (file.exists() && path.endsWith(".csv")) {
           int count = 0;
            try {
                
                List<Card> cardsList = CardsCSVData.getCards(file);
               count  = CardDao.addAllCards(cardsList,groupCb.getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Saved Card s: "+count, "Saved Cards", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
            }
            
            filePath.setText("Insert update cound : "+count);
            
        } else {
            JOptionPane.showMessageDialog(this, "File not exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWrodBtn;
    private javax.swing.JButton createGroupBtn;
    private javax.swing.JTextField filePath;
    private javax.swing.JComboBox<String> groupCb;
    private javax.swing.JTextField groupTf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton startTestBtn;
    // End of variables declaration//GEN-END:variables

    private String[] fillComboBox() {
        String[] list = CardDao.getAllGroups();
        try {
            list = GroupDao.getAllGroups();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }


}

```

---

### 11. TestFrame.java

#### src\main\java\com\suji\cram\gui\TestFrame.java

```java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suji.cram.gui;



import com.suji.cram.ds.ReAsk;
import com.suji.cram.ds.TestIterator;
import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;



import java.awt.Color;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestFrame extends javax.swing.JFrame {

    private TestIterator testWords;
    private ReAsk reAsk;
    private Card presentCard;
  
    
    
    
    
    TestFrame(JFrame parent, String group) {
        initComponents();
        setLocationRelativeTo(parent);
        testWords = new TestIterator(group);
        reAsk = new ReAsk(2);
        loadNextCard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        front = new javax.swing.JTextArea();
        back = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Test Frame");
        setMinimumSize(new java.awt.Dimension(600, 400));

        jScrollPane1.setFocusable(false);

        front.setColumns(20);
        front.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        front.setLineWrap(true);
        front.setRows(5);
        front.setWrapStyleWord(true);
        front.setFocusable(false);
        front.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(front);

        back.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        back.setMargin(new java.awt.Insets(5, 5, 5, 5));
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Fail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hint");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(back)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        checkAns();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
       checkAns();
    }//GEN-LAST:event_backActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            JOptionPane.showMessageDialog(this, "Ans is " + presentCard.getBack(), "Answer", JOptionPane.INFORMATION_MESSAGE);
            
            boolean isUpdated = CardDao.setScore(presentCard, -3);
            System.out.println(presentCard+" "+isUpdated);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            JOptionPane.showMessageDialog(this, "Hint: " + presentCard.getHint(), "Hint", JOptionPane.INFORMATION_MESSAGE);
            CardDao.setScore(presentCard, -2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        IndexFrame af = new IndexFrame(this);
        af.setVisible(true);
        setVisible(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField back;
    private javax.swing.JTextArea front;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private Random r = new Random();
    private void changeCardColor(){
        
        int h = r.nextInt(360);
        float hue = 1f/360f * h;
        
        front.setBackground(new Color(Color.HSBtoRGB(hue, 1, 1)));
        
        if(h<30){
            front.setForeground(Color.WHITE);
        }else if(h<55){
            front.setForeground(Color.BLACK);
        }else if(h<80){
            front.setForeground(Color.WHITE);
        }else if(h<200){
            front.setForeground(Color.BLACK);
        }else if(h<290){
            front.setForeground(Color.WHITE);
        }else if(h<360){
            front.setForeground(Color.BLACK);
        }
        /*
        0-30 White
31-55 Black
56-80 White
81-200 Black
201-290 White
290 - 360 Black
        */
        
        
        
    }
    
    
     private void loadNextCard() {

        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);

        try {
            if (testWords.hasNext()) {
                presentCard = testWords.next();
                front.setText(presentCard.getFront());
                changeCardColor();
            } else {
                JOptionPane.showMessageDialog(this, "You're test successfully completed.\nIt will restart again.", "Completed.", JOptionPane.INFORMATION_MESSAGE);
                testWords.reset();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkAns() {
        try {
            if (reAsk.checkAnswer(presentCard, back.getText())) {

                CardDao.setScore(presentCard, 1);

                back.setBackground(Color.GREEN);
                back.setForeground(Color.WHITE);
                back.setText("");

                System.out.println("Correct: " + presentCard);
                if (reAsk.proceed()) {
                    System.out.println("Proceed further...");
                    loadNextCard();
                }
            } else {

                back.setBackground(Color.RED);
                back.setForeground(Color.WHITE);
                back.setText("");
                CardDao.setScore(presentCard, -1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }

    }
}

```

---

### 12. Cram.java

#### src\main\java\com\suji\cram\main\Cram.java

```java

package com.suji.cram.main;


import com.suji.cram.gui.IndexFrame;
import java.awt.Font;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;


public class Cram {

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        FontUIResource fur = new FontUIResource("", Font.BOLD, 18);
        setUIFont(fur);

           
       // UIManager.setLookAndFeel( new NoireLookAndFeel());
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IndexFrame(null).setVisible(true);
            }
        });
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {

        java.util.Enumeration keys = UIManager.getDefaults().keys();
        UIDefaults ud = UIManager.getDefaults();
        javax.swing.plaf.ColorUIResource dark = new ColorUIResource(0, 0, 150);
        
        
        javax.swing.plaf.ColorUIResource white = new ColorUIResource(255, 255, 255);
        javax.swing.plaf.ColorUIResource ambar = new ColorUIResource(255, 191, 0);
       // rgb(255, 191, 0)
       
       List<String> uiList = new ArrayList<>();
       
       
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            
            uiList.add(key.toString());
            
            if (value instanceof javax.swing.plaf.FontUIResource) {
               // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, f);

            }
            
            
            if (key.toString().matches(".*[.]background")) {
                // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, Color.BLACK);
                //uiList.add(key.toString());
            }
            
             if(key.toString().matches(".*[.]foreground")){
                // System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, Color.WHITE);
                //uiList.add(key.toString());
             }
             
          
 
           //System.out.println(key + " : " + value);
        }
        
        
        Collections.sort(uiList);
        for (String string : uiList) {
            System.out.println(string);
        }
        //Theme Setting.
        UIManager.put("TextField.foreground", ambar);
        UIManager.put("TextField.inactiveBackground", Color.BLACK);
        UIManager.put("Label.foreground", Color.GREEN);
        UIManager.put("TextField.caretForeground", Color.white);
         UIManager.put("OptionPane.messageForeground", Color.white);
    }
}

```

---

### 13. Math.java

#### src\main\java\com\suji\cram\prac\Math.java

```java

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.suji.cram.prac;

import java.awt.Color;

/**
 *
 * @author sujit
 */
public class Math {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int h = 201;
       
        
        
        
        if(h<30){
            System.out.println("White");
        }else if(h<55){
           System.out.println("Black");
        }else if(h<80){
            System.out.println("White");
        }else if(h<200){
             System.out.println("Black");
        }else if(h<290){
            System.out.println("White");
        }else if(h<360){
              System.out.println("Black");
        }
        
    }
    
}

```

---

### 14. NewJFrame.java

#### src\main\java\com\suji\cram\prac\NewJFrame.java

```java

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.suji.cram.prac;

import java.awt.Color;

/**
 *
 * @author sujit
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        bgH = new javax.swing.JSpinner();
        bgB = new javax.swing.JSpinner();
        bgS = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fgH = new javax.swing.JSpinner();
        fgB = new javax.swing.JSpinner();
        fgS = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area.setColumns(20);
        area.setFont(new java.awt.Font("Monospaced", 3, 24)); // NOI18N
        area.setRows(5);
        area.setText("Hellow this is demo text.");
        area.setWrapStyleWord(true);
        area.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(area);

        bgH.setModel(new javax.swing.SpinnerNumberModel(0, 0, 360, 5));
        bgH.setInheritsPopupMenu(true);
        bgH.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgHStateChanged(evt);
            }
        });

        bgB.setModel(new javax.swing.SpinnerNumberModel(100, 0, 100, 1));
        bgB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgBStateChanged(evt);
            }
        });

        bgS.setModel(new javax.swing.SpinnerNumberModel(100, 0, 100, 1));
        bgS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgSStateChanged(evt);
            }
        });

        jLabel2.setText("Background");

        jLabel3.setText("H");

        jLabel4.setText("S");

        jLabel5.setText("B");

        jLabel6.setText("Foreground");

        fgH.setModel(new javax.swing.SpinnerNumberModel(0, 0, 360, 5));
        fgH.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fgHStateChanged(evt);
            }
        });

        fgB.setModel(new javax.swing.SpinnerNumberModel(100, 0, 100, 1));
        fgB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fgBStateChanged(evt);
            }
        });

        fgS.setModel(new javax.swing.SpinnerNumberModel(100, 0, 100, 1));
        fgS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fgSStateChanged(evt);
            }
        });

        jLabel7.setText("H");

        jLabel8.setText("S");

        jLabel9.setText("B");

        jButton1.setText("Choose");

        jButton2.setText("Choose");

        jButton3.setText("Black");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("White");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bgS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bgB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bgH, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(270, 270, 270)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fgS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4))
                                    .addComponent(fgB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fgH, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3)))
                                .addGap(0, 31, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(119, 119, 119))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(bgH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bgS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bgB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(fgH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fgS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fgB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bgHStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgHStateChanged
        changeBg();
    }//GEN-LAST:event_bgHStateChanged

    private void bgSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgSStateChanged
 changeBg();
    }//GEN-LAST:event_bgSStateChanged

    private void bgBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgBStateChanged
       changeBg();
    }//GEN-LAST:event_bgBStateChanged

    private void fgHStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fgHStateChanged
 changeFg();
    }//GEN-LAST:event_fgHStateChanged

    private void fgSStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fgSStateChanged
 changeFg();
    }//GEN-LAST:event_fgSStateChanged

    private void fgBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fgBStateChanged
 changeFg();
    }//GEN-LAST:event_fgBStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        area.setForeground(Color.BLACK);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
               area.setForeground(Color.WHITE);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JSpinner bgB;
    private javax.swing.JSpinner bgH;
    private javax.swing.JSpinner bgS;
    private javax.swing.JSpinner fgB;
    private javax.swing.JSpinner fgH;
    private javax.swing.JSpinner fgS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private static final float NUM_OF_COLORS = 360;
    private void changeBg() {
        
        float hue = 1f/NUM_OF_COLORS * (Integer) bgH.getValue();
        float sat = 1f/100 * (Integer) bgS.getValue();
        float bri = 1f/100 * (Integer) bgB.getValue();
        area.setBackground(new Color(Color.HSBtoRGB(hue, sat, bri)));
    }
    
    private Color autoSetFg(){
        int hue = (Integer) bgH.getValue();
        int sat = (Integer)  bgS.getValue();
        int bri = (Integer)  bgB.getValue();
        if(bri > 60){
            return Color.WHITE;
        }
        return null;
    }
    
    private void changeFg() {
        
        float hue = 1f/NUM_OF_COLORS * (Integer) fgH.getValue();
        float sat = 1f/100 * (Integer) fgS.getValue();
        float bri = 1f/100 * (Integer) fgB.getValue();
        area.setForeground(new Color(Color.HSBtoRGB(hue, sat, bri)));
       
    }
}

```

---

### 15. DialogUtil.java

#### src\main\java\com\suji\cram\util\DialogUtil.java

```java


package com.suji.cram.util;

import com.suji.cram.gui.AddFrame;
import com.suji.cram.model.Card;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JOptionPane;


public class DialogUtil {

    
    public static void main(String[] args) {
        Card card = new Card("Sujith", "Man", "dfdf");
        System.out.println("card "+card.isValid());
    }
    
    public static String demo(String str){
        String output = "Work";
        
        if(str.length() < 4){
            System.out.println("Before Return null");
            return null;
        }
        if(str.equalsIgnoreCase("exit")){
            return "Exit From return";
        }
        System.out.println("End of the method");
        return output;
    }
    
    
    
    
    public static void showMsg(Frame parent, String msg){
        JOptionPane.showMessageDialog(parent,msg ,"Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showEx(Frame parent, Exception ex){
        JOptionPane.showMessageDialog(parent,ex ,ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

    public static void showMsg(Frame parent, String title, String msg) {
       JOptionPane.showMessageDialog(parent,msg ,title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showEx(Frame parent, String title, String msg) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    
}

```

---

### 16. FileDialougeUtil.java

#### src\main\java\com\suji\cram\util\FileDialougeUtil.java

```java

package com.suji.cram.util;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileDialougeUtil {

    public static File openFile(JFrame frame, String path){
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(path);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files Files", "csv", "csv");
        fc.setFileFilter(filter);
        fc.setSize(800,400);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
        
        int state = fc.showOpenDialog(frame);
        
        
        File chosenFile = null;
        
        if(state == JFileChooser.APPROVE_OPTION){
            chosenFile = fc.getSelectedFile();
        }
        return chosenFile;
    }
    
    public static File saveFile(){
        return saveFile(null, "");
    }
    
    public static File saveFile(JFrame frame){
        return saveFile(frame, "");
    }
    
    public static File saveFile(JFrame frame, String path){
        
        
        JFileChooser fc = new JFileChooser();
        File currentFile = new File(path);
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files Files", "csv", "csv");
        fc.setFileFilter(filter);
        fc.addChoosableFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setCurrentDirectory(currentFile.getParentFile());
       // fc.setSelectedFile(currentFile);
        
        

        int state = fc.showSaveDialog(frame);
        System.out.println("State: "+state);
        
        File chosenFile = null;

        if (state == JFileChooser.APPROVE_OPTION) {
            chosenFile = fc.getSelectedFile();
             
            boolean isAlreadyExists = Arrays.asList(chosenFile.getParentFile().listFiles()).contains(chosenFile);
            System.out.println("is Already Exists? : "+isAlreadyExists);
            if(isAlreadyExists){
                int selectedOption = JOptionPane.showConfirmDialog(frame, "Do you want to REPLACE?", "Already Existed", JOptionPane.ERROR_MESSAGE);
                if(selectedOption == JOptionPane.NO_OPTION){
                    chosenFile = null;
                }
            }
        }
        return chosenFile;
    }
}

```

---

### 17. SaveCSVData.java

#### src\main\java\com\suji\cram\util\SaveCSVData.java

```java


package com.suji.cram.util;

public class SaveCSVData {

    public static void main(String[] args) {
        
    }
    
}

```

---

### 18. TextUtil.java

#### src\main\java\com\suji\cram\util\TextUtil.java

```java

package com.suji.cram.util;

public class TextUtil {

    public static String removeSpaces(String str) {
        return str.replaceAll("\\s+", " ").trim();
    }
    
    public static String clean(String str){
        return upper(removeSpaces(str));
    }

    public static String upper(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}

```

---

