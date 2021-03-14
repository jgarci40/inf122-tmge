package com.TMGE.Games.Bejeweled.UI;

import com.TMGE.Games.Bejeweled.Bejeweled;

import java.util.Scanner;

public class BejeweledMenu {
    Bejeweled bejeweled;

    public BejeweledMenu() {
        this.bejeweled = Bejeweled.refreshGame();
    }

    public BejeweledMenu(Bejeweled bejeweled) {
        this.bejeweled = bejeweled;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to " + bejeweled.getGameName());

        int command;
        while(true){
            this.displayOptions();
            System.out.print("[command]: ");
            command = sc.nextInt();
            switch(command){
                case 0:
                    System.out.println("Thanks for playing " + this.bejeweled.getGameName());
                    return;
                case 1:
                    this.displayGameRules();
                    break;
                case 2:
                    this.addPlayers();
                    break;
                case 3:
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
        System.out.println("\t2: Add Player");
        System.out.println("\t3: Start Game");
    }

    private void addPlayers(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numberOfPlayers = sc.nextInt();
        for(int i = 0; i < numberOfPlayers; ++i){
            this.bejeweled.getPm().addPlayer(String.format("Player %d", numberOfPlayers + 1));
        }
        this.bejeweled.getPm().printPlayersInfo();
        System.out.println();
    }

    private void startGame(){
        // Add a default player if none are added
        if(this.bejeweled.getPm().getPlayers().size() == 0) this.bejeweled.getPm().addPlayer("Default Player");
        else this.bejeweled.getPm().getPlayers().forEach(player->player.setPoints(0));

        // TODO: Start timer

        // Initialize board
        this.bejeweled.getBoard().init();

        boolean shouldLoop = true;
        while(shouldLoop){
            // 1) Make move
            this.makeMove();
            // 2) Calculate points from move
            // 3) Destroy
            this.bejeweled.getBoard().destroy();
            // 3) Post Destroy
            this.bejeweled.getBoard().postDestroy();
            // 4) Check time -> continue or end game and switch players


            shouldLoop = false;
        }

        this.bejeweled.getPm().printPlayersInfo();

        System.out.println("Thanks for playing!!!");
    }

    private void displayGameRules(){
        // TODO: print game rules
    }

    private void makeMove(){
        // TODO: implement
    }


}
