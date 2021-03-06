package com.TMGE.Logic;

import java.util.ArrayList;

public class TMGE {
    private ArrayList<ArrayList<Tile>> gameBoard;
    private String gameType;
    private PlayerManager playerManager;

    public TMGE() {
        gameBoard = new ArrayList<ArrayList<Tile>>();
        gameType = "";
        playerManager = new PlayerManager();
    }

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

    public void setGameType(String g) {
        gameType = g;
    }

    public void printBoard() {
        for (ArrayList<Tile> g : gameBoard) {
            for (int i = 0; i < g.size(); i++) {
                if (g.get(i).getOccupied() == null) {
                    System.out.print(" * ");
                } else {
                    System.out.print(g.get(i).getOccupied().getPieceType());
                }
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Tile>> createBoard(int rows, int columns){
        ArrayList<ArrayList<Tile>> temp = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            temp.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                Tile t = new Tile(i, j);
                temp.get(i).add(t);
            }
        }
        return temp;
    }
}
