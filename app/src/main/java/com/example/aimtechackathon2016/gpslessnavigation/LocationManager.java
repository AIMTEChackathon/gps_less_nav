package com.example.aimtechackathon2016.gpslessnavigation;

import java.util.ArrayList;

/**
 * Created by peta on 19.3.16.
 */
public class LocationManager {

    private int x;
    private int y;
    private Directions orientation = Directions.SOUTH;

    private char turn = 'F';

    private ArrayList<PathInstructions> instructions = new ArrayList<>();

    private static LocationManager ourInstance = new LocationManager();

    public static LocationManager getInstance() {
        return ourInstance;
    }

    private LocationManager() {
    }

    public void move() {
        if (instructions.isEmpty()) {
            return;
        }
        if (instructions.get(0).getLength() == 0) {
            turn = instructions.get(0).getTurn();

            return;
        }
        x += orientation.xChange;
        y += orientation.yChange;

        instructions.get(0).setLength(instructions.get(0).getLength() - 1);
    }

    public void turnLeft() {
        orientation = Directions.values()[(orientation.ordinal() - 1 + 4) % 4];
        turn = 'F';
        instructions.remove(0);
        System.out.println(instructions.size());
    }

    public void turnRight() {
        orientation = Directions.values()[(orientation.ordinal() + 1) % 4];
        turn = 'F';
        instructions.remove(0);
    }

    public void setPosition(int x, int y) {
        turn = 'F';
        this.x = x;
        this.y = y;
    }

    public void setPath(String path) {
        int i = 0;
        String temp = "";
        char c = path.charAt(i);
        while (i < path.length()) {
            while (Character.isDigit(c)) {
                temp += c;
                i++;
                if (i >= path.length()) {
                    instructions.add(new PathInstructions('E', Integer.parseInt(temp)));
                    return;
                }
                c = path.charAt(i);
            }
            instructions.add(new PathInstructions(c, "".equals(temp) ? 0 : Integer.parseInt(temp)));
            temp = "";
            i++;
            c = path.charAt(i);
        }
        instructions.add(new PathInstructions('E', "".equals(temp) ? 0 : Integer.parseInt(temp)));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getTurn() {
        return turn;
    }
}
