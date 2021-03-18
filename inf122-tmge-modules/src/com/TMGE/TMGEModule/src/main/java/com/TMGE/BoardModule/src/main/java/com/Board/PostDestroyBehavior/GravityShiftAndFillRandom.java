package com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.PostDestroyBehavior;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Board;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;

public class GravityShiftAndFillRandom extends GravityShift{

    @Override
    public void postDestroy(Board board) {
        super.postDestroy(board);

        for(int row = 0; row < board.getBoard().size(); ++row){
            for(int col = 0; col < board.getBoard().get(row).size(); ++col){
                if(board.getBoard().get(row).get(col) == null){
                    int randomTilePieceIndex = (int)(Math.random() * board.getTilePieceSet().size());
                    Tile tile = new Tile(board.getTilePieceSet().get(randomTilePieceIndex));
                    board.getBoard().get(row).set(col, tile);
                }
            }
        }
    }
}
