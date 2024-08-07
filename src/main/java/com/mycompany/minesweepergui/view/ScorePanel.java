/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.minesweepergui.view;

import java.awt.Dimension;
import java.awt.Insets;

/**
 *
 * @author Eduard
 */
public class ScorePanel extends javax.swing.JDialog {
    
    private String[] difficultiesNames;
    private String fileName;
    /**
     * Creates new form ScorePanel
     */
    public ScorePanel(MinesweeperPanel mainPanel, String[] difficultiesNames, String fileName) {
        super(mainPanel, "Game Scores", true);
        this.difficultiesNames = difficultiesNames;
        this.fileName = fileName;
        initComponents();
        setDifficultiesListHeight();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void setDifficultiesListHeight() {
        
        int rowHeight = difficultiesList.getFixedCellHeight();
        int numRows = difficultiesList.getModel().getSize();
        Insets scrollPaneInsets = difficultiesScrollPane.getInsets();

        int totalHeight = (rowHeight * numRows) + scrollPaneInsets.top + scrollPaneInsets.bottom;
        
        difficultiesScrollPane.setPreferredSize(new Dimension(100, totalHeight));

        difficultiesScrollPane.revalidate();
        difficultiesScrollPane.repaint();
        this.pack();
    }
    
    public void updateInfo() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        difficultiesScrollPane = new javax.swing.JScrollPane();
        difficultiesList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        difficultiesList.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        difficultiesList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return difficultiesNames.length; }
            public String getElementAt(int i) { return difficultiesNames[i]; }
        });
        difficultiesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        difficultiesList.setFixedCellHeight(28);
        difficultiesScrollPane.setViewportView(difficultiesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(difficultiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(500, 500, 500))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(difficultiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> difficultiesList;
    private javax.swing.JScrollPane difficultiesScrollPane;
    // End of variables declaration//GEN-END:variables
}
