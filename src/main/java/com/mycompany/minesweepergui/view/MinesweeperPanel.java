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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

/**
 *
 * @author Eduard
 */
public class MinesweeperPanel extends javax.swing.JFrame {
    
    // Game logic and Window
    private Minesweeper game;
    private Square[][] allSquares;
    GridLayout gameSquares = new GridLayout();
    private boolean gameStarted;
    private int previousDifficulty;
    private static final int SQUARE_SIZE = 40;
    
    // Scroll
    private static final int MAX_HORIZONTAL_SCROLL = 1600;
    private static final int MAX_VERTICAL_SCROLL = 720;
    private static int HORIZONTA_SCROLL_HEIGHT;
    private static int VERTICAL_SCROLL_WIDTH;
    private static int SCROLL_UNIT_INCREMENT = 16;
    
    // Difficulties
    private static final int CUSTOM = 0;
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int DIFFICULT = 3;
    private static final int HARDCORE = 4;
    private static final int INSANE = 5;
    private static final int LUNATIC = 6;
    private static final int DEFAULT_DIFFICULTY = EASY;
    private static final String[] DIFFICULTIES_NAMES = {"Custom", "Easy", "Medium", "Difficult", "Hardcore", "Insane", "Lunatic"};
    
    // Difficulties params
    private static final int EASY_ROWS = 8;
    private static final int EASY_COLS = 8;
    private static final int EASY_MINES = 10;
    private static final int MEDIUM_ROWS = 16;
    private static final int MEDIUM_COLS = 16;
    private static final int MEDIUM_MINES = 40;
    private static final int DIFFICULT_ROWS = 16;
    private static final int DIFFICULT_COLS = 30;
    private static final int DIFFICULT_MINES = 99;
    private static final int HARDCORE_ROWS = 24;
    private static final int HARDCORE_COLS = 30;
    private static final int HARDCORE_MINES = 200;
    private static final int INSANE_ROWS = 50;
    private static final int INSANE_COLS = 50;
    private static final int INSANE_MINES = 500;
    private static final int LUNATIC_ROWS = 100;
    private static final int LUNATIC_COLS = 100;
    private static final int LUNATIC_MINES = 2000;
    private Integer [][] difficultiesParams = {
        null,
        { EASY_ROWS, EASY_COLS, EASY_MINES },
        { MEDIUM_ROWS, MEDIUM_COLS, MEDIUM_MINES },
        { DIFFICULT_ROWS, DIFFICULT_COLS, DIFFICULT_MINES },
        { HARDCORE_ROWS, HARDCORE_COLS, HARDCORE_MINES },
        { INSANE_ROWS, INSANE_COLS, INSANE_MINES },
        { LUNATIC_ROWS, LUNATIC_COLS, LUNATIC_MINES }};
    InfoDifficultiesTable infoDifficultiesPanel = null;
    ScorePanel scorePanel = null;
    
    // Timer Difficulties
    private Timer timer = null;
    
    private static final String[] USE_TIMER_DIFFICULTIES_NAMES = Arrays.copyOfRange(DIFFICULTIES_NAMES, 1, DIFFICULTIES_NAMES.length);;
    private static final Map<Integer, Integer> DIFFICULTIES_MAX_TIME = new HashMap<>();
    static {
        DIFFICULTIES_MAX_TIME.put(EASY, 99);
        DIFFICULTIES_MAX_TIME.put(MEDIUM, 999);
        DIFFICULTIES_MAX_TIME.put(DIFFICULT, 999);
        DIFFICULTIES_MAX_TIME.put(HARDCORE, 999);
        DIFFICULTIES_MAX_TIME.put(INSANE, 9999);
        DIFFICULTIES_MAX_TIME.put(LUNATIC, 99999);
    }
    private boolean showTimer;
    private boolean useTimer = false;
    
    // Listeners
    ActionListener cmbSelectDifficultyListener;
    
    /**
     * Creates new form MinesweeperPanel
     */
    public MinesweeperPanel() {
        game = new Minesweeper();
        initComponents();
        setCmbSelectDifficultyListener();
        
        // Board
        gameBoard.setLayout(gameSquares);
        gameSquares.setHgap(0);
        gameSquares.setVgap(0);
        
        // Scroll
        HORIZONTA_SCROLL_HEIGHT = (int) Math.ceil(gameBoardScroll.getHorizontalScrollBar().getPreferredSize().getHeight());
        VERTICAL_SCROLL_WIDTH = (int) Math.ceil(gameBoardScroll.getVerticalScrollBar().getPreferredSize().getWidth());
        gameBoardScroll.getVerticalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        gameBoardScroll.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNIT_INCREMENT);
        
