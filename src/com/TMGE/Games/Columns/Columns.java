package com.TMGE.Games.Columns;

import com.TMGE.Logic.Board.DestroyBehavior.VerticalHorizontalDestroyBehavior;
import com.TMGE.Logic.Board.DisplayBehavior.DisplayFullBoard;
import com.TMGE.Logic.Board.Init.BlankBoardInit;
import com.TMGE.Logic.Board.PostDestroy.GravityShift;
import com.TMGE.Logic.TMGE;

import java.util.ArrayList;

public class Columns extends TMGE {
    public Columns() {
        super("Columns", 5, 5, new BlankBoardInit(), new ArrayList<>(), new VerticalHorizontalDestroyBehavior(), new GravityShift(), new DisplayFullBoard());
    }
}
