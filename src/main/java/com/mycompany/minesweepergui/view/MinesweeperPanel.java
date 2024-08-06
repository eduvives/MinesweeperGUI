/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.minesweepergui.view;

import com.mycompany.minesweepergui.model.Minesweeper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduard
 */
public class MinesweeperPanel extends javax.swing.JFrame {
    
    private Minesweeper game;
    private Square[][] allSquares;
    public boolean gameStarted;
    GridLayout gameSquares = new GridLayout();
    
    /**
     * Creates new form MinesweeperPanel
     */
    public MinesweeperPanel() {
        game = new Minesweeper();
        initComponents();
        gameBoard.setLayout(gameSquares);
        gameSquares.setHgap(0);
        gameSquares.setVgap(0);
        gameReady(1, false);
        markedMines.setFont(new Font("SansSerif", Font.PLAIN, 20));
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbSelectDifficulty = new javax.swing.JComboBox<>();
        gameBoard = new javax.swing.JPanel();
        repeatBoardBtn = new javax.swing.JButton();
        markedMines = new javax.swing.JLabel();
        newBoardBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbSelectDifficulty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Easy", "Medium", "Difficult" }));
        cmbSelectDifficulty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectDifficultyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gameBoardLayout = new javax.swing.GroupLayout(gameBoard);
        gameBoard.setLayout(gameBoardLayout);
        gameBoardLayout.setHorizontalGroup(
            gameBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );
        gameBoardLayout.setVerticalGroup(
            gameBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );

        repeatBoardBtn.setText("Repeat Game");
        repeatBoardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatBoardBtnActionPerformed(evt);
            }
        });

        markedMines.setText("🚩 0");

        newBoardBtn.setText("New Game");
        newBoardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBoardBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbSelectDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(markedMines)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newBoardBtn)
                            .addComponent(repeatBoardBtn))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(markedMines)
                            .addComponent(cmbSelectDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(newBoardBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repeatBoardBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(gameBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public class Square extends JButton {
        private int row;
        private int col;
        private int numMinesAroundPos;
        private boolean discovered;
        private boolean marked;
        private boolean disabled;
        private MouseAdapter mouseListenerCreated;
        private MouseAdapter mouseListenerDiscovered;

        private Square (int r, int c){
            row = r;
            col = c;
            discovered = false;
            marked = false;
            disabled = false;
            
            setFocusable(false);
            setMargin(new Insets(0,0,0,0));
            setPreferredSize(new Dimension(40, 40));
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setBorder(null);
        }
        public int getMineRow() {
            return row;
        }
        public int getMineCol() {
            return col;
        }
        public int getNumMinesAroundPos() {
            return numMinesAroundPos;
        }
        public void setNumMinesAroundPos(int num) {
            numMinesAroundPos = num;
        }
        public boolean isDisabled() {
            return disabled;
        }
        public boolean isDiscovered() {
            return discovered;
        }
        public void setDiscovered(boolean discovered) {
            this.discovered = discovered;
        }
        public boolean isMarked() {
            return marked;
        }
        public void setMarked(boolean marked) {
            this.marked = marked;
        }
        public void addListenerCreated(){
            mouseListenerCreated = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Square mine = (Square) e.getSource();

                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (gameStarted) {
                            if(game.makeMove("💣", mine.getMineRow(), mine.getMineCol())) {
                                gameEndPane(false, JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            game.generateMines(mine.row, mine.col);                               
                            gameStarted = true;
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        if (gameStarted) {
                            game.makeMove("🚩", mine.getMineRow(), mine.getMineCol());
                            markedMines.setText("🚩 "+(game.getNumMines()-game.getMarkedMinesNum()));
                        } else {
                            JOptionPane.showMessageDialog(null, "First select where to start","",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    if (game.checkWin()) {
                        gameEndPane(true, JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            };
            addMouseListener(mouseListenerCreated);   
        }
        public void addListenerDiscovered(){
            mouseListenerDiscovered = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Square mine = (Square) e.getSource();
                    int numMinesMarkedAround = 0;
                    List<int[]> notMarkedSquaresAround = new ArrayList<>();
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        for (int i = -1; i < 2; i++) {
                            for (int y = -1; y < 2; y++) {
                                if (i+row >= 0 && y+col >= 0 && i+row < game.getBoardRows() && y+col < game.getBoardCols()){
                                    if (allSquares[i+row][y+col].isMarked()) {
                                        numMinesMarkedAround++;
                                    } else if (!allSquares[i+row][y+col].isDiscovered()) {
                                        notMarkedSquaresAround.add(new int[] {i+row, y+col});
                                    }
                                }
                            }
                        }
                        if (mine.getNumMinesAroundPos() == numMinesMarkedAround) {
                            allSquares[row][col].disableSquareAll();
                            boolean boom = false;
                            for (int[] pos : notMarkedSquaresAround) {
                                boolean innerBoom = game.makeMove("💣", pos[0], pos[1]);                                    
                                if (innerBoom && !boom){
                                    boom = true;
                                }
                            }
                            if(boom) {
                                gameEndPane(false, JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            notMarkedSquaresAround.clear();
                       }
                    }
                }
            };
            addMouseListener(mouseListenerDiscovered);
        }
        public void disableSquare() {
            this.removeMouseListener(mouseListenerCreated);
        }
        public void disableSquareDiscovered() {
            this.removeMouseListener(mouseListenerDiscovered);
        }
        public void disableSquareAll() {
            this.removeMouseListener(mouseListenerCreated);
            this.removeMouseListener(mouseListenerDiscovered);
            disabled = true;
        }
    }
   
    private void cmbSelectDifficultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectDifficultyActionPerformed
        gameReady(cmbSelectDifficulty.getSelectedIndex()+1, false);

    }//GEN-LAST:event_cmbSelectDifficultyActionPerformed

    private void repeatBoardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatBoardBtnActionPerformed
        if(gameStarted){
            gameReady(cmbSelectDifficulty.getSelectedIndex()+1, true);
        } else {
            gameReady(cmbSelectDifficulty.getSelectedIndex()+1, false);
        }
    }//GEN-LAST:event_repeatBoardBtnActionPerformed

    private void newBoardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBoardBtnActionPerformed
        gameReady(cmbSelectDifficulty.getSelectedIndex()+1, false);
    }//GEN-LAST:event_newBoardBtnActionPerformed

    private void gameReady(int difficulty, boolean repeat) {
        resetGameSquaresBoard();
        gameStarted = repeat;
        game.startGame(difficulty, repeat);
        markedMines.setText("🚩 "+(game.getNumMines()-game.getMarkedMinesNum()));
        gameSquares.setRows(game.getBoardRows());
        gameSquares.setColumns(game.getBoardCols());
        allSquares = new Square[game.getBoardRows()][game.getBoardCols()];
        game.setUserBoard(allSquares);
        
        for(int i=0; i<gameSquares.getRows(); i++){
            for(int y=0; y<gameSquares.getColumns(); y++){
                Square mine = new Square(i,y);
                allSquares[i][y] = mine;
                mine.addListenerCreated();
                gameBoard.add(mine);
            }
        }
        this.pack();
        this.setVisible(true);
    }
    
    private void gameEndPane(boolean isWin, int msgType) {
        String[] options = {"New game","Repeat game","Examine board", "Exit"};
        int selectedOption;
        if (isWin) {
            selectedOption = JOptionPane.showOptionDialog(null, "Congratulations!", "", JOptionPane.DEFAULT_OPTION, msgType, null, options, options[3]);
        } else {
            selectedOption = JOptionPane.showOptionDialog(null, "Boom!", "", JOptionPane.DEFAULT_OPTION, msgType, null, options, options[3]);
        }
        switch (selectedOption) {
            case 0:
                gameReady(cmbSelectDifficulty.getSelectedIndex()+1, false);
                break;
            case 1:
                gameReady(cmbSelectDifficulty.getSelectedIndex()+1, true);
                break;
            case 3:
                this.dispose();
                System.exit(0);
                break;
        };
    }
            
    private void resetGameSquaresBoard() {
        gameBoard.removeAll();
        gameBoard.revalidate();
        gameBoard.repaint();
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
            java.util.logging.Logger.getLogger(MinesweeperPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinesweeperPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinesweeperPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinesweeperPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinesweeperPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbSelectDifficulty;
    private javax.swing.JPanel gameBoard;
    private javax.swing.JLabel markedMines;
    private javax.swing.JButton newBoardBtn;
    private javax.swing.JButton repeatBoardBtn;
    // End of variables declaration//GEN-END:variables
}
