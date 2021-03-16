package com.TMGE.Logic.Board;

import com.TMGE.Logic.Board.Init.BoardInit;
import com.TMGE.Logic.Board.PostDestroy.PostDestroy;
import com.TMGE.Logic.Board.DestroyBehavior.DestoyBehavior;
import com.TMGE.Logic.Board.DisplayBehavior.DisplayBehavior;
import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;

public class Board {
    private boolean isInitialized               = false;
    private ArrayList<ArrayList<Tile>> board;
    private int numOfColumns;
    private int numOfRows;
    private ArrayList<String> tilePieceSet;
    private BoardInit boardInit;
    private DestoyBehavior destroyBehavior;
    private PostDestroy postDestroy;
    private DisplayBehavior displayBehavior;


    public Board(int numOfColumns, int numOfRows, BoardInit boardInit, ArrayList<String> tilePieceSet, DestoyBehavior d, PostDestroy pd, DisplayBehavior db) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        this.boardInit = boardInit;
        this.board = boardInit.initialize(numOfRows, numOfColumns, tilePieceSet);
        this.tilePieceSet = tilePieceSet;
        this.destroyBehavior = d;
        this.postDestroy = pd;
        this.displayBehavior = db;
    }

    public ArrayList<ArrayList<Tile>> getBoard() {
        return board;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public ArrayList<String> getTilePieceSet() {
        return tilePieceSet;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setBoard(ArrayList<ArrayList<Tile>> board) {
        this.board = board;
    }

    public void setNumOfColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public void init(){
        this.board = this.boardInit.initialize(this.numOfRows, this.numOfColumns, this.tilePieceSet);
        this.isInitialized = true;
    }

    public int destroy(){
        return this.destroyBehavior.destroy(this);
    }

    public void postDestroy(){
        this.postDestroy.postDestroy(this);
    }

    public void display() {
        this.displayBehavior.displayBoard(this);
    }
}
