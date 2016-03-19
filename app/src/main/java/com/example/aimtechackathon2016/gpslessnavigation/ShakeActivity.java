package com.example.aimtechackathon2016.gpslessnavigation;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by peta on 19.3.16.
 */
public class ShakeActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mShakeActivity;

    private long lastUpdate = System.currentTimeMillis();
    private long lastTime = System.currentTimeMillis();

    private static final int SHAKE_THRESHOLD = 100;

    float last_x = 0;
    float last_y = 0;
    float last_z = 0;

    private int counter = 0;
    float speed = 0;

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_activity);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mShakeActivity = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        textView2 = (TextView) findViewById(R.id.textView2);
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
        long time = lastTime;
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
                //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                counter++;

            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
        textView2.setText(" counter: " + counter + "\n speed: " + speed
                + "\n time: " + (curTime - time));
    }
}
