package com.TMGE.Games.Bejeweled.UI;

import com.TMGE.Games.Bejeweled.Game;

import java.util.Scanner;

public class MenuUI {
    public static void run(){
        Scanner sc = new Scanner(System.in);
        boolean shouldExitGame = false;
        int menuOption = -1;

        while(!shouldExitGame){
            MenuUI.displayOptions();
            menuOption = sc.nextInt();
            switch(menuOption){
                case 0:
                    shouldExitGame = true;
                    break;
                case 1:
                    System.out.println("Constructor");
                    GameUI gameUI = new GameUI(new Game());
                    System.out.println("Setup");
                    gameUI.setup();
                    System.out.println("Start");
                    gameUI.start();
                    break;
                default:
                    System.out.println("Invalid option please try again");
                    break;
            }
        }
    }

    public static void displayOptions(){
        System.out.println("0: exit");
        System.out.println("1: start");
    }
}
