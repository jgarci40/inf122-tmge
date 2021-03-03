package com.TMGE;

public interface Game {
    public TMGE tmge = null;

    public void makeMove(int fromColumn, int fromRow, int toColumn, int toRow);
    public void matchPatter();
    public void checkMove(int fromColumn, int fromRow, int toColumn, int toRow);
    public void changeTurn(int playerIndex);
    public void checkBoard();
    public void updateGame();
}
