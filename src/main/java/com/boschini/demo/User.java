package com.boschini.demo;

public class User {

    int mode;
    String username;

    int coin;

    Thing oggetto;
    int score;

    public User() {
        this.mode = 0;
        this.username = "-";
        this.coin =0;
        this.score=0;
        this.oggetto= new Thing(1,"coin");
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public Thing getOggetto() {
        return oggetto;
    }

    public void setOggetto(Thing oggetto) {
        this.oggetto = oggetto;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" + "mode=" + mode + ", username='" + username + '\'' + ", coin=" + coin + ", oggetto=" + oggetto + ", score=" + score + '}';
    }
}
