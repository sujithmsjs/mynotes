# SpaceFightGame


### File Structure
```pre
+ SpaceFightGame\ 
	|---  pom.xml
	+ \src\main\java\com\suji\spacefight
		|---  App.java
	+ \src\test\java\com\suji\spacefight
		|---  AppTest.java
	+ \src\main\java\com\suji\spacefight\code
		|---  Board.java
		|---  Demo.java
		|---  GameLooper.java
		|---  TestMap.java
	+ \src\main\java\com\suji\spacefight\img
		|---  GameImages.java
		|---  ImageCollection.java
		|---  ImageCollectionLoader.java
		|---  ImageLoader.java
	+ \src\main\java\com\suji\spacefight\sprits
		|---  Bullet.java
		|---  Craft.java
		|---  Enemy.java
		|---  EnemyHandler.java
	+ \src\main\java\com\suji\spacefight\swing
		|---  ColorUtil.java
		|---  MyCanvas.java
		|---  ReusableFrame.java
		|---  SizeUtil.java
	+ \src\main\java\com\suji\spacefight\util
		|---  GameLoop.java
		|---  GameLooper.java
		|---  MShape.java
		|---  ShapesLoader.java
		|---  Sprite.java
		|---  Sprite3.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\spacefight\App.java
3. src\main\java\com\suji\spacefight\code\Board.java
4. src\main\java\com\suji\spacefight\code\Demo.java
5. src\main\java\com\suji\spacefight\code\GameLooper.java
6. src\main\java\com\suji\spacefight\code\TestMap.java
7. src\main\java\com\suji\spacefight\img\GameImages.java
8. src\main\java\com\suji\spacefight\img\ImageCollection.java
9. src\main\java\com\suji\spacefight\img\ImageCollectionLoader.java
10. src\main\java\com\suji\spacefight\img\ImageLoader.java
11. src\main\java\com\suji\spacefight\sprits\Bullet.java
12. src\main\java\com\suji\spacefight\sprits\Craft.java
13. src\main\java\com\suji\spacefight\sprits\Enemy.java
14. src\main\java\com\suji\spacefight\sprits\EnemyHandler.java
15. src\main\java\com\suji\spacefight\swing\ColorUtil.java
16. src\main\java\com\suji\spacefight\swing\MyCanvas.java
17. src\main\java\com\suji\spacefight\swing\ReusableFrame.java
18. src\main\java\com\suji\spacefight\swing\SizeUtil.java
19. src\main\java\com\suji\spacefight\util\GameLoop.java
20. src\main\java\com\suji\spacefight\util\GameLooper.java
21. src\main\java\com\suji\spacefight\util\MShape.java
22. src\main\java\com\suji\spacefight\util\ShapesLoader.java
23. src\main\java\com\suji\spacefight\util\Sprite.java
24. src\main\java\com\suji\spacefight\util\Sprite3.java
25. src\test\java\com\suji\spacefight\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.suji</groupId>
  <artifactId>SpaceFightGame</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>SpaceFightGame</name>
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

#### src\main\java\com\suji\spacefight\App.java

```java

package com.suji.spacefight;

import com.suji.spacefight.code.Board;
import com.suji.spacefight.swing.ReusableFrame;

public class App {

	public static void main(String[] args) {
		Board b = new Board();
		ReusableFrame r = new ReusableFrame(b, "Space Fighter 2.o", false);
		r.setVisible(true);
	}
}

```

---

### 3. Board.java

#### src\main\java\com\suji\spacefight\code\Board.java

```java

package com.suji.spacefight.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.suji.spacefight.sprits.Bullet;
import com.suji.spacefight.sprits.Craft;
import com.suji.spacefight.sprits.Enemy;
import com.suji.spacefight.sprits.EnemyHandler;
import com.suji.spacefight.swing.MyCanvas;
import com.suji.spacefight.util.GameLoop;
import com.suji.spacefight.util.GameLooper;

public class Board extends MyCanvas implements GameLooper {

    
	private static final long serialVersionUID = 1L;
	public static final int DELAY = 10;
    Craft craft;
    GameLoop gameLoop;
    EnemyHandler eh;
    boolean inGame;
    int kills = 0;
    int missed = 0;
    long lastRF, startRF, stopRF, timeTook;
    
