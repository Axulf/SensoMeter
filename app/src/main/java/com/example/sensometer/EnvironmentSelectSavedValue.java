package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnvironmentSelectSavedValue extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "SensoMeter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_select_saved_value);
    }

    public void OpenDisplaySelectedValue(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radio_group);
        Intent intent = new Intent(this, DisplaySelectedValue.class);
        int id = radiogroup.getCheckedRadioButtonId();
        if (id == -1) {
            //no item --> Toast Display
            Toast.makeText(getBaseContext(),getString(R.string.choose_type), Toast.LENGTH_SHORT).show();
            return;
        } else {
            switch (id) {

                case R.id.radio_temperature:
                    String temperature_file_name = "mytemperaturevalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, temperature_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_light:
                    String light_file_name = "mylightvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, light_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_pressure:
                    String pressure_file_name = "mypressurevalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, pressure_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_humidity:
                    String humidity_file_name = "myhumidityvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, humidity_file_name);
                    startActivity(intent);
                    break;
            }

        }
    }
}