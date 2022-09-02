package oracle.imgs;
import com.zetcode.Util;
import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class SaveImage2 extends Component implements ActionListener {

    String descs[] = {
        "Original",
        "Convolve : LowPass",
        "Convolve : Sharpen",
        "LookupOp",};

    int opIndex;
    private BufferedImage bi, biFiltered;
    int w, h;


    public SaveImage2() {
        try {
            
            bi = ImageIO.read(new File(Util.imageLoc));
            
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                
                BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                
              
                
                big.drawImage(bi, 0, 0, null);

                biFiltered = bi2;
                bi = bi2;
            }
        } catch (IOException e) {
            
            System.out.println("Image could not be read");
            System.exit(1);
            
        }
    } 

    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    String[] getDescriptions() {
        return descs;
    }

    void setOpIndex(int i) {
        opIndex = i;
    }

    public void paint(Graphics g) {
        g.drawImage(biFiltered, 0, 0, null);
    }

    int lastOp;

    

    /* Return the formats sorted alphabetically and in lower case */
    public String[] getFormats() {
        String[] formats = ImageIO.getWriterFormatNames();
        
        TreeSet<String> formatSet = new TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
            System.out.println(s);
        }
        return formatSet.toArray(new String[0]);
    }

    public void actionPerformed(ActionEvent e) {
        
        JComboBox cb = (JComboBox) e.getSource();
        if (cb.getActionCommand().equals("SetFilter")) {
            setOpIndex(cb.getSelectedIndex());
            repaint();
        } else if (cb.getActionCommand().equals("Formats")) {
            /* Save the filtered image in the selected format.
              * The selected item will be the name of the format to use
             */
            String format = (String) cb.getSelectedItem();
            /* Use the format name to initialise the file suffix.
              * Format names typically correspond to suffixes
             */
            File saveFile = new File("savedimage." + format);
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(saveFile);
            int rval = chooser.showSaveDialog(cb);
            if (rval == JFileChooser.APPROVE_OPTION) {
                saveFile = chooser.getSelectedFile();
                /* Write the filtered image in the selected format,
                  * to the file chosen by the user.
                 */
                try {
                    ImageIO.write(biFiltered, format, saveFile);
                    if(saveFile.exists()){
                        System.out.println("Image saved successfully.");
                    }else{
                        System.out.println("Image Not saved.");
                    }
                    
                } catch (IOException ex) {
                }
            }
        }
    }

    ;

    public static void main(String s[]) {
        JFrame f = new JFrame("Save Image Sample");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        
        
        SaveImage2 si = new SaveImage2();
        f.add("Center", si);


        JComboBox formats = new JComboBox(si.getFormats());
        formats.setActionCommand("Formats");
        
        formats.addActionListener(si);
        JPanel panel = new JPanel();

        panel.add(new JLabel("Save As"));
        panel.add(formats);
        f.add("South", panel);
        f.pack();
        f.setVisible(true);
    }
}
