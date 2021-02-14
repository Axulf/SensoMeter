package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
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

public class GeorotationFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Georotationsensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_georotation_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(GeoRotationSensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> GeorotationList = sensormanager.getSensorList(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        featureslist = findViewById(R.id.georotationfeatureslist);
        featureslist.setText(getString(R.string.name) + GeorotationList.get(sensor_index).getName() + "\n"
                + getString(R.string.type) + GeorotationList.get(sensor_index).getStringType() + "\n"
                + getString(R.string.vendor) + GeorotationList.get(sensor_index).getVendor() + "\n"
                + getString(R.string.version) + GeorotationList.get(sensor_index).getVersion() + "\n"
                + getString(R.string.resolution) + GeorotationList.get(sensor_index).getResolution() + "\n"
                + getString(R.string.range) + GeorotationList.get(sensor_index).getMaximumRange() + "\n"
                + getString(R.string.power) + GeorotationList.get(sensor_index).getPower() );
        displayvalue = findViewById(R.id.displaygeorotationvalue);
        Georotationsensor = GeorotationList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float georotation_value_x = event.values[0];
        float georotation_value_y = event.values[1];
        float georotation_value_z = event.values[2];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(GeoRotationSensors.EXTRA_MESSAGE)) + 1);
        displayvalue.setText(getString(R.string.number) + sensor_number + getString(R.string.value_x) + georotation_value_x + "\n"
                +getString(R.string.number) + sensor_number + getString(R.string.value_y) + georotation_value_y + "\n"
                +getString(R.string.number) + sensor_number + getString(R.string.value_z) + georotation_value_z + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Georotationsensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("mygeorotationvalue.txt", MODE_PRIVATE);
            OutputStreamWriter outputwriter = new OutputStreamWriter(fileout);
            outputwriter.write(displayvalue.getText().toString());
            outputwriter.close();

            //Display file save message
            Toast.makeText(getBaseContext(), getString(R.string.correct_save),Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BtnRead(View view) {
        //Reading test from file
        try{
            FileInputStream filein = openFileInput("mygeorotationvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.georotationsavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}