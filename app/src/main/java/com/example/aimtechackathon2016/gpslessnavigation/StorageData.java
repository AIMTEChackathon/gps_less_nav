package com.example.aimtechackathon2016.gpslessnavigation;

/**
 * Created by peta on 19.3.16.
 */
public class StorageData {

    public static final int ROWS = 10;
    public static final int COLUMNS = 5;

    private int[] map;

    private int startX;
    private int startY;

    private int destX;
    private int destY;

    private String path;

    public StorageData() {
    }

    public int[] getMap() {
        return map;
    }

    public void setMap(int[] map) {
        this.map = map;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
