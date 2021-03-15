package com.TMGE.Logic;

import com.TMGE.Logic.Board.Board;
import com.TMGE.Logic.Board.Init.BoardInit;
import com.TMGE.Logic.Board.DestroyBehavior.DestoyBehavior;
import com.TMGE.Logic.Board.PostDestroy.PostDestroy;
import com.TMGE.Logic.Player.PlayerManager;

import java.util.ArrayList;

public abstract class TMGE {
    private Board board;
    private String gameName;
    private PlayerManager pm;

    public TMGE(String gameName, int numOfRows, int numOfColumns, BoardInit bi, ArrayList<String> tileset, DestoyBehavior db, PostDestroy pd) {
        this.gameName = gameName;
        this.pm = new PlayerManager();
        this.board = new Board(numOfColumns, numOfRows, bi, tileset, db, pd);
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
}
