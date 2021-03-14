package com.TMGE.Logic.Player;

import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<Player> players;
    private int playerTurnIndex;

    public PlayerManager() {
        this.players = new ArrayList<>();
        this.playerTurnIndex = 0;
    }

    public PlayerManager(ArrayList<Player> players, int playerTurnIndex) {
        this.players = players;
        this.playerTurnIndex = playerTurnIndex;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    public Player getCurrentTurnPlayer() {
        return this.players.get(this.playerTurnIndex);
    }

    public void switchTurn(int playerIndex) {
        this.playerTurnIndex = playerIndex;
    }

    public void updatePoints(int points, int playerIndex) {
        this.players.get(playerIndex).setPoints(points);
    }

    public void addPoints(int points, int playerIndex) {
        System.out.println(playerIndex);
        int currentPoints = this.players.get(playerIndex).getPoints();
        this.players.get(playerIndex).setPoints(currentPoints + points);
    }

    public void subtractPoints(int points, int playerIndex) {
        int currentPoints = this.players.get(playerIndex).getPoints();
        this.players.get(playerIndex).setPoints(currentPoints - points);
    }

    public Player getWinner() {
        // This assumes that the winner is always the player with the highest amount of
        // points
        if (players.size() < 1)
            return null;

        Player winner = players.get(0);
        for (int i = 1; i < players.size(); ++i) {
            if (players.get(i).getPoints() > winner.getPoints())
                winner = players.get(i);
        }

        return winner;
    }

    public boolean isPlayersTurns(int playerIndex) {
        return this.playerTurnIndex == playerIndex;
    }

    public void addPlayer(String name) {
        Player p = new Player(name);
        players.add(p);
    }

    public void printPlayersInfo() {
        for (Player p : players) {
            System.out.println(p.getName() + ": " + Integer.toString(p.getPoints()));
        }
    }

    public void shiftTurns(){
        playerTurnIndex = (playerTurnIndex + 1) % this.players.size();
    }
}
