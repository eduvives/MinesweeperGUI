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
    
    private static final int easyRows = 8;
    private static final int easyCols = 8;
    private static final int easyMines = 10;
    private static final int mediumRows = 16;
    private static final int mediumCols = 16;
    private static final int mediumMines = 40;
    private static final int difficultRows = 16;
    private static final int difficultCols = 30;
    private static final int difficultMines = 99;
    
    private static int boardRows;
    private static int boardCols;
    private static int numMines;
    private int markedMinesNum;
    private int correctMarkedMinesNum;
    
    private List<int[]> minesPositions = new ArrayList<>();
    private List<int[]> availablePositions = new ArrayList<>();
    private boolean[][] accessedPositions;
    private boolean gameOver;
    
    private boolean[][] board;
    public Square[][] userBoard;
    
    public boolean startGame (int difficulty, boolean repeat) {
        
        if (!repeat) {
            switch (difficulty) {
                case 1:
                    boardRows = easyRows;
                    boardCols = easyCols;
                    numMines = easyMines;
                    break;
                case 2:
                    boardRows = mediumRows;
                    boardCols = mediumCols;
                    numMines = mediumMines;
                    break;
                case 3:
                    boardRows = difficultRows;
                    boardCols = difficultCols;
                    numMines = difficultMines;
                    break;
                default:
                    irrationalMove();
                    return false;
            }
            minesPositions.clear();
            board = new boolean[boardRows][boardCols];
        }
        
        gameOver = false;
        markedMinesNum = 0;
        correctMarkedMinesNum = 0;
        availablePositions.clear();
        accessedPositions = new boolean[boardRows][boardCols];
        
        // Create a list of all possible positions within the array
        for (int i = 0; i < boardRows; i++) {
            final int row = i;
            for (int j = 0; j < boardCols; j++) {
                final int col = j;
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
    public void setUserBoard(Square[][] allSquares){
        userBoard = allSquares;
    }
    
    public boolean makeMove(String action, int row, int col){
        if (action.equals("🚩")){
            if (userBoard[row][col].isMarked()) {
                userBoard[row][col].setDiscovered(false);
                userBoard[row][col].setMarked(false);
                userBoard[row][col].setText("");
                userBoard[row][col].setFont(new Font("SansSerif", Font.BOLD, 20));
                markedMinesNum--;
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum--;
                }
            } else if (!userBoard[row][col].isDiscovered()) {
                userBoard[row][col].setDiscovered(true);
                userBoard[row][col].setMarked(true);                                        
                userBoard[row][col].setText("🚩");
                userBoard[row][col].setFont(new Font("SansSerif", Font.PLAIN, 18));
                markedMinesNum++;
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum++;
                }
            } else {
                irrationalMove();
            }
        } else if (action.equals("💣")){
            if (!userBoard[row][col].isMarked()) {
                if(board[row][col]){
                    gameOver();
                    return true;
                } else {
                    if (searchMinesAround(row,col)) {
                        gameOver();
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
    
    public void gameOver() {
        if (!gameOver){
            for (int[] pos : availablePositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
            }
            for (int[] pos : minesPositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
                userBoard[pos[0]][pos[1]].setText("💣");
                userBoard[pos[0]][pos[1]].setBackground(new Color(230, 76, 101));
                userBoard[pos[0]][pos[1]].setFont(new Font("SansSerif", Font.PLAIN, 18));
            }
        }
        gameOver = true;
    }
    public boolean checkWin (){
        if(correctMarkedMinesNum == numMines && markedMinesNum == numMines) {
            for (int[] pos : minesPositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
            }
            for (int[] pos : availablePositions) {
                userBoard[pos[0]][pos[1]].disableSquareAll();
                //if (!userBoard[pos[0]][pos[1]].isDiscovered()){
                    searchMinesAround(pos[0],pos[1]);
                //}
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void irrationalMove(){
        System.out.println("Don't be stupid... please.");
    }
    
    private boolean searchMinesAround(int row, int col){
        if (userBoard[row][col].isMarked()) {
            return false;
        }
        boolean boom = false;
        if (board[row][col]) {
            boom = true;
        }
        accessedPositions[row][col] = true;
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
            if (!accessedPositions[pos[0]][pos[1]] && (numMinesAroundPos == 0)){
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
            }
        }
        if (!userBoard[row][col].isDisabled()) {
            userBoard[row][col].disableSquare();
        }
        userBoard[row][col].setDiscovered(true);
        userBoard[row][col].setBackground(new Color(168, 168, 168));
        return boom;
    }
    
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
