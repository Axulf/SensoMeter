package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class EnvironmentalSensors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_sensors);
    }

    public void OpenAmbientTemperatureSensors(View view) {
        Intent intent = new Intent(this, TemperatureSensors.class);
        startActivity(intent);
    }

    public void OpenLightSensors(View view) {
        Intent intent = new Intent(this, LightSensors.class);
        startActivity(intent);
    }

    public void OpenPressureSensors(View view) {
        Intent intent = new Intent(this, PressureSensors.class);
        startActivity(intent);
    }

    public void OpenRelativeHumiditySensors(View view) {
        Intent intent = new Intent(this, RelativeHumiditySensors.class);
        startActivity(intent);
    }
}