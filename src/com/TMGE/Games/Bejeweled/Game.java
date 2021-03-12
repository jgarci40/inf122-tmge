package com.TMGE.Games.Bejeweled;

import com.TMGE.Logic.*;

import java.text.Normalizer.Form;
import java.util.ArrayList;
public class Game extends TMGE {
    private int maxPoints;
    private ArrayList<String> tilePieceTypes;
    private boolean isInitialized               = false;
    private final int COLUMNS                   = 10;
    private final int ROWS                      = 10;

    public Game() {
        super();
        this.setGameBoard(TMGE.createBoard(this.ROWS, this.COLUMNS));
        this.setGameType("Bejeweled");
        this.getPlayerManager().addPlayer("Default Player");
        this.maxPoints = 100;
        this.tilePieceTypes = new ArrayList<>();
        this.tilePieceTypes.add("*");
        this.tilePieceTypes.add("+");
        this.tilePieceTypes.add("#");
        this.tilePieceTypes.add("@");
        this.tilePieceTypes.add("$");
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void initializeBoard() throws Error{
        if(!isInitialized){
            for(int row = 0; row < this.getGameBoard().size(); ++row){
                for(int col = 0; col < this.getGameBoard().get(row).size(); ++col){
                    int pieceIndex = (int)(Math.random() * this.tilePieceTypes.size());
                    // piece type needs to be different from left and upper
                    String tileType = this.tilePieceTypes.get(pieceIndex);
                    if (row != 0 && col != 0) { // if tile has a left and above tile
                        Tile above  = this.getGameBoard().get(row-1).get(col);
                        Tile left   = this.getGameBoard().get(row).get(col-1);
                        // while current tile type equals left or above, get a new type
                        while (above.getOccupied().getPieceType().equals(tileType) || left.getOccupied().getPieceType().equals(tileType)) {
                            pieceIndex = (int)(Math.random() * this.tilePieceTypes.size());
                            tileType = this.tilePieceTypes.get(pieceIndex);
                        }
                    } else if (row == 0 && col != 0) {  // if first row but not first element
                        Tile left   = this.getGameBoard().get(row).get(col-1);
                        // while current tile type equals left, get a new type
                        while (left.getOccupied().getPieceType().equals(tileType)) {
                            pieceIndex = (int)(Math.random() * this.tilePieceTypes.size());
                            tileType = this.tilePieceTypes.get(pieceIndex);
                        }
                    } else if (col == 0 && row != 0) {  // if first col but not first element
                        Tile above  = this.getGameBoard().get(row-1).get(col);
                        // while current tile type equals left, get a new type
                        while (above.getOccupied().getPieceType().equals(tileType)) {
                            pieceIndex = (int)(Math.random() * this.tilePieceTypes.size());
                            tileType = this.tilePieceTypes.get(pieceIndex);
                        }
                    }
                    // set the tile type
                    this.getGameBoard().get(row).get(col).setOccupied(new TilePiece(tileType));
                }
            }
            this.isInitialized = true;
        } else{
            throw new Error("Already initialized");
        }
    }

    public boolean isAnotherMove(){
        // TODO: returns true if there is a move that could be made the would make a patter
        return true;
    }

    public int findHorizontalMatch(){
        // Checks for 3 or more horizontal matching tiles
        // iterate horizontally over gameboard to check for matches
        // https://gamedev.stackexchange.com/a/14933
        ArrayList<ArrayList<Tile>> gameBoard = this.getGameBoard();
        int total = 0;
        for (int row = 0; row < this.ROWS; row++) {
            // at the beginning of each row
            int num_matches     = 0;
            String piece_type   = null;
            Tile end            = null;

            for (int col = 0; col < this.COLUMNS; col++) {
                // current tile can be null
                Tile current_tile   = gameBoard.get(row).get(col);
                String current_type = current_tile.getOccupied().getPieceType();

                if (current_type.equals(piece_type)) {num_matches++;}
                if (!current_type.equals(piece_type) || col == this.COLUMNS-1) {
                    if (num_matches >= 3) {
                        if (!current_type.equals(piece_type)) {
                            end = gameBoard.get(row).get(col-1);
                        } else {
                            end = current_tile;
                        }
                        total += destroyHorizontalMatches(gameBoard.get(row).get(col-num_matches), end);
                        printBoard();
                        shiftPiecesDown();
                    }
                    piece_type  = current_type;
                    num_matches = 1;
                }
            }
        }
        return total;
    }

    public int findVerticalMatch(){
        // Checks for 3 or more vertical matching tiles
        // iterate vertically over gameboard to check for matches
        // https://gamedev.stackexchange.com/a/14933
        ArrayList<ArrayList<Tile>> gameBoard = this.getGameBoard();
        int total = 0;
        for (int col = 0; col < this.COLUMNS; col++) {
            // at the beginning of each column
            int num_matches     = 0;
            String piece_type   = null;
            Tile end            = null;

            for (int row = 0; row < this.ROWS; row++) {
                Tile current_tile   = gameBoard.get(row).get(col);
                String current_type = current_tile.getOccupied().getPieceType();

                if (current_type.equals(piece_type)) {num_matches++;}
                if (!current_type.equals(piece_type) || row == this.ROWS-1) {
                    if (num_matches >= 3) {
                        if (!current_type.equals(piece_type)) {
                            end = gameBoard.get(row-1).get(col);
                        } else {
                            end = current_tile;
                        }
                        total += destroyVerticalMatches(gameBoard.get(row-num_matches).get(col), end);
                        printBoard();
                        shiftPiecesDown();
                    }
                    piece_type  = current_type;
                    num_matches = 1;
                }
            }
        }
        return total; 
    }

    public boolean hasPlayerWon (){
        // TODO: check players points to see if they have reached or exceded the goal
        boolean hasPlayerWon = false;
        for (Player p : this.getPlayerManager().getPlayers()) {
            if(p.getPoints() >= this.maxPoints){
                hasPlayerWon = true;
            }
        }
        return hasPlayerWon;
    }

    public void printBoard(){
        System.out.println("Rows");
        for(int row = 0; row < this.getGameBoard().size(); ++row){
            System.out.print(row + "\t|");
            for(int col = 0; col < this.getGameBoard().get(row).size(); ++col) {
                String piece_type;
                // check if tile is null; if so, print empty space
                if (this.getGameBoard().get(row).get(col).getOccupied() == null) {piece_type = " ";}
                else {piece_type = this.getGameBoard().get(row).get(col).getOccupied().getPieceType();}
                System.out.print( " " + piece_type + " ");
                System.out.print("|");
            }
            System.out.println();
            System.out.print("\t  ");
            System.out.println();
        }

        // Print Column numbers
        System.out.print(" \t |");
        for(int col = 0; col < this.getGameBoard().get(0).size(); ++col){
            System.out.print(" " + col + " |");
        }
        System.out.print("Columns");
        System.out.println();
    }

    public boolean isValidColumn(int col){
        return col >= 0 && col < this.COLUMNS;
    }

    public boolean isValidRow(int row){
        return row >= 0 && row < this.ROWS;
    }

    public boolean isValidMove(int fromCol, int fromRow, int toCol, int toRow){
        boolean isFromColInBounds = this.isValidColumn(fromCol);
        boolean isToColInBounds = this.isValidColumn(toCol);
        boolean isFromRowInBounds = this.isValidRow(fromRow);
        boolean isToRowInBounds = this.isValidRow(toRow);

        // TODO: add boolean for if there is a valid destroy from the swap

        return isFromColInBounds && isToColInBounds && isFromRowInBounds && isToRowInBounds;
    }

    public void swapPieces(int fromCol, int fromRow, int toCol, int toRow){
        // swap by changing Tile coordinates

        Tile fromTile   = this.getGameBoard().get(fromRow).get(fromCol);
        Tile toTile     = this.getGameBoard().get(toRow).get(toCol);

        TilePiece temp = fromTile.getOccupied();
        fromTile.setOccupied(toTile.getOccupied());
        toTile.setOccupied(temp);

    }

    public int destroyHorizontalMatches(Tile beginning, Tile end) {
        int row     = beginning.getRow();
        int points  = 0;
        for (int col = beginning.getCol(); col <= end.getCol(); col++) {
            getGameBoard().get(row).get(col).setOccupied(null);
            points++;
        }
        return points; 
    }

    public int destroyVerticalMatches(Tile beginning, Tile end) {
        int col     = beginning.getRow();
        int points  = 0;
        for (int row = beginning.getRow(); row <= end.getRow(); row++) {
            getGameBoard().get(row).get(col).setOccupied(null);
            points++;
        }
        return points; 
    }

    public int destroyMatches(){
        // TODO: Destroy all matches that are 3 or more
        int total = 0;
        total += this.findHorizontalMatch();
//        total += this.findVerticalMatch();
        
        // Returns the total number of pieces destroyed
        return total;
    }

    public boolean isTherePiecesToDestroy(){
        // TODO: true if there are pieces to destroy
        return false;
    }

    public void shiftPiecesDown(){
        for(int col = 0; col < COLUMNS; ++col){
            // Create Arraylist with current column
            ArrayList<Tile> tiles = new ArrayList<>();
            for(int row = 0; row < ROWS; ++row) {
                Tile t = this.getGameBoard().get(row).get(col);
                tiles.add(new Tile(t));
            }

            // Remove all null items in the array
            for(int i = tiles.size() - 1; i >= 0; --i){
                if(tiles.get(i).getOccupied() == null){
                    tiles.remove(i);
                }
            }

            ArrayList<ArrayList<Tile>> tmpGameBoard = this.getGameBoard();
            int tileIndex = tiles.size() -1;
            // Map back
            for(int row = ROWS - 1; row >= 0; --row){
                if(tileIndex >= 0){
                    tmpGameBoard.get(row).get(col).setOccupied(new TilePiece(tiles.get(tileIndex--).getOccupied().getPieceType()));
                } else{
                    tmpGameBoard.get(row).get(col).setOccupied(null);
                }
            }
        }
    }

    public void fillEmptyPieces(){
        ArrayList<ArrayList<Tile>> board = this.getGameBoard();
        for(int row = 0; row < ROWS; ++row){
            for(int col = 0; col < COLUMNS; ++col){
                Tile t = board.get(row).get(col);
                if(t.getOccupied() == null){
                    int pieceTypeIndex = (int)(Math.random() * this.tilePieceTypes.size());
                    t.setOccupied(new TilePiece(this.tilePieceTypes.get(pieceTypeIndex)));
                }
            }
        }
    }

    public int destroyShiftFillUntilNoDestroysAvailable(){
        int total = 0;
        boolean shouldLoop = true;
        while(shouldLoop){
            int loopTotal = 0;
            loopTotal += this.findHorizontalMatch();
            this.shiftPiecesDown();
            this.fillEmptyPieces();
            loopTotal += this.findVerticalMatch();
            this.shiftPiecesDown();
            this.fillEmptyPieces();
            if(loopTotal == 0){
                shouldLoop = false;
            } else{
                total += loopTotal;
            }
        }
        return total;
    }
}
