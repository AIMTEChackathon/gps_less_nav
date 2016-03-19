package com.example.aimtechackathon2016.gpslessnavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by peta on 19.3.16.
 */
public class MapView extends View {

    static final float PADDING = 20;
    static final float RADIUS = 10;

    Paint position = new Paint();
    Paint destination = new Paint();

    StorageData data;

    public MapView(Context context) {
        super(context);
        position.setColor(Color.BLUE);
        destination.setColor(Color.GREEN);

    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        position.setColor(Color.BLUE);
        destination.setColor(Color.GREEN);

    }

    private LocationManager locationManager = LocationManager.getInstance();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (data == null) {
            data = DataLoader.getInstance().loadData("storage.txt");
            locationManager.setPosition(data.getStartX(), data.getStartY());
            locationManager.setPath(data.getPath());
        }
        data = DataLoader.getInstance().getStorageData();
        float stepSize = (canvas.getWidth() - 2 * PADDING)*1.0f / StorageData.COLUMNS/3*2;
        int[] map = data.getMap();
        for (int i = 0; i < StorageData.ROWS; i++) {
            for (int j = 0; j < StorageData.COLUMNS; j++) {
                int index = 4 * (i * StorageData.COLUMNS + j);
                int left = map[index];
                int up = map[index + 1];
                int right = map[index + 2];
                int down = map[index + 3];
                if (up == 1) {
                    canvas.drawLine(PADDING + j * stepSize, PADDING + i * stepSize, PADDING + (j + 1) * stepSize, PADDING + i * stepSize, new Paint());
                }
                if (left == 1) {
                    canvas.drawLine(PADDING + j * stepSize, PADDING + i * stepSize, PADDING + j * stepSize, PADDING + (i + 1) * stepSize, new Paint());
                }
                if (right == 1) {
                    canvas.drawLine(PADDING + (j + 1) * stepSize, PADDING + i * stepSize, PADDING + (j + 1) * stepSize, PADDING + (i + 1) * stepSize, new Paint());
                }
                if (down == 1) {
                    canvas.drawLine(PADDING + j * stepSize, PADDING + (i + 1) * stepSize, PADDING + (j + 1) * stepSize, PADDING + (i + 1) * stepSize, new Paint());

                }
            }
        }
        drawPoints(canvas, stepSize);

    }

    public void drawPoints(Canvas canvas,  float stepSize) {
        canvas.drawCircle(data.getDestX() * stepSize + PADDING + RADIUS + stepSize/2, data.getDestY() * stepSize + PADDING +  stepSize/2 + RADIUS, RADIUS, destination);
        canvas.drawCircle(locationManager.getX() * stepSize + RADIUS + PADDING + stepSize / 2, locationManager.getY() * stepSize + stepSize / 2 + PADDING + RADIUS, RADIUS, position);
    }
}
