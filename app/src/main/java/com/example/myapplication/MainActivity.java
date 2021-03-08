package com.example.myapplication;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light, accel, gyro, magnet, pressure;
    TextView lightValue, accelValue, gyroValue, magnetValue, pressureValue;
    EditText accelX, accelY, accelZ ;
    EditText gyroX, gyroY, gyroZ;
    EditText magnetX, magnetY, magnetZ ;
    Button getGPSBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightValue = (TextView)findViewById(R.id.light);
        pressureValue  = (TextView)findViewById(R.id.pressure);
        getGPSBtn = (Button)findViewById(R.id.GPSBtn);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        getGPSBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             GPSTracker g = new GPSTracker(getApplicationContext());
                                             Location l = g.getLocation();
                                             if (l != null) {
                                                 double lat = l.getLatitude();
                                                 double longi = l.getLongitude();
                                                 Toast.makeText(getApplicationContext(), "LAT: " + lat + "LONG: " +
                                                         longi, Toast.LENGTH_LONG).show();
                                             }
                                         }
                                     });



        accelX = (EditText)findViewById(R.id.accelX); accelX.setEnabled(false);
        accelY = (EditText)findViewById(R.id.accelY); accelY.setEnabled(false);
        accelZ = (EditText)findViewById(R.id.accelZ); accelZ.setEnabled(false);
        gyroX = (EditText)findViewById(R.id.gyroX); gyroX.setEnabled(false);
        gyroY = (EditText)findViewById(R.id.gyroY); gyroY.setEnabled(false);
        gyroZ = (EditText)findViewById(R.id.gyroZ); gyroZ.setEnabled(false);
        magnetX = (EditText)findViewById(R.id.magnetX); magnetX.setEnabled(false);
        magnetY = (EditText)findViewById(R.id.magnetY); magnetY.setEnabled(false);
        magnetZ = (EditText)findViewById(R.id.magnetZ); magnetZ.setEnabled(false);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (light != null) this.sensorManager.registerListener(MainActivity.this, light, sensorManager.SENSOR_DELAY_NORMAL);
        else lightValue.setText("Light sensor not available");
        if (accel != null) this.sensorManager.registerListener(MainActivity.this, accel, sensorManager.SENSOR_DELAY_NORMAL);
        else accelValue.setText("Accelerometer not available");
        if (gyro != null) this.sensorManager.registerListener(MainActivity.this, gyro, sensorManager.SENSOR_DELAY_NORMAL);
        else gyroValue.setText("Gyrometer not available");
        if (magnet != null) this.sensorManager.registerListener(MainActivity.this, magnet, sensorManager.SENSOR_DELAY_NORMAL);
        else magnetValue.setText("Magnetometer not available");
        if (pressure != null) this.sensorManager.registerListener(MainActivity.this, pressure, sensorManager.SENSOR_DELAY_NORMAL);
        else pressureValue.setText("Pressure sensor not available");




        //Toast.makeText(this, "Result: " + et3.getText().toString(), Toast.LENGTH_SHORT).show();
        // The activity is created
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch(event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                accelX.setText(String.format("X: %.3f",event.values[0]));
                accelY.setText(String.format("Y: %.3f",event.values[1]));
                accelZ.setText(String.format("Z: %.3f",event.values[2]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyroX.setText(String.format("X: %.3f",event.values[0]));
                gyroY.setText(String.format("Y: %.3f",event.values[1]));
                gyroZ.setText(String.format("Z: %.3f",event.values[2]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnetX.setText(String.format("X: %.3f", event.values[0]));
                magnetY.setText(String.format("Y: %.3f", event.values[1]));
                magnetZ.setText(String.format("Z: %.3f", event.values[2]));
                break;
            case Sensor.TYPE_LIGHT:
                lightValue.setText("Light intensity: " + event.values[0]);
                break;
            case Sensor.TYPE_PRESSURE:
                pressureValue.setText(String.format("Pressure: %.3f atm", event.values[0]));
                break;
            default:
                break;
        }
    }
}