package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MotionSelectSavedValue extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "SensoMeter.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_select_saved_value);
    }

    public void OpenDisplaySelectedValue(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radio_group);
        Intent intent = new Intent(this, MotionDisplaySavedValue.class);
        int id = radiogroup.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(getBaseContext(), getString(R.string.choose_type), Toast.LENGTH_SHORT).show();
        } else {
            switch (id) {

                case R.id.radio_accelerometer:
                    String accelerometer_file_name = "myaccelerometervalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, accelerometer_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_gravity:
                    String gravity_file_name = "mygravityvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, gravity_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_gyroscope:
                    String gyroscope_file_name = "mygyroscopevalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, gyroscope_file_name);
                    startActivity(intent);
                    break;

                case R.id.radio_linear_acceleration:
                    String linearacceleration_file_name = "mylinearaccelerationvalue.txt";
                    intent.putExtra(EXTRA_MESSAGE, linearacceleration_file_name);
                    startActivity(intent);
                    break;

            }

        }
    }
}