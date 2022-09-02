# PongGame


### File Structure
```pre
+ PongGame\ 
	|---  pom.xml
	+ \src\main\java\com\suji\ponggame
		|---  App.java
	+ \src\test\java\com\suji\ponggame
		|---  AppTest.java
	+ \src\main\java\com\suji\ponggame\frame
		|---  GameFrame.java
		|---  GamePanel.java
	+ \src\main\java\com\suji\ponggame\sprites
		|---  Ball.java
		|---  Paddle.java
		|---  Score.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\ponggame\App.java
3. src\main\java\com\suji\ponggame\frame\GameFrame.java
4. src\main\java\com\suji\ponggame\frame\GamePanel.java
5. src\main\java\com\suji\ponggame\sprites\Ball.java
6. src\main\java\com\suji\ponggame\sprites\Paddle.java
7. src\main\java\com\suji\ponggame\sprites\Score.java
8. src\test\java\com\suji\ponggame\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.suji</groupId>
  <artifactId>PongGame</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>PongGame</name>
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

#### src\main\java\com\suji\ponggame\App.java

```java

package com.suji.ponggame;


public class App 
{
    public static void main( String[] args )
    {
        new com.suji.ponggame.frame.GameFrame();
    }
}

```

---

### 3. GameFrame.java

#### src\main\java\com\suji\ponggame\frame\GameFrame.java

```java

package com.suji.ponggame.frame;

import java.awt.Color;

import javax.swing.JFrame;



public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	GamePanel panel;
    
    public GameFrame() {
        panel = new GamePanel();
        add(panel);
        setTitle("Pong Game");
        setResizable(false);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

}

```

---

### 4. GamePanel.java

#### src\main\java\com\suji\ponggame\frame\GamePanel.java

```java

package com.suji.ponggame.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.suji.ponggame.sprites.Ball;
import com.suji.ponggame.sprites.Paddle;
import com.suji.ponggame.sprites.Score;

import java.util.*;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.55);
    public static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static int BALL_DIAMETER = 20;

    public static final int PADDLE_WIDTH = 25;
    public static final int PADDLE_HEIHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    public GamePanel() {
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        setFocusable(true);
        addKeyListener(new AL());
        setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall() {
        random = new Random();
        // ball = new Ball((GAME_WIDTH/2-BALL_DIAMETER/2),GAME_HEIGHT/2-BALL_DIAMETER/2,BALL_DIAMETER,BALL_DIAMETER);
        ball = new Ball((GAME_WIDTH / 2 - BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddle() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIHT / 2), PADDLE_WIDTH, PADDLE_HEIHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIHT / 2), PADDLE_WIDTH, PADDLE_HEIHT, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
      //  super.paintComponent(g);
                
                image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    
    
    
    /*
    @Override
    public void paint(Graphics g) {
    
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0,this);
    
    // draw(g);
    }
    
    */
     public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    //This stops paddles
    public void checkCollision() {

        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        //This bounds ball off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Optional for more diffulty;
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //Optional
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Optional for more diffulty;
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //Optional
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIHT;
        }

        //Give a player 1 point and new Game.
        if (ball.x <= 0) {
            score.player2++;
            newPaddle();
            newBall();
            System.out.println("Player 2 Score: " + score.player2);
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddle();
            newBall();
            System.out.println("Player 1 Score: " + score.player1);
        }
    }

    @Override
    public void run() {
        //Game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
                //System.out.println("Test");
            }
        }

        /*        while (true) {
        try {
        Thread.sleep(10);
        move();
        checkCollision();
        repaint();
        } catch (InterruptedException ex) {
        System.out.println(ex);
        }
        }*/
    }

    public class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

    }

}

```

---

### 5. Ball.java

#### src\main\java\com\suji\ponggame\sprites\Ball.java

```java

package com.suji.ponggame.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	Random random = new Random();
    public int xVelocity;
    public int yVelocity;
    public int initialSpeed = 2;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection*initialSpeed);
        
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection++;
        }
        setYDirection(randomYDirection*initialSpeed);


    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
       g.setColor(Color.WHITE);
       g.fillOval(x, y, width, height);
    }
    
    
    

}

```

---

### 6. Paddle.java

#### src\main\java\com\suji\ponggame\sprites\Paddle.java

```java

package com.suji.ponggame.sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Paddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	int id;
    int yVelocity;
    int speed = 10;
    
    public Paddle(int x, int y, int width, int height, int id) {
       super(x,y,width,height);
       this.id = id;
    }
    
   
    public void keyReleased(KeyEvent e) {
        System.out.println("Key pressed.");
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0); 
                  //  move();
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                  //  move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                  //  move();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                  //  move();
                }
                break;
        }
        move();
    }

    public void keyPressed(KeyEvent e) {
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    //move();
                }
                
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                   // move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                   // move();
                }
                
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                  //  move();
                }
                break;           
        }
        move();
        
    }
    
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y += yVelocity;
    }
    
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }else{
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
            
        }
    }
    
    
}

```

---

### 7. Score.java

#### src\main\java\com\suji\ponggame\sprites\Score.java

```java

package com.suji.ponggame.sprites;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    public int player1;
    public int player2;
    
    public Score(int width,int height) {
        GAME_HEIGHT = height;
        GAME_WIDTH   = width;
    }

    

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consoloas",Font.PLAIN,60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), GAME_WIDTH/2-85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), GAME_WIDTH/2+20, 50);
    }
    
    

}

```

---

### 8. AppTest.java

#### src\test\java\com\suji\ponggame\AppTest.java

```java

package com.suji.ponggame;

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

