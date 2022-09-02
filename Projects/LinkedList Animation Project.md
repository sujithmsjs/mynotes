# LinkedList Animation Project
### Main Class
```java
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MainPro4 extends Frame implements Runnable, ActionListener {

    LinkedList<Node> ll;
    TextField tf;
    boolean showhl;
    String status;
    Choice ch;
    Button af;
    Button al;
    Button am;
    Button df;
    Button dl;
    Button dm;
    Panel pan;
    private Image img;
    private Graphics doubleG;

    private static String command;
    private static final int HGAP = 120;
    private static final int VGAP = 250;

    public MainPro4(){
        setTitle("LinkedList Animation");
        setSize(1200,800);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent e){
               System.exit(0);
           } 
        });
        init();
    }
    
    public static void main(String[] args){
        new MainPro4().setVisible(true);
    }
    
    public void init() {
        pan = new Panel();
        status = "Welcome";
        ll = new LinkedList<>();
        af = new Button("AddFirst");
        al = new Button("AddLast");
        am = new Button("AddMiddle");
        df = new Button("DeleteFirst");
        dl = new Button("DeleteLast");
        dm = new Button("DeleteMiddle");
        tf = new TextField(10);
        ch = new Choice();
        al.setBackground(Color.GREEN);
        af.setBackground(Color.CYAN);
        am.setBackground(Color.PINK);
        dl.setBackground(Color.BLUE);
        dm.setBackground(Color.YELLOW);
        df.setBackground(Color.red);
        pan.setBackground(Color.LIGHT_GRAY);
        /*
       Node n = new Node(100, 100);
        Node n2 = new Node(200, 200);
        Node n3 = new Node(500, 188);
        ll.add(n);
        ll.add(n2);
        ll.add(n3);
         */
        ch.setPreferredSize(new Dimension(50,20));
        af.addActionListener(this);
        al.addActionListener(this);
        am.addActionListener(this);
        df.addActionListener(this);
        dl.addActionListener(this);
        dm.addActionListener(this);
        pan.add(tf);
        pan.add(af);
        pan.add(al);
        pan.add(df);
        pan.add(dl);
        pan.add(ch);
        pan.add(am);
        pan.add(dm);
        add(pan,BorderLayout.NORTH);
    }

    public void paint(Graphics g) {
        g.setFont(new Font("",Font.PLAIN,16));
        for (int i = 0; i < ll.size(); i++) {
            int x = ll.get(i).p.x;
            int y = ll.get(i).p.y;
            g.setColor(ll.get(i).getColor());
            g.fillRect(x, y, ll.get(i).d.width, ll.get(i).d.height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ll.get(i).d.width, ll.get(i).d.height);
            g.drawString(i+"",x+50,y-5);
            g.drawLine(x+60,y,x+60,y+40);
            g.setColor(Color.BLACK);
            
            g.drawLine(ll.get(i).start.x, ll.get(i).start.y,ll.get(i).end.x, ll.get(i).end.y );
            g.drawLine(ll.get(i).end.x, ll.get(i).end.y, ll.get(i).end.x-5, ll.get(i).end.y-5);
            g.drawLine(ll.get(i).end.x, ll.get(i).end.y, ll.get(i).end.x-5, ll.get(i).end.y+5);
            g.drawString(ll.get(i).getValue()+"", x + 10, y + 20);
        }
        g.drawString(status, 30, 400);
        if(ll.size()>=1)
            {
                
                g.setColor(Color.YELLOW);
                 g.fillRect(10,50, 30,30);
                 g.setColor(Color.BLACK);
                 g.drawRect(10,50, 30, 30);
                g.drawString("Head",15,45);
                if(showhl){
                g.drawLine(25,80, 25  , 200);
                g.drawLine(25,200,60,200);
                g.drawLine(60, 200, 55, 195);
                g.drawLine(60, 200, 55, 205);    
                }
                  Point p = ll.get(ll.size() - 1).getEnd();
                  g.setColor(Color.pink);
                  g.fillOval(p.x, p.y - 15, 30, 30);
                  g.setColor(Color.black);
                  g.drawOval(p.x, p.y - 15, 30, 30);
                  g.drawString("X", p.x+10, p.y+5);
            }
    }

    public void setEnable() {
        af.setEnabled(true);
        am.setEnabled(true);
        al.setEnabled(true);
        dl.setEnabled(true);
        dm.setEnabled(true);
        df.setEnabled(true);

    }

    public void setDisible() {
        af.setEnabled(false);
        al.setEnabled(false);
        am.setEnabled(false);
        dm.setEnabled(false);
        dl.setEnabled(false);
        df.setEnabled(false);
    }

    private void moveNodeX(int index, int x) {
        int i = ll.get(index).p.x;
        try {
            for (; i < x; i++) {
                Thread.sleep(10);
                ll.get(index).p.x++;
                switch (command) {
                    case "AddFirst":
                        if (ll.size() >= 1) {
                            ll.get(i).setStart(ll.get(1).getEdge());
                        } else {
                            ll.get(index).p.x++;
                        }
                        break;
                }
                ll.get(index).start.x++;
                ll.get(index).end.x++;

                repaint();
            }
        } catch (Exception e) {
        }
    }

    private void moveNodeY(int index, int y) {
        int i = ll.get(index).p.y;
        try {
            for (; i < y; i++) {
                Thread.sleep(10);
                ll.get(index).p.y++;
                switch (command) {
                    case "AddFirst":
                        if (ll.size() > 1) {
                            ll.get(0).setEnd(ll.get(1).getEdge());
                        }
                        break;
                    case "AddLast":
                        if (ll.size() > 1) {
                            int last = ll.size() - 1;
                            int pre = ll.size() - 2;
                            ll.get(pre).setEnd(ll.get(last).getEdge());
                        }
                        break;
                    case "AddMiddle":
                        if (ll.size() > 1) {
                            int pre = index - 1;
                            int next = index + 1;
                            ll.get(pre).setEnd(ll.get(index).getEdge());
                            ll.get(index).setEnd(ll.get(next).getEdge());
                        }
                        break;
                    case "DeleteFirst":
                        if (ll.size() > 1) {
                            int next = index + 1;
                            ll.get(index).setEnd(ll.get(next).getEdge());
                        }
                        break;
                    case "DeleteMiddle":
                        break;
                    default:
                }
                ll.get(index).start.y++;
                ll.get(index).end.y++;
                repaint();
            }
        } catch (Exception e) {
        }
    }

    private void addFirst(int index, int y) {

        if (ll.size() > 1) {
            ll.get(index).p.y++;
            ll.get(index).start.y++;
            ll.get(0).end = ll.get(1).edge;

        } else {
            ll.get(index).p.y++;
            ll.get(index).start.y++;
            ll.get(index).end.y++;
            ll.get(index).edge.y++;
        }
    }

    private void setIndex() {
        ch.removeAll();
        for (int i = 1; i < ll.size(); i++) {
            ch.add(i + "");
        }
    }
    
    private void moveNodeset(int index, int x) { //How much long it will move
        int i = ll.get(index).p.x;
        try {
            if (i < x) {
                for (; i <= x; i++) {
                    Thread.sleep(10);
                    for (int j = index; j < ll.size(); j++) {
                        ll.get(j).p.x++;
                        //Moving Arrow
                        ll.get(j).start.x++;
                        ll.get(j).end.x++;
                        ll.get(j).edge.x++;
                        repaint();
                    }
                }
            } else {
                for (; i > x; i--) {
                    Thread.sleep(10);
                    for (int j = index; j < ll.size(); j++) {
                        ll.get(j).p.x--;

                        //Moving Arrow
                        ll.get(j).start.x--;
                        ll.get(j).end.x--;
                        ll.get(j).edge.x--;
                        repaint();
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    private void alAni() {
        int str=Integer.parseInt(tf.getText());
         int lastIndex = ll.size() - 1;
        Node n = new Node(ll.get(lastIndex).p.x, 50);
        n.setColor(Color.GREEN);
        n.setValue(str);
       
        ll.addLast(n);
        moveNodeX(lastIndex + 1, ll.get(lastIndex).p.x + HGAP);
        moveNodeY(lastIndex + 1, VGAP);
        n.setColor(Color.LIGHT_GRAY);
    }
    
    private void afAni() {
        try {
            showhl=false;
            int str = Integer.parseInt(tf.getText());
            Node n = new Node(str);
            n.setColor(Color.CYAN);
            ll.addFirst(n);
            
                    if (ll.size() > 1) {
                moveNodeset(1, ll.get(1).p.x + HGAP);
            }
            moveNodeY(0, VGAP);
            showhl=true;
            n.setColor(Color.LIGHT_GRAY);
            status = str +" node is added first";
        } catch (Exception e) {
        }
    }
    
    private void amAni() {
        try {
            int ind = Integer.parseInt(ch.getSelectedItem());
            int val = Integer.parseInt(tf.getText());            
            Node n = new Node(ll.get(ind).p.x, 110, Color.ORANGE);
            n.setValue(val);
            ll.add(ind, n);
            moveNodeset(ind + 1, ll.get(ind + 1).p.x + HGAP);
            moveNodeY(ind, VGAP);
            n.setColor(Color.lightGray);
        } catch (Exception e) {
        }
    }

    private void dfAni() {
        showhl=false;
        int x = ll.get(0).p.x;
        moveNodeY(0, ll.get(0).p.y + 100);
        ll.removeFirst();
        moveNodeset(0, x);
        showhl=true;
    }

    private void dmAni() {
        int ind = Integer.parseInt(ch.getSelectedItem());
        int x = ll.get(ind).p.x;
        moveNodeY(ind, ll.get(ind).p.y + 100);
        ll.remove(ind);
        moveNodeset(ind, x);
    }

    private void dlAni() {
        int index = ll.size() - 1;
        moveNodeY(index, ll.get(index).p.y + 100);
        ll.removeLast();
    }

    public void update(Graphics g) {
        if (img == null) {
            img = this.createImage(getSize().width, getSize().height);
            doubleG = img.getGraphics();
        }
        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, getSize().width, this.getSize().height);
        doubleG.setColor(getForeground());
        paint(doubleG);
        g.drawImage(img, 0, 0, this);
    }

    public void run() {
        try{
        setDisible();
        if (command == "AddFirst") {
            afAni();
        } else if (command == "AddLast") {
            alAni();
        } else if (command == "AddMiddle") {
            amAni();
        } else if (command == "DeleteFirst") {
            dfAni();
        } else if (command == "DeleteLast") {
            dlAni();
        } else if (command == "DeleteMiddle") {
            dmAni();
        }
        setIndex();
        }catch(Exception e){
            
        }
        setEnable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        command = cmd;
        switch (cmd) {
            case "AddFirst":
                Thread t = new Thread(this);
                t.start();
                break;
            case "AddLast":
                Thread t2 = new Thread(this);
                t2.start();
                break;
            case "AddMiddle":
                Thread t3 = new Thread(this);
                t3.start();
                break;
            case "DeleteFirst":
                Thread t4 = new Thread(this);
                t4.start();
                break;
            case "DeleteLast":
                Thread t5 = new Thread(this);
                t5.start();
                break;
            case "DeleteMiddle":
                Thread t6 = new Thread(this);
                t6.start();
                break;
        }
    }
}
```
### Node Class
```java

 class Node {

    Point p = new Point(60, 100);
    Dimension d = new Dimension(100, 40);
    Color c = Color.GREEN;

    boolean showArrow;
    Point start,end,edge;
    
    int v = 100;

    //4 Constructors
    public Node() {
        this(60, 100, Color.LIGHT_GRAY);
    }

    public Node(Color c) {
        this(60, 100, Color.LIGHT_GRAY);
        this.c = c;
    }

   public Node(int  v){
       this();
       this.v =v;
       
   }
    
    public Node(int x, int y) {
        this(x, y, Color.LIGHT_GRAY);
    }

    public Node(int x, int y, Color c) {
        p = new Point(x, y);
        this.c = c;
        edge = new Point(x, y + 20);
        start = new Point(x + 80, y + 20);
        end = new Point(x + 120, y + 20);

    }
    
    public void setColor(Color c){
        this.c = c;
    }

    public Color getColor(){
        return c;
    }
    public Point getStart() {//GET ARROW STARTING 
        return start;
    }

    public Point getEnd() { //ENDING POINT
        return new Point(p.x+120,p.y+20);
    }
    

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Point getEdge() {
        return new Point(p.x, p.y + 20);
    }
    public void setValue(int val){
        v  = val;
    }
    public int getValue(){
        return v;
    }
}
```