        // Game
        previousDifficulty = DEFAULT_DIFFICULTY;
        newGame(DEFAULT_DIFFICULTY);
        
        // Window
        markedMines.setFont(new Font("SansSerif", Font.PLAIN, 20));
        timerLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        timerPanel.setVisible(false);
        setTimerPanelHideListener();
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        shortMenuPanel = new javax.swing.JPanel();
        markedMines = new javax.swing.JLabel();
        newBoardBtn = new javax.swing.JButton();
        repeatBoardBtn = new javax.swing.JButton();
        timerPanel = new javax.swing.JPanel();
        timerLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbSelectDifficulty = new javax.swing.JComboBox<>();
        infoDifficultiesBtn = new javax.swing.JButton();
        scoresBtn = new javax.swing.JButton();
        gameBoardScroll = new javax.swing.JScrollPane();
        gameBoard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(null);

        shortMenuPanel.setMinimumSize(new java.awt.Dimension(320, 79));

        markedMines.setText("🚩 0");

        newBoardBtn.setText("New Game");
        newBoardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBoardBtnActionPerformed(evt);
            }
        });

        repeatBoardBtn.setText("Repeat Game");
        repeatBoardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatBoardBtnActionPerformed(evt);
            }
        });

        timerLabel.setText("⏱ 000");

        javax.swing.GroupLayout timerPanelLayout = new javax.swing.GroupLayout(timerPanel);
        timerPanel.setLayout(timerPanelLayout);
        timerPanelLayout.setHorizontalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(timerLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        timerPanelLayout.setVerticalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(timerLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cmbSelectDifficulty.setModel(new DefaultComboBoxModel<>(DIFFICULTIES_NAMES));
        cmbSelectDifficulty.setSelectedIndex(DEFAULT_DIFFICULTY);

        infoDifficultiesBtn.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        infoDifficultiesBtn.setText("𝐢");
        infoDifficultiesBtn.setMargin(new java.awt.Insets(-3, -3, -3, -3));
        infoDifficultiesBtn.setPreferredSize(new java.awt.Dimension(28, 28));
        infoDifficultiesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoDifficultiesBtnActionPerformed(evt);
            }
        });

        scoresBtn.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        scoresBtn.setText("★");
        scoresBtn.setMargin(new java.awt.Insets(-8, -8, -8, -8));
        scoresBtn.setPreferredSize(new java.awt.Dimension(28, 28));
        scoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoresBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbSelectDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(infoDifficultiesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoDifficultiesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(cmbSelectDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout shortMenuPanelLayout = new javax.swing.GroupLayout(shortMenuPanel);
        shortMenuPanel.setLayout(shortMenuPanelLayout);
        shortMenuPanelLayout.setHorizontalGroup(
            shortMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shortMenuPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(markedMines)
                .addGap(0, 0, 0)
                .addComponent(timerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(shortMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newBoardBtn)
                    .addComponent(repeatBoardBtn)))
        );
        shortMenuPanelLayout.setVerticalGroup(
            shortMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shortMenuPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(shortMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shortMenuPanelLayout.createSequentialGroup()
                        .addComponent(newBoardBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(repeatBoardBtn))
                    .addGroup(shortMenuPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(shortMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(markedMines)))))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        gameBoardScroll.setBorder(null);
        gameBoardScroll.setMaximumSize(null);
        gameBoardScroll.setViewportView(null);

        gameBoard.setMaximumSize(null);
        gameBoard.setName(""); // NOI18N

        javax.swing.GroupLayout gameBoardLayout = new javax.swing.GroupLayout(gameBoard);
        gameBoard.setLayout(gameBoardLayout);
        gameBoardLayout.setHorizontalGroup(
            gameBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        gameBoardLayout.setVerticalGroup(
            gameBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        gameBoardScroll.setViewportView(gameBoard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gameBoardScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shortMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(shortMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(gameBoardScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void setCmbSelectDifficultyListener() {
        cmbSelectDifficultyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame(cmbSelectDifficulty.getSelectedIndex());
            }
        };
        cmbSelectDifficulty.addActionListener(cmbSelectDifficultyListener);
    }
    
    public class Square extends JButton {
        private int row;
        private int col;
        private int numMinesAroundPos;
        private boolean discovered;
        private boolean marked;
        private boolean doubtful;        
        private boolean disabled;
        private MouseAdapter mouseListenerFocus;
        private MouseAdapter mouseListenerCreated;
        private MouseAdapter mouseListenerDiscovered;

        private Square (int r, int c){
            row = r;
            col = c;
            discovered = false;
            marked = false;
            doubtful = false;
            disabled = false;            
            
            setFocusable(false);
            setMargin(new Insets(0,0,0,0));
            setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));

            setFont(new Font("SansSerif", Font.BOLD, 20));
            setBorder(null);
        }
        public int getSquareRow() {
            return row;
        }
        public int getSquareCol() {
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
        public boolean isDoubtful() {
            return doubtful;
        }
        public void setDoubtful(boolean doubtful) {
            this.doubtful = doubtful;
        }
        private void addListenerFocus(){
            mouseListenerFocus = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    getContentPane().requestFocusInWindow();
                }
            };
            addMouseListener(mouseListenerFocus);
        }
        public void addListenerCreated(){
            mouseListenerCreated = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
    
                        Square square = (Square) e.getSource();
                        
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (gameStarted) {
                                if (!game.isTimerStarted() && useTimer) {
                                    startTimer(cmbSelectDifficulty.getSelectedIndex());
                                }
                                if(game.makeMove("💣", square.getSquareRow(), square.getSquareCol())) {
                                    gameEndPane(false, JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                if (useTimer) {
                                    startTimer(cmbSelectDifficulty.getSelectedIndex());
                                }
                                game.generateMines(square.getSquareRow(), square.getSquareCol());                               
                                gameStarted = true;
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            if (gameStarted) {
                                if (!game.isTimerStarted() && useTimer) {
                                    startTimer(cmbSelectDifficulty.getSelectedIndex());
                                }
                                game.makeMove("🚩", square.getSquareRow(), square.getSquareCol());
                                markedMines.setText("🚩 "+(game.getNumMines()-game.getMarkedMinesNum()));
                            } else {
                                JOptionPane.showMessageDialog(null, "First select where to start","",JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        if (game.checkWin()) {
                            gameEndPane(true, JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            };
            addMouseListener(mouseListenerCreated);   
        }
        public void addListenerDiscovered(){
            mouseListenerDiscovered = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        
                        Square square = (Square) e.getSource();
                        int numMinesMarkedAround = 0;
                        List<int[]> notMarkedSquaresAround = new ArrayList<>();
                    
                        for (int i = -1; i < 2; i++) {
                            for (int y = -1; y < 2; y++) {
                                int newRow = square.getSquareRow() + i;
                                int newCol = square.getSquareCol()+ y;
                                if (newRow >= 0 && newCol >= 0 && newRow < game.getNumBoardRows() && newCol < game.getNumBardCols()){
                                    if (allSquares[newRow][newCol].isMarked()) {
                                        numMinesMarkedAround++;
                                    } else if (!allSquares[newRow][newCol].isDiscovered() && !allSquares[newRow][newCol].isDoubtful()) {
                                        notMarkedSquaresAround.add(new int[] {newRow, newCol});
                                    }
                                }
                            }
                        }
                        if (square.getNumMinesAroundPos() == numMinesMarkedAround) {
                            allSquares[square.getSquareRow()][square.getSquareCol()].disableSquare();
                            boolean boom = false;
                            for (int[] pos : notMarkedSquaresAround) {
                                if (!allSquares[pos[0]][pos[1]].isDiscovered() && !allSquares[pos[0]][pos[1]].isDoubtful()){
                                    boolean innerBoom = game.makeMove("💣", pos[0], pos[1]);                                    
                                    if (innerBoom && !boom){
                                        boom = true;
                                    }
                                }
                            }
                            if(boom) {
                                gameEndPane(false, JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            notMarkedSquaresAround.clear();
                        }
                        if (game.checkWin()) {
                            gameEndPane(true, JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            };
            addMouseListener(mouseListenerDiscovered);
        }
        private void removeListenerFocus() {
            this.removeMouseListener(mouseListenerFocus);
        }
        public void removeListenerCreated() {
            this.removeMouseListener(mouseListenerCreated);
        }
        public void removeListenerDiscovered() {
            this.removeMouseListener(mouseListenerDiscovered);
        }
        public void disableSquare() {
            if(!game.isGameEnd()){
                game.getAvailablePositions().removeIf(p -> p[0] == row && p[1] == col);
            }
            this.removeMouseListener(mouseListenerCreated);
            this.removeMouseListener(mouseListenerDiscovered);
            disabled = true;
        }
    }
    
    private void removeSquaresListenerFocus(){
        if (allSquares != null) {
            for (Square[] row : allSquares) {
                for (Square square : row) {
                    square.removeListenerFocus();
                }
            }
        }
    }
    
    private int[] getParamsBoard(int difficulty){
        return new int[] {difficultiesParams[difficulty][0], difficultiesParams[difficulty][1], difficultiesParams[difficulty][2]};
        /*
        return switch (difficulty) {
            case EASY -> new int[] {EASY_ROWS, EASY_COLS, EASY_MINES};
            case MEDIUM -> new int[] {MEDIUM_ROWS, MEDIUM_COLS, MEDIUM_MINES};
            case DIFFICULT -> new int[] {DIFFICULT_ROWS, DIFFICULT_COLS, DIFFICULT_MINES};
            case HARDCORE -> new int[] {HARDCORE_ROWS, HARDCORE_COLS, HARDCORE_MINES};
            case INSANE -> new int[] {INSANE_ROWS, INSANE_COLS, INSANE_MINES};
            case LUNATIC -> new int[] {LUNATIC_ROWS, LUNATIC_COLS, LUNATIC_MINES};
            default -> new int[] {EASY_ROWS, EASY_COLS, EASY_MINES};
        };
        */
    }
   
    private void repeatBoardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatBoardBtnActionPerformed
        gameReady(cmbSelectDifficulty.getSelectedIndex(), new int[] {game.getNumBoardRows(), game.getNumBardCols(), game.getNumMines()}, gameStarted);
    }//GEN-LAST:event_repeatBoardBtnActionPerformed

    private void newBoardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBoardBtnActionPerformed
        newGame(cmbSelectDifficulty.getSelectedIndex());
    }//GEN-LAST:event_newBoardBtnActionPerformed

    private void infoDifficultiesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoDifficultiesBtnActionPerformed
        boolean isCustomBoard = cmbSelectDifficulty.getSelectedIndex() == CUSTOM;
        if (infoDifficultiesPanel == null) {
            infoDifficultiesPanel = new InfoDifficultiesTable(this, DIFFICULTIES_NAMES, difficultiesParams);
            infoDifficultiesPanel.createTable(isCustomBoard);
        } else {
            if (infoDifficultiesPanel.needUpdate(isCustomBoard)) {
                infoDifficultiesPanel.updateTable(isCustomBoard);
            }
        }
        infoDifficultiesPanel.setVisible(true);
    }//GEN-LAST:event_infoDifficultiesBtnActionPerformed

    private void scoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoresBtnActionPerformed
        boolean isCustomBoard = cmbSelectDifficulty.getSelectedIndex() == CUSTOM;
        if (scorePanel == null) {
            scorePanel = new ScorePanel(this, USE_TIMER_DIFFICULTIES_NAMES, Minesweeper.getFileName());
        }
        scorePanel.updateScorePanel();
        scorePanel.setVisible(true);
    }//GEN-LAST:event_scoresBtnActionPerformed

    private void newGame(int difficulty) {
        if (difficulty == CUSTOM) {
            CustomBoardForm customBoardForm = new CustomBoardForm(this, difficultiesParams[0]);
            customBoardForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    cmbSelectDifficulty.removeActionListener(cmbSelectDifficultyListener);
                    cmbSelectDifficulty.setSelectedIndex(previousDifficulty);
                    cmbSelectDifficulty.addActionListener(cmbSelectDifficultyListener);
                }
            });
            customBoardForm.setVisible(true);
        } else {
            gameReady(difficulty, getParamsBoard(difficulty), false);
            previousDifficulty = difficulty;
        }
    }
    
    public void newCustomGame(int[] paramsBoard) {
        difficultiesParams[0] = Arrays.stream(paramsBoard).boxed().toArray(Integer[]::new);
        previousDifficulty = CUSTOM;
        gameReady(CUSTOM, paramsBoard, false);
    }
    
    private void gameReady(int difficulty, int[] paramsBoard, boolean repeat) {
        resetGameSquaresBoard();
        gameStarted = repeat;
        game.startGame(paramsBoard[0], paramsBoard[1], paramsBoard[2], repeat);
        markedMines.setText("🚩 "+(game.getNumMines()-game.getMarkedMinesNum()));
        setTimer(difficulty);
        gameSquares.setRows(game.getNumBoardRows());
        gameSquares.setColumns(game.getNumBardCols());
        removeSquaresListenerFocus();
        allSquares = new Square[game.getNumBoardRows()][game.getNumBardCols()];
        game.setUserBoard(allSquares);
        
        for(int i=0; i<gameSquares.getRows(); i++){
            for(int y=0; y<gameSquares.getColumns(); y++){
                Square square = new Square(i,y);
                allSquares[i][y] = square;
                square.addListenerCreated();
                square.addListenerFocus();
                gameBoard.add(square);
            }
        }
        int horizontalSize = (paramsBoard[0]*SQUARE_SIZE > MAX_VERTICAL_SCROLL) ? paramsBoard[1]*SQUARE_SIZE + VERTICAL_SCROLL_WIDTH : paramsBoard[1]*SQUARE_SIZE;
        int verticalSize = (paramsBoard[1]*SQUARE_SIZE > MAX_HORIZONTAL_SCROLL) ? paramsBoard[0]*SQUARE_SIZE + HORIZONTA_SCROLL_HEIGHT : paramsBoard[0]*SQUARE_SIZE;
        gameBoardScroll.setPreferredSize(new Dimension(Math.min(horizontalSize, MAX_HORIZONTAL_SCROLL), Math.min(verticalSize, MAX_VERTICAL_SCROLL)));
        this.pack();
        this.setVisible(true);
    }
    
    private void gameEndPane(boolean isWin, int msgType) {
        if(useTimer){
            timer.cancel();
            game.saveScore(isWin, cmbSelectDifficulty.getSelectedIndex(), game.getTimerCount());
        }
        String[] options = {"New game","Repeat game","Examine board", "Exit"};
        int selectedOption;
        String message;
        if (isWin) {
            markedMines.setText("🚩 "+(game.getNumMines()-game.getMarkedMinesNum()));
            message = "Congratulations!";
            if(useTimer){
                message += "\r\nYour time was " + game.getTimerCount() + " seconds.";
            }
        } else {
            message = "Boom!";
        }
        
        selectedOption = JOptionPane.showOptionDialog(null, message, "", JOptionPane.DEFAULT_OPTION, msgType, null, options, options[0]);
        switch (selectedOption) {
            case 0:
                newGame(cmbSelectDifficulty.getSelectedIndex());
                break;
            case 1:
                gameReady(cmbSelectDifficulty.getSelectedIndex(), new int[] {game.getNumBoardRows(), game.getNumBardCols(), game.getNumMines()}, true);
                break;
            case 3:
                this.dispose();
                System.exit(0);
                break;
        }
    }
            
    private void resetGameSquaresBoard() {
        gameBoard.removeAll();
        gameBoard.revalidate();
        gameBoard.repaint();
        
        // Reset Scrollbar
        JScrollBar verticalScrollBar = gameBoardScroll.getVerticalScrollBar();
        JScrollBar horizontalScrollBar = gameBoardScroll.getHorizontalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMinimum());
        horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
    }
    
    private void setTimer(int difficulty) {
        
        showTimer = difficulty != EASY && difficulty != CUSTOM;
        
        game.setTimerStarted(false);
        
        // Comprueba si la dificultada anterior tenía un timer activo
        if(useTimer) {
            timer.cancel();
        }
        
        timerPanel.setVisible(showTimer);
        
        // Asigna si la dificultad actual requiere timer
        useTimer = difficulty != CUSTOM;
        
        if (useTimer){
            resetTimerCount(difficulty);
        } else {
            timer = null;
        }
    }
    
    private void resetTimerCount(int difficulty) {
        
        timer = new Timer();
        game.setTimerCount(0);
        timerLabel.setText("⏱ " + String.format("%0" + String.valueOf(DIFFICULTIES_MAX_TIME.get(difficulty)).length() + "d", 0));
    }
    
    private void startTimer(int difficulty) {
        
        Integer difficultyTime = DIFFICULTIES_MAX_TIME.get(difficulty);
        int difficultyTimeDigits = String.valueOf(difficultyTime).length();
        
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                
                int timerCount = game.getTimerCount();
                game.setTimerCount(timerCount + 1);
                timerCount++;
                if (timerCount <= difficultyTime) {
                    timerLabel.setText("⏱ " + String.format("%0" + difficultyTimeDigits + "d", timerCount));
                }
            }
        };
        // Schedule the timer to run every second (1000 ms)
        timer.scheduleAtFixedRate(tarea, 1000, 1000);
        game.setTimerStarted(true);
    }
    
    private void setTimerPanelHideListener(){
        timerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && !game.isGameEnd()) {
                    timerPanel.setVisible(false);
                }
            }
        });
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
    private javax.swing.JScrollPane gameBoardScroll;
    private javax.swing.JButton infoDifficultiesBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel markedMines;
    private javax.swing.JButton newBoardBtn;
    private javax.swing.JButton repeatBoardBtn;
    private javax.swing.JButton scoresBtn;
    private javax.swing.JPanel shortMenuPanel;
    private javax.swing.JLabel timerLabel;
    private javax.swing.JPanel timerPanel;
    // End of variables declaration//GEN-END:variables
}
