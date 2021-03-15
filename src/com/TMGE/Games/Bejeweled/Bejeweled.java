package com.TMGE.Games.Bejeweled;

import com.TMGE.Logic.Board.DestroyBehavior.VerticalHorizontalDestroyBehavior;
import com.TMGE.Logic.Board.Init.FileRandomBoardInit;
import com.TMGE.Logic.Board.PostDestroy.GravityShiftAndFillRandom;
import com.TMGE.Logic.TMGE;

import java.util.ArrayList;

public class Bejeweled extends TMGE {

    static Bejeweled bj;

    public Bejeweled() {
        super("Bejeweled", 5, 5, new FileRandomBoardInit(), new ArrayList<>(), new VerticalHorizontalDestroyBehavior(), new GravityShiftAndFillRandom());
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
        // TODO: implement
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
