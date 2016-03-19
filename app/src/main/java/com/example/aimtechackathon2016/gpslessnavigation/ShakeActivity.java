package com.example.aimtechackathon2016.gpslessnavigation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by peta on 19.3.16.
 */
public class ShakeActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mShakeActivity;
    private Vibrator vibrator;

    private long lastUpdate = 0;
    private long lastTime = System.currentTimeMillis();

    private static final int SHAKE_THRESHOLD = 100; /* Lenovo P70 Android 4.4 configuration */
    //private static final int SHAKE_THRESHOLD = 400; /* Lenovo P70 Android 5.1 configuration */

    float last_x = 0;
    float last_y = 0;
    float last_z = 0;

    private int counter = 0;
    float speed = 0;

    private TextView textView2;
    private Button buttonTurn;
    private MapView mapView;

    private LocationManager locationManager = LocationManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_activity);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mShakeActivity = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        textView2 = (TextView) findViewById(R.id.textView2);
        buttonTurn = (Button) findViewById(R.id.buttonTurn);
        mapView = (MapView)findViewById(R.id.imageView);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mShakeActivity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();

        // only allow one update every 100ms.
        if ((curTime - lastTime) > 300) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
                lastTime = curTime;
                counter++;
                locationManager.move();
                mapView.invalidate();

                if (locationManager.getTurn() == 'R') {
                    buttonTurn.setText("RIGHT");
                    buttonTurn.setClickable(true);
                    buttonTurn.setBackgroundColor(Color.GREEN);
                    vibrator.vibrate(200);
                }
                else if (locationManager.getTurn() == 'L') {
                    buttonTurn.setText("LEFT");
                    buttonTurn.setClickable(true);
                    buttonTurn.setBackgroundColor(Color.GREEN);
                    vibrator.vibrate(200);
                }
                else if (locationManager.getTurn() == 'E') {
                    buttonTurn.setBackgroundColor(Color.MAGENTA);
                    buttonTurn.setClickable(true);
                    buttonTurn.setText("END");
                }
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
        textView2.setText("Steps counter: " + counter);
    }

    public void confirmTurn(View v) {
        if (locationManager.getTurn() == 'R') {
            locationManager.turnRight();
        }
        else if (locationManager.getTurn() == 'L') {
            locationManager.turnLeft();
        }
        /*else if (locationManager.getTurn() == 'E') {
            System.out.println("restart");
            StorageData data = DataLoader.getInstance().loadData(null);
            locationManager.setPath(data.getPath());
            locationManager.setPosition(data.getStartX(), data.getStartY());
        }*/
        buttonTurn.setClickable(false);
        buttonTurn.setBackgroundColor(Color.LTGRAY);
        buttonTurn.setText("Go");
    }
}
