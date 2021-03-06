package com.TMGE.Logic;

public class TilePiece {

    // private String color; dont think we need a color
    private String pieceType;

    public TilePiece(String pieceType) {
        // this.color = color;
        this.pieceType = pieceType;
    }

    // public String getColor() {
    // return color;
    // }

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pt) {
        pieceType = pt;
    }
}
