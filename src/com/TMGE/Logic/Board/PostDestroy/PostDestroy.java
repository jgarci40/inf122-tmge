package com.TMGE.Logic.Board.PostDestroy;

import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;

public interface PostDestroy {
    public void postDestroy(ArrayList<ArrayList<Tile>> board);
}
