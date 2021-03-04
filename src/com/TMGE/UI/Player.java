package com.TMGE.UI;

import com.TMGE.PlayerManager;

import java.util.Scanner;

public class Player {
    public static void createAccount(PlayerManager pm){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = sc.nextLine();
        pm.addPlayer(name);
        pm.printPlayersInfo();
    }
}
