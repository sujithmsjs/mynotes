package learn.oops.cls.c1;

import game.loop.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;


public class Check extends JPanel implements GameLooper {

    GameLoop gameLoop;

    public Check() {
        gameLoop = new GameLoop(this,1000);
        gameLoop.start();
    }
    
    public static void main(String[] args) {
        Check c = new Check();
    }

    @Override
    public void tick() {
        System.out.println("Tick");
    }

    @Override
    public void render() {
        System.out.println("Render");
    }

}

class GameLoop extends TimerTask{

    private GameLooper gameLooper;
    private Timer timer;
    private final long DELAY;

    public GameLoop(GameLooper gameLooper, final int delay) {
        this.gameLooper = gameLooper;
        timer = new Timer("GameLoop");
        DELAY = delay;
    }
    
    public void start(){
        timer.schedule(this,0,DELAY);
    }

    @Override
    public void run() {
        gameLooper.tick();
        gameLooper.render();
    }
}

interface GameLooper{
    public void tick();
    public void render();
}