package com.TMGE;

import java.util.ArrayList;

public interface Game {
    // public final ArrayList<ArrayList<Tile>> gameBoard = new
    // ArrayList<ArrayList<Tile>>();
    // public final PlayerManager pm = new PlayerManager();
    // public final TMGE tmge = new TMGE(gameBoard, "", pm);
    public final TMGE tmge = new TMGE();

    public void makeMove(int fromColumn, int fromRow, int toColumn, int toRow);

    public void matchPatter();

    public void checkMove(int fromColumn, int fromRow, int toColumn, int toRow);

    public void changeTurn(int playerIndex);

    public void checkBoard();

    public void updateGame();
}
