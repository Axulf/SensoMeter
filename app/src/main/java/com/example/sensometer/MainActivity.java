package com.example.sensometer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenEnvironmentalSensors(View view) {
        Intent intent = new Intent(this, EnvironmentalSensors.class);
        startActivity(intent);
    }

    public void OpenMotionSensor(View view) {
        Intent intent = new Intent(this, MotionSensor.class);
        startActivity(intent);
    }

    public void OpenPositionSensor(View view) {
        Intent intent = new Intent(this, PositionSensor.class);
        startActivity(intent);
    }

    public void OpenSelectTypeSavedValue(View view ) {
        Intent intent = new Intent(this, SelectTypeSavedValue.class);
        startActivity(intent);
    }

    public void OpenSettings(View view ) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}