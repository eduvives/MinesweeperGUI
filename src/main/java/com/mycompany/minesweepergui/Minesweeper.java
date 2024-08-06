/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweepergui;

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
    private static final int difficultRows = 30;
    private static final int difficultCols = 16;
    private static final int difficultMines = 99;
    
    private static int boardRows;
    private static int boardCols;
    private static int numMines;
    private int correctMarkedMinesNum = 0;
    
    private List<int[]> minesPositions = new ArrayList<>();
    private List<int[]> availablePositions = new ArrayList<>();
    private boolean[][] accessedPositions;
    
    private boolean[][] board;
    public String[][] userBoard;
    
    public boolean startGame (int difficulty) {
        
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
        // Create a list of all possible positions within the array
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardCols; j++) {
                availablePositions.add(new int[] {i, j});
            }
        }
        
        accessedPositions = new boolean[boardRows][boardCols];
        board = new boolean[boardRows][boardCols];
        userBoard = new String[boardRows][boardCols];
        
        return true;
    }
    
    public boolean makeMove(String action, int row, int col){
        if (action.equals("p") || action.equals("P")){
            if (userBoard[row][col].equals(".")) {
                userBoard[row][col] = "p";
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum++;
                }
            } else if (userBoard[row][col].equals("p")) {
                userBoard[row][col] = ".";
                if (minesPositions.stream().anyMatch(pos -> pos[0] == row && pos[1] == col)) {
                    correctMarkedMinesNum--;
                }
            } else {
                irrationalMove();
            }
        } else if ("x".equals(action) || "X".equals(action)){
            if(board[row][col]){
                for (int[] pos : minesPositions) {
                    userBoard[pos[0]][pos[1]] = "x";
                }
                userBoard[row][col] = "o";
                return false;
            } else {
                searchMinesAround(row,col);
            }
        } else {
            irrationalMove();
        }
        return true;
    }
    
    public boolean checkWin (){
        if(correctMarkedMinesNum == numMines) {
            for (int[] pos : availablePositions) {
                if (userBoard[pos[0]][pos[1]].equals(".")){
                    userBoard[pos[0]][pos[1]] = " ";
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
    
    private void searchMinesAround(int row, int col){
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
                searchMinesAround(pos[0],pos[1]);
            }
        }
        
        if (numMinesAroundPos != 0){
            userBoard[row][col] = String.valueOf(numMinesAroundPos);
        } else {
            userBoard[row][col] = " ";
        }
        availablePositions.removeIf(p -> p[0] == row && p[1] == col);
    }
    
    public void showBoard() {
        for (int i = 0; i < userBoard.length; i++) {
            for (int j = 0; j < userBoard[i].length; j++) {
                System.out.print(userBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public boolean generateMines(int row, int col){
        
        if (row < 0 || col < 0 || row >= boardRows || col >= boardCols) {
            irrationalMove();
            return false;
        }
        
        /*
        for (String[] fila : userBoard) {
            Arrays.fill(fila, String.valueOf("."));
        }
        
        board[0][0] = true;
        board[0][6] = true;
        board[2][0] = true;
        board[3][6] = true;
        board[5][0] = true;
        board[5][2] = true;
        board[6][0] = true;
        board[6][3] = true;
        board[6][7] = true;
        board[7][0] = true;
        
        userBoard[0][0] = "x";
        userBoard[0][6] = "x";
        userBoard[2][0] = "x";
        userBoard[3][6] = "x";
        userBoard[5][0] = "x";
        userBoard[5][2] = "x";
        userBoard[6][0] = "x";
        userBoard[6][3] = "x";
        userBoard[6][7] = "x";
        userBoard[7][0] = "x";
        
        searchMinesAround(row,col);
        */
        
        for (String[] fila : userBoard) {
            Arrays.fill(fila, String.valueOf("."));
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
        
        return true;
    }
    
    // Method to obtain random positions excluding the initial ones
    private void generateRandomPositionExcluding(List<int[]> excludePositions) {
        Random random = new Random();
        
        // Remove excluded positions from the list of available positions
        for (int[] pos : excludePositions) {
            availablePositions.removeIf(p -> p[0] == pos[0] && p[1] == pos[1]);
        }
        
        for (int i = 0; i < numMines; i++) {
            int randomIndex = random.nextInt(availablePositions.size());
            int[] randomMatrixIndex = availablePositions.get(randomIndex);
            minesPositions.add(randomMatrixIndex);
            board[randomMatrixIndex[0]][randomMatrixIndex[1]] = true;
            //userBoard[randomMatrixIndex[0]][randomMatrixIndex[1]] = "x";
            availablePositions.remove(randomIndex);
        }
    }
}
