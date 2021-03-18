package com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior;

import java.util.ArrayList;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;

public interface BoardInit {
    public ArrayList<ArrayList<Tile>> initialize(int rows, int cols, ArrayList<String> tilePieceSet);
}
