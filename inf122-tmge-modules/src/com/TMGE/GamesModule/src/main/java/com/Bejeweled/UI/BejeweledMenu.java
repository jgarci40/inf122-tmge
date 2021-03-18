package com.TMGE.GamesModule.src.main.java.com.Bejeweled.UI;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

import com.TMGE.GamesModule.src.main.java.com.Coords;
import com.TMGE.GamesModule.src.main.java.com.Bejeweled.Bejeweled;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.Player.Player;

public class BejeweledMenu {
    Bejeweled bejeweled;

    public BejeweledMenu() {
        this.bejeweled = Bejeweled.getInstance();
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
                case 4: 
                    this.editPlayerInfo();
                    break;
                case 5:
                    this.seeHighScore();
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
        System.out.println("\t4: Edit Player Info");
        System.out.println("\t5: See High Score");
    }

    private void editPlayerInfo() {
        Scanner sc = new Scanner(System.in);
        int player = selectPlayer();
        System.out.print("Enter new name: ");
        String name = sc.next();
        try {
            this.bejeweled.getPm().getPlayers().get(player-1).setName(name);
            this.bejeweled.getPm().printPlayersInfo();
        } catch (Exception e) {
            System.out.println("Error. Players not changed: ");
            return;
        }
    }
    
    private int selectPlayer() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Player> players = this.bejeweled.getPm().getPlayers();
        if (players.isEmpty()) {
            System.out.println("No players added yet!");
            return -1;
        }
        this.bejeweled.getPm().printPlayersInfo();

        int player = 1;
        if (players.size() == 2) {
            System.out.print("Enter player 1 or 2: ");
            player = sc.nextInt();
        }
        return player;
    }

    private void seeHighScore() {
        int player = this.selectPlayer();
        try {
            Player p    = this.bejeweled.getPm().getPlayers().get(player-1);
            int score   = p.getHighScore();
            String name = p.getName();
            System.out.println(name + "'s high score: " + score);
        } catch (Exception e) {
            System.out.println("Error. Invalid player selected: ");
            return;
        }
    }

    private void addPlayers(){
        if (this.bejeweled.getPm().getPlayers().size() == 2) {
            System.out.println("2 Player maximum already reached!");
            this.bejeweled.getPm().printPlayersInfo();
            return;
        }
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter player name: ");
        String name = sc.next();
        this.bejeweled.getPm().addPlayer(name);

        this.bejeweled.getPm().printPlayersInfo();
        System.out.println();
    }

    private void startGame(){
        // Add a default player if none are added
        if(this.bejeweled.getPm().getPlayers().size() == 0) this.bejeweled.getPm().addPlayer("Default Player");
        else this.bejeweled.getPm().getPlayers().forEach(player->player.setPoints(0));

        

        // Initialize board
        this.bejeweled.getBoard().init();
        long roundTime = 60000;
        this.bejeweled.getPm().switchTurn(0);   // start player 1

        // Start timer
        Instant start = Instant.now();

        // player 1 game turn
        gameLoop(start, roundTime);

        // get a new board for player 2
        this.bejeweled = Bejeweled.refreshGame();
        this.bejeweled.getPm().switchTurn(1);   // start player 2

        // if 1 player game, return
        if (this.bejeweled.getPm().getPlayers().size() < 2) {return;}

        // Start timer for player 2
        start = Instant.now();

        // player 2 game turn
        gameLoop(start, roundTime);

        this.bejeweled.getPm().printPlayersInfo();
        Player winner = this.bejeweled.getPm().getWinner();
        System.out.println("The winner is... " + winner.getName() + "!");
        System.out.println("Thanks for playing!!!");
    }

    private void gameLoop(Instant start, long roundTime) {
        while (true) {
            // get current Player's point count thus far
            int points = this.bejeweled.getPm().getCurrentTurnPlayer().getPoints();

            // 1) Display board
            this.bejeweled.getBoard().display();
            // 2) Make move
            this.getPlayerMove();
            // 3) Display board again with swapped move
            this.bejeweled.getBoard().display();

            int pointsThisMove = 0;
            // 4) Destroy and calculate points from move
            while (true) {
                int p  = this.bejeweled.getBoard().destroy();
                if (p == 0) {
                    break;
                }    
                this.bejeweled.getBoard().display();
                this.bejeweled.getBoard().postDestroy();
                this.bejeweled.getBoard().display();
                points += p;
                pointsThisMove += p;
            }

            // Update current player points
            this.bejeweled.getPm().getCurrentTurnPlayer().setPoints(points); 

            // Tell player how many points they got
            System.out.println(pointsThisMove + " points this move!");
            System.out.println(points + " total points!");

            // 6) Post Destroy
            this.bejeweled.getBoard().postDestroy();
            // 7) Check time -> continue or end game and switch players
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();

            System.out.println("Seconds elapsed: " + (timeElapsed / 1000));
            if (timeElapsed >= roundTime) {
                System.out.println("Your time is up " + this.bejeweled.getPm().getCurrentTurnPlayer().getName());
                System.out.println("");
                // set high score if applicable
                if (points > this.bejeweled.getPm().getCurrentTurnPlayer().getHighScore()) {
                    this.bejeweled.getPm().getCurrentTurnPlayer().setHighScore(points);
                }
                return;
            }
        }
    }

    private void displayGameRules(){
        System.out.println("welcome to our implementation of Bejeweled!");
        System.out.println("If you're unfamiliar with the game, Bejeweled is a Tile-matching game where players can swap adjacent pieces");
        System.out.println("  to create matches of 3 or more. ");
        System.out.println("For our implementation, you can create horizontal or vertical matches.");
        System.out.println("Horizontal matches are cleared first, followed by vertical matches");
        System.out.println("Player 1 has 1 minute to get as many matches as possible, followed by Player 2.");
        System.out.println("The player with the higher score wins!");
    }

    // methods for getting player input
    private void getPlayerMove(){
        // asks current player for two pieces to swap
        // player input is expected in the form "0 1" where 0 is row and 1 is column
        Coords fromCoords   = getValidCoords("Enter 1st piece to swap separated by space: Ex: 0 1: ");
        Coords toCoords     = getValidCoords("Enter 2nd piece to swap separated by space: Ex: 0 1: "); 
        this.bejeweled.swapPieces(fromCoords, toCoords);
    }

    private Coords getValidCoords(String text) {
        // prompts and validates pieces to swap
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
