package com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior;

import java.util.ArrayList;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;

public class FileRandomBoardInit implements BoardInit {
    @Override
    public ArrayList<ArrayList<Tile>> initialize(int rows, int cols, ArrayList<String> tilePieceSet) {
        // create our board
        ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();

        // iterate over board adding tiles at each coordinate
        for (int row = 0; row < rows; row++) {
            board.add(new ArrayList<Tile>());   // add rows
            for (int col = 0; col < cols; col++) {
                // get a random tile type
                // piece type needs to be different from left and upper
                int pieceIndex          = (int)(Math.random() * tilePieceSet.size());
                String currentTileType  = tilePieceSet.get(pieceIndex);
                if (row != 0 && col != 0) {     // if tile has a tile left and above
                    Tile above  = board.get(row-1).get(col);
                    Tile left   = board.get(row).get(col-1);
                    // while current tile is same as tile above or left, get a new type
                    while (above.getTilePiece().equals(currentTileType) || left.getTilePiece().equals(currentTileType)) {
                        pieceIndex      = (int)(Math.random() * tilePieceSet.size());    // random index of piece types
                        currentTileType = tilePieceSet.get(pieceIndex);
                    }
                } else if (row == 0 && col != 0) {      // if first row but not first element
                    Tile left   = board.get(row).get(col-1);
                    // while current tile type equals left, get a new type
                    while (left.getTilePiece().equals(currentTileType)) {
                        pieceIndex      = (int)(Math.random() * tilePieceSet.size());    // random index of piece types
                        currentTileType = tilePieceSet.get(pieceIndex);
                    }
                } else if (col == 0 && row != 0) {      // if first col but not first element
                    Tile above  = board.get(row-1).get(col);
                    while (above.getTilePiece().equals(currentTileType)) {
                        pieceIndex      = (int)(Math.random() * tilePieceSet.size());    // random index of piece types
                        currentTileType = tilePieceSet.get(pieceIndex);
                    }
                }
                // set tile in board
                board.get(row).add(new Tile(currentTileType));     // add random tile type to board
            }
        }
        return board;
    }
}
