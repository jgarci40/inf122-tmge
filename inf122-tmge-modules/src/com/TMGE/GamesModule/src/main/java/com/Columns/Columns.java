package com.TMGE.GamesModule.src.main.java.com.Columns;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import com.TMGE.TMGEModule.src.main.java.com.TMGE.TMGE;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DestroyBehavior.VerticalHorizontalEmptyDestroy;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.DisplayBehavior.DisplayFullBoard;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.InitBehavior.BlankBoardInit;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.PostDestroyBehavior.GravityShift;
import com.TMGE.TMGEModule.src.main.java.com.TMGE.BoardModule.src.main.java.com.Board.Tile.Tile;

public class Columns extends TMGE {

    static Columns c;

    public Columns() {
        super("Columns", 7, 7, new BlankBoardInit(), new ArrayList<String>(Arrays.asList("S", "T", "V", "W", "X", "Y", "Z")), new VerticalHorizontalEmptyDestroy(), new GravityShift(), new DisplayFullBoard());
    }

    public static Columns getInstance(){
        if(c == null) c = new Columns();
        return c;
    }

    public static Columns refreshGame(){
        c =  new Columns();
        return c;
    }

    public String[] rotateColors(String one, String two, String three)
    {
        String[] colors = {one, two, three};
        int i;
        String temp;
        temp = colors[0];
        for (i = 0; i < 3 - 1; i++)
            colors[i] = colors[i + 1];
        colors[i] = temp;

        return colors;

    }

    public boolean generateColors()
    {
        String[] colors = {"S", "T", "V", "W", "X", "Y", "Z"};
        Random rand = new Random();
        int upperBound = 7;
        String one = colors[rand.nextInt(upperBound)];
        String two = colors[rand.nextInt(upperBound)];
        String three = colors[rand.nextInt(upperBound)];

        System.out.println("Your generated faller from top to bottom is: " + three + " " + two + " " + one);
        Scanner obj = new Scanner(System.in);
        String cmd = "";
        System.out.println("Would you like to R: rotate faller or P: place on board?");
        cmd = obj.nextLine();
        while (cmd.toLowerCase().startsWith("r")){
            String[] newFaller = rotateColors(one, two, three);
            three = newFaller[2];
            two = newFaller[1];
            one = newFaller[0];
            System.out.println("Your rotated faller from top to bottom is: " + newFaller[2] + " " + newFaller[1] + " " + newFaller[0]);
            System.out.println("Would you like to R: rotate faller or P: place on board?");
            cmd = obj.nextLine();
            if (cmd.toLowerCase().startsWith("p")){
                break;
            }
        }
        System.out.println("What column # would you like to place faller in?");
        cmd = obj.nextLine();
        String[] faller = {"F", cmd, one, two, three};
        return spawnFaller(faller);
    }

    public boolean spawnFaller(String[] commandArray){
        int column = Integer.parseInt(commandArray[1]);
        Tile t1 = new Tile(commandArray[2]);
        Tile t2 = new Tile(commandArray[3]);
        Tile t3 = new Tile(commandArray[4]);
        boolean found = false;
        for (int i = getBoard().getNumOfRows()-1; i >= 0; i--){
            if (c.getBoard().getBoard().get(i).get(column) == null){
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

    
}
