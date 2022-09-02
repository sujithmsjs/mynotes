package game.loops;


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
             //   System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    
    
}
