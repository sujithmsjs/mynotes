package com.suji.cram.gui;



import com.suji.cram.csv.CardsCSVData;
import com.suji.cram.model.Card;
import com.suji.cram.model.CardDao;
import com.suji.cram.model.GroupDao;
import com.suji.cram.util.FileDialougeUtil;

import java.io.File;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class IndexFrame extends javax.swing.JFrame {

    public IndexFrame(JFrame parent) {
        initComponents();
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupTf = new javax.swing.JTextField();
        createGroupBtn = new javax.swing.JButton();
        groupCb = new javax.swing.JComboBox<>();
        startTestBtn = new javax.swing.JButton();
        addWrodBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        filePath = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome");
        setMinimumSize(new java.awt.Dimension(600, 400));

        groupTf.setMargin(new java.awt.Insets(5, 5, 5, 5));

        createGroupBtn.setText("Create Group");
        createGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupBtnActionPerformed(evt);
            }
        });

        groupCb.setModel(new javax.swing.DefaultComboBoxModel<>(fillComboBox()));
        groupCb.setActionCommand(Card.DEFAULT);

        startTestBtn.setText("Start Test");
        startTestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTestBtnActionPerformed(evt);
            }
        });

        addWrodBtn.setText("Add Words");
        addWrodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWrodBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Choose CSV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        filePath.setEditable(false);
        filePath.setMargin(new java.awt.Insets(5, 5, 5, 5));

        saveBtn.setText("load CSV");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
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
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(startTestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(addWrodBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(groupCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filePath)
                            .addComponent(groupTf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createGroupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGroupBtn)
                    .addComponent(groupTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(groupCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTestBtn)
                    .addComponent(addWrodBtn)
                    .addComponent(saveBtn))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addWrodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWrodBtnActionPerformed
        AddFrame af = new AddFrame(this, groupCb.getSelectedItem());
        af.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_addWrodBtnActionPerformed

    private void startTestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTestBtnActionPerformed
        TestFrame af = new TestFrame(this,groupCb.getSelectedItem().toString());
        af.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_startTestBtnActionPerformed

    private void createGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupBtnActionPerformed
        
        try {

            String groupName = groupTf.getText();
            if (groupName.length() < 5) {
                JOptionPane.showMessageDialog(this, "New Group Name > 3 Characters. ", "Check Group name.", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            System.out.println("Hehe, skipped return.");

            if (GroupDao.addGroup(groupName)) {
                JOptionPane.showMessageDialog(this, "New Group " + groupName + " added successfully.", "Added", JOptionPane.INFORMATION_MESSAGE);
                groupCb.addItem(groupName);
                groupCb.setSelectedItem(groupName);

            } else {
                JOptionPane.showMessageDialog(this, "Unaable to add new group.", "Failed", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        groupTf.setText("");
        
    }//GEN-LAST:event_createGroupBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File file = FileDialougeUtil.openFile(this, "");
        //String path = file.getPath();
        try {
            
            if (file.exists()) {
                filePath.setText(file.getPath());
            } else {
                JOptionPane.showMessageDialog(this, "File not exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (Exception e) {
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String path = filePath.getText();
        File file = new File(path);
        if (file.exists() && path.endsWith(".csv")) {
           int count = 0;
            try {
                
                List<Card> cardsList = CardsCSVData.getCards(file);
               count  = CardDao.addAllCards(cardsList,groupCb.getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Saved Card s: "+count, "Saved Cards", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE);
            }
            
            filePath.setText("Insert update cound : "+count);
            
        } else {
            JOptionPane.showMessageDialog(this, "File not exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWrodBtn;
    private javax.swing.JButton createGroupBtn;
    private javax.swing.JTextField filePath;
    private javax.swing.JComboBox<String> groupCb;
    private javax.swing.JTextField groupTf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton startTestBtn;
    // End of variables declaration//GEN-END:variables

    private String[] fillComboBox() {
        String[] list = CardDao.getAllGroups();
        try {
            list = GroupDao.getAllGroups();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex, "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }


}
