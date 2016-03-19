package com.example.aimtechackathon2016.gpslessnavigation;

/**
 * Created by peta on 19.3.16.
 */
public enum Directions {

    NORTH(0,-1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    public final int xChange;

    public final int yChange;

    Directions(int xChange, int yChange) {
        this.xChange = xChange;
        this.yChange = yChange;
    }
}
