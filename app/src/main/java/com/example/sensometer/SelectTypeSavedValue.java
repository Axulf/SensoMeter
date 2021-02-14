package com.example.sensometer;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectTypeSavedValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_saved_value);
    }

    public void OpenDisplaySelectedType(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.sensor_type_radio_group);
        Intent intent = new Intent();
        int id = radiogroup.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(getBaseContext(),getString(R.string.choose_type),Toast.LENGTH_SHORT).show();
            //return;
        } else {
            switch (id) {

                case R.id.environmental_sensor_button:
                    intent.setClass(this, EnvironmentSelectSavedValue.class);
                    startActivity(intent);
                    break;

                case R.id.motion_sensor_button:
                    intent.setClass(this, MotionSelectSavedValue.class);
                    startActivity(intent);
                    break;

                case R.id.position_sensor_button:
                    intent.setClass(this, PositionSelectSavedValue.class);
                    startActivity(intent);
                    break;
            }

        }
    }
}