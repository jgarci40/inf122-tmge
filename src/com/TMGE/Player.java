package com.TMGE;

public class Player {
    private String name;
    private Integer points;

    public Player() {
        this.name = "Default Player Name";
        this.points = 0;
    }

    public Player(String name) {
        this.name = name;
        this.points = 0;
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
}
