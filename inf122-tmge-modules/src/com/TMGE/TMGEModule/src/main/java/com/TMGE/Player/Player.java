package com.TMGE.Logic.Player;


public class Player {
    private String name;
    private Integer points;
    private int highScore;

    public Player() {
        this.name = "Default Player Name";
        this.points = 0;
        this.highScore = 0;
    }

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.highScore = 0;
    }

    public Player(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public void setHighScore(int score) {this.highScore = score;} 
}
