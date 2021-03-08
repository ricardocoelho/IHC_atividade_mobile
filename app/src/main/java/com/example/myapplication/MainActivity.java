package com.example.myapplication;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.hardware.SensorEventListener;


import android.widget.Toast;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    EditText coordX;
    EditText coordY;
    EditText coordZ;
    TextView accuracyView;
    private float sensorX;
    private float sensorY;
    private float sensorZ;
    private boolean check_sensor_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setContentView(R.layout.activity_main);

        coordX = (EditText) findViewById(R.id.textView6);
        coordX.setText("X: ");
        coordY = (EditText) findViewById(R.id.textView7);
        coordY.setText("Y: ");
        coordZ = (EditText) findViewById(R.id.textView8);
        coordZ.setText("Z: ");
        accuracyView = (TextView) findViewById(R.id.textView9);
        super.onCreate(savedInstanceState);
        check_sensor_change = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            float _sensorX = event.values[0];
            float _sensorY = event.values[1];
            float _sensorZ = event.values[2];
            coordX.setText("X: " + String.valueOf(_sensorX));
            coordY.setText("Y: " + String.valueOf(_sensorY));
            coordZ.setText("Z: " + String.valueOf(_sensorZ));

            boolean hugeXDiff= false;
            boolean hugeYDiff= false;
            boolean hugeZDiff= false;
            if(check_sensor_change) {
                if (abs(sensorX - _sensorX) > 7) {
                    hugeXDiff = true;
                }
                if (abs(sensorY - _sensorY) > 7) {
                    hugeYDiff = true;
                }
                if (abs(sensorZ - _sensorZ) > 7) {
                    hugeZDiff = true;
                }
                if(hugeXDiff || hugeYDiff || hugeZDiff) {
                    Send(hugeXDiff, hugeYDiff, hugeZDiff);
                    hugeXDiff = hugeYDiff = hugeZDiff = false;
                }

            }

            sensorX = _sensorX;
            sensorY = _sensorY;
            sensorZ = _sensorZ;
            if(check_sensor_change == false) {
                check_sensor_change = true;
            }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        if(sensor.getType()== Sensor.TYPE_ACCELEROMETER) {
            accuracyView.setText("ACC: " + Integer.toString(accuracy));
        }
    }

    public void Send(boolean x, boolean y, boolean z)
    {
        Intent i = new Intent(this, ShowMessageActivity.class);
        String msg = "Huge change on ";
        if(x) msg += "X ";
        if(y) msg += "Y ";
        if(z) msg += "Z ";
        msg += "Axis!";
        i.putExtra("data", msg);
        startActivity(i);
    }

}

