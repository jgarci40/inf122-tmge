package com.TMGE.Logic;

public class Display {
    public Display() {
        // no attributes to initiallize yet
    }

    public void displayMainMenu() {
        System.out.println("1: Create account \n2: Play Game \n0: exit");
    }

    public void displayGameOption(int option) {
        if (option == 1) {
            System.out.print("Please enter your name: ");
        } else if (option == 2) {
            System.out.println("Which of the following games would you like to play?\n1: Candy Crush \n2: Tetris");
        }
    }

}