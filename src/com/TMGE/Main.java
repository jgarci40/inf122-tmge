package com.TMGE;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static TMGE tmge = new TMGE(); // when we implement Game might need to replace this with Game
    static Display display = new Display();
    // might need a list of games later

    public static void main(String[] args) {
        Scanner intUserInp = new Scanner(System.in);
        Scanner strUserInp = new Scanner(System.in);
        String strInp;
        int intInp;
        // TMGE tmge = new TMGE();
        // games.add(tmge);

        display.displayMainMenu();
        System.out.print("Enter: ");
        intInp = intUserInp.nextInt();

        // loops until user inputs 0
        while (intInp != 0) {
            if (intInp == 1) {
                display.displayGameOption(intInp);
                strInp = strUserInp.nextLine();
                tmge.getPlayerManager().addPlayer(strInp); // when games are implemented you use Game instead of TMGE
                tmge.getPlayerManager().printPlayersInfo(); // remove this its for debugging
                display.displayMainMenu();
                System.out.print("Enter: ");
                intInp = intUserInp.nextInt();
            } else if (intInp == 2) {
                if (tmge.getPlayerManager().getPlayers().isEmpty()) {
                    System.out.println("There are no current players. Please create and account to play.");
                    display.displayMainMenu();
                    System.out.print("Enter: ");
                    intInp = intUserInp.nextInt();
                } else {
                    display.displayGameOption(intInp);
                    System.out.print("Enter: ");
                    intInp = intUserInp.nextInt();
                    ArrayList<ArrayList<Tile>> temp = new ArrayList<ArrayList<Tile>>();
                    for (int i = 0; i < 6; i++) {
                        temp.add(new ArrayList<Tile>());
                        for (int j = 0; j < 6; j++) {
                            Tile t = new Tile(i, j);
                            temp.get(i).add(t);
                        }
                    }
                    tmge.setGameBoard(temp);
                    tmge.printBoard(); // for debug remove later
                    intInp = intUserInp.nextInt(); // so that don't get stuck in loop remove later

                }

            }

        }
        intUserInp.close();
        strUserInp.close();
    }
}
