package com.TMGE.Games.Bejeweled.UI;

import com.TMGE.Games.Bejeweled.Bejeweled;
import com.TMGE.Logic.Coords;
import com.TMGE.Logic.Player.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
        Instant start = Instant.now();
              


        // Initialize board
        this.bejeweled.getBoard().init();
        boolean shouldLoop = true;

        while(shouldLoop) {
            // get current Player and point count thus far
            Player current_player   = this.bejeweled.getPm().getCurrentTurnPlayer();
            int points              = current_player.getPoints();

            // 1) Display board
            this.bejeweled.getBoard().display();
            // 2) Make move
            this.getPlayerMove();
            // 3) Display board again with swapped move
            this.bejeweled.getBoard().display();

            // 4) Destroy and calculate points from move
            points += this.bejeweled.getBoard().destroy();
            
            // Update current player points
            this.bejeweled.getPm().getCurrentTurnPlayer().setPoints(points); 

            // Tell player how many points they got
            

            // 6) Post Destroy
            this.bejeweled.getBoard().postDestroy();
            // 7) Check time -> continue or end game and switch players
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            // System.out.println("Time elapsed: " + timeElapsed);
            if (timeElapsed >= 30000) {
                shouldLoop = false;
                System.out.println("Your time is up " + current_player.getName());
            }
    


            // shouldLoop = false;
        }

        this.bejeweled.getPm().printPlayersInfo();

        System.out.println("Thanks for playing!!!");
    }

    private void displayGameRules(){
        // TODO: print game rules
    }

    // methods for getting player input
    private void getPlayerMove(){
        // TODO: implement
        Coords fromCoords   = getValidCoords("Enter 1st piece to swap separated by space: Ex: 0 1: ");
        Coords toCoords     = getValidCoords("Enter 2nd piece to swap separated by space: Ex: 0 1: "); 
        this.bejeweled.swapPieces(fromCoords, toCoords);
    }

    private Coords getValidCoords(String text) {
        Scanner sc = new Scanner(System.in);
        Integer row = null;
        Integer col = null;

        while(row == null || col == null) {
            System.out.print(text);
            row = (sc.nextInt());
            col = (sc.nextInt());
            
            // validate coords
            if(!this.isValidRow(row)) {
                System.out.println("Invalid row. Please try again");
                row = null;
                col = null;
                continue;
            }
            if(!this.isValidRow(col)) {
                System.out.println("Invalid col. Please try again");
                row = null;
                col = null;
                continue;
            }
        }
        return new Coords(row.intValue(), col.intValue());
    }

    public boolean isValidColumn(int col){
        return col >= 0 && col < this.bejeweled.getBoard().getNumOfColumns();
    }

    public boolean isValidRow(int row){
        return row >= 0 && row < this.bejeweled.getBoard().getNumOfRows();
    }
}
