package com.TMGE.Games.Columns.UI;

import com.TMGE.Games.Columns.Columns;

import java.util.Scanner;

public class ColumnsMenu {
    Columns columns;

    public ColumnsMenu() {
        this.columns = Columns.refreshGame();
    }

    public ColumnsMenu(Columns columns) {
        this.columns =  columns;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to " + columns.getGameName());
        int command;
        while(true){
            this.displayOptions();
            System.out.print("[command]: ");
            command = sc.nextInt();
            switch(command){
                case 0:
                    System.out.println("Game exited  " + this.columns.getGameName());
                    return;
                case 1:
                    System.out.println("Game rules are ");
                    break;
                case 2:
                    System.out.println("Start Game is selected");
                    this.startGame();
                    break;
                default:
                    System.out.println("Invalid Command Please try again");
                    break;
            }
        }
    }

    private void displayOptions(){
        System.out.println("Menu Options: ");
        System.out.println("\t0: Exit");
        System.out.println("\t1: Game rules");
        System.out.println("\t2: Start Game");
    }

    private void startGame() {
        this.columns.getBoard().init();
        Scanner obj = new Scanner(System.in);
        String cmd = " ";
        boolean shouldLoop = true;
        while(shouldLoop) {
            this.columns.getBoard().display();
            cmd = obj.nextLine();
            if (cmd.equals(">")) {
                System. out.println("right >>>>>");
            }
            else if (cmd.equals("<")) {
                System. out.println("Left <<<<<<");
            }
            else if (cmd.equals("q")) {
                shouldLoop = false;
            }
            else {
                System. out.println(cmd);
            }
        }
    }
}

