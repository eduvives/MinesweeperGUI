/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweepergui.model;

import com.mycompany.minesweepergui.view.MinesweeperPanel.Square;
import java.awt.Color;
import java.awt.Font;
import java.util.*;

/**
 *
 * @author Eduard
 */
public class Minesweeper {
    
    private static int boardRows;
    private static int boardCols;
    private static int numMines;
    private int markedMinesNum;
    private int correctMarkedMinesNum;
    private int doubtfulMinesNum;
    
    private List<int[]> minesPositions = new ArrayList<>();
    private List<int[]> availablePositions = new ArrayList<>();
    private List<int[]> notDiscoveredPositions = new ArrayList<>();
    private boolean gameOver;
    private boolean gameEnd;
    
    private boolean[][] board;
    public Square[][] userBoard;
    
    public boolean startGame (int rows, int cols, int mines, boolean repeat) {
        
        if (!repeat) {
            boardRows = rows;
            boardCols = cols;
            numMines = mines;
            minesPositions.clear();
            board = new boolean[boardRows][boardCols];
        }
        
        gameOver = false;
        gameEnd = false;
        markedMinesNum = 0;
        correctMarkedMinesNum = 0;
        doubtfulMinesNum = 0;

        availablePositions.clear();
        notDiscoveredPositions.clear();
        
        // Create a list of all possible positions within the array
        for (int i = 0; i < boardRows; i++) {
            final int row = i;
            for (int j = 0; j < boardCols; j++) {
                final int col = j;
                notDiscoveredPositions.add(new int[] {i, j});
                if (!repeat || (repeat && !minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) ){
                    availablePositions.add(new int[] {i, j});
                    
                }
            }
        }
        return true;
    }
    
    public int getBoardRows(){
        return boardRows;
    }
    public int getBoardCols(){
        return boardCols;
    }
    public int getNumMines(){
        return numMines;
    }
    public int getMarkedMinesNum(){
        return markedMinesNum;
    }
    public List<int[]> getAvailablePositions(){
        return availablePositions;
    }
    public boolean isGameEnd(){
        return gameEnd;
    }
    public void setUserBoard(Square[][] allSquares){
        userBoard = allSquares;
    }
    