    public Board() {
        setFocusable(true);
        addKeyListener(new KA());
        
        eh = new EnemyHandler();
        craft = new Craft();
        craft.setColorAt(1, Color.WHITE);
        gameLoop = new GameLoop(true, this);
        inGame = true;
        lastRF = System.nanoTime();
        gameLoop.start();
        
    }
    
    public void gameDesc(Graphics2D g){
        
        
        g.setColor(Color.DARK_GRAY);
        g.drawString("Enemies in Air :  "+eh.getEnemies().size(),20,30);
        g.drawString("\nBullets in Air  :  "+craft.getBullets().size(),20,60);
        g.drawString("\nKills :  "+kills,20,90);
        g.drawString("\nMissed: "+eh.missed,20,120);
    }

    @Override
    protected void paintComponent(Graphics g) {
        startRF = System.nanoTime();
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        
        eh.draw(g2d);
        
        Font font = new Font("", Font.BOLD, 20);
        g2d.setFont(font);
        gameDesc(g2d);
        
        craft.draw(g2d);
        
        if(! inGame){
            Font font2 = new Font("", Font.BOLD, 150);
            g2d.setFont(font2);
            g2d.setPaint(Color.RED);
            g2d.drawString("GAME OVER", 50, 300);
        }
        
                                                 stopRF = System.nanoTime();
        timeTook = stopRF - startRF;
        System.out.println("timeTook = " + timeTook);
        
        
    }
    
    
    @Override
    public void tick() {
        craft.move();
        eh.move();
        checkCollisions();
    }

    @Override
    public void render() {
        repaint();
    }
     
    

    private void checkCollisions() {
        for (Enemy enemy : eh.getEnemies()) {
            for (Bullet bullet: craft.getBullets()) {
                
                if(bullet.isVisible() && bullet.isCollideWith(enemy)){
                    
                    enemy.setVisible(false);
                    bullet.setVisible(false);
                    kills++;
                                                        break;
                }
            }
            
            if(enemy.isCollideWith(craft)){
                inGame = false;
                               break;
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

    
    
    
    
    
    class KA extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
             craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
           craft.keyPressed(e);
        }
        
    }

}

```

---

### 4. Demo.java

#### src\main\java\com\suji\spacefight\code\Demo.java

```java

package com.suji.spacefight.code;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import com.suji.spacefight.img.GameImages;
import com.suji.spacefight.img.ImageCollection;
import com.suji.spacefight.swing.MyCanvas;
import com.suji.spacefight.swing.ReusableFrame;


public class Demo extends MyCanvas{
   
    
	private static final long serialVersionUID = 1;
	private ImageCollection images;
    private Image background;
    private Image player;
    
    public Demo() {
        images = new ImageCollection(new GameImages());
        background = images.getImage("Background");
        player = images.getImage("Player");
    }

    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;

       g2d.drawImage(background, 0, 0,getWidth(),getHeight(), null);                      
    }
    
    
    
    public static void main(String[] args) {
        Demo demo = new Demo();
        ReusableFrame r = new ReusableFrame(demo, "Just practice",false);
        r.setVisible(true);
    }

}

```

---

### 5. GameLooper.java

#### src\main\java\com\suji\spacefight\code\GameLooper.java

```java

package com.suji.spacefight.code;


public class GameLooper implements Runnable {

    private boolean running;
    private Thread thread;
    
    public GameLooper(boolean running) {
        this.running = running;
        thread = new Thread(this);
    }
    
    public void start(){
        thread.start();
    }
    
    

    private void tick(){
     
    }

    private void render(){
       
    }
    
    public static void main(String[] args) {
        GameLooper gl = new GameLooper(true);
        Thread t = new Thread(gl);
        t.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    
    
}

```

---

### 6. TestMap.java

#### src\main\java\com\suji\spacefight\code\TestMap.java

```java

package com.suji.spacefight.code;

import java.util.HashMap;


public class TestMap {

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("1","Hey");
        map.put("2","Hellow");
        map.put("3","There");
        map.put("1","Heyhey");
        map.put("1","Hey");
        map.put("1","Hey Las");
        map.put("2", "Lash 2");
        System.out.println(map);
        System.out.println(map.size());
    }

}

