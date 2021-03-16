package com.TMGE.Logic.Board.Init;

import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;

public class BlankBoardInit implements BoardInit {

    @Override
    public ArrayList<ArrayList<Tile>> initialize(int rows, int cols, ArrayList<String> tilePieceSet) {
        ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();

        for (int row = 0; row < rows; row++){
            board.add(new ArrayList<Tile>());
            for (int col = 0; col < cols; col++)
            {
                board.get(row).add(new Tile(" "));
            }

        }

        return board;
    }
}
