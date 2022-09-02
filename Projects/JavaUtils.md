# JavaUtils


### File Structure
```pre
+ JavaUtils\ 
	|---  pom.xml
	+ \src\main\java\com\suji\utils
		|---  App.java
		|---  BlodDataUtil.java
		|---  FlamesCals.java
		|---  FrameGraphics.java
		|---  LovePersent.java
		|---  Math.java
		|---  SQLDateUtil.java
	+ \src\test\java\com\suji\utils
		|---  AppTest.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\utils\App.java
3. src\main\java\com\suji\utils\BlodDataUtil.java
4. src\main\java\com\suji\utils\FlamesCals.java
5. src\main\java\com\suji\utils\FrameGraphics.java
6. src\main\java\com\suji\utils\LovePersent.java
7. src\main\java\com\suji\utils\Math.java
8. src\main\java\com\suji\utils\SQLDateUtil.java
9. src\test\java\com\suji\utils\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.suji</groupId>
  <artifactId>JavaUtils</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>JavaUtils</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>

```

---

### 2. App.java

#### src\main\java\com\suji\utils\App.java

```java

package com.suji.utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

```

---

### 3. BlodDataUtil.java

#### src\main\java\com\suji\utils\BlodDataUtil.java

```java

package com.suji.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BlodDataUtil {

    public static void main(String[] args) {

    }

    public static InputStream getBlobFile(File file) {
        InputStream is = null;
        try {

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return is;
    }

    public static ImageIcon getImageIcon(File file, Dimension size) {
        ImageIcon imageIcon = null;
        BufferedImage img = null;
        try {
            if (file != null) {
                img = ImageIO.read(file);
                Image dimg = img.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(dimg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageIcon;
    }

    public static InputStream toIPS(JLabel label) {
        InputStream is = null;
        try {
            ImageIcon imgIcon = (ImageIcon) label.getIcon();
            BufferedImage bi = getBufferedImage(imgIcon);
            is = getInputStream(bi);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return is;
    }

    public static BufferedImage getBufferedImage(ImageIcon icon) {
        int h = icon.getIconHeight();
        int w = icon.getIconWidth();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

    public static InputStream getInputStream(BufferedImage buffImage) {
        InputStream is = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffImage, "jpeg", os);
            // Passing: ​(RenderedImage im, String formatName, OutputStream output)
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return is;
    }

    public static ImageIcon getImageIcon(Blob blob, Dimension size) {
        ImageIcon imageIcon = null;
        try {
            InputStream is = blob.getBinaryStream();
            BufferedImage bimg = ImageIO.read(is);
            Image image = bimg.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return imageIcon;
    }

    public static File getImageFile(Component parent) {
        File f = null;
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg");
        fc.setFileFilter(filter);

        int ip = fc.showOpenDialog(parent);

        if (ip == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
        }
        return f;
    }
    
}

```

---

### 4. FlamesCals.java

#### src\main\java\com\suji\utils\FlamesCals.java

```java

package com.suji.utils;


/**
 * LOVE CALCULATOR FLAMES see result of all persons at a time
 */
import java.util.Scanner;

public class FlamesCals {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name");
        String str1 = scan.nextLine();
        System.out.println("Enter How many persons\ndo you want to check flames:");
        int num = scan.nextInt();
        String[] results = new String[num];

        System.out.println("Enter Person's name frequencly using enter key");
        String str2;
        for (int i = 0; i < results.length; i++) {
            System.out.print((i + 1) + "   ");
            str2 = getName();
            results[i] = Flames.finalResultString(str1, str2);
        }

        System.out.println("Please wait result will be display\n\n");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        for (String str : results) {
            System.out.println("  *  " + str);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("\nThankyou for using this");
    }

    public static String getName() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
        
    }
}

class Flames {

    private String name1, name2, text2;

    public Flames(String str1, String str2) {
        name1 = str1;
        name2 = text2 = str2;
    }

    //Main method to ger the string results
    public static String finalResultString(String str1, String str2) {
        Flames f = new Flames(str1, str2);
        f = f.compareNames(f);
        int i = f.totalOfNamesLength();
        int res = f.intResults(i);
        return f.textResutls(res);
    }

    //Static method for geting final results
    public int totalOfNamesLength() {
        return name1.length() + name2.length();
    }

    //Compareing two names
    public Flames compareNames(Flames f) {
        //Edit the Names into Upper Case
        String name1 = f.name1;
        String name2 = f.name2;
        name1 = getEdit(name1);
        name2 = getEdit(name2);
        //Make the String into Builder
        StringBuilder n1 = new StringBuilder(name1);
        StringBuilder n2 = new StringBuilder(name2);
        //Compair two Strings 
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                if (n1.charAt(i) == n2.charAt(j)) {
                    n1.deleteCharAt(i);
                    n2.deleteCharAt(j);
                    i--;
                    j--;
                    break; //Very imp to "break" here
                }
            }
        }
        f.name1 = n1.toString();
        f.name2 = n2.toString();
        return f;
    }

    //Edit the name's and get Only pure Text
    public String getEdit(String name) {
        name = name.toUpperCase().trim();
        StringBuilder str = new StringBuilder(name);
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }
        return str.toString();

    }

    public String textResutls(int n) {
        String str = "";
        switch (n) {
            case 1:
                str = text2 + " is Your Friend";
                break;
            case 2:
                str = text2 + " is Your Love";

                break;
            case 3:
                str = text2 + " is Your Affection";
                break;
            case 4:
                str = text2 + " and You going to Marry";
                break;
            case 5:
                str = text2 + " is Your Enemy";
                break;
            case 6:
                str = text2 + " is Your Sister/Brother";
                break;
            default:
                str = "Wrong Name's";
                break;
        }
        return str;
    }

    /*Its very critically programmed in c
     please dont try to edit it
     */
    public int intResults(int n) {
        int m, l, k, j, temp;
        m = l = k = j = temp = 0;
        int[] num = {1, 2, 3, 4, 5, 6};
        l = -1;
        k = 6;
        if (n > 0) {
            for (int i = 4; i >= 0; i--) {
                if (m == n) {
                    l--;
                }
                for (m = 1; m <= n; m++) {
                    l++;
                    if (l == k) {
                        l = 0;
                    }
                }
                m--;
                for (j = l; j < 5; j++) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
                k--;
            }
            return num[0];
        } else {
            return 0;
        }
    }
}

```