```

---

### 7. GameImages.java

#### src\main\java\com\suji\spacefight\img\GameImages.java

```java

package com.suji.spacefight.img;

public class GameImages implements ImageCollectionLoader {

    @Override
    public void loadImageCollection(ImageCollection images) {
        images.add("Background", "C:\\Users\\sujit\\OneDrive\\Desktop\\wall.jpg");
        images.add("Player", "C:\\Users\\sujit\\OneDrive\\Desktop\\images\\mario.png");
    }


}

```

---

### 8. ImageCollection.java

#### src\main\java\com\suji\spacefight\img\ImageCollection.java

```java

package com.suji.spacefight.img;

import java.awt.Image;
import java.util.HashMap;


public class ImageCollection {
    
    private HashMap<String,Image> images;
    
    public ImageCollection(ImageCollectionLoader loader){
        this();
        loader.loadImageCollection(this);
    }
    
    public ImageCollection() {
        images = new HashMap<>();
    }
    
    public int getSize(){
       return images.size();
    }
    
    public void add(String name, Image image){
        images.put(name, image);
    }
    
    public void add(String name, String path){
        images.put(name, ImageLoader.getImage(path));
    }
    
    public  Image getImage(String name){
        return images.get(name);
    }
}

```

---

### 9. ImageCollectionLoader.java

#### src\main\java\com\suji\spacefight\img\ImageCollectionLoader.java

```java

package com.suji.spacefight.img;

public interface ImageCollectionLoader {
    
    public void loadImageCollection(ImageCollection images);
    
}

```

---

### 10. ImageLoader.java

#### src\main\java\com\suji\spacefight\img\ImageLoader.java

```java

package com.suji.spacefight.img;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;


public class ImageLoader {

    public static Image getImage(String path) {
        File file = new File(path);
        Image img = null;
        if(file.exists()){
           img = new  ImageIcon(path).getImage();
           System.out.println("Image found: "+img.getWidth(null)+"x"+img.getWidth(null));
        }else{
            System.out.println("Image not found.");
        }
       return img;
    }
}

```

---

### 11. Bullet.java

#### src\main\java\com\suji\spacefight\sprits\Bullet.java

```java

package com.suji.spacefight.sprits;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import com.suji.spacefight.swing.SizeUtil;
import com.suji.spacefight.util.MShape;
import com.suji.spacefight.util.ShapesLoader;
import com.suji.spacefight.util.Sprite;

public class Bullet extends Bullet_ShapesLoader {

	private int dx = 10;

	public Bullet(int x, int y) {
		System.out.println("x:" + x + ";  y:" + y);

		setFrame(x + 56, y + 17, 25, 26);
	}

	public void move() {
		setX(dx + getX());

		if (getX() > SizeUtil.KINDLE.width - getWidth()) {
			setVisible(false);
		}
	}
}

class Bullet_ShapesLoader extends Sprite implements ShapesLoader {

	public Bullet_ShapesLoader() {
		loadShapes(this);
	}

	public void initShapes(List<MShape> shapes) {
		Rectangle2D head2 = new Rectangle2D.Double(290.0, 290.0, 58.0, 29.0);
		shapes.add(new MShape(head2, new Color(255, 255, 255)));

		Arc2D head = new Arc2D.Double(290.0, 261.0, 87.0, 87.0, -92.0, 182.0, 2);
		shapes.add(new MShape(head, new Color(255, 0, 0)));
	}
}
```

---

### 12. Craft.java

#### src\main\java\com\suji\spacefight\sprits\Craft.java

```java

package com.suji.spacefight.sprits;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import com.suji.spacefight.swing.SizeUtil;
import com.suji.spacefight.util.MShape;
import com.suji.spacefight.util.ShapesLoader;
import com.suji.spacefight.util.Sprite;

public class Craft extends Craft_ShapeLoader {

    private List<Bullet> list;

    private int dx = 0, dy = 0;
    private int speed = 10;
    private boolean firing;
    private long lastFired = 0, reloadTime = (long) (1_000_000_000 * 0.25);

    public Craft() {
        setFrame(0, 0, 80, 60);
        list = new ArrayList<>();
    }

