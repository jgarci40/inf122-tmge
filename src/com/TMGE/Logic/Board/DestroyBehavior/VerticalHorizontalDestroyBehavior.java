package com.TMGE.Logic.Board.DestroyBehavior;

import com.TMGE.Logic.Board.Board;
import com.TMGE.Logic.Tile.Tile;

public class VerticalHorizontalDestroyBehavior implements DestoyBehavior {
    @Override
    public int destroy(Board board) {
        // Checks for 3 or more horizontal matching tiles
        // iterate horizontally over gameboard to check for matches
        // https://gamedev.stackexchange.com/a/14933
        int totalPoints = 0;    // points for making matches
        for (int row = 0; row < board.getNumOfRows(); row++) {
            // at the beginning of each row
            int num_matches     = 0;
            String piece_type   = null;
            int end_col         = 0;

            for (int col = 0; col < board.getNumOfColumns(); col++) {
                // current tile can be null
                Tile current_tile   = board.getBoard().get(row).get(col);
                String current_type = current_tile.getTilePiece();

                if (current_type.equals(piece_type)) {num_matches++;}
                if (!current_type.equals(piece_type) || col == board.getNumOfColumns()-1) {
                    if (num_matches >= 3) {
                        if (!current_type.equals(piece_type)) {
                            end_col = col - 1;
                        } else {
                            end_col = col;
                        }

                        // iterate over row from beginning col to end col, removing the matches
                        for (int c = col-num_matches; c <= end_col; c++) {
                            board.getBoard().get(row).set(c, null);     // set each matching tile to null, effectively destroying them
                            totalPoints++;
                        }
                    }
                    piece_type  = current_type;      // set piece type to current tile type to begin checking for new matches
                    num_matches = 1;
                }
            }
        }

        // Checks for 3 or more vertical matching tiles
        // iterate vertically over gameboard to check for matches
        for (int col = 0; col < board.getNumOfColumns(); col++) {
            // at the beginning of each column
            int num_matches     = 0;
            String piece_type   = null;
            int end_row         = 0; 

            for (int row = 0; row < board.getNumOfRows(); row++) {
                // current tile can be null
                Tile current_tile   = board.getBoard().get(row).get(col);
                String current_type = current_tile.getTilePiece();

                if (current_type.equals(piece_type)) {num_matches++;}
                if (!current_type.equals(piece_type) || row == board.getNumOfRows()-1) {
                    if (num_matches >= 3) {
                        if (!current_type.equals(piece_type)) {
                            end_row = row - 1;
                        } else {
                            end_row = row;
                        }

                        // iterate over column from beginning row to end row, removing the matches
                        for (int r = row-num_matches; r <= end_row; r++) {
                            board.getBoard().get(r).set(col, null);     // set each matching tile to null, effectively destroying them
                            totalPoints++;
                        }
                    }
                    piece_type  = current_type;      // set piece type to current tile type to begin checking for new matches
                    num_matches = 1;
                }
            }
        }
        return totalPoints;
    }
}
