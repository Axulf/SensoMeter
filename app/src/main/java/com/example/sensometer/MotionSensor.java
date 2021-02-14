package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MotionSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);
    }

    public void OpenMotionAccelerometerSensors(View view) {
        Intent intent = new Intent(this, AccelerometerSensors.class);
        startActivity(intent);
    }

    public void OpenMotionGravitySensors(View view) {
        Intent intent = new Intent(this, GravitySensors.class);
        startActivity(intent);
    }

    public void OpenMotionGyroscopeSensors(View view) {
        Intent intent = new Intent(this, GyroscopeSensors.class);
        startActivity(intent);
    }

    public void OpenMotionLinearAccelerationSensors(View view) {
        Intent intent = new Intent(this, LinearAccelerationSensors.class);
        startActivity(intent);
    }

}