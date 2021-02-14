package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import androidx.annotation.RequiresApi;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class GravityFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView tv;
    TextView displayvalue;
    private Sensor Gravitysensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(GravitySensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> GravityList = sensormanager.getSensorList(Sensor.TYPE_GRAVITY);
        featureslist = findViewById(R.id.gravityfeatureslist);
        featureslist.setText(getString(R.string.name) + GravityList.get(sensor_index).getName() + "\n"
                + getString(R.string.type) + GravityList.get(sensor_index).getStringType() + "\n"
                + getString(R.string.vendor) + GravityList.get(sensor_index).getVendor() + "\n"
                + getString(R.string.version) + GravityList.get(sensor_index).getVersion() + "\n"
                + getString(R.string.resolution) + GravityList.get(sensor_index).getResolution() + "\n"
                + getString(R.string.range) + GravityList.get(sensor_index).getMaximumRange() + "\n"
                + getString(R.string.power) + GravityList.get(sensor_index).getPower() );
        displayvalue = findViewById(R.id.displaygravityvalue);
        Gravitysensor = GravityList.get(sensor_index);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float gravity_value_x = event.values[0];
        float gravity_value_y = event.values[1];
        float gravity_value_z = event.values[2];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(GravitySensors.EXTRA_MESSAGE)) + 1);
        displayvalue.setText(getString(R.string.number) + sensor_number + getString(R.string.value_x) + gravity_value_x + " m/s^2" + "\n" +
                getString(R.string.number) + sensor_number + getString(R.string.value_y) + gravity_value_y + " m/s^2" + "\n" +
                getString(R.string.number) + sensor_number + getString(R.string.value_z) + gravity_value_z + " m/s^2" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Gravitysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("mygravityvalue.txt", MODE_PRIVATE);
            OutputStreamWriter outputwriter = new OutputStreamWriter(fileout);
            outputwriter.write(displayvalue.getText().toString());
            outputwriter.close();

            //Display file save message
            Toast.makeText(getBaseContext(), getString(R.string.correct_save), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BtnRead(View view) {
        //Reading test from file
        try{
            FileInputStream filein = openFileInput("mygravityvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.gravitysavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}