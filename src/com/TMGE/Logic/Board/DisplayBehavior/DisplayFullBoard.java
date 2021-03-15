package com.TMGE.Logic.Board.DisplayBehavior;

import com.TMGE.Logic.Board.Board;

public class DisplayFullBoard implements DisplayBehavior{
   @Override
   public void displayBoard(Board board) {
      System.out.println("Rows");
        for(int row = 0; row < board.getNumOfRows(); ++row){
            System.out.print(row + "\t|");
            for(int col = 0; col < board.getNumOfColumns(); ++col) {
                String piece_type;
                // check if tile is null; if so, print empty space
                if (board.getBoard().get(row).get(col) == null) {piece_type = " ";}
                else {piece_type = board.getBoard().get(row).get(col).getTilePiece();}
                System.out.print( " " + piece_type + " ");
                System.out.print("|");
            }
            System.out.println();
            System.out.print("\t  ");
            System.out.println();
        }

        // Print Column numbers
        System.out.print(" \t |");
        for(int col = 0; col < board.getNumOfColumns(); ++col){
            System.out.print(" " + col + " |");
        }
        System.out.print("Columns");
        System.out.println();
   }
}
