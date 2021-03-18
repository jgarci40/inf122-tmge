package com.TMGE.UIModule.src.main.java.com.UI;

import java.util.Scanner;

import com.TMGE.GamesModule.src.main.java.com.Bejeweled.UI.BejeweledMenu;
import com.TMGE.GamesModule.src.main.java.com.Columns.UI.ColumnsMenu;

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
                    ColumnsMenu columnsMenu = new ColumnsMenu();
                    columnsMenu.run();
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
