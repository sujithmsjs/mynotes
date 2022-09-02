/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suji.ui;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import suji.com.mod.Word;
import suji.com.mod.WordSet;


public class Test extends javax.swing.JFrame {

    /////////////////////////////////////////////
    private WordBox box;
    
    
    
   /////////////////////////////////////////////
    public Test() {
        initComponents();
        subBtn.setText("Load");
        setLocationRelativeTo(null);
       // setUI();
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progress = new javax.swing.JProgressBar();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        ansTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        index = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        wordTxt = new javax.swing.JTextField();
        hintTxt = new javax.swing.JTextField();
        subBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        addBunchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Words Up 2");
        setResizable(false);

        progress.setMaximum(10);

        prevBtn.setText("Prev");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        ansTxt.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        ansTxt.setMargin(new java.awt.Insets(4, 10, 4, 4));
        ansTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ansTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Answer:");

        index.setText("Index");

        jLabel2.setText("Hint:");

        jLabel3.setText("Word:");

        wordTxt.setEditable(false);
        wordTxt.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        wordTxt.setFocusable(false);
        wordTxt.setMargin(new java.awt.Insets(4, 10, 4, 4));
        wordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordTxtActionPerformed(evt);
            }
        });

        hintTxt.setEditable(false);
        hintTxt.setFont(new java.awt.Font("Monospaced", 1, 24)); // NOI18N
        hintTxt.setFocusable(false);
        hintTxt.setMargin(new java.awt.Insets(4, 10, 4, 4));
        hintTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintTxtActionPerformed(evt);
            }
        });

        subBtn.setText("Submit");
        subBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnActionPerformed(evt);
            }
        });

        viewBtn.setText("View");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        addBunchBtn.setText("Add Wrods");
        addBunchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBunchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(index)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addBunchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(subBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prevBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextBtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ansTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hintTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(index)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(wordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hintTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ansTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn)
                    .addComponent(prevBtn)
                    .addComponent(subBtn)
                    .addComponent(viewBtn)
                    .addComponent(addBunchBtn))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        if(box != null) {
            nextAction();
        }
        
    }//GEN-LAST:event_nextBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        if(box != null) {
            prevAction();
        }
    }//GEN-LAST:event_prevBtnActionPerformed

    private void ansTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ansTxtActionPerformed
        if(box != null) {
            nextAction();
        }
    }//GEN-LAST:event_ansTxtActionPerformed

    private void wordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordTxtActionPerformed
        
    }//GEN-LAST:event_wordTxtActionPerformed

    private void hintTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintTxtActionPerformed
        
    }//GEN-LAST:event_hintTxtActionPerformed

    private void subBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnActionPerformed
        subAction(evt);
        System.out.println(evt.getActionCommand());
    }//GEN-LAST:event_subBtnActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        ViewWords vw = new ViewWords(this);
        vw.setVisible(true);
    }//GEN-LAST:event_viewBtnActionPerformed

    private void addBunchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBunchBtnActionPerformed
        AddBunch ab = new AddBunch(this);
        ab.setVisible(true);
    }//GEN-LAST:event_addBunchBtnActionPerformed


    
    
    
    

    
    private static void setUIFont(javax.swing.plaf.FontUIResource f) {

        java.util.Enumeration keys = UIManager.getDefaults().keys();
        UIDefaults ud = UIManager.getDefaults();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof javax.swing.plaf.FontUIResource) {
                System.out.println(key + " : " + UIManager.get(value));
                UIManager.put(key, f);

            }
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBunchBtn;
    private javax.swing.JTextField ansTxt;
    private javax.swing.JTextField hintTxt;
    private javax.swing.JLabel index;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton prevBtn;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton subBtn;
    private javax.swing.JButton viewBtn;
    private javax.swing.JTextField wordTxt;
    // End of variables declaration//GEN-END:variables

    private void fillFields(Word w){
        wordTxt.setText(w.getWord());
        hintTxt.setText(w.getHint());
        ansTxt.setText(w.getInput());
    }
     private void clearFields(){
        wordTxt.setText("");
        hintTxt.setText("");
        ansTxt.setText("");
        box = null;
    }
    
    
    private void nextAction() {
        
        if(box.isValidIndex()){
            Word w = box.get();
            w.setInput(ansTxt.getText());
        }
        
        if (box.hasNext()) {
            
            Word w = box.next();
            fillFields(w);
            
            setUI();
        }
    }

    private void prevAction() {
        
        if (box.isValidIndex()) {
            Word w = box.get();
            w.setInput(ansTxt.getText());
        }
        
        if (box.hasPrevious()) {

            Word w = box.previous();
            fillFields(w);

            setUI();
        }
    }

    public void setUI() {

        progress.setValue(box.getIndex() + 1);
        index.setText("Index " + (box.getIndex()+1)+"/"+box.size());

        if (box.hasNext()) {
            nextBtn.setEnabled(true);
        } else {
            nextBtn.setEnabled(false);
        }

        if (box.hasPrevious()) {
            prevBtn.setEnabled(true);
        } else {
            prevBtn.setEnabled(false);
        }

    }
    
    

    private void subAction(ActionEvent evt) {
       switch(evt.getActionCommand()){
           case "Submit":
              
               
               ResultUI rui = new ResultUI(this,false,box.getResults());
               rui.setVisible(true);
               clearFields();
               
               subBtn.setText("Load");
               break;
               
           case "Load":
               
               box = WordSet.getWordBox();
               Word w = box.next();
               fillFields(w);
               progress.setMaximum(box.size());
               
               setUI();
               
               subBtn.setText("Submit");
               break;
       }
    }
}
