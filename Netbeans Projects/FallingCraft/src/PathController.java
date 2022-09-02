

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class PathController implements ActionListener {
    
    public final int MAX_STARS = 10;
    private final int DELAY = 500;
    private List<Star> stars; 
    private Timer timer;
    
    public PathController() {
         stars = new ArrayList<>();
         timer = new Timer(DELAY, this);
    }
    
    public void start(){
        timer.start();
    }
    
    public void stop(){
        timer.stop();
    }

    public List<Star> getStars() {
        return stars;
    }
    
    
    public void addStar(){
        try {
            
            Random random = new Random();
            Star star = new Star();
            star.setLocation(Board.SCREEN_SIZE.width, (random.nextInt((int) ( Board.SCREEN_SIZE.height - star.getHeight() ))));
            stars.add(star);
            System.out.println("Starts Adding thread in addStar(): "+Thread.currentThread().getId());
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void draw(Graphics2D g){
        for (Star star : stars) {
            star.drawImage(g);
        }
        System.out.println("Drawing Thread: "+Thread.currentThread().getId());
        System.out.println("Drawing Thread Name: "+Thread.currentThread().getName());
    }
    
    public void move(){
        for (Star star : stars) {
            if(star.isVisible()){
                star.move();
            }else{
                stars.remove(star);
                break;
            }
        }
    }
    
    public boolean checkCollision(Rectangle r){
        boolean iscollided = false;
        for (Star star : stars) {
            if(r.intersects(star)){
                iscollided = true;
                break;
            }
        }
        return iscollided;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(MAX_STARS>=stars.size()){
            addStar();
        }else{
            System.out.println("Reached max.");
        }
         System.out.println("Starts Adding thread: "+Thread.currentThread().getId());
         System.out.println("Starts Adding thread Name: "+Thread.currentThread().getName());
    }
    
    
    
    


}