    public List<Bullet> getBullets() {
        return list;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        for (Bullet bullet : list) {
            bullet.draw(g2d);
            // System.out.println(bullet.getBounds());
        }
    }

    public void moveCraft() {

        int x = getX() + dx * speed;
        int y = getY() + dy * speed;

        setX(x);
        setY(y);
        if (x >= SizeUtil.KINDLE.width - getWidth()) {
            setX(SizeUtil.KINDLE.width - getWidth());
        }
        if (x < 0) {
            setX(0);
        }
        if (y >= SizeUtil.KINDLE.height - getHeight()) {
            setY(SizeUtil.KINDLE.height - getHeight());
        }
        if (y < 0) {
            setY(0);
        }

    }

    public void move() {
        moveCraft();

        for (Bullet bullet : list) {
            if (bullet.isVisible()) {
                bullet.move();
            } else {
                list.remove(bullet);
                break;
            }
        }
        fire();
    }

    public void fire() {
        long now = System.nanoTime() - lastFired;
        if (firing && now >= reloadTime) {
            Bullet b = new Bullet(getX(), getY());
            list.add(b);
            lastFired = System.nanoTime();
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;

            case KeyEvent.VK_LEFT:
                dx = -1;
                break;

            case KeyEvent.VK_UP:
                dy = -1;
                break;

            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;

            case KeyEvent.VK_SPACE:
                firing = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;

            case KeyEvent.VK_LEFT:
                dx = 0;
                break;

            case KeyEvent.VK_UP:
                dy = 0;
                break;

            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;

            case KeyEvent.VK_SPACE:
                firing = false;
                break;
        }
    }

}

//<editor-fold defaultstate="collapsed" desc="Craft_Loader">
class Craft_ShapeLoader extends Sprite implements ShapesLoader {

    public Craft_ShapeLoader() {
        loadShapes(this);
    }

    @Override
    public void initShapes(List<MShape> shapes) {

        Arc2D head = new Arc2D.Double(290.0, 261.0, 87.0, 87.0, -92.0, 182.0, 2);
        shapes.add(new MShape(head, new Color(255, 0, 0)));

        Rectangle2D body = new Rectangle2D.Double(87.0, 232.0, 261.0, 145.0);
        shapes.add(new MShape(body, new Color(51, 153, 255)));

        Rectangle2D head2 = new Rectangle2D.Double(290.0, 290.0, 58.0, 29.0);
        shapes.add(new MShape(head2, new Color(255, 255, 255)));

        Path2D stripe2 = new Path2D.Double();
        stripe2.moveTo(116.0, 203.0);
        stripe2.lineTo(290.0, 203.0);
        stripe2.lineTo(290.0, 261.0);
        stripe2.lineTo(116.0, 261.0);
        stripe2.lineTo(116.0, 203.0);
        stripe2.lineTo(116.0, 203.0);
        stripe2.closePath();
        shapes.add(new MShape(stripe2, new Color(0, 0, 204)));

        Path2D stripe = new Path2D.Double();
        stripe.moveTo(116.0, 348.0);
        stripe.lineTo(290.0, 348.0);
        stripe.lineTo(290.0, 406.0);
        stripe.lineTo(116.0, 406.0);
        stripe.lineTo(116.0, 348.0);
        stripe.lineTo(116.0, 348.0);
        stripe.closePath();
        shapes.add(new MShape(stripe, new Color(0, 0, 255)));

    }
}
//</editor-fold>

```

---

### 13. Enemy.java

#### src\main\java\com\suji\spacefight\sprits\Enemy.java

```java

package com.suji.spacefight.sprits;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import com.suji.spacefight.util.MShape;
import com.suji.spacefight.util.ShapesLoader;
import com.suji.spacefight.util.Sprite;




public class Enemy extends Enemy_ShapeLoader{

    public Enemy(int x, int y, int w, int h) {
        setFrame(x, y, 100, 50);
    }
    
    
    
}

class Enemy_ShapeLoader extends Sprite implements ShapesLoader{

	public Enemy_ShapeLoader() {
		loadShapes(this);
	}

