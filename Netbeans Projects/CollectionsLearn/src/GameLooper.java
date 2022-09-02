
public class GameLooper implements Runnable {

    private boolean running;

    public GameLooper(boolean running) {
        this.running = running;
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
