package com.TMGE.Games.Bejeweled;

import com.TMGE.Logic.Board.DestroyBehavior.VerticalHorizontalDestroyBehavior;
import com.TMGE.Logic.Board.DisplayBehavior.DisplayFullBoard;
import com.TMGE.Logic.Board.Init.FileRandomBoardInit;
import com.TMGE.Logic.Board.PostDestroy.GravityShiftAndFillRandom;
import com.TMGE.Logic.Tile.Tile;
import com.TMGE.Logic.TMGE;

import java.util.ArrayList;
import java.util.Arrays;

public class Bejeweled extends TMGE {

    static Bejeweled bj;

    private Bejeweled() {
        super("Bejeweled", 5, 5, new FileRandomBoardInit(), new ArrayList<String>(Arrays.asList("!","@","$","&")), new VerticalHorizontalDestroyBehavior(), new GravityShiftAndFillRandom(), new DisplayFullBoard());
    }

    public static Bejeweled getInstance(){
        if(bj == null) bj = new Bejeweled();
        return bj;
    }

    public static Bejeweled refreshGame(){
        bj = new Bejeweled();
        return bj;
    }

    private void swapPieces(int fromRow, int fromCol, int toRow, int toCol){
        // swap tiles on the bejeweled board
        Tile from_tile  = bj.getBoard().getBoard().get(fromRow).get(fromCol);
        Tile to_tile    = bj.getBoard().getBoard().get(toRow).get(toCol);
        bj.getBoard().getBoard().get(fromRow).set(fromCol, to_tile);
        bj.getBoard().getBoard().get(toRow).set(toCol, from_tile);
    }

    private void postSwap(){
        this.getBoard().destroy();
        this.getBoard().postDestroy();
    }

    public void takeMove(int fromRow, int fromCol, int toRow, int toCol){
        this.swapPieces(fromRow, fromCol, toRow, toCol);
        this.postSwap();
    }
}
