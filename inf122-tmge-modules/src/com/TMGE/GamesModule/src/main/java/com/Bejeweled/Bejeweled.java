package com.TMGE.GamesModule.src.main.java.com.Bejeweled;

import java.util.ArrayList;
import java.util.Arrays;

import com.TMGE.GamesModule.src.main.java.com.Coords;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.TMGE;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DestroyBehavior.VerticalHorizontalDestroyBehavior;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DisplayBehavior.DisplayFullBoard;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior.FileRandomBoardInit;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.PostDestroyBehavior.GravityShiftAndFillRandom;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.Player.PlayerManager;

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
        PlayerManager pm = bj.getPm();
        bj = new Bejeweled();
        bj.setPm(pm);
        return bj;
    }

    public void swapPieces(int fromRow, int fromCol, int toRow, int toCol){
        // swap tiles on the bejeweled board
        Tile from_tile  = bj.getBoard().getBoard().get(fromRow).get(fromCol);
        Tile to_tile    = bj.getBoard().getBoard().get(toRow).get(toCol);
        bj.getBoard().getBoard().get(fromRow).set(fromCol, to_tile);
        bj.getBoard().getBoard().get(toRow).set(toCol, from_tile);
    }

    public void swapPieces(Coords from, Coords to) {
        // swap tiles on the bejeweled board
        Tile from_tile  = bj.getBoard().getBoard().get(from.row).get(from.col);
        Tile to_tile    = bj.getBoard().getBoard().get(to.row).get(to.col);
        bj.getBoard().getBoard().get(from.row).set(from.col, to_tile);
        bj.getBoard().getBoard().get(to.row).set(to.col, from_tile);
    }
}
