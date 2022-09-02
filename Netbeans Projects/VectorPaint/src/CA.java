
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class CA extends ComponentAdapter {

    @Override
    public void componentHidden(ComponentEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : componentHidden(e)");
    }

    @Override
    public void componentShown(ComponentEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : componentShown(e)");
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : componentMoved(e)");
    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println(e.getSource().getClass().getSimpleName()+" : componentResized(e)");
    }

}
