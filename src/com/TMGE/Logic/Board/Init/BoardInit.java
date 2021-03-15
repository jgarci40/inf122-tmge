package com.TMGE.Logic.Board.Init;

import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;

public interface BoardInit {
    public ArrayList<ArrayList<Tile>> initialize(int rows, int cols);
}
