package com.TMGE;

public class TilePiece {

    private String color;
    private String pieceType;

    public TilePiece(String color, String pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public String getColor() {
        return color;
    }

    public String getPieceType() {
        return pieceType;
    }
}
