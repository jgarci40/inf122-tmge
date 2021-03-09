package com.TMGE.Games.Bejeweled.UI;

import com.TMGE.Games.Bejeweled.Game;
import com.TMGE.Logic.PlayerManager;

import java.util.Scanner;

public class GameUI {
    private Game game;

    public GameUI(Game game) {
        this.game = game;
    }

    public void setup(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Bejeweled");

        int goal = -1;
        while(goal < 1){
            System.out.print("Enter your jewel goal(100):");
            if(sc.hasNextInt()){
                int newGoal = sc.nextInt();
                if(newGoal > 0) {
                    this.game.setMaxPoints(newGoal);
                    goal = newGoal;
                }
                else{
                    System.out.println("Invalid input");
                }
            }
            else{
                goal = 100;
            }
        }

        System.out.println("Awesome! Let's start the game");
    }

    public void start(){
        this.game.initializeBoard();
        while(this.game.isAnotherMove() && !this.game.hasPlayerWon()){
            this.game.getPlayerManager().printPlayersInfo();
            this.game.printBoard();
            this.getPlayerMove();
            int playerPoints = this.game.destroyShiftFillUntilNoDestroysAvailable();
            PlayerManager pm = this.game.getPlayerManager();
            pm.addPoints(playerPoints, pm.getPlayerTurnIndex());
            pm.shiftTurns();
        }
        System.out.println("Game Over!");
        this.game.getPlayerManager().printPlayersInfo();
    }

    private void getPlayerMove(){
        boolean hasPlayerPlayed = false;
        int fromRow = -1;
        int fromCol= -1;
        int toRow= -1;
        int toCol= -1;
        while(!hasPlayerPlayed){
            fromRow = this.getValidRow("Enter From Row: ");
            fromCol = this.getValidColumn("Enter From Column: ");
            toRow = this.getValidRow("Enter To Row: ");
            toCol = this.getValidRow("Enter to Column: ");

            if(this.game.isValidMove(fromCol, fromRow, toCol, toRow)){
                System.out.println("You made a move!");
                hasPlayerPlayed = true;
            } else {
                System.out.println("Invalid move. Try again");
            }
        }
        this.game.swapPieces(fromCol, fromRow, toCol, toRow);
    }

    private int getValidRow(String text){
        Scanner sc = new Scanner(System.in);
        Integer row = null;

        while(row == null){
            System.out.print(text);
            if(sc.hasNextInt()){
                int value = sc.nextInt();
                if(this.game.isValidRow(value)){
                    row = value;
                } else{
                    System.out.println("Invalid row. Please try again");
                }
            }
        }
        return row.intValue();
    }

    private int getValidColumn(String text){
        Scanner sc = new Scanner(System.in);
        Integer col = null;

        while(col == null){
            System.out.print(text);
            if(sc.hasNextInt()){
                int value = sc.nextInt();
                if(this.game.isValidColumn(value)){
                    col = value;
                } else{
                    System.out.println("Invalid row. Please try again");
                }
            }
        }
        return col.intValue();
    }

}