	@Override
	public void initShapes(List<MShape> shapes) {
		Rectangle2D head = new Rectangle2D.Double(87.0, 232.0, 58.0, 29.0);
		shapes.add(new MShape(head,new Color(255,0,0) ));

		Rectangle2D body = new Rectangle2D.Double(145.0, 203.0, 174.0, 87.0);
		shapes.add(new MShape(body,new Color(51,153,255) ));

		Path2D stripe2 = new Path2D.Double();
			stripe2.moveTo(261.0, 174.0);
			stripe2.lineTo(290.0, 174.0);
			stripe2.lineTo(290.0, 319.0);
			stripe2.lineTo(261.0, 319.0);
			stripe2.lineTo(261.0, 174.0);
			stripe2.lineTo(261.0, 174.0);
			stripe2.closePath();
		shapes.add(new MShape(stripe2,new Color(255,0,255) ));

		Path2D stripe = new Path2D.Double();
			stripe.moveTo(174.0, 174.0);
			stripe.lineTo(203.0, 174.0);
			stripe.lineTo(203.0, 319.0);
			stripe.lineTo(174.0, 319.0);
			stripe.lineTo(174.0, 174.0);
			stripe.lineTo(174.0, 174.0);
			stripe.closePath();
		shapes.add(new MShape(stripe,new Color(255,0,255) ));

	}
}
```

---

### 14. EnemyHandler.java

#### src\main\java\com\suji\spacefight\sprits\EnemyHandler.java

```java

package com.suji.spacefight.sprits;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import com.suji.spacefight.swing.ColorUtil;
import com.suji.spacefight.swing.SizeUtil;


public class EnemyHandler implements ActionListener {
    
    private List<Enemy> list;
    private Timer timer;
    private int delay = 1000;
    private Random ran = new Random();
    private int xVelocity = -5;
    private Dimension size = new Dimension(200,100);
    public int missed=0;
    
    
    public EnemyHandler() {
        list = new ArrayList<Enemy>();
        timer = new Timer(delay, this);
        timer.start();
    }

    public List<Enemy> getEnemies() {
        return list;
    }
    
    
    
    public void move(){
        for (Enemy enemy : list) {
            if(enemy.isVisible()){
                enemy.setX(enemy.getX()+xVelocity);
                if(enemy.getX() < -size.width){
                    missed++;
                    enemy.setVisible(false);
                }
            }else{
                list.remove(enemy);
                break;
            }
        }
    }
    
    public void draw(Graphics2D g){
        for (Enemy enemy : list) {
            enemy.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // Enemy enemy = new Enemy();
       
        Enemy en = new Enemy(SizeUtil.KINDLE.width, ran.nextInt(SizeUtil.KINDLE.height - size.height), size.width, size.height);
        en.setColorAt(1,ColorUtil.nextColor()); 
        list.add(en);
        System.out.println("Enimies alive: "+list.size());
    }
    
    

}

```

---

### 15. ColorUtil.java

#### src\main\java\com\suji\spacefight\swing\ColorUtil.java

```java

package com.suji.spacefight.swing;

import java.awt.Color;
import java.util.Random;



public class ColorUtil {

    public static Color nextColor(){
        Random r = new Random();
        return new Color(Color.HSBtoRGB(1f/50*r.nextInt(50), 1, 1));
    }

}

```

---

### 16. MyCanvas.java

#### src\main\java\com\suji\spacefight\swing\MyCanvas.java

```java

package com.suji.spacefight.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;


public class MyCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MyCanvas() {
        setPreferredSize(SizeUtil.KINDLE);
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;
        /*
        for (int i = 0; i < 50; i++) {
        // Rectangle r = new Rectangle(ran.nextInt(SCREEN_SIZE.width - 50),ran.nextInt(SCREEN_SIZE.height - 50),50,50);
        Rectangle r = new Rectangle(ran.nextInt(w - 50), ran.nextInt(h - 50), 50, 50);
        g2.fill(r);
        }
        */

        // Rectangle r = new Rectangle(SCREEN_SIZE.width/2 - 25, SCREEN_SIZE.height/2 - 25,50,50);
    }
}

```

---

### 17. ReusableFrame.java

#### src\main\java\com\suji\spacefight\swing\ReusableFrame.java

```java