    public boolean makeMove(String action, int row, int col){
        if (action.equals("🚩")){
            if (!userBoard[row][col].isDiscovered() && !userBoard[row][col].isDoubtful()) {
                userBoard[row][col].setDiscovered(true);
                notDiscoveredPositions.removeIf(p -> p[0] == row && p[1] == col);
                userBoard[row][col].setMarked(true);                                        
                userBoard[row][col].setText("🚩");
                userBoard[row][col].setFont(new Font("SansSerif", Font.PLAIN, 18));
                markedMinesNum++;
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum++;
                }
            } else if (userBoard[row][col].isMarked()) {
                userBoard[row][col].setDiscovered(false);
                notDiscoveredPositions.add(new int[] {row, col});
                userBoard[row][col].setMarked(false);
                userBoard[row][col].setDoubtful(true);
                userBoard[row][col].setText("?");
                userBoard[row][col].setFont(new Font("SansSerif", Font.BOLD, 20));
                doubtfulMinesNum++;
                markedMinesNum--;
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum--;
                }
            } else if (userBoard[row][col].isDoubtful()) {
                userBoard[row][col].setDoubtful(false);
                userBoard[row][col].setText("");
                doubtfulMinesNum--;
            } else {
                irrationalMove();
            }
        } else if (action.equals("💣")){
            if (!userBoard[row][col].isMarked() && !userBoard[row][col].isDoubtful()){
                if(board[row][col]){
                    gameOver(row, col);
                    return true;
                } else {
                    Integer[] explosionPosition = searchMinesAround(row,col);
                    if (explosionPosition != null) {
                        gameOver(explosionPosition[0],explosionPosition[1]);
                        return true;
                    } else {
                        return false;
                    }
                    
                }
            }
        } else {
            irrationalMove();
        }
        return false;
    }
    
    public void gameOver(Integer startRow, Integer startCol) {
        if (!gameOver){
            gameEnd = true;
            
            for (int[] pos : availablePositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
            }
            for (int[] pos : minesPositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
                userBoard[pos[0]][pos[1]].setText("💣");                
                userBoard[pos[0]][pos[1]].setFont(new Font("SansSerif", Font.PLAIN, 18));
                
                if (pos[0] == startRow && pos[1] == startCol ) {
                    userBoard[pos[0]][pos[1]].setBackground(new Color(255, 62, 62));
                } else if (!userBoard[pos[0]][pos[1]].isMarked()) {
                    userBoard[pos[0]][pos[1]].setBackground(new Color(255, 135, 135));
                } else {
                    userBoard[pos[0]][pos[1]].setBackground(new Color(150, 198, 125));
                    
                }
            }
            gameOver = true;
        }
    }
    public boolean checkWin (){
        boolean allMinesDiscovered = notDiscoveredPositions.size() == numMines - correctMarkedMinesNum && correctMarkedMinesNum == markedMinesNum;
        boolean allMinesMarked = correctMarkedMinesNum == numMines && markedMinesNum == numMines && doubtfulMinesNum == 0;
        
        if (allMinesDiscovered || allMinesMarked) {
            gameEnd = true;
            
            for (int[] pos : minesPositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
            }
            for (int[] pos : availablePositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
                if (!userBoard[pos[0]][pos[1]].isDiscovered() && !userBoard[pos[0]][pos[1]].isDoubtful() && allMinesMarked){
                    searchMinesAround(pos[0],pos[1]);
                }
            }
            if (allMinesDiscovered) {
                for (int[] pos : notDiscoveredPositions) {
                    userBoard[pos[0]][pos[1]].setText("🚩");
                    userBoard[pos[0]][pos[1]].setFont(new Font("SansSerif", Font.PLAIN, 18));
                    markedMinesNum++;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void irrationalMove(){
        System.out.println("Don't be stupid... please.");
    }

    // STACK IMPLEMENTATION
    private Integer[] searchMinesAround(int startRow, int startCol){
        Integer[] explosionPosition = null;
        Stack<int[]> squaresStack = new Stack<>();
        squaresStack.push(new int[] { startRow, startCol });
        userBoard[startRow][startCol].setDiscovered(true);
        notDiscoveredPositions.removeIf(p -> p[0] == startRow && p[1] == startCol);
        
        while (!squaresStack.isEmpty()) {
            int[] pos = squaresStack.pop();
            int row = pos[0];
            int col = pos[1];
            
            if (board[row][col] && explosionPosition == null) {
                explosionPosition = new Integer[] {row, col};
            }

            int numMinesAroundPos = 0;
            List<int[]> positionsToSearch = new ArrayList<>();

            for (int i = -1; i < 2; i++) {
                for (int y = -1; y < 2; y++) {
                    int newRow = row + i;
                    int newCol = col + y;
                    if (newRow >= 0 && newCol >= 0 && newRow < boardRows && newCol < boardCols) {
                        if (board[newRow][newCol]) {
                            numMinesAroundPos++;
                        } else if (!userBoard[newRow][newCol].isDiscovered() && !userBoard[newRow][newCol].isDoubtful()) {
                            positionsToSearch.add(new int[] {newRow, newCol});
                        }
                    }
                }
            }

            for (int[] position : positionsToSearch) {
                if (numMinesAroundPos == 0) {
                    squaresStack.push(position);
                    userBoard[position[0]][position[1]].setDiscovered(true);
                    notDiscoveredPositions.removeIf(p -> p[0] == position[0] && p[1] == position[1]);
                }
            }
            
            if (numMinesAroundPos != 0) {
                userBoard[row][col].setText(String.valueOf(numMinesAroundPos));
                userBoard[row][col].setForeground(getColorForNumber(numMinesAroundPos));
                userBoard[row][col].setNumMinesAroundPos(numMinesAroundPos);
                if (!userBoard[row][col].isDisabled()) {
                    userBoard[row][col].addListenerDiscovered();
                    userBoard[row][col].disableSquare();
                }
            } else {
                userBoard[row][col].disableSquareAll();
            }
            userBoard[row][col].setBackground(new Color(168, 168, 168));
        }
        return explosionPosition;
    }
    
    private Color getColorForNumber(int num) {
        switch (num) {
            case 1: return new Color(0, 1, 249);
            case 2: return new Color(1, 126, 4);
            case 3: return new Color(251, 2, 0);
            case 4: return new Color(1, 1, 128);
            case 5: return new Color(129, 1, 3);
            case 6: return new Color(0, 128, 128);
            case 7: return new Color(0, 0, 0);
            case 8: return new Color(128, 128, 128);
            default: return Color.BLACK;
        }
    }

    /* RECURSIVE IMPLEMENTATION
    private boolean searchMinesAround(int row, int col){
        // La siguiente no es una comprobación necesaria, ya que esta misma se realiza previamente en todos los casos antes de llamar a searchMinesAround, 
        // para ahorrarse llamadas innecesarias al método, por lo que la comprobación inicial dentro del método nunca será verdadera. 
        // Aun así se mantiene esta comprobación como precaución por si se crea un nuevo caso en el que se llama a la función sin controlarlo previamente.
        if (userBoard[row][col].isDiscovered() || userBoard[row][col].isDoubtful()) {
            return false;
        }
        boolean boom = false;
        if (board[row][col]) {
            boom = true;
        }
        userBoard[row][col].setDiscovered(true);
        notDiscoveredPositions.removeIf(p -> p[0] == row && p[1] == col);
        int numMinesAroundPos = 0;
        List<int[]> positionsToSearch = new ArrayList<>();
        
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                if (i+row >= 0 && y+col >= 0 && i+row < boardRows && y+col < boardCols){
                    if (board[i+row][y+col]) {
                        numMinesAroundPos++;
                    } else {
                        positionsToSearch.add(new int[] {i+row, y+col});
                    }
                }
            }
        }

        for (int[] pos : positionsToSearch) {
            if (!userBoard[pos[0]][pos[1]].isDiscovered() && (numMinesAroundPos == 0) && !userBoard[pos[0]][pos[1]].isDoubtful()){
                boolean innerBoom = searchMinesAround(pos[0],pos[1]);
                if (innerBoom && !boom){
                    boom = true;
                }
                
            }
        }
        
        if (numMinesAroundPos != 0){
            userBoard[row][col].setText(String.valueOf(numMinesAroundPos));
            switch (numMinesAroundPos) {
                case 1:
                    userBoard[row][col].setForeground(new Color(0, 1, 249));
                    break;
                case 2:
                    userBoard[row][col].setForeground(new Color(1, 126, 4));
                    break;
                case 3:
                    userBoard[row][col].setForeground(new Color(251, 2, 0));
                    break;
                case 4:
                    userBoard[row][col].setForeground(new Color(1, 1, 128));
                    break;
                case 5:
                    userBoard[row][col].setForeground(new Color(129, 1, 3));
                    break;
                case 6:
                    userBoard[row][col].setForeground(new Color(0, 128, 128));
                    break;
                case 7:
                    userBoard[row][col].setForeground(new Color(0, 0, 0));
                    break;
                case 8:
                    userBoard[row][col].setForeground(new Color(128, 128, 128));
                    break;
            }
            userBoard[row][col].setNumMinesAroundPos(numMinesAroundPos);
            if (!userBoard[row][col].isDisabled()) {
                userBoard[row][col].addListenerDiscovered();
                userBoard[row][col].disableSquare();
            }
        } else {
            userBoard[row][col].disableSquareAll();
        }
        userBoard[row][col].setBackground(new Color(168, 168, 168));
        return boom;
    }
    */
    
    public void generateMines(int row, int col){
        
        if (row < 0 || col < 0 || row >= boardRows || col >= boardCols) {
            irrationalMove();
        }
        
        List<int[]> excludePositions = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int y = -1; y < 2; y++) {
                if (i+row >= 0 && y+col >= 0 && i+row < boardRows && y+col < boardCols){
                    excludePositions.add(new int[] {i+row, y+col});
                }
            }
        }
        generateRandomPositionExcluding(excludePositions);
        searchMinesAround(row,col);
    }
    
    // Method to obtain random positions excluding the initial ones
    private void generateRandomPositionExcluding(List<int[]> excludePositions) {
        Random random = new Random();
        
        // Remove excluded positions (initial and adjacents) from the list of available positions
        for (int[] pos : excludePositions) {
            availablePositions.removeIf(p -> p[0] == pos[0] && p[1] == pos[1]);
        }
        
        for (int i = 0; i < numMines; i++) {
            int randomIndex = random.nextInt(availablePositions.size());
            int[] randomMatrixIndex = availablePositions.get(randomIndex);
            minesPositions.add(randomMatrixIndex);
            board[randomMatrixIndex[0]][randomMatrixIndex[1]] = true;
            availablePositions.remove(randomIndex);
        }
        
        // The initial and adjacent positions are added again to be able to eliminate 
        // the click listeners from all the squares at the end of the game
        for (int[] pos : excludePositions) {
            availablePositions.add(new int[] {pos[0], pos[1]});
        }
    }
}
