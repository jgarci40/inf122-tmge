package com.TMGE.GamesModule.src.main.java.com.Columns.UI;

import java.util.Scanner;

import com.TMGE.GamesModule.src.main.java.com.Columns.Columns;

public class ColumnsMenu {
    Columns columns;

    public ColumnsMenu() {
        this.columns = Columns.refreshGame();
    }

    public ColumnsMenu(Columns columns) {
        this.columns =  columns;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to " + columns.getGameName());
        int command;
        while(true){
            this.displayOptions();
            System.out.print("[command]: ");
            command = sc.nextInt();
            switch(command){
                case 0:
                    System.out.println("Game exited  " + this.columns.getGameName());
                    return;
                case 1:
                    this.displayGameRules();
                    break;
                case 2:
                    System.out.println("Start Game is selected");
                    this.startGame();
                    break;
                default:
                    System.out.println("Invalid Command Please try again");
                    break;
            }
        }
    }


    private void displayOptions(){
        System.out.println("Menu Options: ");
        System.out.println("\t0: Exit");
        System.out.println("\t1: Game rules");
        System.out.println("\t2: Start Game");
    }

    private void startGame() {
        int player1 = 0;
        int player2 = 0;
        this.columns.getBoard().init();
        Scanner obj = new Scanner(System.in);
        String cmd = " ";
        boolean shouldLoop = true;
        System.out.println("Player 1 your turn to play a game of Columns");
        System.out.println();
        while(shouldLoop) {
            this.columns.getBoard().display();
            System.out.println("Select the following commands: Q: quit, F: generate faller");
            cmd = obj.nextLine();
            if (cmd.toLowerCase().startsWith("f")) {
                boolean found = this.columns.generateColors();
                if (!found)
                {
                    this.columns.getBoard().display();
                    System.out.println("Whole piece doesn't fit in selected column! GAME OVER");
                    break;
                }
                else{
                    //int points = this.columns.postSpawn();
                    this.columns.getBoard().display();
                    player1 += this.columns.getBoard().destroy();
                    this.columns.getBoard().postDestroy();
                }

            }
            else if (cmd.toLowerCase().equals("q")) {
                shouldLoop = false;
            }
            else {
                System.out.println("Invalid command! Try again");
                continue;
            }
        }
        System.out.println("Player1 score: " + player1);


        // player2

        System.out.println("Player 2 your turn to play a game of Columns");
        System.out.println();
        this.columns.getBoard().init();
        while(true) {
            this.columns.getBoard().display();
            System.out.println("Select the following commands: Q: quit, F: generate faller");
            cmd = obj.nextLine();
            if (cmd.toLowerCase().startsWith("f")) {
                boolean found = this.columns.generateColors();
                if (!found)
                {
                    this.columns.getBoard().display();
                    System.out.println("Whole piece doesn't fit in selected column! GAME OVER");
                    break;
                }
                else{
                    //int points = this.columns.postSpawn();
                    this.columns.getBoard().display();
                    player2 += this.columns.getBoard().destroy();
                    this.columns.getBoard().postDestroy();
                }

            }
            else if (cmd.toLowerCase().equals("q")) {
                break;
            }
            else {
                System.out.println("Invalid command! Try again");
                continue;
            }
        }
        System.out.println("Player1 score: " + player1);
        System.out.println("Player2 score: " + player2);

        if (player1 > player2)
        {
            System.out.println("Player 1 wins!");
        }
        else if (player2 > player1)
        {
            System.out.println("Player 2 wins!");
        }
        else
        {
            System.out.println("Player 1 and player 2 tied!");
        }
    }

    private void displayGameRules(){
        System.out.println("welcome to our implementation of Columns");
        System.out.println("If you're unfamiliar with the game, Columns is a Tile-matching game where players can generate");
        System.out.println("a faller which is a column of three pieces. The player can choose to rotate the faller's ");
        System.out.println("pieces then select what column to place the faller in, in order to get matches ");
        System.out.println();
        System.out.println("For our implementation, you can create horizontal or vertical matches.");
        System.out.println("Horizontal matches are cleared first and are worth more points, followed by vertical matches");
        System.out.println("If your piece hits the top of the board, you lose!");
        System.out.println("Player1 goes first then Player 2. The player with the higher score wins!");
    }
}

