package oracle.imgs;

import com.zetcode.Util;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random;

class JumbledImage extends Component {

    private int numlocs = 2;
    private int numcells = numlocs * numlocs;
    private int[] cells;
    private BufferedImage bi;
    int w, h, cw, ch;

    public JumbledImage(File imageFile) {
        try {
            bi = ImageIO.read(imageFile);
            w = bi.getWidth(null);
            h = bi.getHeight(null);
        } catch (IOException e) {
            System.out.println("Image could not be read");
//            System.exit(1);
        }
        cw = w / numlocs;
        ch = h / numlocs;
        cells = new int[numcells];
        for (int i = 0; i < numcells; i++) {
            cells[i] = i;
        }
    }

    void jumble() {
        Random rand = new Random();
        int ri;
        for (int i = 0; i < numcells; i++) {
            while ((ri = rand.nextInt(numlocs)) == i);

            int tmp = cells[i];
            cells[i] = cells[ri];
            cells[ri] = tmp;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    public void paint(Graphics g) {

        int dx, dy;
        for (int x = 0; x < numlocs; x++) {
            int sx = x * cw;
            for (int y = 0; y < numlocs; y++) {
                int sy = y * ch;
                int cell = cells[x * numlocs + y];
                dx = (cell / numlocs) * cw;
                dy = (cell % numlocs) * ch;
                g.drawImage(bi,
                        dx, dy, dx + cw, dy + ch,
                        sx, sy, sx + cw, sy + ch,
                        null);
            }
        }
    }
}

public class JumbledImageApplet extends JPanel {

    static String imageFileName = Util.imageLoc;
    private JumbledImage jumbledImage;

    public JumbledImageApplet(File imageFile)
    {
        jumbledImage = new JumbledImage(imageFile);

    }


    public void buildUI() {
        File imageFile = new File(Util.imageLoc);
        
        final JumbledImage ji = new JumbledImage(imageFile);
        add("Center", ji);
        JButton jumbleButton = new JButton("Jumble");
        jumbleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                ji.jumble();
                ji.repaint();
            }
        ;
        });
        Dimension jumbleSize = ji.getPreferredSize();
        resize(jumbleSize.width, jumbleSize.height + 40);
        add("South", jumbleButton);
    }

    public static void main(String s[]) {
        JFrame f = new JFrame("Jumbled Image");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        JumbledImageApplet jumbler = new JumbledImageApplet(new File(Util.imageLoc));
        jumbler.buildUI();
        //f.add("Center", jumbler);
        f.add(jumbler);
        f.pack();
        f.setVisible(true);
    }
}