package com.suji.spacefight.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReusableFrame extends JFrame {

    private JPanel board;

    public ReusableFrame(JPanel board, String title, boolean fullScreen) {
        this.board = board;
        add(board);
        setTitle(title);
        if (fullScreen) {
            this.setUndecorated(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            //this.setSize(600, 400);
            this.setResizable(true);
            pack();
            this.setLocationRelativeTo(null);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

```

---

### 18. SizeUtil.java

#### src\main\java\com\suji\spacefight\swing\SizeUtil.java

```java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suji.spacefight.swing;

import java.awt.Dimension;

/**
 *
 * @author sujit
 */
public interface SizeUtil {
    public static final Dimension VIDEO = new Dimension(1280,720);
    public static final Dimension INSTAGRAM = new Dimension(600,600);
    public static final Dimension ICON = new Dimension(57,57);
    public static final Dimension IPOD = new Dimension(1280,720);
    public static final Dimension XGA = new Dimension(1024,768);
    public static final Dimension VGA = new Dimension(640,480);
    public static final Dimension KINDLE = new Dimension(1024,600);
}

```

---

### 19. GameLoop.java

#### src\main\java\com\suji\spacefight\util\GameLoop.java

```java

package com.suji.spacefight.util;


public class GameLoop implements Runnable {

    private GameLooper gameLooper;
    private Thread thread;
    private boolean running;
    
    
    public GameLoop(boolean running,GameLooper gameLooper){
        this.gameLooper = gameLooper;
        this.running = running;
        thread = new Thread(this, "Game Loop");
    }
    
    public void start(){
        thread.start();
    }
    
    
   

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                gameLooper.tick();
                updates++;
                delta--;
            }
            gameLooper.render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    
    
}

```

---

### 20. GameLooper.java

#### src\main\java\com\suji\spacefight\util\GameLooper.java

```java


package com.suji.spacefight.util;

public interface GameLooper {
    public void tick();
    public void render();
}

```

---

### 21. MShape.java

#### src\main\java\com\suji\spacefight\util\MShape.java

```java

package com.suji.spacefight.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class MShape {

    private Shape shape;
    private Color color;
    private boolean visible;

    
    public MShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.visible = true;
    }
    public MShape(Shape shape, Color color,boolean visible) {
        this.shape = shape;
        this.color = color;
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
   
    public void fill(Graphics2D g2d) {
        if(visible){
            g2d.setColor(color);
            g2d.fill(shape);
        }
    }
    
    public void draw(Graphics2D g2d) {
        if(visible){
            g2d.setColor(color);
            g2d.draw(shape);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Rectangle2D getBounds2D(){
       return shape.getBounds2D();
    }
    

    @Override
    public String toString() {
        return "MShape{" + "shape=" + shape.getBounds() + ", color=" + color + ", visible=" + visible + '}';
    }

   
}

```

---

### 22. ShapesLoader.java

#### src\main\java\com\suji\spacefight\util\ShapesLoader.java

```java

package com.suji.spacefight.util;

import java.util.List;

/**
 *
 * @author sujit
 */
public interface ShapesLoader {

    public void initShapes(List<MShape> shapes);
}

```

---

### 23. Sprite.java

#### src\main\java\com\suji\spacefight\util\Sprite.java

```java

package com.suji.spacefight.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

public class Sprite {

    private List<MShape> shapes;
    private ShapesLoader loader;
    private boolean visible = true;
    private boolean loaded;
    private boolean isDimensinsSetted;
    private int size;

    public Sprite() {
        shapes = new ArrayList<>();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void desc() {
        for (MShape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void loadShapes(ShapesLoader loader) {
        if (!loaded) {
            this.loader = loader;

            loader.initShapes(shapes);

            Area area = new Area();
            for (int i = 0; i < shapes.size(); i++) {
                area.add(new Area(shapes.get(i).getShape()));
            }
            shapes.add(new MShape(area, Color.WHITE, false));

            size = shapes.size();

            loaded = true;
        }
    }
    
    public boolean isCollideWith(Sprite shape){
        Area a = new Area(getArea());
        //Area a2  = new Area(shape.getArea());
        return a.intersects(shape.getBounds());
    }

    public void setColorAt(int index, Color color) {
        shapes.get(index).setColor(color);
    }

    public MShape get(int index) {
        return shapes.get(index);
    }

    public int size() {
        return size;
    }

    public Sprite duplicate() {
        Sprite s = new Sprite();
        s.loadShapes(loader);
        return s;
    }

    public void draw(Graphics2D g2d) {
        if (visible) {
            for (MShape shape : shapes) {
                shape.fill(g2d);
            }
        }
    }

    public Shape getArea() {
        return shapes.get(size - 1).getShape();
    }

    public Rectangle getBounds() {
        return getArea().getBounds();
    }

    public Rectangle getDetails(int[] args) {
        Area a = new Area();

        for (int arg : args) {
            a.add(new Area(shapes.get(arg).getShape()));
        }
        return a.getBounds();
    }

    private void moveTo00(int px, int py) {
        Rectangle rect = getBounds();
        for (MShape shape : shapes) {
            AffineTransform at = new AffineTransform();
            int nx = px - rect.x;
            int ny = py - rect.y;
            at.translate(nx, ny);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }

        if (px != getX() || getY() != py) {
            StringBuilder sd = new StringBuilder();
            System.out.println(sd.toString());
            System.out.println("Error in moveTo()");
            System.exit(0);
        }
    }

    public void moveTo(int px, int py) {
        if (px != getX() || getY() != py) {
            moveTo00(px, py);
        }
    }

    public void check(final int x, final int y) {
        final int nX = getX() + x;
        final int nY = getY() + y;
        final int gY = getY();
        System.out.println();
        
        setX(nY);
        
        if (getX() == nX && getY() == gY) {
            System.out.println("nX = " + nX + "; nY = " + y + "; getX(): " + getX() + "; getY(): " + getY());
        }else{
            System.out.println("Error X : nX = " + nX + "; nY = " + y + "; getX(): " + getX() + "; getY(): " + getY());
            System.exit(0);
        }

        setY(nY);
        
        if (getX() == nX && getY() == nY) {
            System.out.println("nX = " + nX + "; nY = " + nY + "; getX(): " + getX() + "; getY(): " + getY());
        }else{
            System.out.println("Error Y:  nX = " + nX + "; nY = " + nY + "; getX(): " + getX() + "; getY(): " + getY());
            System.exit(0);
        }
    }

    public int getX() {
        return getBounds().x;
    }

    public int getY() {
        return getBounds().y;

    }

    public void setX(int x) {
        int last = getY();
        moveTo(x, last);
        if (last != getY()) {
            System.out.println("setX() Error!");
            System.exit(0);
        }
    }

    public void setY(int y) {
        int last = getX();
        moveTo(last, y);
        if (last != getX()) {
            System.out.println("setY() Error!");
            System.exit(0);
        }
    }
    
    public int getWidth(){
        return getBounds().width;
    }
    public int getHeight(){
        return getBounds().height;
    }

    public void setSize(int width, int height) {
        Rectangle2D rect = getArea().getBounds2D();
        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            double w = (1 / rect.getWidth()) * width;
            double h = (1 / rect.getHeight()) * height;

            at.scale(w, h);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }
    }

    public void setFrame(int x, int y, int width, int height) {
        if (!isDimensinsSetted) {
            Rectangle r = shapes.get(0).getShape().getBounds();
            if (r.width == width && r.height == height) {
                System.err.println("No change");
            } else {
                setSize(width, height);
            }
            moveTo(x, y);
            System.out.println("setFrameCalled");
            isDimensinsSetted = true;
        } else {
            System.out.println("Frame can't be called secound time.");
        }

        if (x != getBounds().x || y != getBounds().y /* || width != getBounds().width || height != getBounds().height */) {
            System.out.println("Requested: x:" + x + "    y:" + y + "   width:" + width + "    height:" + height + "");
            Rectangle b = getBounds();

            System.out.println("Requested: x:" + b.x + "    y:" + b.y + "   width:" + b.width + "    height:" + b.height + "");
            System.out.println("Error is setFrame method.");
            System.exit(0);
        }

        if (width != getBounds().width || height != getBounds().height) {
            System.out.println("Requested: x:" + x + "    y:" + y + "   width:" + width + "    height:" + height + "");
            Rectangle b = getBounds();
            System.out.println("Requested: x:" + b.x + "    y:" + b.y + "   width:" + b.width + "    height:" + b.height + "");
            System.out.println("Error is setFrame method.");
        }

    }
}

```

---

### 24. Sprite3.java

#### src\main\java\com\suji\spacefight\util\Sprite3.java

```java

package com.suji.spacefight.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;


public class Sprite3 extends RectangularShape{

    private List<MShape> shapes;
    private ShapesLoader loader;
    private boolean visible = true;
    private int size;
    
    
    public Sprite3() {
        shapes = new ArrayList<>();   
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
    
    public void desc(){
        for (MShape shape : shapes) {
            System.out.println(shape);
        }
    }
    
    
    /**
     * 
     * @return Returns the area
     */
    public Shape getArea(){
        return shapes.get(size - 1).getShape();
    }

    public void loadShapes(ShapesLoader loader) {
        this.loader = loader;
        
        //Loading Shapes
        loader.initShapes(shapes); 
        
       
        
        //Creating Area.
        Area area = new Area();
        for (int i = 0; i < shapes.size() ; i++) {
            area.add(new Area(shapes.get(i).getShape()));
        }
        shapes.add(new MShape(area,Color.WHITE,false));
        
        //Init Size
        size = shapes.size();
        System.out.println(shapes.size());
    }
    
   
    
    public void changeColorAt(int index, Color color){
        shapes.get(index).setColor(color);
    }
    
    public MShape get(int index){
        return shapes.get(index);
    }
    
    public int size(){
        return size;
    }
       
    public Sprite3 duplicate(){
        Sprite3 s = new Sprite3();
        s.loadShapes(loader);
        return s;
    }
    
    
    public void draw(Graphics2D g2d) {
        if(visible){
            for (MShape shape : shapes) {
                shape.fill(g2d);
            }
        }
      //  System.out.println(getBounds());
    }
    
    public void drawFrame(Graphics2D g2d){
        g2d.draw(getBounds2D());
    }
    
    
    
    public void moveTo(double px, double py) {

        Rectangle2D rect = getBounds();
        for (MShape shape : shapes) {

            AffineTransform at = new AffineTransform();
            double nx = px - rect.getX();
            
            double ny = py - rect.getY();
           
            at.translate(nx, ny);
            
            shape.setShape(at.createTransformedShape(shape.getShape()));
            //System.out.println("moveTo is called.");
        }
    }
    
    public void setX(double x){
        moveTo(x, getY());
    }
    
    public void setY(double y){
        moveTo(getX(),y);
    }
    
    public void setSize(double width, double height) {
        Rectangle2D rect = getArea().getBounds2D();
        for (MShape shape : shapes) {
            
            AffineTransform at = new AffineTransform();
            double w =  (1 / rect.getWidth()  ) * width;
            double h =  (1 / rect.getHeight() ) * height;

            
            
            at.scale(w, h);
          //  System.out.println(w);
          //  System.out.println(h);
            shape.setShape(at.createTransformedShape(shape.getShape()));
        }
    }
    
    @Override
    public double getX() {
        return getBounds2D().getX();
    }

    @Override
    public double getY() {
        return getBounds2D().getY();
    }

    @Override
    public double getWidth() {
       return getBounds2D().getWidth();
    }

    @Override
    public double getHeight() {
       return getBounds2D().getHeight();
    }

    @Override
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    @Override
    public void setFrame(double x, double y, double width, double height) {
        Rectangle r = shapes.get(0).getShape().getBounds();
        if (r.width == width && r.height == height) {
            
            System.err.println("No change");
            
        } else {
            setSize(width, height);
        }
        moveTo(x, y);
        System.out.println("setFrameCalled");
    }

    @Override
    public Rectangle2D getBounds2D() {
        return getArea().getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return getArea().contains(x, y);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return getArea().intersects(x, y, w, h);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return getArea().contains(x, y, w, h);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return getArea().getPathIterator(at);
    }
}

```

---

### 25. AppTest.java

#### src\test\java\com\suji\spacefight\AppTest.java

```java

package com.suji.spacefight;

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

