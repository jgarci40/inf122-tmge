package com.TMGE;

import java.util.ArrayList;

public class TMGE {
    private ArrayList<ArrayList<Tile>> gameBoard;
    private String gameType;
    private PlayerManager playerManager;



    public TMGE(ArrayList<ArrayList<Tile>> gameBoard, String gameType, PlayerManager playerManager) {
        this.gameBoard = gameBoard;
        this.gameType = gameType;
        this.playerManager = playerManager;
    }

    public ArrayList<ArrayList<Tile>> getGameBoard() {
        return gameBoard;
    }

    public String getGameType() {
        return gameType;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setGameBoard(ArrayList<ArrayList<Tile>> gameBoard) {
        this.gameBoard = gameBoard;
    }
}
