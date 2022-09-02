
import java.awt.Font;
import java.awt.Graphics2D;


public class Score {

    private long start, now , diff;

    public Score() {
        start = System.nanoTime();
    }
    
    public void updateScoure(){
        long cr = 1_000_000_0;
        
        now = System.nanoTime();
        
        diff =  (now - start)/cr;
    }
    
    public void drawScore(Graphics2D g){
        g.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,24));
        g.drawString("Scire "+diff, 20, 100);
    }
}
