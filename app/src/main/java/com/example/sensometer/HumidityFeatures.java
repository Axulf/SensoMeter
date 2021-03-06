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

public class HumidityFeatures extends AppCompatActivity implements SensorEventListener {

    SensorManager sensormanager;
    TextView featureslist;

    TextView displayvalue;
    TextView tv;

    private Sensor Humiditysensor;

    static final int read_block_size = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_features);
        Intent intent = getIntent();
        int sensor_index = Integer.parseInt(intent.getStringExtra(RelativeHumiditySensors.EXTRA_MESSAGE));
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> HumidityList = sensormanager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
        featureslist = findViewById(R.id.humidityfeatureslist);
        featureslist.setText(getString(R.string.name) + HumidityList.get(sensor_index).getName() + "\n"
                + getString(R.string.type) + HumidityList.get(sensor_index).getStringType() + "\n"
                + getString(R.string.vendor) + HumidityList.get(sensor_index).getVendor() + "\n"
                + getString(R.string.version) + HumidityList.get(sensor_index).getVersion() + "\n"
                + getString(R.string.resolution) + HumidityList.get(sensor_index).getResolution() + "\n"
                + getString(R.string.range) + HumidityList.get(sensor_index).getMaximumRange() + "\n"
                + getString(R.string.power) + HumidityList.get(sensor_index).getPower() );
        displayvalue = findViewById(R.id.displayhumidityvalue);
        Humiditysensor = HumidityList.get(sensor_index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float humidity_value = event.values[0];
        Intent intent = getIntent();
        int sensor_number = (Integer.parseInt(intent.getStringExtra(RelativeHumiditySensors.EXTRA_MESSAGE)) + 1);
        displayvalue.setText(getString(R.string.number) + sensor_number + getString(R.string.value)  + humidity_value + " %" + "\n");
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, Humiditysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    public void BtnSafe(View view) {
        //Write text into file
        try {
            FileOutputStream fileout = openFileOutput("myhumidityvalue.txt", MODE_PRIVATE);
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
            FileInputStream filein = openFileInput("myhumidityvalue.txt");
            InputStreamReader inputread = new InputStreamReader(filein);

            char [] inputBuffer = new char[read_block_size];
            String s = "";
            int charRead;

            while((charRead = inputread.read(inputBuffer)) > 0) {
                //Char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            tv = findViewById(R.id.humiditysavedvalue);
            inputread.close();
            tv.setText(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}