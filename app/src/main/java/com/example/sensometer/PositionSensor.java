package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PositionSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_sensor);
    }

    public void OpenGeomagneticRotationSensors(View view) {
        Intent intent = new Intent(this, GeoRotationSensors.class);
        startActivity(intent);
    }

    public void OpenMagneticFieldSensors(View view) {
        Intent intent = new Intent(this, MagneticFieldSensors.class);
        startActivity(intent);
    }

    public void OpenProximitySensors(View view) {
        Intent intent = new Intent(this, ProximitySensors.class);
        startActivity(intent);
    }
}