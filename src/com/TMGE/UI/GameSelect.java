package com.TMGE.UI;

import com.TMGE.Games.Bejeweled.Bejeweled;
import com.TMGE.Games.Bejeweled.UI.BejeweledMenu;
import com.TMGE.Games.Bejeweled.UI.GamePlay;
import com.TMGE.Games.Columns.Columns;

import java.util.Scanner;

public class GameSelect {

    public void run(){
        Scanner sc = new Scanner(System.in);
        boolean shouldStop = false;
        int gameOption;

        while(!shouldStop){
            GameSelect.printGameOptions();
            System.out.print("Enter game option: ");
            gameOption = sc.nextInt();

            switch(gameOption){
                case 0:
                    return;
                case 1:
                    BejeweledMenu bejeweledMenu = new BejeweledMenu();
                    bejeweledMenu.run();
                    break;
                case 2:
                    Columns columns = new Columns();
                    // TODO: throw in UI here
                    break;
                default:
                    System.out.println("Invalid game options. Please try again");
            }
        }
    }
    public static void printGameOptions(){
        System.out.println("Game Options:");
        System.out.println("\t0: exit");
        System.out.println("\t1: Bejeweled");
        System.out.println("\t2: Columns");
    }
}
