# SnakeGame


### File Structure
```pre
+ SnakeGame\ 
	|---  pom.xml
	+ \src\main\java\com\suji\snake
		|---  App.java
		|---  GameFrame.java
		|---  GamePanel.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\snake\App.java
3. src\main\java\com\suji\snake\GameFrame.java
4. src\main\java\com\suji\snake\GamePanel.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.suji</groupId>
  <artifactId>SnakeGame</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>SnakeGame</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

</project>

```

---

### 2. App.java

#### src\main\java\com\suji\snake\App.java

```java

package com.suji.snake;

public class App 
{
    public static void main( String[] args )
    {
    	new GameFrame();
    }
}

```

---

### 3. GameFrame.java

#### src\main\java\com\suji\snake\GameFrame.java

```java

package com.suji.snake;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GameFrame() {
        
        intiComponents();
    }

    private void intiComponents() {
        this.setTitle("Snake Game");
        this.setResizable(false);
         
        GamePanel gp = new GamePanel();
        this.add(gp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
       
    }

        

}

```

---

### 4. GamePanel.java

#### src\main\java\com\suji\snake\GamePanel.java

```java

package com.suji.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final boolean MORTAL = true;

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 30;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 100;
	final int[] x = new int[GAME_UNITS];
	final int[] y = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	Point apple = new Point();
	char directions = 'R';
	boolean isRunning;
	Timer timer;
	Random random;

	public GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		newApple();
		isRunning = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void showGrids(Graphics g) {
		// Draw Vertical Grids
		for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
		}

		// Draw Horizontal Grids
		for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
		}
	}

	public void draw(Graphics g) {
		if (isRunning) {
			// showGrids(g);
			// g.setColor(new
			// Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			// g.setColor(Color.GREEN);
			g.fillOval(apple.x, apple.y, UNIT_SIZE, UNIT_SIZE);

			// drawSnake(g);
			snakeTheam02(g);

			drawScoreBoard(g);
		} else {
			gameOver(g);
			drawScoreBoard(g);
		}

	}

	// Draw Snake
	public void drawSnake(Graphics g) {

		for (int i = 0; i < bodyParts; i++) {
			if (i == 0) { // Snake Head
				g.setColor(Color.red);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			} else {
				g.setColor(Color.PINK);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	}

	// Snake Theame
	private void snakeTheam02(Graphics g) {
		for (int i = 0; i < bodyParts; i++) {
			if (i == 0) { // Snake Head
				// g.setColor(new
				// Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.setColor(Color.RED);
				g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

				g.drawOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			} else {
				g.setColor(Color.WHITE);
				// g.setColor(new
				// Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.drawOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

			}

		}
	}

	private void snakeTheam01(Graphics g) {
		for (int i = 0; i < bodyParts; i++) {
			if (i == 0) { // Snake Head
				g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				// g.setColor(Color.RED);
				g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

				g.drawOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			} else {
				g.setColor(Color.WHITE);
				// g.setColor(new
				// Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				g.drawOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	}

	public void drawScoreBoard(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		FontMetrics fontMetrics = this.getFontMetrics(g.getFont());
		String text = "Score : " + applesEaten;
		int stringWidth = fontMetrics.stringWidth(text);

		g.drawString(text, (SCREEN_WIDTH - stringWidth) / 2, UNIT_SIZE * 2);
	}

	public void newApple() {
		apple.x = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
		apple.y = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (directions) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	public void checkApple() {
		if (x[0] == apple.x && y[0] == apple.y) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}

	public void checkCollisions() {
		for (int i = bodyParts; i > 0; i--) {
			// Checks Head Colides with body
			if (x[0] == x[i] && y[0] == y[i]) {
				isRunning = false;
			}
		}
		// Check if Head touches to Left screen.
		if (x[0] < 0) {
			isRunning = false;
		}
		// Check if Head touches to Right screen.
		if (x[0] > SCREEN_WIDTH) {
			isRunning = false;
		}
		// Check if Head touches to Top screen.
		if (y[0] < 0) {
			isRunning = false;
		}
		// Check if Head touches to Down screen.
		if (y[0] > SCREEN_HEIGHT) {
			isRunning = false;
		}
	}

	public void gameOver(Graphics g) {
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics fontMetrics = this.getFontMetrics(g.getFont());
		String text = "Game Over";
		int stringWidth = fontMetrics.stringWidth("Game Over");

		g.drawString(text, (SCREEN_WIDTH - stringWidth) / 2, SCREEN_HEIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			move();
			checkApple();

			if (MORTAL) {
				checkCollisions();
			}

		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (directions != 'R') {
					directions = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (directions != 'L') {
					directions = 'R';
				}
				break;

			case KeyEvent.VK_DOWN:
				if (directions != 'U') {
					directions = 'D';
				}
				break;

			case KeyEvent.VK_UP:
				if (directions != 'D') {
					directions = 'U';
				}
				break;

			}
		}
	}

}

```

---

