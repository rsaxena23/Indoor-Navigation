package com.example.w.a.y;
public class Team {
    public String position;
    public String name ,wins ,draws;
    public Team(String position, String name)
    {
        this.setPosition(position);
        this.setName(name);
    }
    public Team(String position, String name,String wins,String draws)
    {
        this.setPosition(position);
        this.setName(name);
        this.setWins(wins);
        this.setDraws(draws);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }
    
    public String getDraws() {
        return wins;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

}
