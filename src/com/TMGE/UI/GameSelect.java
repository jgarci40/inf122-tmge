package com.TMGE.UI;

import com.TMGE.Games.Bejeweled.Bejeweled;
import com.TMGE.Logic.PlayerManager;

import java.util.Scanner;

public class GameSelect {

    public static void GameSelect(PlayerManager pm){
        Scanner sc = new Scanner(System.in);
        boolean shouldStop = false;
        int gameOption;

        while(!shouldStop){
            GameSelect.printGameOptions();
            System.out.print("Enter game option: ");
            gameOption = sc.nextInt();

            switch(gameOption){
                case 0:
                    shouldStop = true;
                    return;
                case 1:
                    Bejeweled.start();
                    break;
                case 2:
                    // Start Game here
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
        System.out.println("\t2: y");
    }
}
