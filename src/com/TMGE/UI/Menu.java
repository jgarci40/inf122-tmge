package com.TMGE.UI;

import com.TMGE.Logic.PlayerManager;

import java.util.Scanner;

public class Menu {
    public static void run(){
        Scanner sc = new Scanner(System.in);
        int input;
        PlayerManager pm = new PlayerManager();

        while(true){
            Menu.printMenuOptions();
            input = sc.nextInt();
            switch(input){
                case 0:
                    return;
                case 1:
                    Player.createAccount(pm);
                    break;
                case 2:
                    if(pm.getPlayers().size() != 0 ) GameSelect.GameSelect(pm);
                    else System.out.println("Please add at least 1 player before selecting a game");
                    break;
                default:
                    System.out.println("Invalid input please try again");
            }
        }
    }

    private static void printMenuOptions(){
        System.out.println("Menu Options:");
        System.out.println("\t1: Create account");
        System.out.println("\t2: Play Game");
        System.out.println("\t0: exit");
    }

}
