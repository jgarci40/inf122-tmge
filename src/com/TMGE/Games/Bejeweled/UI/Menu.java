package com.TMGE.Games.Bejeweled.UI;

import com.TMGE.Games.Bejeweled.Bejeweled;

public class Menu {
    Bejeweled bejeweled;

    public Menu(Bejeweled bejeweled) {
        this.bejeweled = bejeweled;
    }

    public void run(){
        System.out.println("Welcome to " + bejeweled.getGameName());
        // TODO: Add Players
        // TODO: Give Control to GamePlay
    }

    private void displayOptions(){
        System.out.println("Menu Options: ");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
