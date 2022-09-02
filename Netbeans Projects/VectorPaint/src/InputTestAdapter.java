
import java.awt.event.*;
 public class InputTestAdapter implements MouseListener,MouseMotionListener,MouseWheelListener,KeyListener{

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : keyReleased(e)"); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : keyPressed(e)"); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : keyTyped(e)"); 
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseWheelMoved(e)"); 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseMoved(e)"); 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseDragged(e)"); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseExited(e)"); 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseEntered(e)"); 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseReleased(e)"); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mousePressed(e)"); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : mouseClicked(e)"); 
    }
    
}
