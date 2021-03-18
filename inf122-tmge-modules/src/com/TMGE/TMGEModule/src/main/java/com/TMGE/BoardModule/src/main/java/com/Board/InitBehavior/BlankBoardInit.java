package com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior;

import java.util.ArrayList;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;

public class BlankBoardInit implements BoardInit {

    @Override
    public ArrayList<ArrayList<Tile>> initialize(int rows, int cols, ArrayList<String> tilePieceSet) {
        ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();

        for (int row = 0; row < rows; row++){
            board.add(new ArrayList<Tile>());
            for (int col = 0; col < cols; col++)
            {
                board.get(row).add(null);
            }

        }

        return board;
    }
}