---

### 5. FrameGraphics.java

#### src\main\java\com\suji\utils\FrameGraphics.java

```java

package com.suji.utils;

//Drawing Line in Frame

import java.awt.*;
import java.awt.event.*;

public class FrameGraphics extends Frame {

    // change lines as your wish...
    int lines = 7;
    Point[] start = new Point[lines];
    Point[] stop = new Point[lines];
    int count = 0;
    Point drag;

    private FrameGraphics() {
        setFrame();
        addMouse();
        setVisible(true);
    }

    private void setFrame() {
        setTitle("MyGame");
        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent eve) {
                System.exit(0);
            }
        });
    }

    public void addMouse() {
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                drag = new Point(e.getPoint());
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                start[count] = new Point(e.getPoint());
            }

            public void mouseReleased(MouseEvent e) {
                stop[count] = new Point(e.getPoint());
                addLine();
            }
        });
    }

    public void addLine() {
        count++;
        drag = null;
        repaint();
    }

    public void paint(Graphics g) {
        if (drag != null) {
            g.setColor(Color.red);
            g.drawLine(start[count].x, start[count].y, drag.x, drag.y);
        }
        for (int i = 0; i < count; i++) {
            g.setColor(Color.black);
            g.drawLine(start[i].x, start[i].y, stop[i].x, stop[i].y);
        }
    }

    public static void main(String[] args) {
        new FrameGraphics();
    }
}

```

---

### 6. LovePersent.java

#### src\main\java\com\suji\utils\LovePersent.java

```java

package com.suji.utils;

/**
 * Simple Love Calculator Its very simple
 */
import java.util.Scanner;

public class LovePersent {

    public static void main(String[] args) {
        //Get name form User

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Your Name: ");
        String name1 = scan.nextLine();
        System.out.println("Enter Your Partner Name: ");
        String name2 = scan.nextLine();

        //Geting Love Persent through the static method of Love class
        int love = Love.getLovePersent(name1, name2);
        System.out.println(name1 + " & " + name2 + " Love Persent is " + love);
        scan.close();
    }
}

class Love {

//Edit the name's and get Only pure Text
    public String getEdit(String name) {
        name = name.toUpperCase().trim();
        StringBuilder str = new StringBuilder(name);
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }
        return str.toString();
    }

    //Get the Sum of String
    public int getTotal(String str) {
        int sum = 0;
        try {
            for (int i = 0; i < str.length(); i++) {
                sum = sum + str.charAt(i);
            }
        } catch (Exception e) {
            System.out.println("Error Name, Please try different names");
        }
        return sum;
    }

    //Get the Love Persentage
    public static int getLovePersent(String boy, String girl) {
        Love l = new Love();
        boy = l.getEdit(boy);
        girl = l.getEdit(girl);

        int b = l.getTotal(boy);
        int g = l.getTotal(girl);
        int sum = b + g;
        return sum % 100;
    }
}

```

---

### 7. Math.java

#### src\main\java\com\suji\utils\Math.java

```java

package com.suji.utils;

import java.util.Random;
import java.util.Scanner;

public class Math {
	public static void main(String[] args) {
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		int level = 25; // Change the Level
		int rg = 0;
		int wr = 0;
		Timmer t = new Timmer(60); // Change the Time
		while (t.isAlive()) {
			int a, b;
			a = r.nextInt(level);
			b = r.nextInt(level);
			System.out.println(a + " + " + b + " = ?");
			int c = s.nextInt();
			if (c == (a + b)) {
				System.out.print("Right\n");
				rg++;
			} else {
				System.err.print("Wrong\n");
				wr++;
			}
		}
		System.out.println("Time Over");
		System.out.println("Rights : " + rg);
		System.out.println("Wrongs : " + wr);
		s.close();
	}
}

class Timmer extends Thread {
	int time;

	public Timmer(int time) {
		this.time = time;
		start();
	}

	public void run() {
		for (int i = 0; i <= time; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			// System.out.println(""+i);
		}
	}
}
```

---

### 8. SQLDateUtil.java

#### src\main\java\com\suji\utils\SQLDateUtil.java

```java

package com.suji.utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDateUtil {

    
    
    public static java.sql.Date getSQLDate(int year,int month,int day){
        /*
         String str="2015-03-31";  
         Date date=Date.valueOf(str);//converting string into sql date  
         */
        LocalDate date = LocalDate.of(1994, 9, 2);
        return Date.valueOf(date);
    }
    public static LocalDate getLocalDate(java.sql.Timestamp  dateTime){
        java.util.Date date = dateTime;
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ld;
    }
    
    public static String dateInWords(LocalDate date){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(myFormatObj);
        return formattedDate;
    }
    

    public static java.sql.Timestamp getTimeStamp(int year, int month, int date) {
        LocalDate ld = LocalDate.of(1994, 9, 2);
        Date d = Date.valueOf(ld);
        java.sql.Timestamp tm = new java.sql.Timestamp(d.getTime());
        return tm;
    }
    
}

```

---

### 9. AppTest.java

#### src\test\java\com\suji\utils\AppTest.java

```java

package com.suji.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

```

---

