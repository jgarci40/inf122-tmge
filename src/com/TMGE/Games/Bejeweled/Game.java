package com.TMGE.Games.Bejeweled;

import com.TMGE.Logic.PlayerManager;
import com.TMGE.Logic.TMGE;
import com.TMGE.Logic.Tile;
import com.TMGE.Logic.TilePiece;

import java.util.ArrayList;

public class Game extends TMGE {
    private int maxPoints;
    private ArrayList<String> tilePieceTypes;
    private boolean isInitialized = false;
    private final int COLUMNS = 10;
    private final int ROWS = 10;

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
                    String tileType = this.tilePieceTypes.get(pieceIndex);
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

    public boolean hasPlayerWon (){
        // TODO: check players points to see if they have reached or exceded the goal
        boolean hasPlayerWon = false;
        return hasPlayerWon;
    }

    public void printBoard(){
        System.out.println("Rows");
        for(int row = 0; row < this.getGameBoard().size(); ++row){
            System.out.print(row + "\t|");
            for(int col = 0; col < this.getGameBoard().get(row).size(); ++col){
                System.out.print( " " + this.getGameBoard().get(row).get(col).getOccupied().getPieceType() + " ");
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
        // TODO: Swap pieces if it will cause a destroy
    }

    public int destroyMatches(){
        // TODO: Destroy all matches that are 3 or more

        // Returns the total number of pieces destroyed
        return 0;
    }

    public boolean isTherePiecesToDestroy(){
        // TODO: true if there are pieces to destroy
        return false;
    }

    public void shiftPiecesDown(){
        // TODO: Shift all pieces down
    }

    public void fillEmptyPieces(){
        // TODO: Fill in empty pieces with
    }

    public int destroyShiftFillUntilNoDestroysAvailable(){
        int total = 0;
        while(this.isTherePiecesToDestroy()){
            total += this.destroyMatches();
            this.shiftPiecesDown();
            this.fillEmptyPieces();
        }
        return total;
    }
}
