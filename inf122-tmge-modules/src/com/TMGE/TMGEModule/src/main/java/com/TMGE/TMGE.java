package com.TMGE.TMGEModule.src.main.java.com.TMGE;

import java.util.ArrayList;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Board;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DestroyBehavior.DestoyBehavior;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DisplayBehavior.DisplayBehavior;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior.BoardInit;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.PostDestroyBehavior.PostDestroy;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.Player.PlayerManager;

public abstract class TMGE {
    private Board board;
    private String gameName;
    private PlayerManager pm;

    public TMGE(String gameName, int numOfRows, int numOfColumns, BoardInit bi, ArrayList<String> tileset, DestoyBehavior db, PostDestroy pd, DisplayBehavior dispBeh) {
        this.gameName = gameName;
        this.pm = new PlayerManager();
        this.board = new Board(numOfColumns, numOfRows, bi, tileset, db, pd, dispBeh);
        this.board.init();
    }

    public Board getBoard() {
        return board;
    }

    public String getGameName() {
        return gameName;
    }

    public PlayerManager getPm() {
        return pm;
    }

    public void setPm(PlayerManager pm) {this.pm = pm;}
}
