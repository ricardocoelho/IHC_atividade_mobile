package com.example.myapplication;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light;
    TextView lightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightValue = (TextView)findViewById(R.id.light);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (light != null) this.sensorManager.registerListener(MainActivity.this, light, sensorManager.SENSOR_DELAY_NORMAL);
        else lightValue.setText("Light sensor not available");

        //Toast.makeText(this, "Result: " + et3.getText().toString(), Toast.LENGTH_SHORT).show();
        // The activity is created
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor = event.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT){
            lightValue.setText("Light intensity: " + event.values[0]); }


    }



}

