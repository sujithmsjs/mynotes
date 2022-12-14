/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suji.pro1.note;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import javax.swing.JTextArea;

/**
 *
 * @author sujit
 */
public class TextFormatDialog extends javax.swing.JDialog {

    /**
     * Creates new form TextFormatDialog
     */
    
    
    private JTextArea area;
    private Theme themes;
    
    
    
    public TextFormatDialog(java.awt.Frame parent,JTextArea area,Theme themes,boolean modal) {
        super(parent, modal);
        
        this.themes = themes;
         
        initComponents();
        
        
        this.area = area;
        
       // jComboBox1.setSelectedIndex(0);
        
        
        setLocationRelativeTo(parent);
        
        fonts.setSelectedItem(area.getFont().getName());
        style.setSelectedIndex(area.getFont().getStyle());
        size.setValue(area.getFont().getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fonts = new javax.swing.JComboBox<>();
        size = new javax.swing.JSpinner();
        style = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        themeName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        fonts.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fonts.setModel(new javax.swing.DefaultComboBoxModel<>(getAllFonts()));
        fonts.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fontsItemStateChanged(evt);
            }
        });
        fonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontsActionPerformed(evt);
            }
        });

        size.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        size.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sizeStateChanged(evt);
            }
        });

        style.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PLAIN", "BOLD", "ITALIC", "BOTH" }));
        style.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                styleItemStateChanged(evt);
            }
        });
        style.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Save Theme");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(themes.getAllThemes()));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        themeName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(style, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(size, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(themeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fonts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(style, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(size))
                .addGap(18, 18, 18)
                .addComponent(fonts, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(themeName)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fontsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fontsItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            String fontName = fonts.getSelectedItem().toString();
            
            System.out.println("Changed.");
            Font font = new Font(fontName, Font.PLAIN, 20);
            System.out.println(fontName);
            area.setFont(font);
        }
    }//GEN-LAST:event_fontsItemStateChanged

    private void styleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleActionPerformed
   
    }//GEN-LAST:event_styleActionPerformed

    private void sizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sizeStateChanged
        
        int n = (int) size.getValue();
        System.out.println(n);
        setFontSize(n);
        
    }//GEN-LAST:event_sizeStateChanged

    private void styleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_styleItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            int index = style.getSelectedIndex();
            area.setFont(area.getFont().deriveFont(index));
        }
    }//GEN-LAST:event_styleItemStateChanged

    private void fontsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fontsActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
      
      String name = jComboBox1.getSelectedItem().toString();
     // themeName.setText(name);
     
      themes.setTheme(name);
        
      
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = themeName.getText();
        boolean isSaved = themes.saveTheme(name);
        
        if(isSaved){
            dispose();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void setFontSize(int size){
         area.setFont(area.getFont().deriveFont((float)size));
    }
    
    
    
    private String[] getAllFonts(){
        GraphicsEnvironment ge;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] names = ge.getAvailableFontFamilyNames();
        return names;
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> fonts;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JSpinner size;
    private javax.swing.JComboBox<String> style;
    private javax.swing.JTextField themeName;
    // End of variables declaration//GEN-END:variables
}
