# BubbleSort Animation Project

### BubbleSort
```java
/*
 *BubbleSort Main class
    This class need tow classes Animation, Node
 */
import java.util.StringTokenizer;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JOptionPane;

public class BubbleSort extends Frame {

    private Vector<Node> vector; //Vector of nodes
    private int[] array; //numbers array
    private Vector<Vector<Node>> vectorNodes;
    private Button b;
    private Button b2;
    private Button about;
    private TextField t;

    public void init() {
        //Init all the objects
        vector = new Vector();
        b = new Button("     Sort    ");
        about = new Button("Help");
        t = new TextField("7,4,1,3,5,2",30);
        b2 = new Button("Animation");
        int[] n = {7,4,1,3,5,2};
        array = n;
        add(t);
        add(b);
        add(b2);
        add(about);
        vectorNodes = new Vector();
    }
    
    //Constructor
    BubbleSort() {
        super("Bubble Sort");
        setSize(600, 500);
        super.setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        init();
        start();
        addEvents();

    }
    
    //adding Events
    public void addEvents() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        t.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonClicked();

            }

        });
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Type numbers in textfield, separate numbers with ','"
                        + "\n and press \"Sort \" or \"Animation\" button.\n"
                        +" \n Program by: 1601-14-733 - 334/335/336 ","Help",JOptionPane.INFORMATION_MESSAGE);

            }

        });
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonClicked();

            }

        });
        b2.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonClicked();
                    showAnimationWindow();

                } catch (NumberFormatException | HeadlessException exe) {

                }

            }

        });
    }
    
    //buttonClicked executed when Action event occurs
    public void buttonClicked(){
        try {
            StringTokenizer st = new StringTokenizer(t.getText(), ",.;");
            int n = st.countTokens();
            array = new int[n];
            int i = 0;
            while (st.hasMoreTokens()) {
                String key = st.nextToken();
                array[i] = Integer.parseInt(key);
                i++;
            }
            vector.removeAllElements();
            vectorNodes.removeAllElements();
            start();
            repaint();
        } catch (NumberFormatException | HeadlessException exe) {
            JOptionPane.showMessageDialog(null, "Type numbers correctly separate with ','", "Check Textfield", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //Amination display in animation window
    private void showAnimationWindow() {
        Animation win = new Animation(this);
        win.setValues(array);
        win.setVisible(true);

    }

    //Main method
    public static void main(String[] args) {
        BubbleSort c = new BubbleSort();
        c.setVisible(true);
    }
    
    //initial the Array by starting
    public void start() {
        int x = 100;
        for (int i = 0; i < array.length; i++) {
            vector.add(new Node(array[i], x, 100, Color.GRAY));
            x += 70;
        }
        dispContext();
    }

    //Display Sorting Step By Step
    private void dispContext() {
        Node temp = null;
        Node temp2 = null;

        int j = 0, k = 0;
        for (k = 0; k < (vector.size() - 1); k++) {

            vectorNodes.add(new Vector(vector));
            for (j = 0; j < vector.size() - k - 1; j++) {
                if (vector.get(j).getValue() > vector.get(j + 1).getValue()) {
                    int m = j;
                    int n = j + 1;
                    try {
                        //swaping two numbers
                        temp = vector.get(m);
                        temp2 = vector.get(n);
                        vector.set(m, temp2);
                        vector.set(n, temp);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
        vectorNodes.add(new Vector(vector));
        vectorNodes.add(new Vector(vector));

        setColors();
    }
    
    //Setting Random color to nodes
    private void setColors() {
        for (int i = 0; i < array.length; i++) {
            vectorNodes.get(0).get(i).setColor(new Color(Color.HSBtoRGB(i / (float) vector.size(), 1, 1)));

        }
    }

    //Override pain method for graphics
    public void paint(Graphics g) {
        //Animaton Drawing here
        this.showSort(g);
    }

    //Displays sorted Elements
    private void showSort(Graphics g) {
        int xAxes = 200, yAxes = 20;
        Vector<Node> vector = null;
        for (int k = 0; k < vectorNodes.size(); k++) {
            yAxes += 47;
            xAxes = 100;
            vector = vectorNodes.get(k);
            g.setFont(new Font("", Font.BOLD, 20));
            if (k == 0) {
                g.drawString("Input  ", xAxes - 30, yAxes + 25);
            } else if (k == vectorNodes.size() - 1) {
                g.drawString("OutPut " + k, xAxes - 30, yAxes + 25);
            } else {
                g.drawString("Step " + k, xAxes - 30, yAxes + 26);
            }
            for (int i = 0; i < vector.size(); i++) {
                int n = vector.size() - k;
                xAxes += 44;
                if (i < n) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(vectorNodes.get(k).get(i).getColor());
                }
                g.fillRect(xAxes, yAxes, 40, 40);
                g.setColor(Color.black);
                g.drawRect(xAxes, yAxes, 40, 40);
                int val=vector.get(i).getValue();
                
                if(val>=0&&val<=99)
                    g.drawString(vector.get(i).getValue()+ "", xAxes + 12, yAxes + 25);
                else
                    g.drawString(vector.get(i).getValue()+ "", xAxes + 6, yAxes + 25);
            }
        }
    }
}

```
### Node
```java
/**
 * Node is a utility class of BubbleSort
 */

import java.awt.Color;
public class Node{
    private int x,y,value;
    private Color color;
    
    //Node constructor
    Node(int value,int x,int y){
        this.value=value;
        this.x=x;
        this.y=y;
        this.color= Color.GRAY;
    }
    Node(int value,int x,int y,Color color){
       this(value,x,y);
        this.color= color;
    }
    void setColor(Color c){
        this.color=c;
    }
    Color getColor(){
        return color;
    }
    void setNode(int value,Color col, int x,int y){
        this.value=value;
        this.x=x;
        this.y=y;
        this.color= col;
    }
    void setXY(int x,int y){
        this.x=x;
        this.y=y;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    int getValue(){
        return value;
    }
    @Override
    public String toString(){
        return "x:"+x+"y:"+y+"v:"+value+"c:"+color;
    }
}
```
### Animation
```java
/*
Animation class
it a utility class for BubbleSort class
 */
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

//Animation class
public class Animation extends Dialog implements Runnable {

    private Vector<Node> vector;
    private Thread t;
    //  private int[] array = {7, 4, 3, 6, 1};
    private int speed = 5;
    private String status;
    Image img = null;
    Graphics doubleG = null;
    //Remove Flickers

    public void init() {
        //Init all the objects
        try {
            vector = new Vector();
            t = new Thread(this);
            status = "Hellow World";
        } catch (Exception e) {
        }
    }

    //Animation constructor
    Animation(Frame f) {
        super(f, true);
        setTitle("Bubble Sort");
        setSize(800, 400);
        setLayout(new FlowLayout());
        super.setLocationRelativeTo(f);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                windowHide();
            }
        });
        init();
    }

    //Hiding dialog window
    private void windowHide() {
        this.dispose();
    }

    //Set Values for array
    public void setValues(int[] array) {
        int x = 40;
        for (int i = 0; i < array.length; i++) {
            vector.add(new Node(array[i], x, 100));
            x += 70;
        }
        //For Starting an animation
        t.start();
        //For Displaying Step to Step

    }

    //@Override paint method
    public void paint(Graphics g) {
        //Animaton Drawing here
        showAnimation(g);
    }

    //Animation perform here
    private void showAnimation(Graphics g) {
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Bubble Sort", 50, 70);
        for (int i = 0; i < vector.size(); i++) {
            int x = vector.get(i).getX();
            int y = vector.get(i).getY();

            g.setColor(vector.get(i).getColor());
            g.fillRect(x, y, 40, 40);
            g.setColor(Color.black);
            g.drawRect(x, y, 40, 40);

            int val = vector.get(i).getValue();
            if (val >= 0 && val <= 99) {
                g.drawString(vector.get(i).getValue() + "", x + 12, y + 25);
            } else {
                g.drawString(vector.get(i).getValue() + "", x + 6, y + 25);
            }
        }
        g.drawString(status, 50, 230);
    }

    //Run is used to Start an animation of Sorting
    @Override
    public void run() {
        int sortedElement = vector.size() - 1;
        for (int k = 0; k < (vector.size() - 1); k++) {
            if (k == 0) {
                try {
                    Thread.sleep(1000);
                    status = "Input Values";
                } catch (InterruptedException ex) {

                }
            }
            status = "Step " + (k + 1);
            for (int j = 0; j < vector.size() - k - 1; j++) {
                if (vector.get(j).getValue() > vector.get(j + 1).getValue()) {
                    int m = j;
                    int n = j + 1;
                    Node temp = vector.get(m);
                    Node temp2 = vector.get(n);
                    temp.setColor(Color.CYAN);
                    temp2.setColor(Color.CYAN);
                    System.out.println("Moving Started of " + m + "  and   " + n);
                    try {
                        int y1 = vector.get(m).getY();
                        int y2 = vector.get(n).getY();
                        for (int i = 0; i < 30; i++) {
                            Thread.sleep(speed);
                            vector.get(m).setXY(vector.get(m).getX(), y1 - i);
                            vector.get(n).setXY(vector.get(n).getX(), y2 + i);
                            repaint();
                        }
                        int x1 = vector.get(m).getX();
                        int x2 = vector.get(n).getX();
                        for (int i = 0; i < 70; i++) {
                            Thread.sleep(speed);
                            vector.get(m).setXY(x1 + i, vector.get(m).getY());
                            vector.get(n).setXY(x2 - i, vector.get(n).getY());
                            repaint();
                        }
                        int y3 = vector.get(m).getY();
                        int y4 = vector.get(n).getY();
                        for (int i = 0; i < 30; i++) {
                            Thread.sleep(speed);
                            vector.get(m).setXY(vector.get(m).getX(), y3 + i);
                            vector.get(n).setXY(vector.get(n).getX(), y4 - i);
                            repaint();
                        }
                        Thread.sleep(100);

                        vector.set(m, temp2);
                        vector.set(n, temp);
                        temp.setColor(Color.GRAY);
                        temp2.setColor(Color.GRAY);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    System.out.println("Moving complite of " + m + "  and   " + n);

                }
            }
            vector.get(sortedElement).setColor(Color.GREEN);
            sortedElement--;
        }
        try {
            Thread.sleep(300);
            vector.get(sortedElement).setColor(Color.GREEN);
            status = "Output";
            repaint();
        } catch (Exception ex) {

        }
    }

    //Update screen
    public void update(Graphics g) {

        if (img == null) {
            img = this.createImage(this.getSize().width, this.getSize().height);
            doubleG = img.getGraphics();
        }
        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
        doubleG.setColor(getForeground());
        paint(doubleG);
        g.drawImage(img, 0, 0, this);
    }

    void print() {
    }
}

```
