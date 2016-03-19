package com.example.aimtechackathon2016.gpslessnavigation;

/**
 * Created by peta on 19.3.16.
 */
public class PathInstructions {

    private char turn;
    private int length;

    public PathInstructions() {
    }

    public PathInstructions(char turn, int length) {
        this.turn = turn;
        this.length = length;
    }

    public char getTurn() {
        return turn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
