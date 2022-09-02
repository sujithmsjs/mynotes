
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WA extends WindowAdapter {

    
    
    @Override
    public void windowLostFocus(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowLostFocus(e)");
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowGainedFocus(e)");
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowStateChanged(e)");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowDeactivated(e)");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowActivated(e)");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowDeiconified(e)");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowIconified(e)");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowClosed(e)");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowClosing(e)");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println( e.getSource().getClass().getSimpleName()+" : windowOpened(e)");
    }
}
