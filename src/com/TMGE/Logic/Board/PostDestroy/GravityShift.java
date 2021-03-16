package com.TMGE.Logic.Board.PostDestroy;

import com.TMGE.Logic.Board.Board;
import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;

public class GravityShift implements PostDestroy{
    @Override
    public void postDestroy(Board b) {
        ArrayList<ArrayList<Tile>> board = b.getBoard();
        int ROWS = board.size();
        int COLUMNS = board.get(0).size();

        for(int col = 0; col < COLUMNS; ++col){
            // Create Arraylist with current column
            ArrayList<Tile> tiles = new ArrayList<>();
            for(int row = 0; row < ROWS; ++row) {
                Tile t = board.get(row).get(col);
                // handle null Tile
                if (t == null) {
                    tiles.add(null);
                } else {
                    tiles.add(new Tile(t.getTilePiece()));
                }
            }

            // Remove all null items in the array
            for(int i = tiles.size() - 1; i >= 0; --i){
                if(tiles.get(i) == null){
                    tiles.remove(i);
                }
            }

            int tileIndex = tiles.size() -1;
            // Map back
            for(int row = ROWS - 1; row >= 0; --row){
                if(tileIndex >= 0){
                    board.get(row).set(col, new Tile(tiles.get(tileIndex--).getTilePiece()));
                } else{
                    board.get(row).set(col, null);
                }
            }
        }
    }
}
