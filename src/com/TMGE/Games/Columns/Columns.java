package com.TMGE.Games.Columns;

import com.TMGE.Logic.Board.DestroyBehavior.VerticalHorizontalDestroyBehavior;
import com.TMGE.Logic.Board.DisplayBehavior.DisplayFullBoard;
import com.TMGE.Logic.Board.Init.BlankBoardInit;
import com.TMGE.Logic.Board.PostDestroy.GravityShift;
import com.TMGE.Logic.TMGE;
import com.TMGE.Logic.Tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public class Columns extends TMGE {

    static Columns c;

    public Columns() {
        super("Columns", 5, 5, new BlankBoardInit(), new ArrayList<String>(Arrays.asList("S", "T", "V", "W", "X", "Y", "Z")), new VerticalHorizontalDestroyBehavior(), new GravityShift(), new DisplayFullBoard());
    }

    public static Columns getInstance(){
        if(c == null) c = new Columns();
        return c;
    }

    public static Columns refreshGame(){
        c =  new Columns();
        return c;
    }

    public boolean spawnFaller(String[] commandArray){
        int column = Integer.parseInt(commandArray[1]);
        Tile t1 = new Tile(commandArray[2]);
        Tile t2 = new Tile(commandArray[3]);
        Tile t3 = new Tile(commandArray[4]);
        boolean found = false;
        for (int i = getBoard().getNumOfRows()-1; i >= 0; i--){
            if (c.getBoard().getBoard().get(i).get(column).getTilePiece().equals(" ")){
                try {
                    c.getBoard().getBoard().get(i).set(column, t1);
                    c.getBoard().getBoard().get(i - 1).set(column, t2);
                    c.getBoard().getBoard().get(i - 2).set(column, t3);
                    found = true;
                    break;
                }
                catch (Exception e)
                {
                    found = false;
                    break;
                }
            }

        }

        return found;
    }



    public void postSpawn(){
        this.getBoard().destroy();
        this.getBoard().postDestroy();
    }
    
}
