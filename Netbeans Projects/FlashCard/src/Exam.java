
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import java.awt.Container;
import java.awt.Dimension;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Exam extends javax.swing.JFrame {

    private ExamPage ep;
    private String ans;
    
    public Exam(ArrayList<String> list) {
        initComponents();
        setLocationRelativeTo(null);
        Collections.shuffle(list);
        ep = new ExamPage(list);
        System.out.println(ep);
        loadWord();
    }
   
    
    private void loadWord(){
        String title = null;
        if(ep.hasNext()){
            title = ep.next();
            Word word = Word.getWord(title);
            applyWord(word);
            ans = word.getTitle();
        }else{
            JOptionPane.showMessageDialog(null, "title", "Test has been completed.", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void applyWord(Word w){
        briefTa.setText(w.getBrief());
        notesTa.setText(w.getNotes());
        InputStream image1 = w.getImage();
        if(image1!=null){
            ImageIcon imageIcon = BlobImageUtil.getImageIcon(image1);
            if (imageIcon != null) {
                image.setIcon(imageIcon);
                image.setVisible(true);
            } else {
                image.setIcon(null);
                image.setVisible(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleTf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        briefTa = new javax.swing.JTextArea();
        image = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesTa = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Exam");

        titleTf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleTf.setForeground(new java.awt.Color(102, 255, 255));
        titleTf.setMargin(new java.awt.Insets(0, 5, 0, 5));

        briefTa.setEditable(false);
        briefTa.setColumns(20);
        briefTa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        briefTa.setForeground(new java.awt.Color(255, 51, 51));
        briefTa.setRows(2);
        briefTa.setFocusable(false);
        briefTa.setMargin(new java.awt.Insets(7, 7, 7, 7));
        jScrollPane1.setViewportView(briefTa);

        image.setBackground(new java.awt.Color(255, 51, 51));
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setSize(400, 200);
        image.setBorder(javax.swing.BorderFactory.createTitledBorder("Image"));
        image.setPreferredSize(new java.awt.Dimension(400, 600));
        image.setRequestFocusEnabled(false);

        notesTa.setEditable(false);
        notesTa.setColumns(20);
        notesTa.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        notesTa.setRows(5);
        notesTa.setFocusable(false);
        notesTa.setMargin(new java.awt.Insets(7, 7, 7, 7));
        jScrollPane2.setViewportView(notesTa);

        jLabel1.setText("Answer:");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setMnemonic('N');
        jMenuItem7.setText("Add Word");
        jMenu1.add(jMenuItem7);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setMnemonic('O');
        jMenuItem4.setText("Open");
        jMenuItem4.setToolTipText("Opens all the words.");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setMnemonic('x');
        jMenuItem5.setText("Exit");
        jMenuItem5.setToolTipText("");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Display");

        jMenuItem1.setText("Night Owl");
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Early Bird");
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTf, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleTf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String text = titleTf.getText();
        if(ans.equalsIgnoreCase(text)){
            ep.saidAns(text, true);
        }else{
            ep.saidAns(text, false);
        }
        
        loadWord();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static final JScrollPane getScrollPane(JComponent component) {
        Container p = component.getParent();
        if (p instanceof JViewport) {
            Container gp = p.getParent();
            if (gp instanceof JScrollPane) {
                return (JScrollPane) gp;
            }
        }
        return null;

    } 
    
        public void imageAdded(){
        image.setVisible(true);

    }
    public void imageDeleted(){
        

    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            NoireLookAndFeel.setTheme("Large-Font");
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            
            
            /* Create and display the form */   
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
//                    new Exam().setVisible(true);
                    System.out.println(NoireLookAndFeel.getTheme().getName());
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AddWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AddWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AddWord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea briefTa;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea notesTa;
    private javax.swing.JTextField titleTf;
    // End of variables declaration//GEN-END:variables

    private void clearFields() {
        image.setIcon(null);
        image.setVisible(false);
        titleTf.setText("");
        briefTa.setText("");
        notesTa.setText("");
    
    }
}
