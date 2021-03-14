package com.TMGE.UI;

import java.util.Scanner;

public class Menu {
    public void run(){
        Scanner sc = new Scanner(System.in);
        int input;

        System.out.println("Welcome to Tile Matching Game Environment!");
        while(true){
            this.printMenuOptions();
            input = sc.nextInt();
            switch(input){
                case 0:
                    this.printAbout();
                    return;
                case 1:
                    GameSelect gs = new GameSelect();
                    gs.run();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input please try again");
            }
        }
    }

    private void printMenuOptions(){
        System.out.println("Menu Options:");
        System.out.println("\t1: Learn More");
        System.out.println("\t2: Play a Game");
        System.out.println("\t0: exit");
    }

    private void printAbout(){
        // TOOD: Make something about the authors or something
    }

}
