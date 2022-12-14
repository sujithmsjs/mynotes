/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic.literals;

import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;



/**
 *
 * @author sujit
 */
public class BitWiseTest extends javax.swing.JFrame {

    /**
     * Creates new form BitWiseTest
     */
    public BitWiseTest() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        binaryStr = new javax.swing.JTextField();
        decimalStr = new javax.swing.JSpinner();
        rs2 = new javax.swing.JButton();
        rs = new javax.swing.JButton();
        ls = new javax.swing.JButton();
        ls1 = new javax.swing.JButton();
        binaryStr2 = new javax.swing.JTextField();
        binaryStr3 = new javax.swing.JTextField();
        binaryStr1 = new javax.swing.JTextField();
        decimalStr1 = new javax.swing.JSpinner();
        decimalStr2 = new javax.swing.JSpinner();
        and = new javax.swing.JButton();
        or = new javax.swing.JButton();
        xor = new javax.swing.JButton();
        binaryStr4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        binaryStr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        binaryStr.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        decimalStr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        decimalStr.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalStrStateChanged(evt);
            }
        });

        rs2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rs2.setText(">>>");
        rs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rs2ActionPerformed(evt);
            }
        });

        rs.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rs.setText(">>");
        rs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsActionPerformed(evt);
            }
        });

        ls.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ls.setText("<<");
        ls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lsActionPerformed(evt);
            }
        });

        ls1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ls1.setText("~");
        ls1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ls1ActionPerformed(evt);
            }
        });

        binaryStr2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        binaryStr2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        binaryStr3.setEditable(false);
        binaryStr3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        binaryStr3.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        binaryStr1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        binaryStr1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        decimalStr1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        decimalStr1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalStr1StateChanged(evt);
            }
        });

        decimalStr2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        decimalStr2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalStr2StateChanged(evt);
            }
        });

        and.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        and.setText("&");
        and.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andActionPerformed(evt);
            }
        });

        or.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        or.setText("|");
        or.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orActionPerformed(evt);
            }
        });

        xor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        xor.setText("^");
        xor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xorActionPerformed(evt);
            }
        });

        binaryStr4.setEditable(false);
        binaryStr4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        binaryStr4.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(binaryStr3)
                            .addComponent(binaryStr2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(binaryStr1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(binaryStr, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(339, 339, 339)
                                .addComponent(rs2)
                                .addGap(18, 18, 18)
                                .addComponent(rs)
                                .addGap(18, 18, 18)
                                .addComponent(ls, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ls1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(and)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(or)
                        .addGap(18, 18, 18)
                        .addComponent(xor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(decimalStr2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(decimalStr1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(decimalStr)
                    .addComponent(binaryStr4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryStr, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rs2)
                    .addComponent(rs)
                    .addComponent(ls)
                    .addComponent(ls1))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryStr1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalStr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryStr2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decimalStr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryStr3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(binaryStr4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(and)
                    .addComponent(or)
                    .addComponent(xor))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void decimalStrStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalStrStateChanged
        spinnerStateChanged(evt);
    }//GEN-LAST:event_decimalStrStateChanged

    private void spinnerStateChanged(ChangeEvent evt){
        JSpinner sp = (JSpinner) evt.getSource();
        int n = (int) sp.getValue();
        
        String s =getBinaryString(n);

        if (sp.equals(decimalStr)) {
            binaryStr.setText(splitFixedLength(s, 4));
        }else
        if (sp.equals(decimalStr1)) {
            binaryStr1.setText(splitFixedLength(s, 4));
        }else
        if (sp.equals(decimalStr2)) {
            binaryStr2.setText(splitFixedLength(s, 4));
        }
    }
    
    
    
    private void rs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rs2ActionPerformed
       int n =  (int) decimalStr.getValue();
       n >>>= 1 ;
        System.out.println(n);
       decimalStr.setValue(n);
    }//GEN-LAST:event_rs2ActionPerformed

    private void rsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsActionPerformed
        int n = (int) decimalStr.getValue();
        n >>= 1;
        System.out.println(n);
        decimalStr.setValue(n);
    }//GEN-LAST:event_rsActionPerformed

    private void lsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lsActionPerformed
        int n = (int) decimalStr.getValue();
        n <<= 1;
        System.out.println(n);
        decimalStr.setValue(n);
    }//GEN-LAST:event_lsActionPerformed

    private void ls1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ls1ActionPerformed
        int n = (int) decimalStr.getValue();
        n = ~n;
        System.out.println(n);
        decimalStr.setValue(n);
    }//GEN-LAST:event_ls1ActionPerformed

    private void decimalStr1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalStr1StateChanged
        spinnerStateChanged(evt);
    }//GEN-LAST:event_decimalStr1StateChanged

    private void decimalStr2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalStr2StateChanged
        spinnerStateChanged(evt);
    }//GEN-LAST:event_decimalStr2StateChanged

    private void andActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andActionPerformed
        buttionPressed(evt);
    }//GEN-LAST:event_andActionPerformed
    private void buttionPressed(ActionEvent evt){
        JButton b = (JButton) evt.getSource();
        
        int n1 = (int) decimalStr1.getValue();
        int n2 = (int) decimalStr2.getValue();
        int ans = 0;
        
        
        if(b.equals(and)){
            ans = n1&n2;
        }else if(b.equals(or)){
            ans = n1|n2;
        }else if(b.equals(xor)){
            System.out.println("Nor");
            ans = n1^n2;
        }
        String s = getBinaryString(ans);
        
        binaryStr3.setText(splitFixedLength(s, 4));
        binaryStr4.setText(String.valueOf(ans));
            
    }
    private void orActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orActionPerformed
      buttionPressed(evt);
    }//GEN-LAST:event_orActionPerformed

    private void xorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xorActionPerformed
        buttionPressed(evt);
    }//GEN-LAST:event_xorActionPerformed
    private String getBinaryString(int n){
        String str = Integer.toBinaryString(n);
        String s = String.format("%32s", str).replace(' ', '0');
        return s;
    }
    
    public int getDecimal(String str){
        int n = 0;
        try{
            String str1 = str.replaceAll(" ", "");
            n = Integer.parseInt(str1,2);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return n;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BitWiseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BitWiseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BitWiseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BitWiseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BitWiseTest().setVisible(true);
            }
        });
    }
    
    public static String splitFixedLength(String str, int length) {
        Stack<String> stack = new Stack<String>();
        int l = str.length();
        int i = str.length();
        for (; i - length >= 0; i -= length) {
            System.out.println(str.substring(i - length, i));
            stack.push(str.substring(i - length, i));
        }
        stack.push(str.substring(0, i));
        System.out.println(str.subSequence(0, i));

        StringBuilder output = new StringBuilder();
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append("   ");
        }
     
        return output.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton and;
    private javax.swing.JTextField binaryStr;
    private javax.swing.JTextField binaryStr1;
    private javax.swing.JTextField binaryStr2;
    private javax.swing.JTextField binaryStr3;
    private javax.swing.JTextField binaryStr4;
    private javax.swing.JSpinner decimalStr;
    private javax.swing.JSpinner decimalStr1;
    private javax.swing.JSpinner decimalStr2;
    private javax.swing.JButton ls;
    private javax.swing.JButton ls1;
    private javax.swing.JButton or;
    private javax.swing.JButton rs;
    private javax.swing.JButton rs2;
    private javax.swing.JButton xor;
    // End of variables declaration//GEN-END:variables
}
