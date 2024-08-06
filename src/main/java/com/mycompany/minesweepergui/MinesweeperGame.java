/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.minesweepergui;

import java.util.*;

/**
 *
 * @author Eduard
 */
public class MinesweeperGame {

    public static void main(String[] args) {
        
        Minesweeper game = new Minesweeper();

        Scanner scan = new Scanner(System.in);
        
        boolean selectedDifficulty = false;
        
        while (!selectedDifficulty){
            System.out.print("Choose difficulty (1,2,3): ");
            int difficulty = scan.nextInt();
            // Consume the newline character left in the input buffer
            scan.nextLine();
            selectedDifficulty = game.startGame(difficulty);
        }
        
        boolean gameStarted = false;
        
        while (!gameStarted){
            System.out.print("Start position: ");
            String startPos = scan.nextLine();
            String[] startPosSplit = startPos.split(" ");
            gameStarted = game.generateMines(Integer.parseInt(startPosSplit[0]), Integer.parseInt(startPosSplit[1]));
        }
        
        game.showBoard();
        
        while (!game.checkWin()){
            System.out.print("You already know: ");
            String nextMove = scan.nextLine();
            String[] nextMoveSplit = nextMove.split(" ");
            if (!game.makeMove(nextMoveSplit[0], Integer.parseInt(nextMoveSplit[1]), Integer.parseInt(nextMoveSplit[2]))) {
                game.showBoard();
                System.out.println("Wrong");
                return;
            }
            game.showBoard();
        }
        game.showBoard();
        System.out.print("Congratulations");
    }
}