package com.TMGE.Logic;

public class Tile {
    private int row;
    private int col;
    private TilePiece occupied = null;

    public Tile(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public void setOccupied(TilePiece occupied) {
        this.occupied = occupied;
    }

    public TilePiece getOccupied() {
        return occupied;
    }
}
