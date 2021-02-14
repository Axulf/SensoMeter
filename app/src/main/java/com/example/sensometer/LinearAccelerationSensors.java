package com.example.sensometer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class LinearAccelerationSensors extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "SensoMeter.MESSAGE";

    SensorManager sensormanager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_acceleration_sensors);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LinearMainLinearAcceleration);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 36;
        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensormanager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {
            List<Sensor> LinearAccelerationList = sensormanager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
            View.OnClickListener[] listeners = new View.OnClickListener[LinearAccelerationList.size()];
            final Context context = this;
            for (int q = 0; q < LinearAccelerationList.size(); q++) {
                final int temp = q;
                listeners[q] = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LinearAccelerationFeatures.class);
                        intent.putExtra(EXTRA_MESSAGE, String.valueOf(temp));
                        startActivity(intent);
                    }
                };
            }
            for (int i = 0; i < LinearAccelerationList.size(); i++) {
                LinearLayout ll = new LinearLayout(this);
                final Button btn = new Button(this);
                btn.setId(i);
                btn.setText(getString(R.string.linear_acceleration_number) + String.valueOf(i + 1));
                btn.setLayoutParams(params);
                btn.setBackgroundColor(getColor(R.color.navy));
                btn.setTextColor(getColor(R.color.white));
                btn.setTextSize(16);
                btn.setOnClickListener(listeners[i]);
                ll.addView(btn);
                lm.addView(ll);
            }
        } else {
            LinearLayout ll = new LinearLayout(this);
            final TextView textview = new TextView(this);
            textview.setText(getString(R.string.error_message));
            textview.setLayoutParams(params);
            textview.setTextSize(16);
            textview.setTextColor(getColor(R.color.black));
            ll.addView(textview);
            lm.addView(ll);
        }
    }
}