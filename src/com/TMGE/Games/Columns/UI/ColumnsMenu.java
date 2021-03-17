package com.TMGE.Games.Columns.UI;

import com.TMGE.Games.Columns.Columns;
import java.time.Duration;
import java.time.Instant;

import java.util.Scanner;

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
                    System.out.println("Game rules are ");
                    break;
                case 2:
                    this.addPlayers();
                    break;
                case 3:
                    System.out.println("Start Game is selected");
                    this.startGame();
                    break;
                default:
                    System.out.println("Invalid Command Please try again");
                    break;
            }
        }
    }
    private void addPlayers(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numberOfPlayers = sc.nextInt();
        for(int i = 0; i < numberOfPlayers; ++i){
            this.columns.getPm().addPlayer(String.format("Player %d", numberOfPlayers + 1));
        }
        this.columns.getPm().printPlayersInfo();
        System.out.println();
    }


    private void displayOptions(){
        System.out.println("Menu Options: ");
        System.out.println("\t0: Exit");
        System.out.println("\t1: Game rules");
        System.out.println("\t2: Add Player");
        System.out.println("\t3: Start Game");
    }

    private void startGame() {
        this.columns.getBoard().init();
        Scanner obj = new Scanner(System.in);
        String cmd = " ";
        boolean shouldLoop = true;
        while(shouldLoop) {
            this.columns.getBoard().display();
            System.out.println("To create a faller enter in following format: F column# X Y Z");
            cmd = obj.nextLine();
            if (cmd.startsWith("F")) {
                String[] cmdArray = cmd.split(" ");
                boolean found = this.columns.spawnFaller(cmdArray);
                this.columns.postSpawn();
                if (found == false)
                {
                    this.columns.getBoard().display();
                    System.out.println("Whole piece doesn't fit in selected column! GAME OVER");
                    break;
                }
                else{
                    //nt points = this.columns.postSpawn();
                    this.columns.getBoard().destroy();
                    this.columns.getBoard().postDestroy();
                }

            }
            else if (cmd.equals("<")) {
                System. out.println("Left <<<<<<");
            }
            else if (cmd.equals("q")) {
                shouldLoop = false;
            }
            else {
                System. out.println(cmd);
            }
        }
    }
}

