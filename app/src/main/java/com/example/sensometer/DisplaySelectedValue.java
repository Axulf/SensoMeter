package com.example.sensometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;

public class DisplaySelectedValue extends AppCompatActivity {

    TextView tv;

    static final int read_block_size = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_selected_value);
        Intent intent = getIntent();
        String selected_file_name = intent.getStringExtra(EnvironmentSelectSavedValue.EXTRA_MESSAGE);
        File file = this.getFileStreamPath(selected_file_name);
        if ( !file.exists() ) {
            tv = findViewById(R.id.displayselectedvalue);
            tv.setText(getString(R.string.error_display));
        } else {
            //Reading test from file
            try {
                FileInputStream filein = openFileInput(selected_file_name);
                InputStreamReader inputread = new InputStreamReader(filein);

                char[] inputBuffer = new char[read_block_size];
                String s = "";
                int charRead;

                while ((charRead = inputread.read(inputBuffer)) > 0) {
                    //Char to string conversion
                    String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    s += readstring;
                }
                tv = findViewById(R.id.displayselectedvalue);
                inputread.close();
                tv.setText(s);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}